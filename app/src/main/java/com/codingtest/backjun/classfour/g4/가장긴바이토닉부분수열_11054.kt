package com.codingtest.backjun.classfour.g4

import java.io.StreamTokenizer

/**
 * N범위가 적기 때문에
 *  - 왼쪽 값들 중 작은 값이랑 오른쪽 값들 중 작은 값 모두 구하면 됨
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input()
    val arr = IntArray(N) {input()}
    val left = IntArray(N) {1}
    val right = IntArray(N) {1}

    for (i in 1 until N) {
        for (j in i-1 downTo 0) {
            if (arr[i] > arr[j] && left[i] <= left[j] )
                left[i] = left[j] + 1
        }
    }

    for (i in N-1 downTo  0) {
        for (j in i+1 until  N) {
            if (arr[i] > arr[j] && right[i] <= right[j] )
                right[i] = right[j] + 1
        }
    }
    var result = 0
    for (i in 0 until N) {
        if (result < left[i] + right[i])
            result = left[i] + right[i]
    }
    print(result-1)
}