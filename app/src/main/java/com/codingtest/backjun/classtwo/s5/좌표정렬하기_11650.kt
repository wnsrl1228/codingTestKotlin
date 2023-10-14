package com.codingtest.backjun.classtwo.s5

fun main() {
    val sb = StringBuilder("")
    List(readln().toInt()) {
        readln().split(" ").map { it.toInt() }
    }.sortedWith(compareBy({it[0]}, {it[1]})).forEach { n ->
        sb.append("${n[0]} ${n[1]}\n")
    }
    print(sb)
}