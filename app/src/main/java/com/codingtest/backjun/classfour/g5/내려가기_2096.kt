package com.codingtest.backjun.classfour.g5

import java.io.StreamTokenizer

/**
 * 평범한 dp문제
 *  - 단 메모리 제한이 빡세기 때문에 일차원 배열로 해결해야 된다.
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val maxArr = IntArray(3)
    val minArr = IntArray(3)
    val tmp = Array(2) {IntArray(3)}

    repeat(N) {
        repeat(3) { j ->
            val t = input()
            if (j == 0) {
                tmp[0][j] = Math.max(maxArr[j], maxArr[j+1]) + t
                tmp[1][j] = Math.min(minArr[j], minArr[j+1]) + t
            } else if (j == 2) {
                tmp[0][j] = Math.max(maxArr[j], maxArr[j-1]) + t
                tmp[1][j] = Math.min(minArr[j], minArr[j-1]) + t
            } else {
                tmp[0][j] = Math.max(Math.max(maxArr[j], maxArr[j-1]), maxArr[j+1]) + t
                tmp[1][j] = Math.min(Math.min(minArr[j], minArr[j-1]), minArr[j+1]) + t
            }
        }
        for (i in 0..2) {
            maxArr[i] = tmp[0][i]
            minArr[i] = tmp[1][i]
        }
    }
    print("${maxArr.max()} ${minArr.min()}")
}
