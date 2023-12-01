package com.codingtest.backjun.classfour.g5

import java.util.LinkedList

/**
 * bfs 풀이
 * 1. 큐에 현재 위치 + 현재 위치의 모든 순간이동 위치를 담는다.
 * 2. 그 다음 초를 카운트하고 +1, -1 위치를 담아준다.
 * 3. 동생 위치를 찾을때까지 반복한다.
 */
fun main() {

    val (N, K) = readln().split(" ").map { it.toInt() }

    val len = if (N >= K) N else if (K * 2 >= 100000) 100000 else K*2

    val queue = LinkedList<Int>()
    val visited = BooleanArray(len+1)

    fun bfs(): Int {
        var count = 0
        queue.add(N)
        visited[N] = true

        while (queue.isNotEmpty()) {

            /**
             * 2배는 카운트되지 않기 때문에 먼저 구해서 큐에 담아준다.
             */
            var index = 0
            var size = queue.size
            repeat(size) {
                val q = queue[index++]
                val diff = Math.abs(q-K)
                var tq = q * 2
                while (tq != 0 && tq <= len && Math.abs(tq-K) <= diff) {
                    if (!visited[tq]) {
                        if (tq == K)
                            return count
                        queue.add(tq)
                        visited[tq] = true
                    }
                    tq *= 2
                }
            }
            /**
             * +1, -1 처리
             */
            count++
            size = queue.size
            repeat(size) {
                val poll = queue.poll()

                for (i in -1..1 step 2) {
                    val next = poll + i

                    if (next < 0 || next > len)
                        continue
                    if (visited[next])
                        continue
                    if (next == K) return count
                    queue.add(next)
                    visited[next] = true
                }
            }
        }
        return 0
    }
    print(bfs())

//    숨바꼭질3_개선된풀이()
}

/**
 * bfs 풀이
 * 위 풀이는 답을 구한 이후 더 적은 값의 답이 나올 경우를 고려하여 푼 풀이이다.
 * 그 이유는 순간이동은 시간이 들지 않기 때문
 *
 * 하지만 실제로는 답이 나온 이후 더 적은 값이 나오지 않기에 신경쓰지 않아도 된다.
 */
fun 숨바꼭질3_개선된풀이() {

    val (N, K) = readln().split(" ").map { it.toInt() }

    val len = if (N >= K) N else if (K * 2 >= 100000) 100000 else K*2
    val visited = BooleanArray(len+1)

    val queue = LinkedList<Pair<Int,Int>>() // index, count
    queue.add(Pair(N, 0))
    visited[N] = true
    while (queue.isNotEmpty()) {
        val poll = queue.poll()
        val index = poll.first
        val count = poll.second

        if (index == K) {
            print(count)
            break
        }
        if (index * 2 <= len && !visited[index*2]) {
            visited[index*2] = true
            queue.add(Pair(index*2, count))
        }
        if (index != 0 && !visited[index-1]) {
            visited[index-1] = true
            queue.add(Pair(index-1, count+1))
        }
        if (index+1 <= len && !visited[index+1] && index < K) {
            visited[index+1] = true
            queue.add(Pair(index+1, count+1))
        }

    }
}