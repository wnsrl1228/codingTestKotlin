package com.codingtest.backjun.classtwo.s4

fun main() {
    readln()
    val map =mutableMapOf<Int, Int>()

    readln().split(" ").map { it.toInt() }.forEach { i ->
        map[i] = map.getOrDefault(i,0) + 1
    }
    readln()
    readln().split(" ").map { map.getOrDefault(it.toInt(), 0) }
        .joinToString(" ").let(::print)
}