package com.codingtest.backjun.classfive.g3

import java.io.StreamTokenizer

/**
 * dfs 심화
 *   - 시간 복잡도 O(n)으로 처리해줘야 함
 *   - dfs로 탐색이 끝난 사람은 더 이상 탐색하지 않도록 구현
 *   - 꽤 복잡하다.
 *
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val sb = StringBuilder()
    repeat(input()) {

        val N = input()
        val arr = IntArray(N+1)
        val finish = BooleanArray(N+1)  // 이미 탐색이 끝난 사람
        val visited = BooleanArray(N+1) // 현재 dfs의 방문여부, dfs가 끝나면 finish처리

        repeat(N) { i ->
            arr[i+1] = input()
        }

        var result = N
        fun dfs(num: Int) {

            if (finish[num]) return

            val next = arr[num]
            // 방문한적은 있지만 팀이 없는 경우
            // 즉 사이클이 생기는 경우
            if (visited[num] && !finish[num]) {
                finish[num] = true
                result--
                var nx = next
                while (num != nx) {
                    finish[nx] = true
                    nx = arr[nx]
                    result--
                }
                return
            }
            // 방문한적도 탐색이 끝나지도 않은 경우
            visited[num] = true
            dfs(next)
            finish[num] = true
        }

        for (i in 1..N) {
            if (!visited[i] && !finish[i])
                dfs(i)
        }
        sb.appendLine(result)
    }
    print(sb)
}
/**
 * 10,000,000,000
 *
 * 1 2 3 4 5 6 7 8 9 10 11 12
 * 2 3 4 5 6 7 8 9 12 9 10 11


 * 1 2 3 4 5 6 7 8 9 10 11 12
 * 2 3 4 5 6 7 8 9 12 9 6 11
 */