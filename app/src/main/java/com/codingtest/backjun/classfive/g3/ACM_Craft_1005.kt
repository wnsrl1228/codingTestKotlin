package com.codingtest.backjun.classfive.g3

import java.io.StreamTokenizer
import java.util.LinkedList

/**
 * 위상정렬 + dp , bfs로 구현
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    var t = input()
    val sb = StringBuilder()

    while(t-- > 0) {
        val N = input() // 건물 개수
        val K = input() // 규칙 개수

        val arr = IntArray(N+1)  // 건물 생성 시간
        repeat(N) {arr[it+1] = input()}

        val map = Array(N+1) {IntArray(N+1)}
        val degree = IntArray(N+1) // 선수 도시 개수
        val dp = IntArray(N+1)     // 현재 건물 생성까지 걸린 시간

        repeat(K) {
            val a = input()
            val b = input()
            map[a][b] = 1
            degree[b]++
        }

        val subject = input()

        // 선수도시가 0인 도시를 큐에 추가 + dp 초기화
        val queue = LinkedList<Int>()
        for (i in 1..N) {
            if (degree[i] == 0) {
                queue.add(i)
                dp[i] = arr[i]
            }
        }

        fun bfs() {
            while (queue.isNotEmpty()) {
                val cur = queue.poll()

                // 목표 도시 도달할 경우 탈출
                if (cur == subject) {
                    return
                }

                for (i in 1..N) {
                    // 선수 도시인 경우
                    if (map[cur][i] == 1) {

                        // i번 도시의 선수 도시들의 dp값 중 최댓값 = 선수 도시 모두 완성되야지 i번 건물을 지을 수 있기 때문
                        // 선수 도시가 2개 이상인 경우 dp[i]에 이미 값이 있을 수 있다.
                        dp[i] = Math.max(dp[i], dp[cur] + arr[i])

                        // 선수도시가 0이 되는 경우 큐에 추가
                        degree[i]--
                        if (degree[i] == 0) {
                            queue.add(i)
                        }
                    }
                }
            }
        }
        bfs()

        sb.appendLine(dp[subject])
    }
    print(sb)
//    ACM_Craft_재귀()
}

/**
 * 위상정렬 + dp , 재귀로 구현
 * 더 가독성이 좋다.
 */
fun ACM_Craft_재귀() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    var t = input()
    val sb = StringBuilder()

    while(t-- > 0) {
        val N = input() // 건물 개수
        val K = input() // 규칙 개수

        val arr = IntArray(N+1)  // 건물 생성 시간
        repeat(N) {arr[it+1] = input()}

        val map = Array(N+1) {IntArray(N+1)}
        val dp = IntArray(N+1) {-1}     // 현재 건물 생성까지 걸린 시간

        repeat(K) {
            val a = input()
            val b = input()
            map[b][a] = 1
        }

        val subject = input()


        fun dfs(v: Int): Int {
            // 이미 값이 결정된 경우 = 중복방지
            if (dp[v] >= 0) return dp[v]

            var prev = 0

            // 선수 건물 중 최대 시간이 걸린 값을 찾는 과정
            for (u in 1..N) {
                if (map[v][u] == 1) {
                    prev = maxOf(prev, dfs(u))
                }
            }

            dp[v] = prev + arr[v]

            return dp[v]
        }



        sb.appendLine(dfs(subject))
    }
    print(sb)
}
