package com.codingtest.backjun.classtwo.s4

fun main() {
    var N = readln().toInt()
    var result = 0
    while (N >= 3) {
        if (N % 5 == 0) {
            result += N / 5
            N = 0
            break
        } else {
            N -= 3
            result++
        }
    }
    if (N > 0) print(-1)
    else print(result)
}