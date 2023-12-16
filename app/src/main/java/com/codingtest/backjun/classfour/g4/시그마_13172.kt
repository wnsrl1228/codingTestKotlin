package com.codingtest.backjun.classfour.g4

import java.io.StreamTokenizer

/**
 * 거듭 제곱 문제
 * - 수가 크다보니 나머지 계산을 중간중간에도 넣어줘야 한다.
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Long {
        nextToken()
        return nval.toLong()
    }

    val X = 1000000007L
    val M = input().toInt()
    var result = 0L

    fun solve(x: Long, num: Long): Long {

        if (x == 1L) {
            return num
        }

        var result = solve(x / 2, num)

        if (x % 2 == 0L) {
            return result * result % X % X
        } else {
            return result * result % X * num % X
        }
    }

    repeat(M) {
        val N = input() // 분모 b
        val S = input() // 분자 a
        val NInverse =  solve(X-2, N)
        result = (result + S * NInverse % X) % X
    }
    print(result)
}

/**
 * 각 면의 기대값 = 각면의 값의 합 / 면의 개수
 * m개의 주사위가 있을 경우 = (각면의 값의 합 / 면의 개수) + (각면의 값의 합 / 면의 개수) + (각면의 값의 합 / 면의 개수) ... m개
 *      - 이떄 분모 같게해주는 작업 필요(통분) 그럼 수가 너무 커지니 그냥 분자를 정수화 시켜줌
 *      - 7 / 3 -> 7 3^-1 % X
 *      - 3^ x 3 ≡  1 mod X 즉 3^-1 x 3을 X로 나눈 나머지는 1이다.
 *
 *
 *      ex) 7/3 -> 3^-1 = 4 // 3에 어떤 수를 곱한 뒤 11로 나머지 정리하였을 때 1이 나와야 됨. 어떤 수 = 4
 *              -> 7 X 4 mod 11 -> 6
 *
 *      하지만 문제점도 존재
 *          1. 같은 정수로 저장되는 경우 생김
 *          2. X가 분모의 소인수인 경우 나머지는 항상 0이 된다. ex) 7/15 , X = 3,  [15 x B = 1 (mod) 3] 을 만족하는 B는 없다.
 *
 *      해결법 X를 매우 큰 수로 해주면 됨
 *
 *      결론적으로 b^-1을 구해주면 됨
 *      구하는 방법은 b^-1 = b^X-2 를 X로 나누었을때 나머지
 *      따라서 b^X-2만 알면 된다.
 *
 */