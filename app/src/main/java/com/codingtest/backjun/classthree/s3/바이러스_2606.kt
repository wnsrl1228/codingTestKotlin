package com.codingtest.backjun.classthree.s3

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val map = Array(n+1) { arrayListOf<Int>() }
    repeat(readLine().toInt()) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        map[a].add(b)
        map[b].add(a)
    }

    var count = 0
    val visited = BooleanArray(n+1)
    val stack = ArrayDeque<Int>()
    stack.add(1)
    visited[1] = true

    while (stack.isNotEmpty()) {
        val poll = stack.removeLast()

        count++
        for (c in map[poll]) {
            if (!visited[c]) {
                stack.add(c)
                visited[c] = true
            }
        }
    }
    print(count-1)
}