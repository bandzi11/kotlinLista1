package com.example.listaprogramowanie

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
/**
 * testy jednostkowe wykonano na podstawie https://junit.org/junit5/docs/current/user-guide/
 * głównie wykorzystano rozdział 2 (2.2, 2.3, 2.5) oraz 5.15
 * dodatkowo korzystano także z https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-run-tests.html#work-with-more-complex-projects
 */
class HeronTest {

    @Test
    fun testHeron() {
        // Test dla trojkata o bokach 5.0, 4.0 i 5.0
        val wynik = heron(3.0, 4.0, 5.0)
        assertEquals(6.0, wynik, 0.001)
        try {
            heron(1.0, 1.0, 3.0)
            fail("Oczekiwano wyjątku IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertEquals("Z podanych bokow nie da sie stworzyc trojkata", e.message)
        }
    }

}

class OtherTests {
    @Test
    fun testWspolne() {
        val wynik = wspolne(listOf(1, 2, 3, 4), listOf(3, 4, 5, 6))
        assertEquals(listOf(3, 4), wynik)

        try {
            wspolne(emptyList(), listOf(1, 2))
            fail("Oczekiwano wyjątku IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertEquals("Zbiory z ktorych ma byc wyciagnieta czesc wspolna nie moga byc puste", e.message)
        }
    }

    fun testPodzbiory() {
        val wynik = podzbiory(setOf(1, 2, 3))
        assertEquals(listOf(emptySet(), setOf(1), setOf(2), setOf(3), setOf(1, 2), setOf(1, 3), setOf(2, 3), setOf(1, 2, 3)), wynik)

        try {
            podzbiory(emptySet())
            fail("Oczekiwano wyjątku IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertEquals("Zbior nie może byc pusty", e.message)
        }
    }

    fun testFibonacciIteracja() {
        val wynik = fibonacciIteracja(5)
        assertEquals(listOf(0, 1, 1, 2, 3, 5), wynik)

        val wynik1 = fibonacciIteracja(1)
        assertEquals(listOf(0, 1), wynik1)
    }

    fun testFibonacciRekurencja() {
        val wynik = fibonacciRekurencja(5)
        assertEquals(listOf(0, 1, 1, 2, 3, 5), wynik)

        val wynik1 = fibonacciRekurencja(1)
        assertEquals(listOf(0, 1), wynik1)
    }

    fun testCollatz() {
        val wynik = collatz(5).toList()
        assertEquals(listOf(5, 16, 8, 4, 2, 1), wynik)


        val wynik1 = collatz(1).toList()
        assertEquals(listOf(1), wynik1)
    }

    fun testKomplement() {
        val wynik = komplement("ATGC")
        assertEquals("TACG", wynik)

        try {
            komplement("AMGZ")
            fail("Oczekiwano wyjątku IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertEquals("Sekwencja DNA musi skladac sie z dozwolonych znakow -> ATCG", e.message)
        }
    }

    fun testTranskrybuj() {
        val wynik = transkrybuj("ATGC")
        assertEquals("UACG", wynik)

        try {
            transkrybuj("ATHZ")
            fail("Oczekiwano wyjątku IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertEquals("Sekwencja DNA musi skladac sie z dozwolonych znakow -> ATCG", e.message)
        }
    }
}
