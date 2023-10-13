package com.codingtest.backjun.classtwo.s5

fun main() {
    var n = readln().toInt()
    var i = 665
    while(n > 0) {
        i++
        if (i.toString().contains("666")) n--
    }
    print(i)
}