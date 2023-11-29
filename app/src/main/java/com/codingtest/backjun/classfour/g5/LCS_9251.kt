package com.codingtest.backjun.classfour.g5

/**
 * 못 품
 * https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-LCS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Longest-Common-Substring%EC%99%80-Longest-Common-Subsequence#longest-common-subsequence-substring
 * 블로그 글 참고
 *
 * + 두 문자열의 길이가 다를 수도 있다.
 */
fun main() {
    val a = readln()
    val b = readln()

    val dp = Array(a.length+1) { IntArray(b.length+1)}

    for (i in 1..a.length) {
        for (j in 1..b.length) {
            if (a[i-1] == b[j-1]) {
                dp[i][j] = dp[i-1][j-1] + 1
            } else {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])
            }
        }
    }
    print(dp.last().max())
}
/**
 *      - A C A Y K P
 *    - 0 0 0 0 0 0 0
 *    C 0 0 1 1 1 1 1
 *    A 0 1 1 2 2 2 2
 *    P 0 1 1 2 2 2 3
 *    C 0 1 2 2 2 2 3
 *    A 0 1 2 3 3 3 3
 *    K 0 1 2 3 3 4 3
 */