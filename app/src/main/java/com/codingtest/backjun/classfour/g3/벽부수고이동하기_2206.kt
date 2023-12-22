package com.codingtest.backjun.classfour.g3

import java.util.LinkedList

/**
 * bfs 풀이로만 가능
 */

/**
 * bfs + dp(?) 풀이
 * bfs 돌면서 이전 이동횟수를 더해줌
 *  - dp는 아래 두 가지로 만들어 줌
 *      1. 파괴하지 않고 오는 경우
 *      2. 파괴해서 오는 경우
 *
 */
fun main() = with(System.`in`.bufferedReader()){

    val (N, M) = readLine().split(" ").map { it.toInt() }

    val arr = Array(N) {
        readLine().map { it.toString().toInt() }.toIntArray()
    }

    val dp = Array(2) {Array(N) {IntArray(M) }}  // 그냥 오는 경우, 파괴해서 온 경우
    dp[0][0][0] = 1

    data class Pos(val y: Int, val x: Int)
    val dx = arrayOf(0,1,0,-1)
    val dy = arrayOf(-1,0,1,0)


    val queue = LinkedList<Pos>()
    queue.add(Pos(0, 0))

    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        val y =cur.y
        val x =cur.x

        for (i in dx.indices) {
            val nextY = dy[i] + y
            val nextX = dx[i] + x

            if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M)
                continue

            // 1 을 만난 경우
            if (arr[nextY][nextX] == 1) {
                // 벽을 최초로 만난 경우
                if (dp[0][y][x] != 0) {
                    // 최초로 지나가는 경로인 경우 또는 더 적은 이동 경로인 경우
                    if (dp[1][nextY][nextX] == 0 || dp[0][y][x]+1 < dp[1][nextY][nextX]) {
                        dp[1][nextY][nextX] = dp[0][y][x] + 1
                        queue.add(Pos(nextY, nextX))
                    }
                }
            } else { // 0 을 만난 경우

                // 벽을 안 뿌시고 온 경우
                if (dp[0][y][x] != 0) {
                    // 최초로 지나가는 경로인 경우 또는 더 적은 이동 경로인 경우
                    if (dp[0][nextY][nextX] == 0 || dp[0][y][x]+1 < dp[0][nextY][nextX]) {
                        dp[0][nextY][nextX] = dp[0][y][x] + 1
                        queue.add(Pos(nextY, nextX))
                    }
                }

                // 벽을 뿌시고 온 경우
                if (dp[1][y][x] != 0) {
                    // 최초로 지나가는 경로인 경우 또는 더 적은 이동 경로인 경우
                    if (dp[1][nextY][nextX] == 0 || dp[1][y][x]+1 < dp[1][nextY][nextX]) {
                        dp[1][nextY][nextX] = dp[1][y][x] + 1
                        queue.add(Pos(nextY, nextX))
                    }
                }
            }
        }
    }

    // 벽을 통과한 경우, 통과하지 않은 경우의 dp값 비교
    if (dp[0][N-1][M-1] == 0 &&  dp[1][N-1][M-1] == 0) {
        print(-1)
    } else if (dp[0][N-1][M-1] != 0 &&  dp[1][N-1][M-1] == 0) {
        print(dp[0][N-1][M-1])
    } else if (dp[0][N-1][M-1] == 0 &&  dp[1][N-1][M-1] != 0) {
        print(dp[1][N-1][M-1])
    } else {
        print(Math.min(dp[1][N-1][M-1], dp[0][N-1][M-1] ))
    }

//    벽부수고이동하기_개선된풀이()

}

/**
 * bfs
 * 0 -> 한 번도 방문하지 않은 곳
 * 1 -> 벽
 * 2 -> 방문했던 곳
 * 3 -> 방문했으면서 벽을 파괴한 적이 있는 곳
 *
 * 2인 경우 bfs이기때문에 다시 들릴 경우가 없지만
 * 3인 경우는 벽을 파괴한 경우이기 때문에 다시 방문할 수 있다.
 */
fun 벽부수고이동하기_개선된풀이() = with(System.`in`.bufferedReader()){

    val (N, M) = readLine().split(" ").map { it.toInt() }

    val arr = Array(N) {
        readLine().toCharArray()
    }

    data class Pos(val y: Int, val x: Int, val check: Boolean)
    val dx = arrayOf(0,1,0,-1)
    val dy = arrayOf(-1,0,1,0)


    val queue = LinkedList<Pos>()
    queue.add(Pos(0, 0, true))
    arr[0][0] = '2'

    var count = 0
    while (queue.isNotEmpty()) {
        count++
        val size = queue.size

        repeat(size) {
            val cur = queue.poll()
            val y =cur.y
            val x =cur.x

            if (y == N-1 && x == M-1) {
                print(count)
                return
            }
            for (i in dx.indices) {
                val nextY = dy[i] + y
                val nextX = dx[i] + x

                // 경로 밖이거나 이미 방문했던 곳인 경우
                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M || arr[nextY][nextX] == '2')
                    continue

                // 이전에 파괴했던 경로이면서 현재 경로가 파괴를 한 뒤인 경우
                if (arr[nextY][nextX] == '3' && !cur.check) {
                    continue
                }
                if (arr[nextY][nextX] == '1') {
                    // 이미 벽을 통과한 경우
                    if (!cur.check) {
                        continue
                    } else {
                        queue.add(Pos(nextY, nextX, false))
                    }
                } else {
                    queue.add(Pos(nextY, nextX, cur.check))
                    arr[nextY][nextX] = if (cur.check) '2' else '3'
                }

            }
        }
    }
    print(-1)
}