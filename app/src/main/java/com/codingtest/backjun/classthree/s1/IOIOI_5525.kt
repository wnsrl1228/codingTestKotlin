package com.codingtest.backjun.classthree.s1

fun main() {
    val N = readln().toInt()
    val M = readln().toInt()

    val pLen = N + 1 + N
    var result = 0
    var check = false
    var count = 0

    val str = readln()

    for (i in str.indices) {
        val c = str[i]
        if (c == 'I' && check) {
            if (str[i-1] == c) {
                count = 1
                continue
            }
            count++
            if (count == pLen) {
                result++
                count -= 2
            }
        } else if (c == 'I' && !check) {
            check = true
            count++
        } else if (c == 'O' && check) {
            if (str[i-1] == c) {
                check = false
                count = 0
                continue
            }
            count++
        } else if (c == 'O' && !check) {
            continue
        }
    }

    print(result)
}
