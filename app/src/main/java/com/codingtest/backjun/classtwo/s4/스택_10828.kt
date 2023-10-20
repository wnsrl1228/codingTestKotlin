package com.codingtest.backjun.classtwo.s4

fun main() {
    val stack = ArrayDeque<Int>()
    val sb = StringBuilder()
    repeat(readln().toInt()) { 
        val command = readln().split(" ")

        when(command[0]) {
            "push" -> stack.add(command[1].toInt())
            "pop" -> if (stack.isEmpty()) sb.appendLine(-1) else sb.appendLine(stack.removeLast())
            "top" -> if (stack.isEmpty()) sb.appendLine(-1) else sb.appendLine(stack.last())
            "size" -> sb.appendLine(stack.size)
            "empty" -> if (stack.isEmpty()) sb.appendLine(1) else sb.appendLine(0)
        }
    }
    print(sb)
}