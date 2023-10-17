package com.codingtest.backjun.classtwo.s5

fun main() {
    val n = readln().toInt()
    var set = mutableSetOf<String>()
    repeat(n) {
        set.add(readln())
    }

    val customComparator = Comparator<String> { s1, s2 ->
        if (s1.length < s2.length) {
            -1
        } else if (s1.length > s2.length) {
            1
        } else {
            s1.compareTo(s2)
        }
    }
    set.sortedWith(customComparator).forEach {
        println(it)
    }
}

fun 단어정렬_개선되기전_코드() {
    val arr = Array(readln().toInt()) {readln()}
    val group = arr.groupBy { it.length }

    for (i in group.keys.sorted()) {
        val words = group.get(i)!!.toSet()!!.sorted()
        for (j in words.indices) {
            println(words[j])
        }
    }
}

fun 단어정렬_다른풀이() {
    List(readln().toInt()) { readln() }
        .distinct()
        .sortedWith(compareBy({ it.length }, { it }))
        .joinToString("\n")
        .let(::print)
}