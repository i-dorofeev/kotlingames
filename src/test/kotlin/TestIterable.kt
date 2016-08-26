package kotlinggames.tests

import kotlingames.iterable
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class TestIterable {

	@Test
	@DisplayName("empty collection")
	fun test1() {

		val col = iterable<Int> { }
		col.forEach { println("$it") }
	}

	@Test
	@DisplayName("simple collection")
	fun test2() {

		val col = iterable<Int> {
			yield(1)
			yield(2)
		}

		col.forEach {
			println("$it")
		}
	}

	@Test
	@DisplayName("loop collection")
	fun test3() {

		val col = iterable<String> {
			(1..10)
					.map { "Item #$it" }
					.forEach { yield(it) }
		}

		col.forEach {
			println("$it")
		}

		col.forEach {
			println("$it")
		}
	}
}