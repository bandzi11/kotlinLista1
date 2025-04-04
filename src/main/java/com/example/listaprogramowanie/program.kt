package com.example.listaprogramowanie
import kotlin.math.sqrt

/**
 * @author Klaudia Banaszak
 */

/** użycie funkcji require https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/require.html
 * początkowo wykonano wyłapywanie błędnych danych za pomocą pętli if
 * w kolejnych funkcjach wykorzystano funkcję require()
 * https://en.wikipedia.org/wiki/Heron%27s_formula
 * https://kotlinlang.org/docs/exceptions.html#throw-exceptions
 */
fun heron(a: Double, b: Double, c: Double): Double {
    if (a <= 0 || b <= 0 || c <= 0){
        throw IllegalArgumentException("Aby stworzyc trojkat kazdy z jego bokow musi byc dodatni")
    }
    if (a + b <= c || b + c <= a || a + c <= b){
        throw IllegalArgumentException("Z podanych bokow nie da sie stworzyc trojkata")
    }

    val s = (a + b + c) / 2.0
    return sqrt(s * (s - a) * (s - b) * (s - c))
}

/**
 * https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/intersect.html
 * https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/to-set.html
 */
fun wspolne(x: List<Int>, y: List<Int>): List<Int> {
    require(x.isNotEmpty() && y.isNotEmpty()) {"Zbiory z ktorych ma byc wyciagnieta czesc wspolna nie moga byc puste"}
    return x.toSet().intersect(y.toSet().toList()).toList()
}

/**
 * https://kt.academy/article/fk-cp-fold
 * https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/fold.html
 */
fun podzbiory(x: Set<Int>): List<Set<Int>> {
    require(x.isNotEmpty()) { "Zbior nie może byc pusty" }
    return x.fold(listOf(emptySet())) {
        acc, step -> acc + acc.map {it + step}
    }
}

/**
 * wzory wyciągnięto na podstawie poniższych materiałów i przekształcono
 * na wyświetlanie ich jako ciąg, a nie wartość dla danego elementu ciągu
 * https://www.baeldung.com/kotlin/fibonacci-series
 * https://pl.wikipedia.org/wiki/Ciąg_Fibonacciego
 * https://nofluffjobs.com/pl/etc/specjalizacja/ciag-fibonacciego-java/
 * korzystano też z filmu na YouTube z kanału Kunal Kushwaha
 * https://www.youtube.com/watch?v=M2uO2nMT0Bk
 */

fun fibonacciIteracja(n: Int): List<Int>{
    require(n > 0) { "n musi być wieksze niz 0" }
    val wynik = mutableListOf(0, 1)
    if (n == 1) return wynik
    var a = 0
    var b = 1

    for (i in 2 .. n) {
        val suma = a + b
        wynik.add(suma)
        a = b
        b = suma
    }
    return wynik
}

fun fibonacciRekurencja(n: Int): List<Int> {
    require(n > 0) { "n musi być wieksze niz 0" }
    val wynik = mutableListOf(0, 1)
    for (i in 2 .. n ) {
        wynik.add(wynik[i - 1] + wynik[i - 2])
    }
    return wynik
}

/**
 * analiza Collatz Problem
 * https://exercism.org/tracks/kotlin/exercises/collatz-conjecture/solutions/rojiani
 * https://stackoverflow.com/questions/38928443/collatz-conjecture-method-java
 * pierwotnie próbowano wykonać ciąg umieszczając go w liście List
 * wywoływało to jednak za duże obciążenie pamięci
 * zmieniono więc List na funkcję Sequence
 * https://www.baeldung.com/kotlin/sequences
 */
fun collatz(c0: Int) = sequence {
    require(c0 > 0) {"c0 musi byc wieksze od 0"}
    var current = c0

    while(current != 1) {
        yield(current)
        current = if (current % 2 == 0) current / 2
        else 3 * current + 1
    }
    yield(1)
}

/**
 * https://pl.khanacademy.org/science/biology/gene-expression-central-dogma/transcription-of-dna-into-rna/a/stages-of-transcription
 * https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/map-of.html
 * https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/all.html
 * zmiana kodu DNA za pomocą przypisania (mapowania) wartości do klucza
 */
fun komplement(dna: String): String {
    val komplement = mapOf('A' to 'T', 'T' to 'A', 'C' to 'G', 'G' to 'C')
    if(!dna.all {it in komplement.keys}){
        throw IllegalArgumentException("Sekwencja DNA musi skladac sie z dozwolonych znakow -> ATCG")
    }
    return dna.map { komplement[it] ?: it}.joinToString("")
}

fun transkrybuj(dna: String): String {
    val trans = mapOf('T' to 'A', 'A' to 'U', 'C' to 'G', 'G' to 'C')
    if(!dna.all {it in trans.keys}){
        throw IllegalArgumentException("Sekwencja DNA musi skladac sie z dozwolonych znakow -> ATCG")
    }
    return dna.map { trans[it] ?: it}.joinToString("")
}


fun main(){
    try{
        println("Pole trojkata obliczone wzorem herona: " + heron(3.0, 4.0, 5.0))
        println("Czesc wspolna dwoch list: " + wspolne(listOf(1,2,2,2,2,3,4,5,6), listOf(3,4,5,6,2,2,2,7,8,9)))
        println("Podzbiory: " + podzbiory(setOf(1, 2, 3, 5)))
        println("Ciag Fibonacciego wykonany iteracja: " + fibonacciIteracja(11))
        println("Ciag Fibonacciego wykonany rekurencja: " + fibonacciRekurencja(11))


        val c0 = 100
        val sequence = collatz(c0).toList()
        println("Collatz: " + collatz(c0).joinToString(", "))
        println("maksymalna wartosc: ${sequence.maxOrNull()}")
        println("dlugosc ciagu: ${sequence.size}")

        val dnaKod = "ATGATCTCGTAA"
        val dnaKod2 = "AGTGGATTCA"
        val dnaMatryca = komplement(dnaKod)
        val dnaMatryca2 = komplement(dnaKod2)
        val dnaTranskrybowane = transkrybuj(dnaMatryca)
        val dnaTranskrybowane2 = transkrybuj(dnaMatryca2)
        println("Nic kodujaca DNA: $dnaKod")
        println("Nic matrycowa DNA: $dnaMatryca")
        println("Nic transkrybowana RNA: $dnaTranskrybowane")
        println("Druga probka DNA")
        println("Nic kodujaca DNA: $dnaKod2")
        println("Nic matrycowa DNA: $dnaMatryca2")
        println("Nic transkrybowana RNA: $dnaTranskrybowane2")

    }
    catch (d: IllegalArgumentException){
        println("blad: ${d.message}")
    }
/**
 * dokumentacja try-catch https://kotlinlang.org/docs/exceptions.html#throw-exceptions-with-precondition-functions
 */
}