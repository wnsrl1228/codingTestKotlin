package com.codingtest.backjun.classthree.s3

fun main() = with(System.`in`.bufferedReader()){
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val arr = readLine().split(" ").map { it.toInt() }
    val sumArr = IntArray(N)
    var sum = 0
    for (i in 0 until N) {
        sum += arr[i]
        sumArr[i] = sum
    }

    val sb= StringBuilder()
    repeat(M) {
        val (s, e) = readLine().split(" ").map { it.toInt() }
        var d = 0
        if (s > 1) d = sumArr[s-2]
        sb.appendLine(sumArr[e-1] - d)
    }
    print(sb)
}
