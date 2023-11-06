package com.codingtest.backjun.classthree.s1

fun main() = with(System.`in`.bufferedReader()) {

    val N = readLine().toInt()

    val arr = Array(N){IntArray(N)}
    repeat(N) { i ->
        readLine().forEachIndexed {j, c ->
            arr[i][j] = c.toString().toInt()
        }
    }
    var result = 0
    val resultList = arrayListOf<Int>()

    for (y in 0 until N) {
        for (x in 0 until N) {
            if (arr[y][x] == 1) {

                fun bfs(startY: Int, startX: Int): Int{
                    var house = 1
                    val dx = arrayOf(0,1,0,-1)
                    val dy = arrayOf(-1,0,1,0)
                    val queue = ArrayDeque<Pair<Int, Int>>() // (y,x)
                    arr[startY][startX] = 0
                    queue.add(Pair(startY,startX))

                    while (queue.isNotEmpty()) {

                        val poll = queue.removeFirst()

                        for (i in dx.indices) {
                            val nextX = poll.second + dx[i]
                            val nextY = poll.first + dy[i]

                            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N)
                                continue
                            if (arr[nextY][nextX] == 0)
                                continue

                            arr[nextY][nextX] = 0
                            house++
                            queue.add(Pair(nextY, nextX))
                        }
                    }
                    return house
                }

                resultList.add(bfs(y, x))
                result++
            }
        }
    }
    println(result)
    print(resultList.sorted().joinToString("\n"))
}