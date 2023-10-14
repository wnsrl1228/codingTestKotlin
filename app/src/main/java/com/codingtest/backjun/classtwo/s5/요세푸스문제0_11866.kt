package com.codingtest.backjun.classtwo.s5

import java.util.LinkedList

fun main() {
    val (N, K) = readln().split(" ").map { it.toInt() }
    val list = (1..N).toMutableList()
    var currentIndex = K - 1

    val sb = StringBuilder("<")

    while (list.isNotEmpty()) {
        val removeAt = list.removeAt(currentIndex)

        sb.append("${removeAt}, ")

        if (list.isEmpty()) {
            sb.delete(sb.length-2, sb.length).append(">")
            break
        }
        currentIndex = (currentIndex + K - 1) % list.size
    }
    print(sb)
}

fun 요세푸스문제0_개선된코드() {
    val (N, K) = readln().split(" ").map { it.toInt() }
    val list = (1..N).toMutableList()
    var idx = 0
    (1..N).map {
        idx = (idx + K - 1) % list.size
        list.removeAt(idx)
    }.joinToString(", ", prefix = "<", postfix = ">")
        .let(::print)
}