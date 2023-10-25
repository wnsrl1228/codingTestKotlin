package com.codingtest.backjun.classthree.s4

fun main() = with(System.`in`.bufferedReader()){
    val sb = StringBuilder()
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val map = mutableMapOf<String, String>()
    repeat(N) {
        val info = readLine().split(" ")
        map.put(info[0], info[1])
    }
    repeat(M) {
        sb.appendLine(map[readLine()])
    }
    print(sb)
}