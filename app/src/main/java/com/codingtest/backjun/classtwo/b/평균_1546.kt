package com.codingtest.backjun.classtwo.b

fun main() {
    readln()
    var result = 0.0
    val arr =readln().split(" ").map { it.toDouble() }
    val max = arr.max()
    for (i in arr) {
        result += i / max * 100
    }
    print(result/arr.size)
}

fun 평균_개선된코드() {
    readln()
    val score = readln().split(" ").map { it.toDouble() }
    val max = score.max()
    println(score.map { it / max * 100 }.average())
}