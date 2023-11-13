package com.codingtest.backjun.classthree.g5

import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()){

    val N = readLine().toInt()

    val arr = Array(N) { readLine() }

    val visited = Array(N) {BooleanArray(N)}
    val visitedRedGreen = Array(N) {BooleanArray(N)}

    var count1 = 0
    var count2 = 0

    for (row in 0 until N) {
        for (col in 0 until N) {
            if (!visited[row][col]) {
                적록색약.bfs(row, col, visited) { nextY, nextX ->
                    !visited[nextY][nextX] && arr[nextY][nextX] == arr[row][col]
                }
                count1++
            }

            if (!visitedRedGreen[row][col]) {
                if (arr[row][col] == 'B') {
                    적록색약.bfs(row, col, visitedRedGreen) { nextY, nextX ->
                        !visitedRedGreen[nextY][nextX] && arr[nextY][nextX] == 'B'
                    }
                } else {
                    적록색약.bfs(row, col, visitedRedGreen) { nextY, nextX ->
                        !visitedRedGreen[nextY][nextX] && arr[nextY][nextX] in setOf('R', 'G')
                    }
                }
                count2++
            }
        }
    }
    print("$count1 $count2")
}

object 적록색약 {

    data class Node(val y: Int, val x: Int)
    val dx = arrayOf(0, 1, 0, -1)
    val dy = arrayOf(-1, 0, 1, 0)

    fun bfs(y: Int, x: Int, visited: Array<BooleanArray>, condition: (Int, Int) -> Boolean) {

        val queue = LinkedList<Node>()
        queue.add(Node(y,x))

        visited[y][x] = true

        while (queue.isNotEmpty()) {
            val poll = queue.poll()

            for (i in dx.indices) {
                val nextX = poll.x + dx[i]
                val nextY = poll.y + dy[i]

                if (nextX < 0 || nextY < 0 || nextX >= visited.size || nextY >= visited.size) {
                    continue
                }
                if (condition(nextY, nextX)) {
                    queue.add(Node(nextY, nextX))
                    visited[nextY][nextX] = true
                }
            }
        }
    }
}