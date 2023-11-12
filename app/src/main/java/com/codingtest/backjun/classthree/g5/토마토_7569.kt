package com.codingtest.backjun.classthree.g5

import java.io.StreamTokenizer
import java.util.LinkedList

/**
 * 내가 푼 방식은 전체 농장을 돌면서 익은 토마토가 있을 때 마다 bfs 돌림
 * 개선된 풀이 방식은 처음 입력값을 받을 때 익은 토마토를 큐에 바로 담아서 익은 토마토만 바로 bfs 돌림
 */

data class Node(val z: Int, val y: Int, val x: Int)
val dx = listOf(0, 1, 0, -1, 0, 0)
val dy = listOf(-1, 0, 1, 0, 0, 0)
val dz = listOf(0, 0, 0, 0, 1, -1)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val M = input() // 가로
    val N = input() // 세로
    val H = input() // 높이

    val arr = Array(H) {Array(N) {IntArray(M)} }
    repeat(H) { h ->
        repeat(N) { n ->
            repeat(M) { m ->
                arr[h][n][m] = input()
            }
        }
    }
    
    for(i in 0 until H) {
        for (j in 0 until N) {
            for (l in 0 until M) {
                if (arr[i][j][l] == 1) {
                    bfs(i, j, l, arr)
                }
            }
        }
    }

    print(getMaxNum(arr))
}

fun bfs(z: Int, y: Int, x: Int, arr: Array<Array<IntArray>>) {
    val queue = LinkedList<Node>()
    queue.add(Node(z,y,x))
    var number = 2
    while (queue.isNotEmpty()) {

        repeat(queue.size) {
            val poll = queue.poll()
            for (d in dx.indices) {
                val nextX = poll.x + dx[d]
                val nextY = poll.y + dy[d]
                val nextZ = poll.z + dz[d]

                if (nextX < 0 || nextY < 0 || nextZ < 0 ||
                    nextX >= arr[0][0].size || nextY >= arr[0].size || nextZ >= arr.size) 
                    continue

                if (arr[nextZ][nextY][nextX] == 0 || arr[nextZ][nextY][nextX] > number) {
                    arr[nextZ][nextY][nextX] = number
                    queue.add(Node(nextZ, nextY, nextX))
                }
            }
        }
        number++
    }
}

fun getMaxNum(arr: Array<Array<IntArray>>): Int {
    var max = 0
    for(i in 0 until arr.size) {
        for (j in 0 until arr[0].size) {
            for (l in 0 until arr[0][0].size) {
                if (arr[i][j][l] == 0) return -1
                max = Math.max(max, arr[i][j][l])
            }
        }
    }
    return max - 1
}

fun 토마토_개선풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val M = input() // 가로
    val N = input() // 세로
    val H = input() // 높이

    val arr = Array(H) {Array(N) {IntArray(M)} }
    var zero = 0
    val queue = LinkedList<Node>()
    repeat(H) { h ->
        repeat(N) { n ->
            repeat(M) { m ->
                input().also {
                    if (it == 0) {
                        zero++
                    } else if (it == 1) {
                        queue.add(Node(h,n,m))
                    }
                    arr[h][n][m] = it
                }
            }
        }
    }

    if (zero == 0) {
        print(0)
        return@run
    }

    var date = -1
    while (queue.isNotEmpty()) {
        date++
        repeat(queue.size) {
            val poll = queue.poll()
            for (d in dx.indices) {
                val nextX = poll.x + dx[d]
                val nextY = poll.y + dy[d]
                val nextZ = poll.z + dz[d]

                if (nextX < 0 || nextY < 0 || nextZ < 0 ||
                    nextX >= arr[0][0].size || nextY >= arr[0].size || nextZ >= arr.size)
                    continue

                if (arr[nextZ][nextY][nextX] == 0) {
                    zero--
                    arr[nextZ][nextY][nextX] = 1
                    queue.add(Node(nextZ, nextY, nextX))
                }
            }
        }
    }

    if (zero == 0) print(date)
    else print(-1)
}