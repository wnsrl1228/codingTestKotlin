package com.codingtest.backjun.classthree.s4

fun main() = with(System.`in`.bufferedReader()){

    val (N, M) = readLine().split(" ").map { it.toInt() }
    val map = mutableMapOf<String, Int>()

    repeat(N+M) {
        val name = readLine()
        map.put(name, map.getOrDefault(name, 0) + 1)
    }

    val result = map.filter { it.value == 2 }.keys.sorted()
    println(result.size)
    print(result.joinToString("\n"))
}