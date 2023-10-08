package com.codingtest.backjun.classtwo.b

fun main() {
    var N = readln().toInt()
    var i = 1
    var result = 1

    while (N > i) {
        i += (result * 6)
        result++
    }
    print(result)
}