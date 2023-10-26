package com.codingtest.backjun.classthree.s3

fun main() {
    val arr = IntArray(11)
    arr[1] = 1
    arr[2] = 2
    arr[3] = 4 // 1 1 1, 1 2, 2 1, 3
    repeat(readln().toInt()) {
        val n = readln().toInt()
        if (n < 4) {
            println(arr[n])
        } else {
            for (i in 4..n) {
                if (arr[i] == 0) {
                    arr[i] = arr[i-1] + arr[i-2] + arr[i-3]
                }
            }
            println(arr[n])
        }
    }
}