package kotlingames

fun <T> iterable(
		coroutine c: IteratorImpl<T>.() -> Continuation<Unit>): Iterable<T> {

	return object : Iterable<T> {
		override fun iterator(): Iterator<T> = IteratorImpl(c)
	}
}

class IteratorImpl<T>(c: IteratorImpl<T>.() -> Continuation<Unit>) : Iterator<T> {

	private var next: T? = null
	private var machine: Continuation<Unit> = c(this)
	private var hasNext: Boolean? = null

	override fun next(): T {
		if (!hasNext())
			throw RuntimeException("iterator completed")

		val nextValue = next ?: throw RuntimeException("invalid iterator")
		hasNext = null

		return nextValue
	}

	override fun hasNext(): Boolean {
		if (hasNext != null)
			return hasNext == true

		machine.resume(Unit)
		return hasNext == true
	}

	suspend fun yield(value: T, machine: Continuation<Unit>) {
		this.next = value
		this.machine = machine
		this.hasNext = true
	}
}