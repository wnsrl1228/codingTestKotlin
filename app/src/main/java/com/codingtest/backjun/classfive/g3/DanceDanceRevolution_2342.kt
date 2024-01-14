package com.codingtest.backjun.classfive.g3

/**
 * 못 품
 * DP 메모이제이션
 * (거의 완탐)
 */
fun main() {

    fun energy(pos: Int, des: Int) : Int {
        val num = Math.abs(pos - des)
        return if (pos === 0) 2 else if (num == 0) 1 else if (num == 1 || num == 3) 3 else 4
    }

    val input = readln().split(" ").map { it.toInt() }
    val len = input.size - 1

    val dp = Array(len) {Array(5) { IntArray(5)} }

    fun solve(idx: Int, l: Int, r: Int): Int {
        if (idx == len) return 0

        if (dp[idx][l][r] != 0) return dp[idx][l][r]

        val next = input[idx]

        dp[idx][l][r] = Math.min(
            solve(idx+1, next, r) + energy(l, next),
            solve(idx+1, l, next) + energy(r, next)
        )

        return dp[idx][l][r]
    }
    print(solve(0,0,0))
    DDR_bottom_up()
}

fun DDR_bottom_up() {


    fun energy(pos: Int, des: Int) : Int {
        val num = Math.abs(pos - des)
        return if (pos === 0) 2 else if (num == 0) 1 else if (num == 1 || num == 3) 3 else 4
    }

    val input = readln().split(" ").map { it.toInt() }
    val len = input.size

    val MAX = 1000000
    val dp = Array(len) {Array(5) { IntArray(5) {1000000} } }
    dp[0][0][0] = 0
    for (i in 0 until len-1) {
        val next = input[i]
        for (l in 0..4) {
            for (r in 0..4) {
                dp[i+1][l][next] = Math.min(dp[i+1][l][next], dp[i][l][r] + energy(r, next))
                dp[i+1][next][r] = Math.min(dp[i+1][next][r], dp[i][l][r] + energy(l, next))
            }
        }
    }

    var result = MAX
    for (l in 0..4) {
        for (r in 0..4) {
            result = Math.min(result, dp[len-1][l][r])
        }
    }
    print(result)
}

/**
 *   0 100 100 100 100  | 100 100 100 100 100  | 100 100 100 100 100  | 100 100 100 100 100  | 100 100 100 100 100  |
 * 100   2 100 100 100  |   2 100 100 100 100  | 100 100 100 100 100  | 100 100 100 100 100  | 100 100 100 100 100  |
 * 100 100 100 100   5  | 100 100 100 100   4  | 100 100 100 100 100  | 100 100 100 100 100  |   5   4 100 100 100  |
 * 100 100   9 100 100  | 100 100   8 100 100  |   9   8 100 100   7  | 100 100 100 100 100  | 100 100   7 100 100  |
 * 100  12 100 100 100  |  12  11   9 100  10  | 100   9 100 100 100  | 100 100 100 100 100  | 100  10 100 100 100  |
 */
