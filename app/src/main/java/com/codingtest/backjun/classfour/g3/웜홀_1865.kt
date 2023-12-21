package com.codingtest.backjun.classfour.g3

import java.io.StreamTokenizer
import java.util.LinkedList

/**
 * 벨만포드 응용 문제 (2번쨰 풀이)
 *  벨만포드 : 음의 간선 + 음의 사이클이 존재할 경우 노드N에서 모든 노드까지의 최단 거리를 구하는 알고리즘
 *
 *      풀이법
 *      - N(노드개수)번 동안 모든 간선을 돌면서 최단 거리를 갱신해준다.
 *      - 마지막 N번째에 최단 거리가 갱신되는 경우가 있을 경우 음의 사이클이 존재한다고 판명된다.
 *
 *      (N개의 노드가 있을때 특정 노드 X의 최대 간선은 N-1이다.
 *      따라서 최대로 갱신될 수 있는 횟수는 N-1번이다.
 *      하지만 음의 간선으로 인한 사이클이 존재하는 경우 이보다 더 많이 갱신될 수 있다.)
 *
 * 문제 주의점
 *      - 시작 노드가 주어지지 않음
 *      - 두 지점을 연결하는 도로가 한 개보다 많을 수도 있다.
 *      - 노드 a에서 노드 a까지의 도로가 존재할 수 있다.
 */

/**
 * SPFA 풀이
 * SPFA: 벨만포드보다 빠른 장점
 *      - 벨만포드는 모든 간선을 돌면서 갱신해주지만 SPFA는 인접 노드의 간선들만 돌면서 갱신해준다.
 *
 *      하지만 특정 노드X에서 모든 노드까지의 최단 거리를 구하기 때문에
 *      for (모든 노드)
 *          SPFA(노드)
 *      로 모든 노드에서 시작하여 사이클 여부를 체크해줘야 되기에 SPFA를 N번 돌려줘야 한다.
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    var t = input()
    while (t-- > 0) {
        val N = input() // 노드 개수
        val M = input() // 간선
        val W = input() // 웜홀

        var arr = Array(N+1) {IntArray(N+1) {Int.MAX_VALUE} }

        // 간선 입력
        repeat(M) {
            val a = input()
            val b = input()
            val c = input()
            arr[a][b] = Math.min(arr[a][b], c)
            arr[b][a] = Math.min(arr[b][a], c)
        }
        // 웜홀 입력
        repeat(W) {
            val a = input()
            val b = input()
            val c = input() * -1
            arr[a][b] = Math.min(arr[a][b], c)
        }

        fun spfa(start: Int): Boolean {
            val minDist = IntArray(N+1) {Int.MAX_VALUE}  // start 노드로부터 각 노드의 최소 거리
            val cycle = IntArray(N+1)     // 큐에 담긴 횟수, 만약 N번 담기면 사이클 존재,  N-1번이 최대 간선으로부터 큐에 담길 수 있는 횟수이기에 그 이상 큐에 담길 경우 사이클로 인한 것
            val inQ = BooleanArray(N+1)   // 큐에 담겨있는지 여부
            val queue = LinkedList<Int>()
            minDist[start] = 0
            cycle[start]++
            queue.add(start)

            while (queue.isNotEmpty()) {
                val cur = queue.remove()
                inQ[cur] = false

                for (node in arr[cur].indices) {
                    if (arr[cur][node] != Int.MAX_VALUE) {
                        if (minDist[node] > minDist[cur] + arr[cur][node]) {
                            minDist[node] = minDist[cur] + arr[cur][node]

                            if (!inQ[node]) {
                                inQ[node] = true
                                queue.add(node)
                                cycle[node]++

                                // 해당 node가 N번째 갱신된다는 것은 사이클이 존재하다는 것
                                if (cycle[node] >= N) {
                                    return true
                                }
                            }
                        }
                    }
                }
            }
            return false
        }

        fun isCycle(): Boolean {
            // 모든 노드를 돌면서 spfa 진행
            for (i in 1..N) {
                if (spfa(i)) {
                    return true
                }
            }
            return false
        }

        if (isCycle()) println("YES")
        else println("NO")

    }
//    웜홀_밸만포드_풀이()
}

/**
 * 벨만포드 풀이
 *  - 시작 지점이 정해지지 않기 때문에 N번의 벨만포드를 수행해야 되는데 이러면 시간초과가 발생한다.
 *      (사이클이 있는 부분과 연결이 되어있지 않는 노드가 있을 수 있기 때문)
 *
 *  해결 방안 : 벨만포드를 한 번만 실행되게 한다.
 *      - MAX를 계산 범주 안에 크기로 지정한다.
 *      - 기존에는 minDist[i]가 MAX인 경우 계산해주지 않았다. (최단 거리를 못 구하기 때문)
 *      - 하지만 해당 문제는 사이클의 존재여부만을 필요하기 때문에 minDist[i]가 MAX인 경우도 갱신해준다.
 *
 *      ex) 2 -> 3  dist : -1
 *          3 -> 2  dist : -2
 *          1로 시작하면
 *              dist[1] = 0, dist[2] = 1000, dist[3] = 1000 // (1000=MAX)
 *
 *          start = 2, end =3 인 경우
 *              if (dist[end] > dist[start] + arr[start][end])
 *              = if (1000 > 1000 - 1))
 *              음의 간선이 있기에 true가 가능하다.
 *
 */
fun 웜홀_밸만포드_풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val MAX = 100000000
    var t = input()
    while (t-- > 0) {
        val N = input() // 노드 개수
        val M = input() // 간선
        val W = input() // 웜홀

        var arr = Array(N+1) {IntArray(N+1) {MAX} }

        // 간선 입력
        repeat(M) {
            val a = input()
            val b = input()
            val c = input()
            arr[a][b] = Math.min(arr[a][b], c)
            arr[b][a] = Math.min(arr[b][a], c)
        }
        // 웜홀 입력
        repeat(W) {
            val a = input()
            val b = input()
            val c = input() * -1
            arr[a][b] = Math.min(arr[a][b], c)
        }

        fun ballmanFold(): Boolean {
            val minDist = IntArray(N+1) {MAX}  // start 노드로부터 각 노드의 최소 거리
            minDist[1] = 0

            // N 번 돌면서
            for (i in 1.. N) {

                // 모든 간선을 돈다
                for (start in 1..N) {
                    for (end in 1..N) {
                        if (arr[start][end] == Int.MAX_VALUE)
                            continue

                        if ( minDist[end] > minDist[start] + arr[start][end]) {
                            minDist[end] = minDist[start] + arr[start][end]

                            // N번째에도 갱신이 된다는 것은 사이클이 존재한다는 것
                            if (i ==N) return true
                        }
                    }
                }
            }
            return false
        }

        if (ballmanFold()) println("YES")
        else println("NO")
    }
}