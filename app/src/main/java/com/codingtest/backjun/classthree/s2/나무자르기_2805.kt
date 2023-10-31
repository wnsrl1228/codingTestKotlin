package com.codingtest.backjun.classthree.s2

import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()){
    val (n, m) = readLine().split(" ").map { it.toInt() }
    var str = StringTokenizer(readLine(), " ")
    val arr = LongArray(n)
    var end = 0L
    var i = 0
    while (str.hasMoreTokens()) {
        val num = str.nextToken().toLong()
        arr[i++] = num
        end = Math.max(num.toLong(), end)
    }

    var r = m
    var start = 0L

    var result = 0L
    while (start <= end) {
        var mid = (start + end) / 2

        // 자르고 남은 나무 합
        var sum = 0L
        for (a in arr) {
            if (a > mid) {
                sum += a - mid
            }
        }

        if (sum == r.toLong()) {
            result = mid
            break
        } else if (sum < r) { // 나무를 더 잘라야 되는 경우
            end = mid - 1
        } else if (sum > r) { // 너무 많이 혹은 알맞게 잘랐을 경우
            result = Math.max(result, mid)  // 결과와 길이 비교
            start = mid + 1
        }
    }
    print(result)
}