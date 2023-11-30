package com.codingtest.backjun.classfour.g5

import java.io.StreamTokenizer

/**
 * 못 품
 * https://howudong.tistory.com/106
 * 블로그 글 참고
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val K = input()

    val dp = Array(K+1) {IntArray(N+1)}  // 무게, 개수
    val arr = Array(N+1){Pair(0,0)}

    for (i in 1..N) {
        arr[i] = Pair(input(), input())
    }

    for (j in 1..N) {
        for (i in 1..K) {

            if (arr[j].first > i) {
                dp[i][j] = dp[i][j-1]
            } else {
                dp[i][j] = Math.max(dp[i][j-1], arr[j].second + dp[i-arr[j].first][j-1])
            }
        }
    }

    print(dp[K][N])
}
/**
 * 벡준 예시
 * - 가로는 물건 개수4개 (무게, 가치) x
 * - 세로는 물건의 최대 무게(7kg) y
 * - 각 값은 현재 물건의 최대 무게에 대한 최대 가치 dp[y][x]
 *
 *      1. x 물건의 무게 > 최대 무게 y
 *         - x-1 물건의 가치가 최대 가치가 됨
 *
 *         - 식 : dp[y][x] = dp[y][x-1]
 *
 *      2. x 물건의 무게 <= 최대 무게 y
 *         - x-1 물건의 가치가 최대 가치와
 *           x-1 물건에서 현재 물건의 무게를 뺀 최대 가치 + 현재 물건의 가치를 비교해줌
 *
 *         - 식 : dp[y][x] = max(dp[y][x-1], dp[y-현재 물건의 무게][x-1] + 현재 물건의 가치)
 *
 * 예시)
 *      0  (6, 13) (4,8) (3,6) (5,12)
 *  0   0    0       0     0     0
 *  1   0    0       0     0     0
 *  2   0    0       0     0     0
 *  3   0    0       0     6     6
 *  4   0    0       8     8     8
 *  5   0    0       8     8     12
 *  6   0    13      13    13    13
 *  7   0    13      13    14    14
 *
 *  dp[7][3]의 경우 dp[7][2]인 13과  dp[7-3][2] + 6인 14중 최댓값인 14가 들어감
 */