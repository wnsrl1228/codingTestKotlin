package com.codingtest.backjun.classfour.g4

import java.io.StreamTokenizer

/**
 * 기본적인 플로이드 워셜 문제
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val MAX = 10000001
    val n = input()
    val m = input()
    val arr = Array(n+1) {IntArray(n+1) {MAX} }
    for(i in 1..n) arr[i][i]=0

    repeat(m) {
        val a = input()
        val b = input()
        val c = input()
        arr[a][b] = Math.min(arr[a][b], c)
    }

    for (i in 1..n) {
        for (y in 1..n) {
            for (x in 1..n) {
                arr[y][x] = Math.min(arr[y][x], arr[y][i] + arr[i][x])
            }// 2 -> 3 vs 2 -> 1 -> 3
        }
    }

    val sb = StringBuilder()
    for (i in 1..n) {
        for (j in 1..n) {
            sb.append( if (arr[i][j] >= MAX) "0 " else "${arr[i][j]} ")
        }
        sb.appendLine()
    }
    print(sb)
}