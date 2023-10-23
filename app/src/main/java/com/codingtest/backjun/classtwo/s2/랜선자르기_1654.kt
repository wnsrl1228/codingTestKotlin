package com.codingtest.backjun.classtwo.s2

/**
 * 평범한 이분 탐색 문제인데
 * Int -> Long 안 바꿔줘서 애 먹음
 */
fun main() {
    val (k, n) = readln().split(" ").map { it.toInt()}

    val arr =IntArray(k) { readln().toInt() }

    var max : Long = arr.max().toLong()
    var min : Long = 1
    var result : Long = 0
    while (min <= max) {
        val mid : Long = (max + min) / 2
        var count = 0

        for (i in arr) {
            count += i/mid.toInt()
        }
        // N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다.
        if (count >= n) { // 원하는 랜선개수보다 같거나 더 많이 생긴 경우, mid를 높인다.
            result = mid
            min = mid + 1
        } else if (count < n) { // 원하는 랜선개수보다 덜 생긴 경우, mid를 낮춘다.
            max = mid - 1
        }
    }
    print(result)
}
/**
 * 2 3
 * 150
 * 5
 * 일때 50이 답이다.
 */