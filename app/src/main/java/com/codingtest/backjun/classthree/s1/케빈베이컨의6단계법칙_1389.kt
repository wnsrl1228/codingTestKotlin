package com.codingtest.backjun.classthree.s1

import java.io.StreamTokenizer

/**
 * 첫 번째 방식은
 *  1번 노드부터 2,3,4,5 각각의 모든 경우 구해서
 *  for(i in 1..N)(for j in i+1..N) 이 되지만 bfs 내부는 중복이 없음
 * 두 번째 방식은
 *  1번 노드부터 2,3,4,5 모두 더해서 한번에 베이컨 합을 구해서
 *  for(i in 1..N)이 되지만 bfd내부에서 중복이 생김
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input()
    val M = input()
    val arr = Array(N+1) {IntArray(N+1)}
    repeat(M) {
        val n1 = input()
        val n2 = input()
        arr[n1][n2] = 1
        arr[n2][n1] = 1
    }

    val result = Array(N+1) {IntArray(N+1)}
    for (i in 1..N-1) {

        for (j in i+1..N) {
            val visited = BooleanArray(N+1)
            val count = 케빈베이컨_bfs(i, j, visited, arr)
            result[i][j] = count
            result[j][i] = count
        }
    }

    var min = result[1].sum()
    var minPerson = 1
    for (i in 1..N) {
        if (min > result[i].sum()) {
            min = result[i].sum()
            minPerson = i
        }
        min = Math.min(min, result[i].sum())
    }
    print(minPerson)
//    케빈베이컨_개선()
}

fun 케빈베이컨_bfs(start: Int, end: Int, visited: BooleanArray, arr: Array<IntArray>): Int {

    var count = 0
    visited[start] = true
    val queue = ArrayDeque<Int>()
    queue.add(start)

    while (queue.isNotEmpty()) {

        repeat(queue.size) {
            val poll = queue.removeFirst()

            for (i in arr[poll].indices) {
                if (arr[poll][i] == 0 && visited[i])
                    continue
                if (i == end)
                    return count+1

                queue.add(i)
                visited[i] = true
            }
        }
        count++
    }

    return count
}

fun 케빈베이컨_개선() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val M = input()
    val arr = Array(N+1) {IntArray(N+1)}

    repeat(M) {
        val n1 = input()
        val n2 = input()
        arr[n1][n2] = 1
        arr[n2][n1] = 1
    }

    var min = Int.MAX_VALUE
    var minPerson = -1
    for (i in 1..N) {
        val visited = BooleanArray(N+1)
        val count = 케빈베이컨_bfs_개선(i, visited, arr)
        if (min > count) {
            min = count
            minPerson = i
        }
    }
    print(minPerson)
}


fun 케빈베이컨_bfs_개선(start: Int, visited: BooleanArray, arr: Array<IntArray>): Int {

    visited[start] = true

    var count = 0
    var result = 0
    val queue = ArrayDeque<Int>()
    queue.add(start)
    while (queue.isNotEmpty()) {

        repeat(queue.size) {
            val poll = queue.removeFirst()
            for (i in arr[poll].indices) {
                if (arr[poll][i] == 0 || visited[i])
                    continue
                result+= count+1
                queue.add(i)
                visited[i] = true
            }
        }
        count++
    }

    return result
}