package com.codingtest.backjun.classfive.g2

import kotlin.math.log2

/**
 * 못 품
 * 수학 문제
 *    - 규칙을 찾아줘야 풀 수 있음, 어렵다.
 */
fun main() {
    val sum = LongArray(60)  // 2의 제곱근에 대한 비트의 1의 개수 누적합
    sum[0] = 1
    // 비트 자리수 별 1의 누적합
    for (i in 1..59) {
        // dp[n] = dp[n-1]*2 + 2^n
        sum[i] = sum[i-1]*2 + (1L shl i)
    }

    val (a,b) = readln().split(" ").map { it.toLong() }

    fun solve(num: Long): Long {
        var N = num
        var count = N and 1

        // 비트 크기
        val size = (log2(N.toDouble())).toInt()

        for (i in size downTo 1) {
            // 비트가 1일 경우
            if ((N and (1L shl i)) == 1L) {

                /**
                 * ex) 1100 에서의 1000인 경우에 대해
                 *    1000 이전의 0~111까지의 누적합
                 *    + 0~100까지 도달할 때까지의 1000의 횟수
                 *    + 1000일 때의 경우 plus 1
                 */
                count += sum[i - 1] + (N - (1L shl i) + 1)
                N -= (1L shl i)    // 비트 이동시키기
            }
        }
        return count  // 1의 개수 반환
    }

    val result = solve(b) - solve(a-1)
    print(result)

}
/**
 * 10000000000000000
 *
 * 1                          1
 * 1 2                        1+2
 * 1 2 2 3                    1+2+5
 * 1 2 2 3 '2' 3 3 4           1+2+5+12
 * 1 2 2 3  2  3 3 4 2 3 3 4 3 4 4 5  1+2+5+12+28
 *
 *
 *
 * 1
 * 10  2      1
 * 11  3      2
 * 100 4      1
 * 101 5      2
 * 110 6      2
 * 111 7      3
 * 1000  8    1
 * 1001  9    2
 * 1010  10   2
 * 1011  11   3
 * 1100  12   2
 * 1101  13   3
 * 1110  14   3
 * 1111  15   4
 * 10000 16   1
 * 10001 17   2
 * 10010 18   2
 * 10011 19   3
 * 10100 20   2
 * 10101 21   3
 * 10110 22   3
 * 10111 23   4
 * 11000 24   2
 * 11001 25   3
 * 11010 26   3
 * 11011 27   4
 * 11100 28   3
 * 11101 29   4
 * 11110 30   4
 * 11111 31   5
 */