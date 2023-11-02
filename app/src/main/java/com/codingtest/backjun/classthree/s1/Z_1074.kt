package com.codingtest.backjun.classthree.s1

/**
 * 2차월 배열을 4분할하면서 답을 구해나가면 된다.
 */
fun main() {
    var (N, r, c) = readln().split(" ").map { it.toInt() }

    var n = Math.pow(2.0, N.toDouble()).toInt()
    var result = 0
    while (n >= 1) {
        n /= 2
        if (r >= n && c >= n) { // 4분대
            result += (n*n) * 3
            r -= n
            c -= n
        } else if (r < n && c < n) { // 1분대
            result += (n*n) * 0
        } else if (r < n && c >= n) { // 2분대
            result += (n*n) * 1
            c -= n
        } else if (r >= n && c < n) { // 3분대
            result += (n*n) * 2
            r -= n
        }
    }
    print(result)
}