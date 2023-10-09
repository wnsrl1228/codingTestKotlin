package com.codingtest.backjun.classtwo.b


import java.lang.StringBuilder

/**
 * 퀵 정렬도 통과되긴함. 그냥 입출력 버퍼 쓰면 되는 문제
 */
fun main() = with(System.`in`.bufferedReader()) {
    val n = this.readLine().toInt()
    val arr = IntArray(10001)
    repeat(n) { i ->
        arr[this.readLine().toInt()]++
    }
    val sb = StringBuilder()
    for (i in 0 until 10001) {
        while (arr[i] > 0) {
            sb.append(i).append("\n")
            arr[i]--
        }
    }
    print(sb)
}

fun 수정렬_bw사용풀이() = with(System.`in`.bufferedReader()) {
    val n = this.readLine().toInt()
    val arr = IntArray(10001)
    repeat(n) { i ->
        arr[this.readLine().toInt()]++
    }
    val bw = System.out.bufferedWriter()
    for (i in 0 until 10001) {
        bw.write("$i\n".repeat(arr[i]))
    }
    bw.close()
}