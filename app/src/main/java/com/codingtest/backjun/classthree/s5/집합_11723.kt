package com.codingtest.backjun.classthree.s5

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    var set = mutableSetOf<Int>()
    repeat(readLine().toInt()) {
        val command = readLine().split(" ")
        when (command[0]) {
            "add" -> set.add(command[1].toInt())
            "remove" -> set.remove(command[1].toInt())
            "check" -> if (set.contains(command[1].toInt())) sb.appendLine("1") else sb.appendLine("0")
            "toggle" -> if (set.contains(command[1].toInt())) set.remove(command[1].toInt()) else set.add(command[1].toInt())
            "all" -> set = (1..20).toMutableSet()
            "empty" -> set = mutableSetOf()
        }
    }
    print(sb)
}