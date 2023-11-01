package com.codingtest.backjun.classthree.s2

import java.util.Collections
import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()){
    val pq = PriorityQueue<Int>(Collections.reverseOrder())
    val sb = StringBuilder()
    repeat(readLine().toInt()) {
        val a = readLine().toInt()
        if (a == 0) {
            if (pq.isEmpty()) sb.appendLine("0")
            else sb.appendLine(pq.poll().toString())
        } else {
            pq.add(a)
        }
    }
    print(sb)
}