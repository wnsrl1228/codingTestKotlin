package com.codingtest.backjun.classthree.g4


fun main() = with(System.`in`.bufferedReader()) {

    val (N, M) = readLine().split(" ").map { it.toInt() }

    val arr = Array(N) { readLine().split(" ").map { it.toInt() }}
    테트로미노.arr = arr
    // 1, 2, 4, 8, 8
    val tetris1 = arrayOf (
        /**
         * 0 0
         * 0 0
         */
        /**
         * 0 0
         * 0 0
         */
        Pair(arrayOf(1,1,0), arrayOf(0,1,1)),
        /**
         * 0 0 0 0
         */
        /**
         * 0 0 0 0
         */
        Pair(arrayOf(1,2,3), arrayOf(0,0,0)),
        Pair(arrayOf(0,0,0), arrayOf(1,2,3)),
        /**
         *   0      0 0 0       0   0
         * 0 0 0      0       0 0   0 0
         *                      0   0
         */
        /**
         *   0      0 0 0       0   0
         * 0 0 0      0       0 0   0 0
         *                      0   0
         */
        Pair(arrayOf(0,1,-1), arrayOf(-1,0,0)),
        Pair(arrayOf(0,-1,1), arrayOf(1,0,0)),
        Pair(arrayOf(-1,0,0), arrayOf(0,-1,1)),
        Pair(arrayOf(0,1,0), arrayOf(-1,0,1))
    )
    val tetris2 = arrayOf(
        /**
         *   0     0 0 0      0 0         0
         *   0     0            0     0 0 0
         *   0 0                0
         *   x값 -로 바꾸면 반전
         */
        /**
         *   0     0 0 0      0 0         0
         *   0     0            0     0 0 0
         *   0 0                0
         *   x값 -로 바꾸면 반전
         */
        Pair(arrayOf(0,0,1), arrayOf(-1,-2,0)),
        Pair(arrayOf(0,1,2), arrayOf(-1,-1,-1)),
        Pair(arrayOf(1,1,1), arrayOf(0,1,2)),
        Pair(arrayOf(1,2,2), arrayOf(0,0,-1)),
        /**
         *   0       0 0      0         0 0
         *   0 0   0 0        0 0     0 0
         *     0                0
         *   x값 -로 바꾸면 반전
         */
        /**
         *   0       0 0      0         0 0
         *   0 0   0 0        0 0     0 0
         *     0                0
         *   x값 -로 바꾸면 반전
         */
        Pair(arrayOf(0,1,1), arrayOf(-1,0,1)),
        Pair(arrayOf(1,1,2), arrayOf(0,-1,-1)),
        Pair(arrayOf(0,1,1), arrayOf(1,1,2)),
        Pair(arrayOf(1,1,2), arrayOf(0,-1,-1))
    )
    var result = 0
    for (i in 0..<N) {
        for (j in 0..<M) {

            val tResult1 = 테트로미노.check(i, j, tetris1, false, M, N)
            if (tResult1 != 0) {
                result = Math.max(result, tResult1)
            }
            val tResult2 = 테트로미노.check(i, j, tetris2, false, M, N)
            if (tResult2 != 0) {
                result = Math.max(result, tResult2)
            }
            val tResult3 = 테트로미노.check(i, j, tetris2, true, M, N)
            if (tResult3 != 0) {
                result = Math.max(result, tResult3)
            }

        }
    }
    print(result)
}
object 테트로미노 {
    lateinit var arr: Array<List<Int>>
    fun check(y: Int, x: Int, tetris: Array<Pair<Array<Int>, Array<Int>>>, isTwist: Boolean, M: Int, N:Int) : Int{
        var count = 0

        for (tet in tetris) {
            var tCount = arr[y][x]
            val dy = tet.second
            val dx = tet.first
            var isTet = true
            for (i in dy.indices) {
                val nextY = y + dy[i]
                val nextX = if (!isTwist) x + dx[i] else x + dx[i] * -1

                if (nextY < 0 || nextX < 0 || nextX >= M || nextY >= N) {
                    isTet = false
                    break
                }
                tCount += arr[nextY][nextX]
            }
            if (isTet) {
                count = Math.max(count, tCount)
            }
        }
        return count
    }
}

fun 테트로미노_개선된풀이() = with(System.`in`.bufferedReader()) {

    val (N, M) = readLine().split(" ").map { it.toInt() }

    val arr = Array(N) { readLine().split(" ").map { it.toInt() }}
    val visited = Array(N){BooleanArray(M)}

    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)
    var max = 0
    fun dfs(y: Int, x: Int, sum: Int, count: Int) {

        if (count == 4) {
            max = Math.max(max, sum)
            return
        }

        for (i in 0..<4) {
            val nextY = y + dy[i]
            val nextX = x + dx[i]

            if (nextY < 0 || nextX < 0 || nextX >= M || nextY >= N) {
                continue
            }

            if (!visited[nextY][nextX]) {

                if (count == 2) {
                    visited[nextY][nextX] = true
                    dfs(y, x, sum + arr[nextY][nextX], count + 1)
                    visited[nextY][nextX] = false
                }
                visited[nextY][nextX] = true
                dfs(nextY, nextX, sum + arr[nextY][nextX], count + 1)
                visited[nextY][nextX] = false
            }
        }

    }

    for (i in 0..<N) {
        for (j in 0..<M) {
            visited[i][j] = true
            dfs(i, j, arr[i][j], 1)
            visited[i][j] = false
        }
    }
    print(max)

}