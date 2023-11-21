package com.codingtest.backjun.classfour.s2

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun i(): Int {nextToken();return nval.toInt()}
    val sb = StringBuilder()
    val N = i()
    val M = i()
    val arr = IntArray(N)
    repeat(N) {
        arr[it] = i()
    }
    arr.sort()

    val nums = IntArray(M)

    fun recursion(count: Int, index: Int) {

        if (count == M) {
            nums.forEach { sb.append("$it ") }
            sb.appendLine()
            return
        }
        var prev = -1
        for (i in index..<N) {
            if (prev != arr[i]) {
                nums[count] = arr[i]
                recursion(count+1, i)
                prev = arr[i]
            }
        }
    }
    recursion(0, 0)
    print(sb)
}