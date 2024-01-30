package com.codingtest.backjun.classfive.g2

import java.io.StreamTokenizer

/**
 * 브루트포스 + 구현 문제
 *      - 최대 5번만 이동하면 되기 때문에 4^5 가지의 모든 경우를 구해준 뒤 비교해주면 된다.
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input()
    val arr = Array(N) {IntArray(N) {input()} }


    fun iterateRange(range: IntRange, reverse: Boolean = false): Iterable<Int> {
        return if (reverse) range.last downTo range.first else range
    }

    data class Node(val num: Int, var isCombine: Boolean = false)

    fun move(temp: Array<IntArray>, rangeY: Iterable<Int>, rangeX: Iterable<Int>, isUpOrDown: Boolean ) {

        for (y in rangeY) {
            val stack = ArrayDeque<Node>()
            for (x in rangeX) {
                val num = if (isUpOrDown) temp[x][y] else temp[y][x]
                if (num == 0) continue

                if (stack.isEmpty()) {
                    stack.add(Node(num))
                    continue
                }

                val poll = stack.last()
                if (poll.num == num && !poll.isCombine) {
                    stack.removeLast()
                    stack.add(Node(num*2, true))
                } else {
                    stack.add(Node(num))
                }
            }

            if (isUpOrDown) {
                for (x in rangeX) {
                    if (stack.isEmpty()) {
                        temp[x][y] = 0
                        continue
                    }
                    temp[x][y] = stack.removeFirst().num
                }
            } else {
                for (x in rangeX) {
                    if (stack.isEmpty()) {
                        temp[y][x] = 0
                        continue
                    }
                    temp[y][x] = stack.removeFirst().num
                }
            }
        }
    }

    var result = 0
    fun solve(count: Int, command: String, arr: Array<IntArray>) {

        if (count == 5) {
            for (i in arr.indices) {
                if (result < arr[i].max()) {
                    result = arr[i].max()
                }
            }
            return
        }

        val temp = Array(arr.size) { arr[it].copyOf() }
        when (command) {
            "LEFT" -> move(temp, iterateRange(temp.indices), iterateRange(temp.indices), false)
            "RIGHT" -> move(temp, iterateRange(temp.indices), iterateRange(temp.indices, true), false)
            "UP" -> move(temp, iterateRange(temp.indices), iterateRange(temp.indices), true)
            "DOWN" -> move(temp, iterateRange(temp.indices), iterateRange(temp.indices, true), true)
            else -> ""
        }


        solve(count+1, "LEFT", temp)
        solve(count+1, "RIGHT", temp)
        solve(count+1, "UP", temp)
        solve(count+1, "DOWN", temp)

    }
    solve(-1, "", arr)
    println(result)
}
