package com.codingtest.backjun.classfour.g4

import java.io.StreamTokenizer

/**
 * 플로이드 워셜
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val n = input() // 지역 개수
    val m = input() // 수색 범위
    val r = input() // 길의 개수

    val MAX = 10000000
    val map = Array(n+1) {IntArray(n+1) {MAX} }

    val items = IntArray(n+1)
    for (i in 1..n) {
        items[i] = input()
        map[i][i] = 0
    }

    repeat(r) {
        val a = input()
        val b = input()
        val c = input()
        map[a][b] = c
        map[b][a] = c
    }

    // 최단 거리 계산
    for (i in 1..n) {
        for (y in 1..n) {
            for (x in 1..n) {
                map[y][x] = Math.min(map[y][x], map[y][i] + map[i][x])
            }
        }
    }

    // 최대 아이템 개수 구하기
    var result = 0
    for (y in 1..n) {
        var itemCount = 0
        for (x in 1..n) {
            // 수색 범위보다 적을 경우
            if (map[y][x] <= m) {
                itemCount += items[x]
            }
        }
        result = Math.max(result, itemCount)
    }

    print(result)
}