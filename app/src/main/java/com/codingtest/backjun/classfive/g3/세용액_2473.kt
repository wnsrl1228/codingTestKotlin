package com.codingtest.backjun.classfive.g3

import java.io.StreamTokenizer

/**
 * 투 포인터 응용 문제
 * - 하나의 용액을 고정 시킨 뒤에 나머지 용액끼리 투 포인터 진행
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Long {
        nextToken()
        return nval.toLong()
    }

    val N = input().toInt()
    val arr = LongArray(N) {input()}

    arr.sort()
    var min = Long.MAX_VALUE
    var result = LongArray(3)
    // i번째 용액 고정
    for (i in 0 until arr.size-2) {
        val standard = arr[i]

        var left = i+1
        var right = arr.size-1

        // 나머지 2개의 용액 투 포인터로 찾음
        while (left < right) {
            val sum = arr[left] + arr[right]
            if (Math.abs(sum + standard) < min) {
                result[0] = standard
                result[1] = arr[left]
                result[2] = arr[right]
                min = Math.abs(sum + standard)
            }

            if (sum + standard < 0) {
                left++
            } else {
                right--
            }

            if (min == 0L) {
                print(result.joinToString(" "))
                return
            }
        }
    }
    print(result.joinToString(" "))
}
/**
 * 산-알칼리
 *
 * 3가지로 0에 가깝게
 *
 * 5000 C 2
 *
 * 25,000,000
 * 12,500,000
 *
 *
 * -97 -6 -2 6 98
 *
 * -1
 *
 * -30 -29 -3 -2 61 98 100
 *
 * -24 -6 -3 -2 61 98 100
 *
 * -99 -80 -70 -2 50 60 100
 *
 *
 * -120 -104 -1 1 2 3 180 2000
 *
 * 10000 * 5000
 * 50,000,000
 *
 *
 * -4 -2 -1 2 3
 * -2 -1 3
 *
 * -4  -1  2
 *
 * -942278495  -104336608  239510944  627058077  722156401  997686289
 *
 * -94 -10 23 52 62 72 99
 *
 *
 *
 * -97 -6 -2 6 98
 * -97 -6 -2 6 98
 * -97 -6 -2 6 98
 * */