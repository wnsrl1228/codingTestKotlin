package com.codingtest.backjun.classfive.g1

import java.io.StreamTokenizer

/**
 * 못 품
 * - 백 트래킹 + 브루트포스
 * - 체스판의 흰색부분, 검은색 부분 나눠서 구해주면 된다.
 * - 비숍은 대각선으로만 이동하기 때문에 서로 영향을 주지 않기 때문
 * - ex 0부분 백트래킹 + 1부분 백트래킹
 *     0 1 0 1
 *     1 0 1 0
 *     0 1 0 1
 *     1 0 1 0
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val visitedCrossMinus = BooleanArray(n * 2) { false }
    val visitedCrossPlus = BooleanArray(n * 2) { false }
    val arr = Array(n) { BooleanArray(n) { input() == 1 } }

    var tResult = 0
    var result = 0

    fun backTracking(yy: Int, xx: Int, count: Int, isBlack: Boolean) {
        var y = yy
        var x = xx
        if (x >= n) {
            y++
            if (isBlack) {
                if (y % 2 ==0) x=0
                else x = 1
            } else {
                if (y % 2 ==1) x=0
                else x = 1
            }
        }
        if (y == n) {
            tResult = Math.max(tResult, count)
            return
        }

        for (i in y until n) {
            for (j in x until n step 2) {
                if (arr[i][j] && !visitedCrossPlus[i+j] && !visitedCrossMinus[i-j+n]) {
                    visitedCrossPlus[i+j] = true
                    visitedCrossMinus[i-j+n] = true
                    backTracking(i, j+2, count+1, isBlack)
                    visitedCrossPlus[i+j] = false
                    visitedCrossMinus[i-j+n] = false
                }
            }
            if (isBlack) {
                if ((i+1) % 2 ==0) x=0
                else x = 1
            } else {
                if ((i+1) % 2 ==1) x=0
                else x = 1
            }
        }
        tResult = Math.max(tResult, count)
    }

    backTracking(0, 0, 0, true)
    result += tResult
    tResult = 0
    backTracking(0, 1, 0, false)
    print(result+tResult)
}
/**
 * 100 X 100
 *
 * 0 0
 * 0 0
 *
 * 1 1 1
 * 0 0 0
 * 0 1 0
 *
 *  * 1 1 0
 *  * 0 0 0
 *  * 1 1 0
 *
 *  1 1 1 1
 *  0 0 0 0
 *  0 0 0 0
 *  0 1 1 0
 *
 *  1 1 1 0
 *  0 0 0 0
 *  0 0 0 0
 *  1 1 1 0
 *
 *  *  1 1 1 1 1
 *  *  0 0 0 0 0
 *  *  0 0 0 0 0
 *  *  0 0 0 0 0
 *     0 1 1 1 0
 *
 *
 *     1000000
 *
 *        0 1 2 3 4
 *      0
 *      1
 *      2
 *      3
 *      4
 *
 */