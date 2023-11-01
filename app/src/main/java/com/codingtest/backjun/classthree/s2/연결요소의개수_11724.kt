package com.codingtest.backjun.classthree.s2

import java.io.StreamTokenizer

// dfs 풀이
fun main() = with(System.`in`.bufferedReader()){

    val (N, M) = readLine().split(" ").map { it.toInt() }
    val graph = Array(N+1) {IntArray(N+1)}

    repeat(M) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a][b] = 1
        graph[b][a] = 1
    }

    val visited = BooleanArray(N+1)
    var result = 0
    for (node in 1..N) {
        if (!visited[node]) {

            fun dfs(start: Int, graph: Array<IntArray>, visited: BooleanArray) {
                visited[start] = true

                for (i in graph.indices) {
                    if (!visited[i] && graph[start][i] == 1) {
                        dfs(i, graph, visited)
                    }
                }
            }

            dfs(node, graph, visited)
            result++
        }
    }
    print(result)
//    연결요소의개수_Union_find_풀이()
}

fun 연결요소의개수_Union_find_풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {

    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val m = input()

    val lines = IntArray(n+1) {it}
    val visited = BooleanArray(n+1)

    // 루트 노드를 찾는 함수
    fun find(value: Int): Int{
        if (lines[value] == value) {
            return value
        }
        lines[value] = find(lines[value])
        return lines[value]
    }
    // 서로 다른 노드에 루트 노드를 합쳐주는 함수
    fun merge(x: Int, y: Int) {
        val fx = find(x)
        val fy = find(y)
        if (fx != fy) {
            lines[fy] = fx
        }
    }

    repeat(m) {
        merge(input(), input())
    }

    var result = 0

    // 모든 노드를 돌면서 루트 노드를 카운팅해줌
    for(i in 1..n) {
        if (visited[find(lines[i])]) continue

        visited[find(lines[i])] = true
        result++
    }
    print(result)
}
/**
 * index  = 1 2 3 4 5 6
 * --------------------
 * init  -> 1 2 3 4 5 6
 * 1 2   -> 1 1 3 4 5 6
 * 2 5   -> 1 1 3 4 1 6
 * 5 1   -> 1 1 3 4 1 6
 * 3 4   -> 1 1 3 3 1 6
 * 4 6   -> 1 1 3 3 1 3
 */