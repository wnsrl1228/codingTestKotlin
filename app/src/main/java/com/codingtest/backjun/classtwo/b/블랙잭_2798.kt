package com.codingtest.backjun.classtwo.b

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }
    var result = 0
    for (i in 0 until arr.size - 2) {
        for (j in i+1 until arr.size - 1) {
            for (l in j+1 until arr.size) {
                val sum = arr[i] + arr[j] + arr[l]
                if (sum in (result + 1) .. M) result = sum
            }
        }
    }
    print(result)
}