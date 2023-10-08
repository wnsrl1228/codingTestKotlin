package com.codingtest.backjun.classtwo.b

fun main() {
    while (true) {
        val n = readln()
        if (n == "0") break
        var isP = true
        for (i in 0 until n.length/2) {
            if (n[i] != n[n.length - i - 1]) {
                println("no")
                isP = false
                break
            }
        }
        if (isP) println("yes")
    }
}

fun 다른풀이() {
    while (true){
        val n = readln()
        if(n == "0") break
        if(n == n.reversed()) println("yes")
        else println("no")
    }
}