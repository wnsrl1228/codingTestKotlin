package com.codingtest.backjun.classfive.g4

import java.io.StreamTokenizer

/**
 * 투 포인터 문제
 *      - 문제 설명 부실
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val S = input()

    val arr = IntArray(N+1)  // 마지막 인덱스도 체크해줘야 되기 때문에 N+1을 통해 빈 공간 하나 채워줌
    for (i in 0 until N){
        arr[i] = input()
    }

    var result = N+1
    var left = 0
    var right = 0
    var sum = 0

    while (right <= N) {
        if (sum < S) {
            sum += arr[right]
            right++
        } else {
            sum -= arr[left]
            val len = right - left
            if (len < result) result = len
            left++
        }
    }
    print(if (result == N+1) 0 else result)
}
