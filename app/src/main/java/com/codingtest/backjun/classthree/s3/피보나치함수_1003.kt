package com.codingtest.backjun.classthree.s3

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val arr = Array(41){Pair(-1,-1)}
    arr[0] = Pair(1, 0)
    arr[1] = Pair(0, 1)
    repeat(readLine().toInt()) {
        val n = readLine().toInt()
        for (i in 2..n) {
            if (arr[i].first != -1) continue
            arr[i] = Pair(arr[i-1].first + arr[i-2].first, arr[i-1].second + arr[i-2].second)
        }
        sb.appendLine("${arr[n].first} ${arr[n].second}")
    }
    print(sb)
}


