package com.codingtest.backjun.classfour.g4

import java.io.StreamTokenizer

/**
 *  주의 : 이진 트리가 아닌 경우도 가능
 *  dfs로 풀면 된다.
 *    - 1부터 N의 노드까지 모두 dfs를 돈다.
 *      dfs의 끝 지점 즉 자식이 없는 노드에 도달하였을 때 지금까지의 길이를 측정한다.
 *      해당 길이를 비교하여준다.
 *
 * 하지만 위 풀이로 할 경우 시간이랑 메모리 엄첨 많이 쓰임
 * 개선된 풀이로 dfs의 중복을 줄일 수 있다.
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    class Node(val num: Int, var dist: Int)
    val arr = Array(N+1) { arrayListOf<Node>() }
    repeat(N-1) {
        val v1 = input()
        val v2 = input()
        val dist = input()
        arr[v1].add(Node(v2, dist))
        arr[v2].add(Node(v1, dist))
    }

    var max = 0
    fun dfs(num: Int, distSum: Int, visited: BooleanArray) {

        visited[num] = true

        var isLast = true
        for (node in arr[num]) {
            if (!visited[node.num]) {
                isLast = false
                dfs(node.num, distSum + node.dist, visited)
            }
        }
        if (isLast) {
            max = Math.max(max, distSum)
        }
    }

    var result = 0
    for (key in 1..N) {
        max = 0
        val visited = BooleanArray(N+1)
        dfs(key, 0, visited)
        result = Math.max(result, max)
    }
    print(result)
//    트리의지름_개선된풀이()
}

/**
 * 똑같이 dfs를 도는데
 *     1. 루트에서 dfs를 돌아 가장 길이가 긴 노드를 찾는다.
 *     2. 가장 긴 노드에서 dfs를 돌아 지름을 찾아준다.
 *
 *  핵심 : 루트 노드와 X노드의 지름이 가장 길 경우
 *        해당 트리에서의 가장 긴 지름의 노드 중 하나는 X노드가 된다.
 */
fun 트리의지름_개선된풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    class Node(val num: Int, var dist: Int)
    val arr = Array(N+1) { arrayListOf<Node>() }
    repeat(N-1) {
        val v1 = input()
        val v2 = input()
        val dist = input()
        arr[v1].add(Node(v2, dist))
        arr[v2].add(Node(v1, dist))
    }

    var max = 0
    var maxNum = 0
    var visited = BooleanArray(N+1)

    fun dfs(num: Int, distSum: Int,) {
        visited[num] = true

        for (node in arr[num]) {
            if (!visited[node.num]) {
                dfs(node.num, distSum + node.dist)
            }
        }
        if (max < distSum) {
            max = distSum
            maxNum = num
        }
    }

    dfs(1, 0)
    visited = BooleanArray(N+1)
    dfs(maxNum, 0)

    print(max)
}
/**
 * N*elonN
 * 400 000 000
 * 100000000
 *
 */