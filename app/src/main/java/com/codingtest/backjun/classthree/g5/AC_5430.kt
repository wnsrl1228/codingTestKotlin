package com.codingtest.backjun.classthree.g5

fun main() = with(System.`in`.bufferedReader()){
    val sb = StringBuilder()
    repeat(readLine().toInt()) {
        val command = readLine()
        val len = readLine().toInt()
        val arr = readLine().drop(1).dropLast(1).split(",").toMutableList()
        if (arr[0] == "") {
            arr.clear()
        }

        var isR = false
        var isE = false
        for (c in command) {
            if (arr.isEmpty() && c == 'D') {
                isE = true
                sb.appendLine("error")
                break
            }

            if (c == 'R') {
                isR = !isR
            } else {
                if (isR) arr.removeLast()
                else arr.removeFirst()
            }
        }

        if (!isE) {
            if (isR) {
                sb.appendLine(arr.reversed().joinToString(",", prefix = "[", postfix = "]"))
            } else {
                sb.appendLine(arr.joinToString(",", prefix = "[", postfix = "]"))
            }
        }
    }
    print(sb)
}
// 더 빠름
fun AC_슬라이스_풀이() = with(System.`in`.bufferedReader()){

    repeat(readLine().toInt()) {
        val command = readLine()
        var len = readLine().toInt()
        val arr = readLine().drop(1).dropLast(1).split(",")

        var isR = false
        var left = 0
        var last = arr.size

        command.forEach {
            if (it == 'R') {
                isR = !isR
            } else {
                len--
                if (left == last || len < 0) {
                    println("error")
                    return@repeat
                }

                if (!isR) {
                    left++
                } else {
                    last--
                }
            }
        }

        if (!isR) {
            println("[${arr.slice(left until last).joinToString(",")}]")
        } else {
            println("[${arr.slice(left until last).reversed().joinToString(",")}]")
        }
    }
}