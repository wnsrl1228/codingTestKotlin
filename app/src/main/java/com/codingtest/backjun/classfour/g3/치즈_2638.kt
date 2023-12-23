package com.codingtest.backjun.classfour.g3

import java.io.StreamTokenizer
import java.util.LinkedList

/**
 * bfs 풀이
 *
 *
 * 1. 가장자리 bfs돌면서 빈 위치에 2를 넣어줌
 * 2. 배열을 돌면서 4방향 중 2의 개수 체크
 *      2의 개수가 2개이상일 경우 사라지는 치즈로 큐에 담아줌
 * 3. 사라지는 치즈를 없애주고 해당 위치에서 bfs돌면서 다시 2채워줌
 * 4. 치즈가 전부 사라질때까지 2랑 3번 반복
 *
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input() // 세로
    val M = input() // 가로

    data class Pos(val y: Int, val x: Int)
    val dx = arrayOf(0,1,0,-1)
    val dy = arrayOf(-1,0,1,0)
    val arr = Array(N) { IntArray(M) {input()} }

    var result = 0

    fun bfs(startY: Int, startX: Int) {
        val queue = LinkedList<Pos> ()
        queue.add(Pos(startY,startX))
        arr[startY][startX] = 2

        while (queue.isNotEmpty()) {
            val pos = queue.poll()

            for (i in dx.indices) {
                val nextY = dy[i] + pos.y
                val nextX = dx[i] + pos.x

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M)
                    continue
                if (arr[nextY][nextX] == 0) {
                    arr[nextY][nextX] = 2
                    queue.add(Pos(nextY, nextX))
                }
            }
        }
    }


    val queue = LinkedList<Pos>()
    queue.add(Pos(0,0))

    while (true) {

        while (queue.isNotEmpty()) {
            val pos = queue.poll()
            bfs(pos.y, pos.x)
        }

        var check = false
        for (i in 1 until N-1) {
            for (j in 1 until M-1) {
                if (arr[i][j] == 1) {
                    check = true
                    var count = 0
                    if (arr[i-1][j] == 2) count++
                    if (arr[i][j-1] == 2) count++
                    if (arr[i+1][j] == 2) count++
                    if (arr[i][j+1] == 2) count++
                    if (count >= 2) {
                        queue.add(Pos(i,j))
                    }
                }
            }
        }
        if (!check) break
        result++
    }

    print(result)

//    치즈_개선된풀이()
}

/**
 * bfs 풀이
 *    방문 횟수 표시로 bfs에서 사라질 치즈를 체크해준다.
 *    따라서 이중 for문으로 사라직 치즈를 따로 찾아주지 않아도 된다.
 */
fun 치즈_개선된풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input() // 세로
    val M = input() // 가로

    data class Pos(val y: Int, val x: Int, val count: Int)
    val dx = arrayOf(0,1,0,-1)
    val dy = arrayOf(-1,0,1,0)
    val arr = Array(N) { IntArray(M) {input()} }
    val visited = Array(N) { IntArray(M) }

    val nextCheese = LinkedList<Pos>()

    fun bfs(startY: Int, startX: Int, count: Int) {
        val queue = LinkedList<Pos> ()
        queue.add(Pos(startY,startX, count))
        visited[startY][startX]++

        while (queue.isNotEmpty()) {
            val pos = queue.poll()

            for (i in dx.indices) {
                val nextY = dy[i] + pos.y
                val nextX = dx[i] + pos.x

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M)
                    continue

                if (arr[pos.y][pos.x] == 0 && arr[nextY][nextX] == 1) {
                    // 이미 큐에 있는 치즈
                    if (visited[nextY][nextX] == 2) {
                        continue
                    }
                    // 두 면이 공기에 닿는 치즈, 큐에 추가
                    else if (visited[nextY][nextX] == 1) {
                        visited[nextY][nextX]++
                        nextCheese.add(Pos(nextY, nextX, pos.count+1))
                    }
                    // 처음으로 공기에 닿는 치즈
                    else {
                        visited[nextY][nextX]++
                    }
                    continue
                }

                // 한 번도 방문하지 않았던 공기인 경우
                if (arr[nextY][nextX] == 0 && visited[nextY][nextX] == 0) {
                    visited[nextY][nextX]++
                    queue.add(Pos(nextY, nextX, pos.count))
                }
            }
        }
    }


    var result = 0
    bfs(0, 0, 0)

    while (nextCheese.isNotEmpty()) {
        val pos = nextCheese.poll()
        visited[pos.y][pos.x] = 1   // 공기 취급
        arr[pos.y][pos.x] = 0       // 공기 취급
        bfs(pos.y, pos.x, pos.count)
        result = pos.count

    }

    print(result)
}