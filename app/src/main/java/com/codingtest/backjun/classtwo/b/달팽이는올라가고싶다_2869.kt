package com.codingtest.backjun.classtwo.b

fun main() {
    val (A, B, V) = readln().split(" ").map { it.toInt() }
    print((Math.ceil((V-A) / (A-B).toDouble()) + 1).toInt())
}