package com.codingtest.backjun.classtwo.s2


/**
 * 땅 고르게 만들기
 * 2초 : 블록제거후 인벤토리에 추가
 * 1초 : 블록 쌓기
 * input : N(행), M(열), B(인벤토리에 존재하는 블록개수)
 * return 최소시간, 땅의 높이
 */
fun main() = with(System.`in`.bufferedReader()) {
    val (N, M, B) = readLine().split(" ").map { it.toInt() }
    val arr = List(N) { readLine().split(" ").map {it.toInt()}.toMutableList() }

    var minTime = Int.MAX_VALUE
    var high = 0
    for (h in 0..256) {
        var b = B
        var time = 0
        for (i in 0 until N) {
            for (j in 0 until M) {
                if (h < arr[i][j]) {
                    b += (arr[i][j] - h)
                    time += (arr[i][j] - h) * 2
                } else {
                    b -= (h - arr[i][j])
                    time += (h - arr[i][j])
                }
            }
        }
        if (b < 0) continue

        if (minTime > time) {
            minTime = time
            high = h
        } else if (minTime == time && high < h) {
            high = h
        }
    }
    print("$minTime $high")
//    마인크래프트_개선된풀이()
}

fun 마인크래프트_개선된풀이() = java.io.StreamTokenizer(System.`in`.bufferedReader()).run {
    fun i(): Int {
        nextToken(); return nval.toInt()
    }

    val n = i() * i() // 땅 개수
    var b = i()
    var time = 0
    var min = 256
    var max = 0
    val blocks = IntArray(257)

    // 각 땅의 블록 개수를 체크해준다.
    repeat(n) {
        val blockHeight = i()
        min = minOf(blockHeight, min)
        max = maxOf(blockHeight, max)
        blocks[blockHeight]++
    }

    // [0,0 0,0,n,0] 완성시키면 됨
    // 블록 이동 작업
    while (min < max) {
        // 인벤토리로 커버가능하고 && 땅을 파는것 보다 땅을 추가하는게 시간이 더 적게 걸릴 경우
         if (b >= blocks[min] && blocks[min] <= blocks[max] * 2) {
            val currentBlockCount = blocks[min]
            time += currentBlockCount
            b -= currentBlockCount
            blocks[min + 1] += currentBlockCount
            blocks[min] = 0
            min++
        } else {  // 인벤토리에 땅이 없거나, 땅을 파는게 시간이 더 적게 걸리는 경우
            val currentBlockCount = blocks[max]
            time += currentBlockCount * 2
            b += currentBlockCount
            blocks[max - 1] += currentBlockCount
            blocks[max] = 0
            max--
        }
    }

    print("$time $max")
}