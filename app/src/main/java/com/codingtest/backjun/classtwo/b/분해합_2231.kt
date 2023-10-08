package com.codingtest.backjun.classtwo.b

fun main() {
    val N = readln().toInt()
    var check = true
    for(i in 1..N) {
        if (i + i.toString().sumOf { it - '0' } == N) {
            check = false
            print(i)
            break
        }
    }
    if (check) {
        print(0)
    }
}

fun 개선된코드() {
    val N = readln()
    val n = N.toInt()
    for (i in (n - N.length * 9)..n) {
        if (i + i.toString().sumOf { it - '0' } == n) {
            print(i)
            return
        }
    }
    print(0)
}