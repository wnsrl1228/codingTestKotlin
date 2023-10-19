package com.codingtest.backjun.classtwo.s4

fun main() = with(System.`in`.bufferedReader()) {
    val arr = ArrayDeque<Int>()
    var sum = 0
    repeat(readLine().toInt()) {
        val n = readLine().toInt()
        if (n == 0) sum -= arr.removeLast()
        else {
            sum += n
            arr.addLast(n)
        }
    }
    print(sum)
}