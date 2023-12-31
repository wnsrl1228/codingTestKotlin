package com.codingtest.backjun.classfive.g5

import java.io.StreamTokenizer

/**
 * 에라토스테네스의 체 응용 문제 + 수학 지식
 *      - N의 범위가 100,000 라서 n제곱의 시간복잡도 불가
 *      - 메모리 제한이 1024MB로 많음
 *      - 숫자 범위 최대 1,000,000의 배열을 만들어 준 뒤 주어진 숫자의 배수에 -1을 해준다.
 *
 *      굉장히 단순한 문제이지만, 에라토스테네스의 체가 시간 제한이 걸릴 거 같아서 시도를 못함
 *      실제로는
 *      N이 최대 100,000일 때
 *           시그마 n=1 ~ 100,000까지 1,000,000 / n
 *           = 10^6 * (1/1 + 1/2 + 1/3 + ... + 1/10^6)
 *           = 10^6 * 14
 *      대략 1400만이다.
 *
 *      조화 급수 = O(n log n)의 복잡도를 가진다.
 *
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val arr = IntArray(N) {input()}
    val max = arr.max() + 1

    val resultArr = IntArray(max)
    val boolArr = BooleanArray(max)

    for (i in arr) {
        boolArr[i] = true
    }

    for (i in arr) {
        var a = i+i
        while (a < max) {
            if (boolArr[a]) resultArr[i]++
            resultArr[a]--
            a += i
        }
    }
    val sb = StringBuilder()
    for (a in arr) {
        sb.append("${resultArr[a]} ")
    }
    print(sb)
}