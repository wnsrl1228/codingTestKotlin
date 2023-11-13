package com.codingtest.backjun.classthree.g5

import java.io.StreamTokenizer
import java.util.LinkedList

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val M = input()
    val N = input()

    data class Node(val y: Int, val x: Int)
    val dx = arrayOf(0, 1, 0, -1)
    val dy = arrayOf(-1, 0, 1, 0)
    val queue = LinkedList<Node>()
    var zero = 0

    val arr = Array(N) { h ->
        IntArray(M) { w ->
            input().also {
                if (it == 0) {
                    zero++
                } else if (it == 1) {
                    queue.add(Node(h,w))
                }
            }
        }
    }

    if (zero == 0) {
        print(0)
        return@run
    }

    var count = -1
    while (queue.isNotEmpty()) {

        count++
        repeat(queue.size) {
            val poll = queue.poll()

            for (i in dx.indices) {
                val nextX = poll.x + dx[i]
                val nextY = poll.y + dy[i]

                if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
                    continue
                }
                if (arr[nextY][nextX] == 0) {
                    queue.add(Node(nextY, nextX))
                    arr[nextY][nextX] = 1
                    zero--
                }
            }
        }
    }
    print(if(zero == 0) count else -1)
}