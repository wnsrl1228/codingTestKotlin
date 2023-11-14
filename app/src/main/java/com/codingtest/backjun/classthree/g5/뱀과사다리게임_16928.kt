package com.codingtest.backjun.classthree.g5

import java.io.StreamTokenizer
import java.util.LinkedList

/**
 * bfs
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input() // 사다리 수
    val M = input() // 뱀의 수

    val arr = IntArray(101)

    repeat(N) {
        arr[input()] = input()
    }

    repeat(M) {
        arr[input()] = input()
    }


    fun bfs(): Int {
        var result = 0
        val queue = LinkedList<Int>()
        queue.add(1)
        arr[1] = -1
        while (queue.isNotEmpty()) {

            repeat(queue.size) {
                val poll = queue.poll()

                for (i in 1..6) {
                    val next = poll + i

                    if (next == 100) return result+1

                    if (next > 100 || arr[next] == -1)
                        continue
                    if (arr[next] != 0) {
                        queue.add(arr[next]) // next= 32 arr[next] = 62
                        arr[arr[next]] -1
                        arr[next] = -1
                    } else {
                        queue.add(next)
                        arr[next] = -1
                    }
                }
            }
            result++
        }
        return result
    }
    print(bfs())
}
/**
 * 사다리만을 우선시하는 경우 반례
 * 3 10
 * 7 100
 *
 *
 * 사다리가 더 높은 곳에 있는 경우를 우선시 할 경우의 반례
 * 2 56
 * 4 30
 * 31 100
 *
 * 뱀은 무시하고 무조건 사다리만 이용하는 경우의 반례
 * 2 56
 * 57 36
 * 58 80
 * 37 100
 */