package com.codingtest.backjun.classfour.g4

import java.io.StreamTokenizer

/**
 * dfs + 백트래킹(완탐)
 * - 모든 입력값의 범위가 작기 때문에 완탐을 돌려도 된다.
 * - bfs보다 dfs가 빠르다.
 * - zero위치와 바이러스 위치 값을 입력받을 때 미리 저장하고 완탐을 돌리는 방법도 있으며 더 빠르다.
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input() // 세로
    val M = input() // 가로

    val arr = Array(N) {IntArray(M) {input()} }

    val dx = arrayOf(0,1,0,-1)
    val dy = arrayOf(-1,0,1,0)

    fun dfs(y: Int, x: Int, tArr: Array<IntArray>) {

        tArr[y][x] = 1

        for (i in dx.indices) {
            val nextY = dy[i] + y
            val nextX = dx[i] + x

            if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) {
                continue
            }
            if (tArr[nextY][nextX] == 0 || tArr[nextY][nextX] == 2) {
                tArr[nextY][nextX] = 1
                dfs(nextY, nextX, tArr)
            }
        }
    }

    var result = 0

    fun backTracking(ty: Int, tx: Int, count: Int) {
        var y = ty
        var x = tx

        if (count == 3) {
            val tArr = Array(N) { i ->
                IntArray(M) { j ->
                    arr[i][j]
                }
            }
            for (i in 0 until N) {
                for (j in 0 until M) {
                    if (tArr[i][j] == 2) {
                        dfs(i, j, tArr)
                    }
                }
            }

            var tResult = 0
            for (i in 0 until N) {
                for (j in 0 until M) {
                    if (tArr[i][j] == 0) {
                        tResult++
                    }
                }
            }

            result = Math.max(result, tResult)
            return
        }
        for (i in y until N) {
            for (j in x until M) {
                if (arr[i][j] == 0) {
                    arr[i][j] = 1
                    if (j == M-1) {
                        backTracking(i+1, 0, count+1)
                    } else {
                        backTracking(i, j+1, count+1)
                    }
                    arr[i][j] = 0
                }
                if (j == M-1) x = 0
            }
        }
    }

    backTracking(0,0,0)
    print(result)
}