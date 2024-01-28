package com.codingtest.backjun.classfive.g2

import java.io.StreamTokenizer

/**
 * 그리드 + 분리집합
 * - union-find 생각을 못해서 그냥 배열에 직접 갱신하는 식으로 풀었는데 풀림
 *
 * - 현재 게이트에 우선적으로 비행기를 넣어준다.
 * - 자리가 없을 경우에 현재 게이트보다 낮은 게이트를 탐색한다.
 *
 */
fun main()  = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val G = input()
    var P = input()
    val arr = IntArray(G+1) {it} // 게이트, 값에는 비어있는 게이트 번호를 넣어준다.
    var result = 0
    while(P-- > 0) {
        val g = input()

        var idx = g
        // 게이트가 자리가 있을 때까지 갱신
        while (idx > 0 && idx != arr[idx]) {
            idx = arr[idx]
        }

        // 더 이상 자리가 없는 경우
        if (idx == 0) break

        // 자리가 있는 경우
        // 현재 게이트와 최종적으로 이동완료된 게이트 모두 갱신
        arr[g] = arr[idx - 1]
        arr[idx] = arr[idx - 1]
        result++

    }
    print(result)
//    공항_unionfind풀이()
}

/**
 * 현재 게이트와 현재 게이트-1 게이트랑 union을 시켜주는 방식
 * 위 첫번쨰 풀이랑 비슷하게 동작한다.
 */
fun 공항_unionfind풀이()  = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val G = input()
    var P = input()
    val parents = IntArray(G+1) {it}
    var result = 0

    fun find(n: Int) : Int {
        if (parents[n] == n)
            return n
        return find(parents[n])
    }
    fun union(n: Int) : Boolean {
        val pn = find(n)

        if (pn == 0) return false

        val pl = find(pn-1)
        parents[n] = pl
        parents[pn] = pl
        return true
    }
    while(P-- > 0) {
        val g = input()

        if (!union(g)) break
        result++
    }

    print(result)
}
