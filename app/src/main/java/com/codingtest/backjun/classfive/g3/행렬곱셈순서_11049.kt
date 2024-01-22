package com.codingtest.backjun.classfive.g3

import java.io.StreamTokenizer

/**
 * 2차원 배열을 이용한 DP 문제
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    data class Matrix(val r: Int, val c: Int, val cost: Int)

    val N = input()

    val dp = Array(N+1) {Array(N){Matrix(0,0, 0)} }
    repeat(N) { i ->
        dp[1][i] = Matrix(input(), input(), 0)
    }

    if (N==1) {
        print(0)
        return
    }
    for (i in 0 until N-1) {
        val cost = dp[1][i].r * dp[1][i].c * dp[1][i+1].c
        dp[2][i] = Matrix(dp[1][i].r, dp[1][i+1].c, cost)
    }


    for (i in 3 ..N) {
        for (j in 0 until N-i+1) {

            var minMat = Matrix(0,0, Int.MAX_VALUE)
            for (z in 1 .. (i/2)) {  // i=4
                // dp[i-z][j] * dp[z][j+i-z]
                val cost1 = dp[i-z][j].r * dp[i-z][j].c * dp[z][j+i-z].c + dp[i-z][j].cost + dp[z][j+i-z].cost
                // dp[z][j] * dp[i-z][j+z]
                val cost2 = dp[z][j].r * dp[z][j].c * dp[i-z][j+z].c + dp[z][j].cost + dp[i-z][j+z].cost

                var mat: Matrix
                if (cost1 <= cost2) {
                    mat = Matrix(dp[i-z][j].r, dp[z][j+i-z].c, cost1)
                } else {
                    mat = Matrix(dp[z][j].r, dp[i-z][j+z].c, cost2)
                }
                if (minMat.cost > mat.cost) {
                    minMat = mat
                }
            }
            dp[i][j] = minMat
        }
    }
    print(dp[N][0].cost)

//    행렬곱셈순서_개선된풀이()
}
fun 행렬곱셈순서_개선된풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    data class Matrix(val r: Int, val c: Int)

    val N = input()

    val dp = Array(N) {IntArray(N)}
    val arr = Array(N) {Matrix(input(), input())}

    for (len in 1 until N) {
        for (start in 0 until N-len) {
            val end = start + len
            dp[start][end] = Int.MAX_VALUE

            for (mid in start until start+len) {
                val cost = dp[start][mid] + dp[mid+1][end] + arr[start].r * arr[mid].c * arr[end].c
                if (dp[start][end] > cost)
                    dp[start][end] = cost

            }

        }
    }
    print(dp[0][N-1])
}
/**
 *    A  B  C   D    E
 * A     1  1   1    1
 * B        2   2    2
 * C            3    3
 * D                 4
 * E
 */