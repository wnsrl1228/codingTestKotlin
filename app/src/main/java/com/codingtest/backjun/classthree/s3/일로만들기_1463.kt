package com.codingtest.backjun.classthree.s3

fun main() {
    val n = readln().toInt()
    val arr = IntArray(6000001)
    arr[1] = 0
    arr[2] = 1
    arr[3] = 1
    for (i in 4..n) {
        if (arr[i] == 0) {
            if (i % 3 == 0 && i % 2 == 0) {
                arr[i] = Math.min(arr[i/3], arr[i/2]) + 1
            } else if (i % 3 ==0 && i % 2 != 0) {
                arr[i] = Math.min(arr[i/3], arr[i-1]) + 1
            } else if (i % 2 ==0 && i % 3 != 0) {
                arr[i] = Math.min(arr[i/2], arr[i-1]) + 1
            } else {
                arr[i] = arr[i-1] + 1
            }
        }
    }
    print(arr[n])
}
