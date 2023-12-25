package com.codingtest.backjun.classfour.g3

import java.io.StreamTokenizer
import java.util.LinkedList
import java.util.PriorityQueue

/**
 * bfs 응용 문제
 *    - bfs로 먹이위치를 찾는 것을 반복한다.
 *
 * 문제 주의점
 *      - 동일한 거리일 때 먹이의 우선순위가 단지 상좌우하 순서로 탐색된다고 해결되지 않는다.
 *        따라서 우선순위 큐를 통해 완전탐색을 해줘야 한다.
 *
 *      ex) 상좌우하일 경우 탐색 순서로 9번째 위치가 10번째 위치보다 먼저 실행되면 안 된다. (반례)
 *              5
 *           6  1  7
 *        8  2  0  3  10
 *           9  4  11
 *              12
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    data class Pos(val y: Int, val x: Int)
    val dx = arrayOf(0,-1,1,0)
    val dy = arrayOf(-1,0,0,1)

    val N = input()
    var start = Pos(0,0)

    val arr = Array(N) { y ->
        IntArray(N) { x ->
            val a =input()
            if (a == 9) start = Pos(y, x)
            a
        }
    }

    var feedSize = 2
    var ateFeed = 0
    var time = 0

    fun bfs(startY: Int, startX: Int): Pos? {

        val visited = Array(N) {BooleanArray(N)}
        val pq = LinkedList<Pos>()
        val pq2 = PriorityQueue<Pos>(compareBy({ it.y }, { it.x }))

        arr[startY][startX] = 0
        visited[startY][startX] = true
        pq.add(Pos(startY, startX))

        var temp = 0
        while (pq.isNotEmpty()) {

            val size = pq.size
            repeat(size) {

                val cur = pq.poll()

                for (i in dy.indices) {
                    val nextY = dy[i] + cur.y
                    val nextX = dx[i] + cur.x

                    if (nextY !in 0..<N || nextX !in 0..<N)
                        continue
                    if (visited[nextY][nextX] || arr[nextY][nextX] > feedSize)
                        continue

                    // 이동만 가능한 경우
                    if (arr[nextY][nextX] == 0 || arr[nextY][nextX] == feedSize) {
                        visited[nextY][nextX] = true
                        pq.add(Pos(nextY, nextX))
                        continue
                    }
                    // 먹이인 경우
                    if (arr[nextY][nextX] < feedSize) {
                        visited[nextY][nextX] = true
                        pq2.add(Pos(nextY, nextX))
                    }
                }
            }
            temp++
            // 먹이를 찾은 경우, 가장 우선순위가 높은 좌표로 이동한다.
            if (pq2.isNotEmpty()) {
                time += temp
                return pq2.poll()
            }
        }
        return null
    }

    // 먹이가 없을떄까지 bfs를 돌면서 먹이를 찾는다
    while (true) {
        start = bfs(start.y , start.x) ?: break
        ateFeed++
        if (ateFeed == feedSize) {
            ateFeed = 0
            feedSize++
        }
    }

    print(time)
//    아기상어_다른풀이()
}

/**
 * bfs 풀이
 *      - 아예 Pos클래스에 거리값까지 넣어준다.
 */
fun 아기상어_다른풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    data class Pos(val y: Int, val x: Int, val dist: Int)
    val dx = arrayOf(0,-1,1,0)
    val dy = arrayOf(-1,0,0,1)

    val N = input()
    var start = Pos(0,0, 0)

    val arr = Array(N) { y ->
        IntArray(N) { x ->
            val a =input()
            if (a == 9) {
                start = Pos(y, x, 0)
                0
            } else {
                a
            }
        }
    }

    var feedSize = 2
    var ateFeed = 0
    var time = 0

    fun bfs(startY: Int, startX: Int) {

        var visited = Array(N) {BooleanArray(N)}
        val pq = PriorityQueue<Pos>(compareBy({ it.dist },{ it.y }, { it.x }))

        visited[startY][startX] = true
        pq.add(Pos(startY, startX, 0))

        while (pq.isNotEmpty()) {

            val cur = pq.poll()

            // 주변 탐색
            for (i in dy.indices) {
                val nextY = dy[i] + cur.y
                val nextX = dx[i] + cur.x

                if (nextY !in 0..<N || nextX !in 0..<N)
                    continue
                if (visited[nextY][nextX])
                    continue
                visited[nextY][nextX] = true

                if (arr[nextY][nextX] <= feedSize) {
                    pq.add(Pos(nextY, nextX, cur.dist+1))
                }
            }

            if(pq.isNotEmpty()) {
                var peekSh = pq.peek()

                // 먹이가 있는 경우
                if(arr[peekSh.y][peekSh.x] in 1..<feedSize) {
                    ateFeed++
                    if(ateFeed == feedSize) {
                        feedSize++
                        ateFeed = 0
                    }
                    arr[peekSh.y][peekSh.x] = 0

                    pq.clear()
                    pq.offer(Pos(peekSh.y, peekSh.x, 0))
                    time += peekSh.dist
                    visited = Array(N){BooleanArray(N)}
                    visited[peekSh.y][peekSh.x] = true
                }
            }
        }
    }

    bfs(start.y , start.x)
    print(time)
}