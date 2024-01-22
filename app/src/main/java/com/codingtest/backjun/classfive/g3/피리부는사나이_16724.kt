package com.codingtest.backjun.classfive.g3

/**
 * dfs 응용 - 텀 프로젝트_9466 문제와 거의 유사
 *
 *      - 회원을 방향대로 움직였을 때 생길 수 있는 상황
 *           1. safe-zone을 만나는 경우
 *              - 새로 safe-zone을 만들어 줄 필요가 없다.
 *           2. safe-zone을 만나지 못하는 경우 = 이미 방문한 지역을 다시 방문하는 경우 = 사이클이 생기는 경우
 *              - 새로 safe-zone을 만들어 준다.
 *      - 한 번 탐색한 지역은 다시 탐색하지 않는다. O(n)
 */
fun main() {

    val (N, M) = readln().split(" ").map { it.toInt() }

    data class Pos(val y: Int, val x: Int)
    val command = hashMapOf<Char, Pos>(
        'U' to Pos(-1, 0),
        'D' to Pos(1, 0),
        'L' to Pos(0, -1),
        'R' to Pos(0, 1),
    )
    val arr = Array(N) { readln().toCharArray() }
    val finish = Array(N) {BooleanArray(M)}    // 이미 safeZone이 있는 경우
    val visited = Array(N) {BooleanArray(M)}   // 현재 dfs 방문여부, dfs가 끝나면 finish 처리
    var safeZone = 0

    fun dfs(y: Int, x: Int) {

        // 이미 safe-zone이 있는 경우
        if (finish[y][x]) return

        // safeZone을 만나지 않고 사이클이 생기는 경우
        // safeZone 필요
        if (visited[y][x]) {
            safeZone++
            return
        }

        val nextY = y + command[arr[y][x]]!!.y
        val nextX = x + command[arr[y][x]]!!.x

        visited[y][x] = true
        dfs(nextY, nextX)
        finish[y][x] = true
    }

    for (y in 0 until N) {
        for (x in 0 until M) {
            if (finish[y][x]) continue
            dfs(y, x)
        }
    }

    print(safeZone)
}
/**
 * 1,000,000
 */