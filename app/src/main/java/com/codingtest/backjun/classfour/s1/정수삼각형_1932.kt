package com.codingtest.backjun.classfour.s1

import java.io.StreamTokenizer

fun main() = with(System.`in`.bufferedReader()){

    val map = HashMap<Int, IntArray>()

    repeat(readLine().toInt()) {
        map[it] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    for (i in 1..<map.size) {

        val tMap = map[i]!!
        for (j in tMap.indices) {
            if (j == 0) {
                tMap[j] += map[i-1]!![j]
                continue
            }
            if (j == tMap.size-1) {
                tMap[j] += map[i-1]!![j-1]
                continue
            }
            tMap[j] += Math.max(map[i-1]!![j-1], map[i-1]!![j])
        }
    }
    print(map[map.size-1]!!.max())
//    정수삼각형_개선된풀이()
}

/**
 * bottom-top으로 가야 신경써야될 부분들이 사라짐
 */
fun 정수삼각형_개선된풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }
    val N = input()
    val arr = Array(N) { i ->
        IntArray(i+1) { j ->
            input()
        }
    }

    for (i in N-2 downTo 0) {
        for (j in arr[i].indices) {
            arr[i][j] += Math.max(arr[i+1][j], arr[i+1][j+1])
        }
    }

    print(arr[0][0])
}