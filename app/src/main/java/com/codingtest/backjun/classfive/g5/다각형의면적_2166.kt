package com.codingtest.backjun.classfive.g5

import java.io.StreamTokenizer

/**
 * 못 품
 * 수학 문제
 *      - 신발 끈 이론을 알아야지 풀 수 있다.
 *        = 다각형의 면접을 구하는 공식
 *        참고 https://chemicals1234.tistory.com/18
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Long {
        nextToken()
        return nval.toLong()
    }
    val n = input().toInt()

    var a = 0L
    var b = 0L
    val x1 = input()
    val y1 = input()
    var tx1 = x1
    var ty1 = y1
    repeat(n-1) {
        val x2 = input()
        val y2 = input()
        a += tx1 * y2
        b += ty1 * x2
        tx1 = x2
        ty1 = y2
    }
    a += tx1 * y1
    b += ty1 * x1

    print( String.format("%.1f", Math.abs(a-b)/ 2.0))
}