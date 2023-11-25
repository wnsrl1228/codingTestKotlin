package com.codingtest.backjun.classfour.s1

import java.io.StreamTokenizer

/**
 * 간단한 규칙의 dp문제
 * [0][i] 에 대해 [1][i-1], [1][i-2] 중 큰 값을 더해준다.
 * [1][i] 에 대해 [0][i-1], [0][i-2] 중 큰 값을 더해준다.
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val sb = StringBuilder()
    repeat(input()) {
        val n = input()
        val arr = Array(2) {
            IntArray(n) {
                input()
            }
        }

        if (n == 1) {
            sb.appendLine(Math.max(arr[0][0], arr[1][0]))
        } else if (n == 2) {
            sb.appendLine(Math.max(arr[0][0]+arr[1][1], arr[1][0]+arr[0][1]))
        } else {
            val dp = Array(2) {IntArray(n)}
            dp[0][0] = arr[0][0]
            dp[1][0] = arr[1][0]
            dp[0][1] = arr[0][1] + arr[1][0]
            dp[1][1] = arr[1][1] + arr[0][0]

            for (i in 2..<n) {
                dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + arr[0][i]
                dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + arr[1][i]
            }
            sb.appendLine(Math.max(dp[0][n-1], dp[1][n-1]))
        }
    }
    print(sb)
}
fun 스티커_개선() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val sb = StringBuilder()
    repeat(input()) {
        val n = input()
        val arr = Array(2) {
            IntArray(n) {
                input()
            }
        }

        if (n > 1) {
            arr[0][1] += arr[1][0]
            arr[1][1] += arr[0][0]
        }
        for (i in 2..<n) {
            arr[0][i] += Math.max(arr[1][i-1], arr[1][i-2])
            arr[1][i] += Math.max(arr[0][i-1], arr[0][i-2])
        }
        sb.appendLine(Math.max(arr[0][n-1], arr[1][n-1]))
    }
    print(sb)
}