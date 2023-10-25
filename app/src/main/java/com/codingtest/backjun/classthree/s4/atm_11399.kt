package com.codingtest.backjun.classthree.s4

fun main() {
    readln()
    var result = 0
    readln().split(" ")
        .map { it.toInt() }
        .sorted()
        .map { result += it; result }
        .sum()
        .let(::print)
}
