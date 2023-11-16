package com.codingtest.backjun.classthree.g4

import java.lang.StringBuilder
import java.util.LinkedList

/**
 * 예제가 1 16의 경우
 * L -> 10
 * S -> 9
 * S -> 8
 * D -> 16
 * 도 가능하다.
 */
fun main() = with(System.`in`.bufferedReader()){

    val sb = StringBuilder()
    repeat(readLine().toInt()) {
        val (A, B) = readLine().split(" ").map { it.toInt() }
        val arr = Array(10000){StringBuilder()}
        arr[A] = StringBuilder("X")
        val queue = LinkedList<Int>()
        queue.add(A)
        while (queue.isNotEmpty()) {

            val poll = queue.poll()

            if (poll == B) {
                sb.appendLine(arr[poll].deleteAt(0))
                break
            }

            // 1234 -> 2341
            val left = poll / 1000 + (poll % 1000) * 10

            if (arr[left].isEmpty()) {
                arr[left].append(arr[poll]).append("L")
                queue.add(left)
            }
            // 1234 -> 4123
            val right  = poll / 10 + (poll % 10) * 1000

            if (arr[right].isEmpty()) {
                arr[right].append(arr[poll]).append("R")
                queue.add(right)
            }

            val minus = if (poll == 0) 9999 else poll-1

            if (arr[minus].isEmpty()) {
                arr[minus].append(arr[poll]).append("S")
                queue.add(minus)
            }

            val double = poll * 2 % 10000

            if (arr[double].isEmpty()) {
                arr[double].append(arr[poll]).append("D")
                queue.add(double)
            }
        }
    }
    print(sb)
}