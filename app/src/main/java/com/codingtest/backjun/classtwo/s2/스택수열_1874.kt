package com.codingtest.backjun.classtwo.s2

import java.util.Stack

fun main() {
    val n = readln().toInt()
    val arr = IntArray(n) { readln().toInt() }

    val stack = Stack<Int>()
    val sb = StringBuilder()

    var i = 0
    for (num in 1..n) {
        if (num == arr[i]) {
            sb.appendLine("+").appendLine("-")
            i++
        } else {
            while (stack.isNotEmpty() && stack.peek() == arr[i]) {
                sb.appendLine("-")
                stack.pop()
                i++
            }
            sb.appendLine("+")
            stack.add(num)

        }
    }
    var result = true
    while (stack.isNotEmpty() && i < n) {
        val pop = stack.pop()
        if (pop != arr[i]) {
            result = false
            break
        }
        i++
        sb.appendLine("-")
    }
    if (result) print(sb)
    else print("NO")
}

fun 스택수열_개선된코드() {
    val sb = StringBuilder()

    val n = readln().toInt()
    val stack = Stack<Int>()
    var cur = 1

    for (i in 0 until n) {
        val s = readln().toInt()
        while (cur <= s) {
            stack.add(cur++)
            sb.append("+\n")
        }

        if (stack.isEmpty() || stack.peek() > s) {
            print("NO")
            return
        }
        stack.pop()
        sb.append("-\n")
    }
    print(sb)
}
