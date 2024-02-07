package com.codingtest.backjun.classfive.g1

/**
 * 못 품
 *   - 비트 필드 dp 문제
 */
fun main() {
    val N = readln().toInt()
    val dp = Array(N) {Array(10) {LongArray(1024)} }
    for (i in 1..9) dp[0][i][1 shl i] = 1
    for (i in 1 until N) {
        for (j in 0..9) {
            for (l in 0 until 1024) {
                val b = l or (1 shl j)
                if (j >= 1) {
                    dp[i][j][b] += dp[i-1][j-1][l] % 1000000000L
                }
                if (j <= 8) {
                    dp[i][j][b] += dp[i-1][j+1][l] % 1000000000L
                }
            }
        }
    }
    var result = 0L
    for (i in 0 until 10) {
        result = (result + dp[N-1][i][1023]) % 1000000000L
    }
    print(result)
}

fun 쉬운계단수() {

    val N = readln().toInt()
    val dp = Array(N) {LongArray(10)}
    for (i in 0..9) dp[0][i] = 1
    for (i in 1 until N) {
        dp[i][0] = dp[i-1][1]
        for (j in 1..8) {
            dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000
        }
        dp[i][9] = dp[i-1][8]
    }

    print((dp[N-1].sum() - dp[N-1][0]) % 1000000000)

}
/**
 *       0 1 2 3 4 5 6 7 8 9
 *    0  1 1 1 1 1 1 1 1 1 1
 *    1  1 2 2 2 2 2 2 2 2 1          10  12
 *    2  0 3 4 4 4 4 4 4 3 2
 *    10
 *    210
 *    010
 *
 *    1111111110
 *
 *
 */
