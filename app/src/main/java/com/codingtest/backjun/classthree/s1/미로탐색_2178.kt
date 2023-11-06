package com.codingtest.backjun.classthree.s1

import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()){

    val (N, M) = readln().split(" ").map { it.toInt() }
    val arr = Array(N) {IntArray(M)}
    repeat(N) { i ->
        readln().forEachIndexed { j, s ->
            arr[i][j] = s.toString().toInt()
        }
    }

    fun bfs(): Int{
        var result = 1
        val dx = arrayOf(0,1,0,-1)
        val dy = arrayOf(-1,0,1,0)
        val visited = Array(N) {BooleanArray(M)}
        val queue = ArrayDeque<Pair<Int, Int>>() // (y,x)
        queue.add(Pair(0,0))
        visited[0][0] = true

        while (queue.isNotEmpty()) {

            repeat(queue.size) {
                val poll = queue.removeFirst()
                if (poll.first == N-1 && poll.second == M-1) {
                    return result
                }
                for (i in dx.indices) {
                    val nextX = poll.second + dx[i]
                    val nextY = poll.first + dy[i]

                    if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N)
                        continue
                    if (arr[nextY][nextX] == 0 || visited[nextY][nextX])
                        continue

                    visited[nextY][nextX] = true
                    queue.add(Pair(nextY, nextX))
                }
            }
            result++
        }
        return result
    }

    print(bfs())
}

fun 미로탐색_개선된풀이() = with(System.`in`.bufferedReader()){

    val (N, M) = readln().split(" ").map { it.toInt() }
    val arr = Array(N) {IntArray(M)}
    repeat(N) { i ->
        readln().forEachIndexed { j, s ->
            arr[i][j] = s.toString().toInt()
        }
    }
    data class Node(val y: Int, val x: Int, val count: Int)
    fun bfs(): Int{
        val dx = arrayOf(0,1,0,-1)
        val dy = arrayOf(-1,0,1,0)
        val queue = LinkedList<Node>() // (y,x)
        queue.add(Node(0,0, 1))

        while (queue.isNotEmpty()) {

            val poll = queue.removeFirst()
            if (poll.y == N-1 && poll.x == M-1) {
                return poll.count
            }
            for (i in dx.indices) {
                val nextX = poll.x + dx[i]
                val nextY = poll.y + dy[i]

                if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N)
                    continue
                if (arr[nextY][nextX] == 0)
                    continue

                arr[nextY][nextX] = 0
                queue.add(Node(nextY, nextX, poll.count+1))
            }
        }
        return 0
    }

    print(bfs())
}