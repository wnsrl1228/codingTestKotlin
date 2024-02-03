package com.codingtest.backjun.classfive.g1

import java.io.StreamTokenizer

/**
 * 못 품
 *  - 최대 수열의 개수가 40개 이므로 만들 수 있는 수열의 개수는 2^40
 *  - 이를 반 나눠서 20개씩 해주면 제한 시간 안에 풀 수 있음
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val S = input()

    val arr = IntArray(N) { input() }

    var result = 0L
    val mid = N/2

    fun first(sum: Int, cur: Int, map: HashMap<Int, Int>) {

        for (i in cur until mid) {
            val a = sum + arr[i]
            if (a == S) result++
            map[a] = map.getOrDefault(a, 0) + 1
            first(a, i+1, map)
        }
    }

    fun second(sum: Int, cur: Int, map: HashMap<Int, Int>) {

        for (i in cur until N) {
            val a = sum + arr[i]
            if (a == S) result++
            if (map.containsKey(S-a)) {
                result += map[S-a]!!
            }
            second(a, i+1, map)
        }
    }

    val map1 = hashMapOf<Int, Int>()
    first(0, 0, map1)
    second(0, mid, map1)
    print(result)
}