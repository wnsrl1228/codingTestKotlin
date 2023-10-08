package com.codingtest.backjun.classtwo.b

fun main() {
    readln()
    print(readln().split(" ").count { i -> isPrime(i.toInt()) })
}
fun isPrime(num: Int) : Boolean {
    if (num <= 1) return false

    for (i in 2..Math.floor(Math.sqrt(num.toDouble())).toInt()) {
        if (num%i == 0) return false
    }
    return true
}