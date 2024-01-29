package com.codingtest.backjun.classfive.g2

import java.io.StreamTokenizer

/**
 * 못 품
 * LIS(Longest Increasing subsequence)의 이분탐색 풀이
 * 참고 : https://jason9319.tistory.com/113
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    var N = input()
    val arr = IntArray(N)
    var cur = -1
    while(N-- > 0) {
        val num = input()

        if (cur == -1) {
            arr[++cur] = num
            continue
        }

        if (arr[cur] < num) {
            arr[++cur] = num
        } else {
            binarySearch(0, cur, num, arr)
        }
    }
    print(cur+1)

}

fun binarySearch(start: Int, end: Int, num: Int, arr: IntArray) {
    var s = start
    var e = end
    while (s <= e) {
        val mid = (s+e)/2
        if (num == arr[mid]) {
            return
        } else if (num < arr[mid]) {
            e = mid-1
        } else {
            s = mid+1
        }
    }
    arr[s] = num
}
