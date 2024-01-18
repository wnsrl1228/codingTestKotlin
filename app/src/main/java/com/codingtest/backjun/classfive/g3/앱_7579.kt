package com.codingtest.backjun.classfive.g3

import java.io.StreamTokenizer

/**
 * 못 품
 * 배낭 문제(2차원 DP)
 *
 * - 가로 : 비용
 * - 세로 : APP
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    data class App(var memory: Int, var cost: Int)
    val N = input() // 앱의 개수
    val M = input() // 추가로 필요한 메모리

    val app = Array(N) {App(0, 0)}

    // 활성화된 앱의 메모리
    repeat(N) { i ->
        app[i].memory = input()
    }
    // 앱을 비활성화 했을 경우 비용
    var sumCost = 0
    repeat(N) { i ->
        app[i].cost = input()
        sumCost += app[i].cost
    }

    val dp = Array(sumCost+1) {IntArray(N)}


    for (y in 0..sumCost) {
        if (y >= app[0].cost) dp[y][0] = app[0].memory
    }

    for (x in 1 until N) {
        for (y in 0..sumCost) {

            if (y < app[x].cost) {
                dp[y][x] = dp[y][x-1]
            } else {
                dp[y][x] = Math.max(dp[y][x-1], app[x].memory + dp[y - app[x].cost][x-1])
            }
        }
    }

    for (y in 0..sumCost) {
        for (x in 0 until N) {
            if (dp[y][x] >= M) {
                print(y)
                return@run
            }
        }
    }
}