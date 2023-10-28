package com.codingtest.backjun.classthree.s2

val dx = intArrayOf(-1, 1, 0, 0)
val dy = intArrayOf(0, 0, -1, 1)
fun main() = with(System.`in`.bufferedReader()){
    repeat(readLine().toInt()) {
        val (M, N, K) = readLine().split(" ").map { it.toInt() }
        val arr = Array(N) {IntArray(M)}
        repeat(K) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            arr[y][x] = 1
        }

        var count = 0
        for (i in 0 until N) {
            for (j in 0 until M) {
                if (arr[i][j] == 1) {
                    dfs(i, j, arr)
                    count++
                }
            }
        }
        println(count)
    }
}

fun dfs(y: Int, x: Int, arr: Array<IntArray>) {

    arr[y][x] = 0

    for (i in dx.indices) {
        val nextY = y + dy[i]
        val nextX = x + dx[i]

        if (nextY < 0 || nextY >= arr.size || nextX < 0 || nextX >= arr[0].size
            || arr[nextY][nextX] == 0) {
            continue
        }
        dfs(nextY, nextX, arr)
    }
}
