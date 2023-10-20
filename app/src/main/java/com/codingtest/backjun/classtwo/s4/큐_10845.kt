package com.codingtest.backjun.classtwo.s4

fun main() {
    val queue = ArrayDeque<Int>()
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val command = readln().split(" ")

        when(command[0]) {
            "push" -> queue.add(command[1].toInt())
            "pop" -> if (queue.isEmpty()) sb.appendLine(-1) else sb.appendLine(queue.removeFirst())
            "front" -> if (queue.isEmpty()) sb.appendLine(-1) else sb.appendLine(queue.first())
            "back" -> if (queue.isEmpty()) sb.appendLine(-1) else sb.appendLine(queue.last())
            "size" -> sb.appendLine(queue.size)
            "empty" -> if (queue.isEmpty()) sb.appendLine(1) else sb.appendLine(0)
        }
    }
    print(sb)
}