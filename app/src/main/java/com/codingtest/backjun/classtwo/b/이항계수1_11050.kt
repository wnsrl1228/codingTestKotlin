package com.codingtest.backjun.classtwo.b

fun main() {
    val (N, K) = readln().split(" ").map { it.toInt() }
    var top = 1
    var bottom = 1
    for (i in N-K+1..N) {
        top *= i
    }
    for (i in 1..K) {
        bottom *=i
    }
    print(top/bottom)
}