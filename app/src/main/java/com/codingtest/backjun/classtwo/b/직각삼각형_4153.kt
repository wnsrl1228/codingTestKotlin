package com.codingtest.backjun.classtwo.b

fun main() {
    while (true) {
        try {
            val (x, y, z) = readln().split(" ").map { it.toInt() }.sorted()
            if (x == 0 && y == 0 && z == 0) break
            if ( z*z == y * y + x * x) println("right")
            else println("wrong")
        } catch (e: Exception) {
            break
        }
    }
}