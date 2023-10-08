package com.codingtest.backjun.classtwo.b

fun main() {
    val M = 1234567891L
    var sum = 0L
    var r = 1L
    readln()
    readln().forEachIndexed { i, c ->
        sum = (sum + (c - 'a' + 1) * r) % M
        r = (r * 31) % M
    }
    print(sum)
}