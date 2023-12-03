package com.codingtest.backjun.classfour.g5

import java.io.StreamTokenizer

/**
 * dp 문제
 *  - bfs로 풀면 시간초과 뜸,
 *      각 방향별로 제곱 수로 복잡도가 늘어나기 때문
 *
 *  - 가로, 세로, 대각선에 대한 방향별로 dp를 구한 뒤 합해주면 된다.
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    var arr = Array(N + 1) {
        IntArray(N + 1)
    }

    for (i in 1..N) {
        for (j in 1..N)
            arr[i][j] = input()
    }

    // 0 : 가로,
    // 1 : 세로,
    // 2 : 대각선
    var dp = Array(3) { Array(N + 1) { IntArray(N + 1) } }

    dp[0][1][2] = 1

    for (i in 1..N) {
        for (j in 3..N) {
            if (arr[i][j] == 1) continue

            /**
             * i=y, j=x
             * 가로 방향으로 들어올 수 있는 경우
             *      - x-1의 가로 방향
             *      - x-1의 대각선 방향
             * 세로 방향도 동일
             */
            dp[0][i][j] = dp[0][i][j - 1] + dp[2][i][j - 1]
            dp[1][i][j] = dp[1][i - 1][j] + dp[2][i - 1][j]

            /**
             * 대각선 방향으로 들어올 수 있는 경우
             *      단. 상,좌 위치가 1이면 존재하지 않는다.
             *    - y-1,x-1 좌표의 대각선 방향, 가로 방향, 세로 방향
             */
            if (arr[i - 1][j] != 1 && arr[i][j - 1] != 1) {
                dp[2][i][j] = dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1]
            }
        }
    }
    print(dp[0][N][N] + dp[1][N][N] + dp[2][N][N])
}