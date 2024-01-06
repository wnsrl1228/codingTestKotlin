package com.codingtest.backjun.classfive.g4

import java.io.StreamTokenizer

/**
 * 이중 배열을 이용한 DP문제
 *
 * 첫 번째 풀이는 그냥 팰린드롬하나 씩 체크해주는 풀이인데 통과됨
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N =input()
    val arr = IntArray(N) {input()}
    val dp = Array(N) {BooleanArray(N)}

    for (i in 0 until N) dp[i][i] = true

    fun isPalindrome(start: Int, end: Int) : Boolean {

        var s = start
        var e = end

        while (s < e) {
            if (arr[s++] != arr[e--]) {
                return false
            }
        }
        return true
    }

    for (i in 0 until N-1) {
        for (j in i+1 until N) {
            if (isPalindrome(i, j)) {
                dp[i][j] = true
            }
        }
    }

    val sb = StringBuilder()
    repeat(input()) {
        val start = input()-1
        val end = input()-1
        sb.appendLine(if (dp[start][end]) 1 else 0)
    }

    print(sb)
//    팰린드롬_DP풀이()
}

/**
 * 이중 배열을 이용한 DP풀이
 *      팰린드롬을 확인하는 방법
 *      - 양 끝의 문자열이 같고 그 안에 문자열이 팰린드롬일 때
 *      따라서 행은 글자수, 열을 문자열에 대한 이중 배열을 만들어주면
 *      안에 문자열이 팰린드롬인지 여부를 바로 확인할 수 있다.
 */
fun 팰린드롬_DP풀이() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N =input()
    val arr = IntArray(N) {input()}
    val dp = Array(N) {BooleanArray(N)}  // 행 : 글자수, 열 : 시작 글자

    for (i in 0 until N) dp[0][i] = true  // 글자가 하나인 경우 모두 팰린드롬
    for (i in 0 until N-1)                // 나중에 i-2값 때문에 두 글자까지 임의로 채워넣어줌
        if (arr[i] == arr[i+1]) dp[1][i] = true


    for (i in 2 until N) {
        for (j in 0 until N-i) {
            /**
             * 현재 문자에서 양 끝을 제거한 문자가 팰린드롬이고 -> dp[현재 글자수 -2][현재 문자의 next 문자]
             * 현재 문자랑 끝 문자가 같은 경우               -> arr[현재글자] == arr[현재글자 + 글자 개수]
             */
            if (dp[i-2][j+1] && arr[j] == arr[j+i]) {
                dp[i][j] = true
            }
        }
    }

    val sb = StringBuilder()
    repeat(input()) {
        val start = input()-1
        val end = input()-1
        sb.appendLine(if (dp[end-start][start]) 1 else 0) // 행 : 글자 개수, 열 : 시작 글자
    }

    print(sb)
}
/**
 *  행 : 글자개수, 열 : 시작 문자
 *       1  2  1  3  1  2  1
 *    1  1  1  1  1  1  1  1
 *    2  0  0  0  0  0  0  0
 *    3  1  0  1  0  1  0  0
 *    4  0  0  0  0  0  0  0
 *    5  0  1  0  0  0  0  0
 *    6  0  0  0  0  0  0  0
 *    7  1  0  0  0  0  0  0
 */