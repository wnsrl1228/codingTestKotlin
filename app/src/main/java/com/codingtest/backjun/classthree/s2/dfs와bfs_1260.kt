package com.codingtest.backjun.classthree.s2

fun main() = with(System.`in`.bufferedReader()){
    val (N, M, V) = readLine().split(" ").map { it.toInt() }
    val map = Array(N+1) { arrayListOf<Int>() }
    repeat(M) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        map[a].add(b)
        map[b].add(a)
    }
    for (i in map) {
        i.sort()
    }
    var visited = BooleanArray(N+1)
    Dfs와bfs.dfs(V, map, visited)

    visited = BooleanArray(N+1)
    Dfs와bfs.bfs(V, map, visited)

    println(Dfs와bfs.dfsResult.joinToString(" "))
    println(Dfs와bfs.bfsResult.joinToString(" "))
}

object Dfs와bfs {

    val dfsResult = arrayListOf<Int>()
    val bfsResult = arrayListOf<Int>()

    fun dfs(node: Int, map: Array<ArrayList<Int>>, visited: BooleanArray) {
        visited[node] = true
        dfsResult.add(node)
        for (i in map[node]) {
            if (!visited[i]) {
                dfs(i, map, visited)
            }
        }
    }

    fun bfs(node: Int, map: Array<ArrayList<Int>>, visited: BooleanArray) {
        visited[node] = true
        val queue = ArrayDeque<Int>()
        queue.add(node)

        while (queue.isNotEmpty()) {
            val first = queue.removeFirst()
            bfsResult.add(first)
            for (i in map[first]) {
                if (!visited[i]) {
                    queue.add(i)
                    visited[i] = true
                }
            }
        }
    }
}

