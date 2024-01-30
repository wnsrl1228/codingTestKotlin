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
    var cur = -1 // 임시 배열 현재 위치

    while(N-- > 0) {
        val num = input()

        // 임시 배열이 비어있는 경우
        if (cur == -1) {
            arr[++cur] = num
            continue
        }

        // 임시 배열의 마지막 값과 비교
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
    // 덮어 씌어서 갱신
    arr[s] = num
}
// 10 40 70  50


/**
 * 10, 20, 3, 30, 20, 5, 6, 7, 8, 9, 10, 50
 *
 * 3 5 6 7 8 9 10 50
 *
 *  1 2 3 4 5 6 .. 50
 *
 *
 *  10 20 10 30 20 50
 *   1 2   1  3
 *
 *
 * 10 20 12 17 12 15 16 17 18 30 20 50
 *
 * 12 15 16 17 18 20 50
 *
 * 10 12 17 20
 *
 *
 * 1000000
 *
 *
 * 10 20 10 30 20 50
 *                 2
 *
 */