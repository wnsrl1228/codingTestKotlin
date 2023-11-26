package com.codingtest.backjun.classfour.s1

import java.io.StreamTokenizer

/**
 * 첫번째 풀이는 y축의 누적 합만을 이용 -> x축 기준 for문이 필요
 * 개선된 풀이는 y축과 x축 범위의 누적 합을 이용 -> for문 필요 없음
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input()
    val M = input()

    val arr = Array(N) {
        IntArray(N) {
            input()
        }
    }

    for (i in 1..<N) {
        for (j in 0..<N) {
            arr[i][j] += arr[i-1][j]
        }
    }
    val sb = StringBuilder()
    repeat(M) {
        val x1 = input() - 1
        val y1 = input() - 1
        val x2 = input() - 1
        val y2 = input() - 1
        var sum = 0

        for (y in y1..y2) {
            if (x1 != 0) {
                sum += arr[x2][y] - arr[x1-1][y]
            } else {
                sum += arr[x2][y]
            }
        }

        sb.appendLine(sum.toString())
    }
    print(sb)
//    구간합구하기5_개선된풀이()
}

fun 구간합구하기5_개선된풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input()
    val M = input()

    val arr = Array(N+1) { IntArray(N+1) }

    for (i in 1..N) {
        for (j in 1..N) {
            arr[i][j] = input() + arr[i-1][j] + arr[i][j-1] - arr[i-1][j-1]
        }
    }
    val sb = StringBuilder()
    repeat(M) {
        val x1 = input()
        val y1 = input()
        val x2 = input()
        val y2 = input()

        var sum = arr[x2][y2] - arr[x1-1][y2] - arr[x2][y1-1] + arr[x1-1][y1-1]

        sb.appendLine(sum.toString())
    }
    print(sb)
}