package com.codingtest.backjun.classfour.g4

import java.io.StreamTokenizer

/**
 * 구현 문제
 * - 문제 설명 그대로 구현만 해주면 된다.
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val R = input() // 행
    val C = input() // 열
    val T = input() // 시간

    var upAirCleanerRow = 0
    var downAirCleanerRow = 0
    val arr = Array(2) { Array(R) {IntArray(C)} }

    for (i in 0 until R) {
        for (j in 0 until C) {
            arr[0][i][j] = input()
            if (arr[0][i][j] == -1) {
                downAirCleanerRow = i
            }
        }
    }
    upAirCleanerRow = downAirCleanerRow - 1

    val dx = arrayOf(0,1,0,-1)
    val dy = arrayOf(-1,0,1,0)

    repeat(T) {

        // 1. 미세먼지 확산
        for (y in 0 until R) {
            for (x in 0 until  C) {
                val num = arr[0][y][x]
                if (num == 0 || num == -1)
                    continue

                var count = 0  // 확산 방향 개수
                val numDiv = num / 5 // 확산되는 미세먼지
                if (numDiv == 0) continue
                for (d in 0 until 4) {
                    val nextY = y + dy[d]
                    val nextX = x + dx[d]

                    if (nextY < 0 || nextY >= R || nextX < 0 || nextX >= C )
                        continue
                    if (arr[0][nextY][nextX] == -1)
                        continue

                    arr[1][nextY][nextX] += numDiv
                    count++
                }
                arr[0][y][x] -= numDiv * count
            }
        }

        for (y in 0 until R) {
            for (x in 0 until  C) {
                arr[0][y][x] += arr[1][y][x]
                arr[1][y][x] = 0
            }
        }
        // 2. 공기청전기 작동

        for (i in upAirCleanerRow-2 downTo 0) {
            arr[0][i+1][0] = arr[0][i][0]
        }
        for (i in downAirCleanerRow+2 until R) {
            arr[0][i-1][0] = arr[0][i][0]
        }

        for (i in 1 until C) {
            arr[0][0][i-1] = arr[0][0][i]
            arr[0][R-1][i-1] = arr[0][R-1][i]
        }

        for (i in 1..upAirCleanerRow) {
            arr[0][i-1][C-1] = arr[0][i][C-1]
        }
        for (i in R-2 downTo downAirCleanerRow) {
            arr[0][i+1][C-1] = arr[0][i][C-1]
        }

        for (i in C-2 downTo  1) {
            arr[0][upAirCleanerRow][i+1] = arr[0][upAirCleanerRow][i]
            arr[0][downAirCleanerRow][i+1] = arr[0][downAirCleanerRow][i]
        }
        arr[0][upAirCleanerRow][1] = 0
        arr[0][downAirCleanerRow][1] = 0
    }

    // 남은 미세먼지 계산
    var result = 0
    for (i in 0 until R) {
        for (j in 0 until C) {
            result += arr[0][i][j]
        }

    }
    print(result + 2)
}
/**
 * 50 x 50
 * 2500 * 4 X 400
 * 4000000 X 1000
 *
 * (10000 + 400) x 1000
 * 10400 000
 */
/**
 * 6 3 6   0 12 5
 * 0 6 7   0 4 0
 * 0 6 0   0 0 12
 *
 * 6 15 11
 * 0 10 7
 * 0 6 12
 */
/**
 *  1 2 2    1 5 4    2 7 6
 *  1 2 3    0 1 2    1 3 5
 *  0 2 1    0 4 4
 */