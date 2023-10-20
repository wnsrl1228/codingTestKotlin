package com.codingtest.backjun.classtwo.s4

fun main() {
    val deque = ArrayDeque<Int>()
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val command = readln().split(" ")

        when(command[0]) {
            "push_front" -> deque.addFirst(command[1].toInt())
            "push_back" -> deque.addLast(command[1].toInt())
            "pop_front" -> if (deque.isEmpty()) sb.appendLine(-1) else sb.appendLine(deque.removeFirst())
            "pop_back" -> if (deque.isEmpty()) sb.appendLine(-1) else sb.appendLine(deque.removeLast())
            "front" -> if (deque.isEmpty()) sb.appendLine(-1) else sb.appendLine(deque.first())
            "back" -> if (deque.isEmpty()) sb.appendLine(-1) else sb.appendLine(deque.last())
            "size" -> sb.appendLine(deque.size)
            "empty" -> if (deque.isEmpty()) sb.appendLine(1) else sb.appendLine(0)
        }
    }
    print(sb)
}