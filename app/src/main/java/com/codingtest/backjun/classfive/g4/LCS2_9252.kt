package com.codingtest.backjun.classfive.g4

/**
 * 못 품
 * 참고 : https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-LCS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Longest-Common-Substring%EC%99%80-Longest-Common-Subsequence#longest-common-subsequence-substring
 *
 * 이중 배열을 이용하는 dp문제
 * 풀이를 생각하기도 어렵고 풀이 자체도 복잡하다.
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

    if (dp[a.length][b.length] == 0) {
        println(0)
        return
    }

    val max = dp[a.length][b.length]
    val sb = StringBuilder()
    var y = a.length
    var x = b.length

    repeat(max) {

        var i = y
        var j = x
        while (dp[i][j] == dp[i-1][j]) {
            i--
        }
        while (dp[i][j] == dp[i][j-1]) {
            j--
        }

        sb.append(a[i-1])

        y = i-1
        x = j-1
    }
    println(dp[a.length][b.length])
    println(sb.reverse())
}