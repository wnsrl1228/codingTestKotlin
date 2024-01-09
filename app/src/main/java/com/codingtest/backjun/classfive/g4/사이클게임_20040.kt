package com.codingtest.backjun.classfive.g4

import java.io.StreamTokenizer

/**
 * 크루스칼 문제
 *  - union-find로 사이클 여부를 체크해주면 된다.
 *
 *  단. 부모 설정을 최적화해주지 않으면 시간초과가 발생한다.
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val M = input()
    val parents = IntArray(N) {it}
    val heights = IntArray(N)

    fun findCycle() : Int {

        fun find(num: Int) : Int{
            if (num == parents[num]) {
                return num
            }
            return find(parents[num])
        }

        fun unionFind(n1: Int, n2: Int) : Boolean{
            val p1 = find(n1)
            val p2 = find(n2)
            if (p1 == p2) return false

            if (heights[p1] == heights[p2]) heights[p1]++

            if (heights[p1] > heights[p2]) parents[p2] = p1
            else parents[p1] = p2
            return true
        }

        repeat(M) { i ->

            val a = input()
            val b = input()

            if (!unionFind(a, b)) {
                return i + 1
            }
        }
        return 0
    }

    print(findCycle())
}
/**

 * 0 ~ n-1 의 점
 * 점을 하나씩 이어주면서 사이클이 생기는지 체크
 *
 * union-find을 해주면 될듯?
 *
 *
 * 1 2 3 4 5 6
 *
 * 1 2 3 4 5 6
 *   1 1 1 1 1
 *
 * 1 2 3 4 5 6
 * 2 2 2 2 2 2
 **/