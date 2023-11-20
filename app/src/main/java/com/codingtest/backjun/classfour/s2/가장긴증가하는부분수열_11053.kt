package com.codingtest.backjun.classfour.s2

fun main() = with(System.`in`.bufferedReader()){

    val N = readLine().toInt()
    val arr = readLine().split(" ").map { it.toInt() }

    val dp = IntArray(N)
    dp[0] = 1
    for (i in 1..<N) {
        var max = 0
        for (j in i-1 downTo  0) {
            if (arr[j] < arr[i]) {
                max = Math.max(max, dp[j])
            }
        }
        dp[i] = max+1
    }

    print(dp.max())
}