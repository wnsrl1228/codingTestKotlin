package com.codingtest.backjun.classfive.g2


/**
 * 그래프 문제
 *   - 0 지역에 대해 dfs를 돌면서 0의 개수와 근처 벽을 체크해줌
 *   - dfs가 끝나면 체크해줬던 벽에 0의 개수를 추가해줌
 */
fun main() = with(System.`in`.bufferedReader()){

    val (N, M) = readLine().split(" ").map{ it.toInt() }

    val arr = Array(N) {
        val line = readLine()
        IntArray(M) { i ->
            line[i] - '0'
        }
    }
    val visited = Array(N) { BooleanArray(M)}

    data class Pos(val y: Int, val x: Int)
    val dx = arrayOf(0, 1, 0, -1)
    val dy = arrayOf(-1, 0, 1, 0)


    var oneTempArr = ArrayDeque<Pos>()
    var zeroCount = 0
    fun dfs(y: Int, x: Int) {

        visited[y][x] = true
        zeroCount++

        for (i in dx.indices) {
            val nextY = y + dy[i]
            val nextX = x + dx[i]

            if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M)
                continue

            if (visited[nextY][nextX])
                continue

            if (arr[nextY][nextX] == 0) {
                dfs(nextY, nextX)
            } else if (arr[nextY][nextX] >= 1) {
                oneTempArr.add(Pos(nextY, nextX))
                visited[nextY][nextX] = true
            }
        }
    }


    for (i in 0 until N) {
        for (j in 0 until M) {
            if (arr[i][j] == 0 && !visited[i][j]) {
                oneTempArr = ArrayDeque()
                zeroCount = 0
                dfs(i, j)

                for (i in oneTempArr) {
                    arr[i.y][i.x] += zeroCount
                    visited[i.y][i.x] = false
                }
            }
        }
    }

    val sb = StringBuilder()
    for (i in 0 until N) {
        for (j in 0 until M) {
            sb.append(arr[i][j] % 10)
        }
        sb.appendLine()
    }
    print(sb)
//    벽부수고이동하기_다른풀이()
}

/**
 *  그래프 문제 (좀 더 빠름)
 *    - 0 지역에 대해 bfs를 돌면서 그룹을 지정해주면서 동시에 그룹 인원을 체크해준다
 *    - 1의 상하좌우의 그룹을 확인해주고 각 그룹에 대한 인원을 더해준다
 *    - 중복된 그룹은 set으로 처리해준다
 */
fun 벽부수고이동하기_다른풀이() = with(System.`in`.bufferedReader()){
    val (N, M) = readLine().split(" ").map{ it.toInt() }

    val resultArr = Array(N) {IntArray(M)}
    val arr = Array(N) { i ->
        val line = readLine()
        IntArray(M) { j ->
            resultArr[i][j] = line[j]- '0'
            resultArr[i][j]
        }
    }
    val groupMap = hashMapOf<Int, Int>() // 그룹, 그룹 인원
    val visited = Array(N) { BooleanArray(M)}

    data class Pos(val y: Int, val x: Int)
    val dx = arrayOf(0, 1, 0, -1)
    val dy = arrayOf(-1, 0, 1, 0)

    var group = 2

    fun bfs(startY: Int, startX: Int) : Int {

        var count = 1
        visited[startY][startX] = true
        val queue = ArrayDeque<Pos>()
        queue.add(Pos(startY, startX))

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()

            arr[cur.y][cur.x] = group
            for (i in dx.indices) {
                val nextY = cur.y + dy[i]
                val nextX = cur.x + dx[i]

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M)
                    continue

                if (visited[nextY][nextX])
                    continue

                if (arr[nextY][nextX] == 0) {
                    visited[nextY][nextX] = true
                    queue.add(Pos(nextY, nextX))
                    count++
                }
            }
        }
        return count
    }


    for (i in 0 until N) {
        for (j in 0 until M) {
            if (arr[i][j] == 0 && !visited[i][j]) {
                groupMap.put(group, bfs(i, j))
                group++
            }
        }
    }


    for (y in 0 until N) {
        for (x in 0 until M) {
            if (resultArr[y][x] == 1) {
                val set = hashSetOf<Int>()
                for (i in dx.indices) {
                    val nextY = y + dy[i]
                    val nextX = x + dx[i]

                    if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M)
                        continue

                    if (arr[nextY][nextX] >= 2) {
                        set.add(arr[nextY][nextX])
                    }
                }

                for (s in set) {
                    resultArr[y][x] += groupMap[s]!!
                }
            }
        }
    }

    val sb = StringBuilder()
    for (i in 0 until N) {
        for (j in 0 until M) {
            sb.append(resultArr[i][j] % 10)
        }
        sb.appendLine()
    }
    print(sb)
}
