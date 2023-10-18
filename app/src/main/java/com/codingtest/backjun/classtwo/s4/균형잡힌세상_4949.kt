package com.codingtest.backjun.classtwo.s4

import java.util.Stack

fun main() {

    val sb = StringBuilder()
    while (true) {
        val str = readln()
        if (str == ".") break

        if (isBalanced(str)) sb.append("yes\n")
        else sb.append("no\n")
    }
    print(sb)
}

fun isBalanced(str: String) : Boolean {

    val stack = Stack<Char>()

    for (c in str) {
        if (c in "([") {
            stack.add(c)
        } else if (c in ")]") {
            if (stack.isEmpty()) return false
            val p = stack.pop()
            if ( (p == '(' && c == ']') || (p == '[' && c == ')') ) return false
        }
    }
    if (stack.isEmpty()) return true
    return false
}