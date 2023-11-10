package com.codingtest.backjun.classthree.g5

import java.io.StreamTokenizer

/**
 * 0 ~ n+x 까지 고장나지 않은 번호로만 이루어진 채널들만 채널 이동횟수를 구한 뒤 비교해준다.
 */
fun main() {
    val n = readln().toInt()
    val m = readln().toInt()

    var minCount = Math.abs(n - 100)

    if (m == 0) { // 102=2, 104=3
        if (minCount > n.toString().length) print(n.toString().length)
        else print(minCount)
        return
    }

    val button = readln().split(" ")

    for (ch in 0..n+minCount) {
        val chStr = ch.toString()
        var check = true
        for (b in button) {
            if (chStr.contains(b)) {
                check = false
                break
            }
        }

        if (check) {
            val count = chStr.length + Math.abs(n - ch) // 번호 입력 + 채널 이동
            minCount = Math.min(minCount, count)
        }
    }
    print(minCount)
}

// contains을 안 쓰는 풀이
fun 리모컨_개선풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val btnArr = BooleanArray(10) { true }

    if (m != 0) {
        repeat(m) {
            btnArr[input()] = false
        }
    }

    var minCount = Math.abs(n - 100)

    for (ch in 0..n+minCount) {

        fun btnClick(n: Int): Int {
            if(n == 0) {
                return if(btnArr[0]) 1 else 0
            }
            var num = n
            var res = 0
            while (num > 0) {
                if(!btnArr[num % 10]) {
                    return 0
                }
                num /= 10
                res++
            }

            return res
        }
        val count = btnClick(ch)
        if (count > 0) {
            minCount = Math.min(minCount, count + Math.abs(n-ch))
        }
    }
    print(minCount)
}