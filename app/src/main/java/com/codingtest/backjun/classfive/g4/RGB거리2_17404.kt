package com.codingtest.backjun.classfive.g4

import java.io.StreamTokenizer

/**
 * 못 품
 * DP를 나눠서 여러번 해줘야 된다는 생각을 못함
 *
 * 풀이
 *      - 시작 지점을 R로 시작하는 경우 마지막 DP의 G, B 의 최솟값
 *      - 시작 지점을 G로 시작하는 경우 마지막 DP의 R, B 의 최솟값
 *      - 시작 지점을 B로 시작하는 경우 마지막 DP의 R, G 의 최솟값
 *      이 세개의 최솟값
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }


    val N = input()
    var dp = Array(N) {IntArray(3)}
    val arr= Array(N) {IntArray(3) {input()} }

    val MAX = 1000001
    var result = MAX

    fun dp() {
        for (i in 1 until N) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i][0]
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[i][1]
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i][2]
        }
    }
    // 1 시작지점 외에는 무한값을 넣어줌
    dp[0][0] = arr[0][0]
    dp[0][1] = MAX
    dp[0][2] = MAX

    dp()
    result = Math.min(Math.min(dp[N-1][1], dp[N-1][2]), result)

    // 2
    dp = Array(N) {IntArray(3)}
    dp[0][0] = MAX
    dp[0][1] = arr[0][1]
    dp[0][2] = MAX

    dp()
    result = Math.min(Math.min(dp[N-1][0], dp[N-1][2]), result)

    // 3
    dp = Array(N) {IntArray(3)}
    dp[0][0] = MAX
    dp[0][1] = MAX
    dp[0][2] = arr[0][2]

    dp()

    result = Math.min(Math.min(dp[N-1][0], dp[N-1][1]), result)

    print(result)
}
