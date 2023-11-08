package com.codingtest.backjun.classthree.s1

import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()){
    val pos = PriorityQueue<Int>()
    val neg = PriorityQueue<Int>(reverseOrder())
    val sb = StringBuilder()
    repeat(readLine().toInt()) {
        val n = readLine().toInt()

        if (n != 0) {
            if (n > 0) {
                pos.add(n)
            } else {
                neg.add(n)
            }
        } else {
            if (pos.isEmpty() && neg.isEmpty()) {
                sb.appendLine(0)
            } else if (pos.isEmpty() && neg.isNotEmpty()) {
                sb.appendLine(neg.poll())
            } else if (pos.isNotEmpty() && neg.isEmpty()) {
                sb.appendLine(pos.poll())
            } else {
                if (pos.peek() < Math.abs(neg.peek())) {
                    sb.appendLine(pos.poll())
                } else {
                    sb.appendLine(neg.poll())
                }
            }
        }
    }
    print(sb)
}

/**
 * 음수 : 우선순위 힙에서 큰 수가 루트
 * 양수 : 우선순위 힙에서 작은 수가 루트
 */