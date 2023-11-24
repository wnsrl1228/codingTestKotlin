package com.codingtest.backjun.classfour.s1

import java.io.StreamTokenizer
// 못 품
/**
 * a^b = a^(b/2) * a^(b/2) 를 이용
 *      b가 홀수인 경우에는  a^(b/2) * a^(b/2) * a
 * 추가적으로 나머지 정리만 해주면 됨
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val A = input()
    val B = input()
    val C = input()


    fun pow(a: Int, b: Int, c: Int) : Long {
        if (b == 0)
            return 1

        val n = pow(a, b / 2, c)  // n = a의 (b/2) 제곱, a의b제곱 = n * n
        return if (b % 2 == 0) {
            n * n % c
        } else {
            (n * n % c) * a % c
        }
    }

    print(pow(A, B, C))
}
