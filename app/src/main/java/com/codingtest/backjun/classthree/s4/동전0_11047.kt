package com.codingtest.backjun.classthree.s4

fun main() = with(System.`in`.bufferedReader()) {
    var (N, K) = readLine().split(" ").map { it.toInt() }
    val value = IntArray(N)
    repeat(N) {
        value[it] = readLine().toInt()
    }

    var result = 0
    var i = N-1
    while (K > 0) {
        if (K / value[i] > 0) {
            result += K / value[i]
            K %= value[i]
        }
        i--
    }
    print(result)
}