package com.codingtest.backjun.classthree.s3

/**
 * n이 있을떄 n-1제곱, n-2제곱, n-3제곱 ... 각각 비교
 * 예) 12일 경우
 *  dp[12 - 1]+1 vs dp[12 - 4] + 1 vs dp[12 - 9] + 1
 *
 *  n은 결국 제곱 + 제곱 + 제곱 ... 이런 식으로 되어 있다.
 *  따라서 n에서 n보다 작은 모든 제곱수를 빼준 뒤 최소횟수를 비교해주면 된다.
 */
fun main() {
    val n = readln().toInt()
    val dp = IntArray(50001)

    dp[1] = 1
    dp[2] = 2
    dp[3] = 3
    dp[4] = 1


    for (i in 5..n) {
        val s = Math.sqrt(i.toDouble()).toString()
        if (s.last() == '0' && s[s.length - 2] == '.') {
            dp[i] = 1
            continue
        }
        var min = 4

        var step = 1
        while(i/2 <= i-(step*step)) {
            val diff = step * step
            if (dp[i-diff] + 1 < 4) {
                min = Math.min(min, dp[i - diff] + 1)
            }
            step++
        }
        dp[i] = min
    }
    print(dp[n])
//    fourSquares_개선된풀이()
}

// 위 방법을 좀 더 효율적으로 짠 코드, 방법은 동일
fun fourSquares_개선된풀이() {
    val number = readln().toInt()
    val data = IntArray(50000 + 1)

    data[1] = 1

    for (i in 2..number) {
        var min = 4  // 12
        var j = 1
        while (j * j <= i) {
            min = Math.min(min, data[i - j * j])
            j++
        }

        // n이 제곱인 경우 data[0] = 0이므로 1 더해줌
        // n이 제곱이 아닌 경우 data[i - j*j] 에 1 더해줌
        data[i] = min + 1
    }
    print(data[number])
}
