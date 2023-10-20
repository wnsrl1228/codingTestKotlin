package com.codingtest.backjun.classtwo.s4

fun main() {
    val n = readln().toInt()
    val d = ArrayDeque<Int>()
    repeat(n) {
        d.add(readln().toInt())
    }
    d.sort()
    val outCount = Math.round(n * (15/100f))

    repeat(outCount) {
        d.removeFirst()
        d.removeLast()
    }
    var result = Math.round(d.sum().toFloat() / d.size)
    print(result)
}