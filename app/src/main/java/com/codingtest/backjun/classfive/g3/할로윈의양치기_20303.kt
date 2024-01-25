package com.codingtest.backjun.classfive.g3

import java.io.StreamTokenizer
import java.util.LinkedList

/**
 * 그래프 + 배낭문제
 *
 *  - 서로서로 친구인 애들끼리 그룹화 해준 뒤 K-1명을 울려서 얻을 수 있는 최대 사탕 수를 dp를 이용하여 구한다.
 *
 *  dfs + 2차원 배열 dp
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input() // 아이들 수
    val M = input() // 아이들의 친구 관계 수
    val K = input() // 울음 소리가 공명하기 위한 최소 아이의 수

    val candy = IntArray(N+1)
    for (i in 1..N) candy[i] = input()
    val arr = Array(N+1) { arrayListOf<Int>() }

    // 친구 관계
    repeat(M) {
        val a= input()
        val b= input()
        arr[a].add(b)
        arr[b].add(a)
    }

    // 친구끼리 그룹을 만들어줌 bfs
    data class Group(val count: Int, val sum: Int)  // 인원수, 캔디 합
    val visited = BooleanArray(N+1)
    val group = arrayListOf<Group>()
    for (i in 1..N) {
        if (!visited[i]) {
            var count = 0
            var sum = 0
            val queue = LinkedList<Int>()

            queue.add(i)
            visited[i] = true

            while (queue.isNotEmpty()) {

                val cur = queue.poll()
                count++
                sum += candy[cur]

                for (next in arr[cur]) {
                    if (!visited[next]) {
                        queue.add(next)
                        visited[next] = true
                    }
                }
            }
            group.add(Group(count, sum))
        }
    }

    // 배낭문제와 동일
    val len = group.size
    val dp = Array(K) {IntArray(len)}
    for (i in 0 until K) {
        if (i >= group.get(0).count)
            dp[i][0] = group.get(0).sum
    }

    for (i in 0 until K) {
        for (j in 1 until len) {
            if (group[j].count > i) {
                dp[i][j] = dp[i][j-1]
            } else {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-group[j].count][j-1] + group[j].sum)
            }
        }
    }
    print(dp[K-1][len-1])
//    할로윈의양치기_개선된풀이()
}

/**
 * - 그룹화 과정은 union-find로 변경
 * - 2차원 배열을 1차원 배열로 축소
 *      - 배낭문제를 1차원 배열로만으로도 해결할 수 있다.
 */
fun 할로윈의양치기_개선된풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input() // 아이들 수
    val M = input() // 아이들의 친구 관계 수
    val K = input() // 울음 소리가 공명하기 위한 최소 아이의 수

    val candy = IntArray(N+1)
    for (i in 1..N) candy[i] = input()

    val parents = IntArray(N+1) {it}
    val rank = IntArray(N+1)

    fun find(num : Int): Int {
        if (parents[num] == num)
            return num
        return find(parents[num])
    }
    fun union(n1: Int, n2: Int) {
        val p1 = find(n1)
        val p2 = find(n2)
        if (p1 == p2) return

        if (rank[p1] == rank[p2]) rank[p1]++
        if (rank[p1] > rank[p2]) parents[p2] = p1
        else parents[p1] = p2
    }
    // 친구끼리 그룹화
    repeat(M) {
        val a= input()
        val b= input()
        union(a, b)
    }

    // 그룹의 총 인원수와 총 캔디수를 구해줌
    val sumArr = IntArray(N+1) {candy[it]}
    val countArr = IntArray(N+1) {1}
    for (i in 1..N) {
        val p = find(i)
        if (p == i) continue
        sumArr[p] += candy[i]
        countArr[p]++
    }

    // 배낭문제와 동일하게 k-1명의 인원일 때 최대 캔디수를 구해줌
    val dp = IntArray(N+1)
    for (i in 1..N) {
        if (parents[i] != i) continue

        for (j in K-1 downTo countArr[i]) {
            dp[j] = Math.max(dp[j], dp[j - countArr[i]] + sumArr[i])
        }
    }
    print(dp[K-1])
}
