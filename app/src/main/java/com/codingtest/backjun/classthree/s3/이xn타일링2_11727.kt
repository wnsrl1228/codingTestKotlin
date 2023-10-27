package com.codingtest.backjun.classthree.s3

fun main() {
    val dp = IntArray(10001)
    dp[1] = 1
    dp[2] = 3
    val n = readln().toInt()
    for (i in 3..n) {
        dp[i] = (dp[i-1] + dp[i-2] * 2)%10007
    }
    print(dp[n])
}