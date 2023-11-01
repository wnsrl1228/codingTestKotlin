package com.codingtest.backjun.classthree.s2
/**
 * bfs가 dfs보다 더 빠르다.
 */
fun main() = with(System.`in`.bufferedReader()){
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val arr = Array(N) {Array(M){' '} }
    repeat(N) {
        val str = readLine()
        for (i in str.indices) {
            arr[it][i] = str[i]
        }
    }

    val dx = arrayOf(0,1,0,-1)
    val dy = arrayOf(-1,0,1,0)
    val visited = Array(N) { BooleanArray(M) }
    var count = 0
    fun dfs(y: Int, x: Int) {
        visited[y][x] =true

        if (arr[y][x] == 'P') count++

        for (i in dx.indices) {
            val nextX = x + dx[i]
            val nextY = y + dy[i]

            if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N)
                continue
            if (visited[nextY][nextX] || arr[nextY][nextX] == 'X')
                continue
            dfs(nextY, nextX)
        }
    }
    fun findFriend(){
        for (i in 0 until N) {
            for (j in 0 until M) {
                if (arr[i][j] == 'I') {
                    dfs(i, j)
                    return
                }
            }
        }
    }
    findFriend()
    if (count == 0) print("TT")
    else print(count)
}

fun 헌내기_bfs풀이() = with(System.`in`.bufferedReader()){
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val arr = Array(N) {Array(M){' '} }
    repeat(N) {
        val str = readLine()
        for (i in str.indices) {
            arr[it][i] = str[i]
        }
    }

    val dx = arrayOf(0,1,0,-1)
    val dy = arrayOf(-1,0,1,0)
    val visited = Array(N) { BooleanArray(M) }
    var count = 0
    data class Point(val y: Int, val x: Int)

    fun bfs(y: Int, x: Int) {
        visited[y][x] =true
        val queue = ArrayDeque<Point>()
        queue.add(Point(y, x))

        while (queue.isNotEmpty()) {
            val poll = queue.removeFirst()

            for (i in dx.indices) {
                val nextX = poll.x + dx[i]
                val nextY = poll.y + dy[i]

                if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N)
                    continue
                if (visited[nextY][nextX] || arr[nextY][nextX] == 'X')
                    continue

                if (arr[nextY][nextX] == 'P') count++
                visited[nextY][nextX] = true
                queue.add(Point(nextY, nextX))
            }
        }
    }

    fun findFriend(){
        for (i in 0 until N) {
            for (j in 0 until M) {
                if (arr[i][j] == 'I') {
                    bfs(i, j)
                    return
                }
            }
        }
    }

    findFriend()
    if (count == 0) print("TT")
    else print(count)
}