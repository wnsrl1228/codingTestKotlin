package com.codingtest.backjun.classthree.s1

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val arr = Array(n) {Pair(0,0)}

    repeat(n) {
        arr[it] = Pair(input(), input())
    }

    arr.sortWith(compareBy( {it.second}, {it.first}))

    var result = 1
    var last = arr[0].second
    for (i in 1 until n) {
        if (last <= arr[i].first) {
            result++
            last = arr[i].second
        }
    }
    print(result)
}

/**
 * 0 6
 * 1 4
 * 2 13
 * 3 5
 * 3 8
 * 5 7
 * 5 8
 * 5 9
 * 6 10
 * 8 11
 * 8 12
 * 12 14
 *
 *
 * 1 4
 * 3 5
 * 0 6
 * 5 7
 * 3 8
 * 5 8
 * 5 9
 * 6 10
 * 8 11
 * 8 12
 * 12 14
 *
 *
 *
 *
 */