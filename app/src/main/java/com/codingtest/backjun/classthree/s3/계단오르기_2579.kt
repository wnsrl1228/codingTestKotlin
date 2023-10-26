package com.codingtest.backjun.classthree.s3

/**
 * 1 or 2
 * 연속 3계단 X
 * 마지막 계단은 필수
 */
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = IntArray(n){readLine().toInt()}
    val result = Array(n){Pair(-1,-1)}  // (n-2을 밟지않은 n-1, n-2중 최댓값)

    result[0] = Pair(arr[0], arr[0])
    if (n != 1) {
        result[1] = Pair(arr[0]+arr[1], arr[1])

        for (i in 2 until n) {
            result[i] = Pair(
                result[i-1].second + arr[i],     // n-2를 밟지 않은 n-1
                Math.max(result[i-2].first + arr[i], result[i-2].second + arr[i])  // n-2를 밟은 계단중 최댓값
            )
        }
    }

    print(Math.max(result[n-1].first, result[n-1].second))
}