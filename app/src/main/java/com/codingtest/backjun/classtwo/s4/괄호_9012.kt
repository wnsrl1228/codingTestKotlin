package com.codingtest.backjun.classtwo.s4


fun main() {
    repeat(readln().toInt()) {
        val str = readln()
        if (isVPS(str)) println("YES")
        else println("NO")
    }
}

fun isVPS(str: String) : Boolean {

    var count = 0
    for (c in str) {
        if (c == '(') {
            count++
        } else {
            if (count == 0) return false
            count--
        }
    }
    return count == 0
}