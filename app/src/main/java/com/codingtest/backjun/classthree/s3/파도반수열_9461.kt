package com.codingtest.backjun.classthree.s3

fun main() {

    val dp = LongArray(101)

    dp[1] = 1
    dp[2] = 1
    dp[3] = 1
    dp[4] = 2
    dp[5] = 2
    dp[6] = 3

    repeat(readln().toInt()) {
        val N = readln().toInt()
        for (i in 7..N) {
            dp[i] = dp[i-1] + dp[i-5]
        }
        println(dp[N])
    }
}
