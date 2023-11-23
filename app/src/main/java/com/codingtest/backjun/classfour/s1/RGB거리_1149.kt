package com.codingtest.backjun.classfour.s1

fun main() = with(System.`in`.bufferedReader()){


    val N = readLine().toInt()
    val result = readLine().split(" ").map {it.toInt()}.toIntArray()

    repeat(N-1) {
        val arr = readLine().split(" ").map {it.toInt()}

        val tmp1 = Math.min(arr[0] + result[1], arr[0] + result[2])
        val tmp2 = Math.min(arr[1] + result[0], arr[1] + result[2])
        val tmp3 = Math.min(arr[2] + result[0], arr[2] + result[1])
        result[0] = tmp1
        result[1] = tmp2
        result[2] = tmp3
    }
    print(Math.min(Math.min(result[0], result[1]), result[2]))

}