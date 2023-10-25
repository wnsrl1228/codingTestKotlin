package com.codingtest.backjun.classthree.s4


fun main() = with(System.`in`.bufferedReader()){

    val (N, M) = readLine().split(" ").map { it.toInt() }
    val map1 = mutableMapOf<Int, String>()
    val map2 = mutableMapOf<String, Int>()
    val sb = StringBuilder()
    repeat(N) {
        val s = readLine()
        map1[it+1] = s
        map2[s] = it+1
    }

    repeat(M) {
        val input = readLine()
        if (isNumeric(input)) {
            sb.appendLine(map1[input.toInt()])
        } else {
            sb.appendLine(map2[input])
        }
    }
    print(sb)
}
fun isNumeric(str: String) = str.all { it.isDigit() }