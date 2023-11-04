package com.codingtest.backjun.classthree.s1

/**
 * 주의점 : K가 N보다 작을 경우도 생각해줘야 한다.
 */
fun main() {

    var (N, K) = readln().split(" ").map { it.toInt() }
    val max = Math.max(N, K)
    val visited = BooleanArray((max+1)*2)

    val dx = arrayOf(1, -1, 2)
    fun bfs(n: Int): Int {
        var count = 0;

        visited[n] = true
        val queue = ArrayDeque<Int>()
        queue.add(n)

        while (queue.isNotEmpty()) {

            repeat(queue.size) {
                val poll = queue.removeFirst()
                if (poll == K) return count
                for (i in dx.indices) {
                    var next = poll + dx[i]
                    if (i == 2) {
                        next = poll * dx[i]
                    }
                    if (next < 0 || next >= max*2)
                        continue
                    if (visited[next])
                        continue
                    visited[next] = true
                    queue.add(next)
                }
            }
            count++
        }
        return count
    }
    print(bfs(N))
}
fun 숨바꼭질_dp풀이() {
    var (n, k) = readln().split(" ").map { it.toInt() }

    // n 아래의 i로 가는 방법은 뒤로방법만 있기에 n-i 임
    val dp = IntArray(100001) { if (it <= n) n - it else 2100000000 }

    for (i in 0..100000) {
        if (i != 0) dp[i] = Math.min(dp[i - 1] + 1, dp[i])       // 뒤로가는 방법
        if (i != 100000) dp[i] = Math.min(dp[i + 1] + 1, dp[i])  // 앞으로가는 방법
        if (2 * i in n + 1..100000) dp[2 * i] = Math.min(dp[i] + 1, dp[2 * i]) // 순간이동 하는 방법
    }

    println(dp[k])
}