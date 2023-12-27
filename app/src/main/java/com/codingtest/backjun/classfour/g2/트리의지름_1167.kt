package com.codingtest.backjun.classfour.g2

import java.io.StreamTokenizer

/**
 * dfs 응용 문제
 *      - 트리의 지름_1967과 동일한 문제 하지만 노드의 범위가 더 크기 때문에
 *        dfs를 2번만 돌리는 풀이로만 해결 가능
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    data class Node(val num: Int, val dist: Int)
    val N = input()
    val tree = Array(N+1) { arrayListOf<Node>() }

    for (i in 1..N) {
        val list = tree[input()]
        while (true) {
            val a = input()
            if (a == -1) break
            list.add(Node(a, input()))
        }
    }

    var maxNum = 0
    var maxDist = 0
    var visited = BooleanArray(N+1)
    fun dfs(num: Int, dist: Int) {

        visited[num] = true

        for (next in tree[num]) {
            if (!visited[next.num]) {
                dfs(next.num, dist + next.dist)
            }
        }

        if (maxDist < dist) {
            maxDist = dist
            maxNum = num
        }
    }
    dfs(1,0)
    visited = BooleanArray(N+1)
    dfs(maxNum, 0)
    print(maxDist)
}
/**
 * 100,000
 *
 * 200,000 dfs
 *
 * 마지막 50000
 *
 */