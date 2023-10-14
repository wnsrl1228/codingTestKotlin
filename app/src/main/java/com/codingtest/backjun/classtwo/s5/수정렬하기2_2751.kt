package com.codingtest.backjun.classtwo.s5

fun main() {
    IntArray(readln().toInt()) { readln().toInt() }
        .sorted()
        .joinToString("\n")
        .let(::print)
}