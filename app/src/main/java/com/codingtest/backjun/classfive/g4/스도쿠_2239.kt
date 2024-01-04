package com.codingtest.backjun.classfive.g4

/**
 * 백 트래킹 문제
 *
 * 비트로 방문여부 파악, 좀 어렵게 푼 풀이로 두 번째 풀이를 추천
 *
 * ex) 1010011100 -> 9,7,4,3,2 는 사용 불가능하다
 *      방문 추가 : 1010011100 or 10 -> 1010011110
 *      방문 제거 : 1010011110 xor 10 -> 1010011100
 *
 * 팁 : 0의 위치만 따로 리스트에 담고 백트래킹 해줘야 구현하기 편함
 */
fun main() = with(System.`in`.bufferedReader()){

    val arr = Array(9) {readLine().toCharArray()}

    data class Pos(val y: Int, val x: Int)
    val rows = IntArray(9)
    val cols = IntArray(9)
    val kans = IntArray(9)
    val zeroPos = arrayListOf<Pos>()

    for (i in 0 until 9) {
        for (j in 0 until 9) {
            if (arr[i][j] - '0' == 0) {
                zeroPos.add(Pos(i,j))
            } else {
                val bit = 1 shl (arr[i][j] - '0')
                rows[i] = rows[i] or bit
                cols[j] = cols[j] or bit
                kans[(i/3*3) + (j/3)] = kans[(i/3*3) + (j/3)] or bit
            }
        }
    }

    var check = false
    fun solve(index: Int) {
        if (check) return
        if (index == zeroPos.size) {
            if (!check) {
                check = true
                arr.forEach { println(it.joinToString("")) }
            }
            return
        }

        val pos = zeroPos[index]

        // 백준에서 Integer.toBinaryString를 쓰면 메모리 엄청 잡아먹음
        val possible = Integer.toBinaryString(1 shl 10 or rows[pos.y] or cols[pos.x] or kans[(pos.y/3*3) + (pos.x/3)])

        for (i in possible.length-2 downTo 1) {
            if (possible[i] != '0') continue

            val num = possible.length -1 - i
            arr[pos.y][pos.x] = '0' + num

            var bit = 1 shl num
            rows[pos.y] = rows[pos.y] or bit
            cols[pos.x] = cols[pos.x] or bit
            kans[(pos.y/3*3) + (pos.x/3)] = kans[(pos.y/3*3) + (pos.x/3)] or bit

            solve(index+1)

            rows[pos.y] = rows[pos.y] xor bit
            cols[pos.x] = cols[pos.x] xor bit
            kans[(pos.y/3*3) + (pos.x/3)] = kans[(pos.y/3*3) + (pos.x/3)] xor bit

        }
    }
    solve(0)
//    스도쿠_비트개선()
//    스도쿠_다른풀이()
}

/**
 * 비트로 방문여부 파악
 * 위 방식보다 개선된 풀이
 */
fun 스도쿠_비트개선() = with(System.`in`.bufferedReader()){


    val arr = Array(9) {readLine().toCharArray()}

    data class Pos(val y: Int, val x: Int)
    val rows = IntArray(9)
    val cols = IntArray(9)
    val kans = IntArray(9)
    val zeroPos = arrayListOf<Pos>()

    for (i in 0 until 9) {
        for (j in 0 until 9) {
            if (arr[i][j] -'0' == 0) {
                zeroPos.add(Pos(i,j))
            } else {
                val bit = 1 shl (arr[i][j] -'0')
                rows[i] = rows[i] or bit
                cols[j] = cols[j] or bit
                kans[(i/3*3) + (j/3)] = kans[(i/3*3) + (j/3)] or bit
            }
        }
    }

    var check = false
    fun solve(index: Int) {
        if (check) return
        if (index == zeroPos.size) {
            if (!check) {
                check = true
                arr.forEach { println(it.joinToString("")) }
            }
            return
        }

        val pos = zeroPos[index]
        for (i in 1..9) {

            var bit = 1 shl i

            // 열과 행과 칸 모두 존재하지 않는 숫자일 경우
            if (rows[pos.y] and bit == 0 && cols[pos.x] and bit == 0 && kans[(pos.y/3*3) + (pos.x/3)] and bit == 0) {
                arr[pos.y][pos.x] = '0' + i
                rows[pos.y] = rows[pos.y] or bit
                cols[pos.x] = cols[pos.x] or bit
                kans[(pos.y/3*3) + (pos.x/3)] = kans[(pos.y/3*3) + (pos.x/3)] or bit

                solve(index+1)

                rows[pos.y] = rows[pos.y] xor bit
                cols[pos.x] = cols[pos.x] xor bit
                kans[(pos.y/3*3) + (pos.x/3)] = kans[(pos.y/3*3) + (pos.x/3)] xor bit
            }
        }
    }
    solve(0)
}

/**
 * Boolean 배열로 방문 파악하는 풀이
 */
fun 스도쿠_다른풀이() = with(System.`in`.bufferedReader()){


    val arr = Array(9) {readLine().map { it.toString().toInt() }.toIntArray()}

    data class Pos(val y: Int, val x: Int)
    val rows =  Array(9) { BooleanArray(10)}
    val cols = Array(9) { BooleanArray(10)}
    val kans = Array(9) { BooleanArray(10)}
    val zeroPos = arrayListOf<Pos>()

    for (i in 0 until 9) {
        for (j in 0 until 9) {
            if (arr[i][j] == 0) {
                zeroPos.add(Pos(i,j))
            } else {
                val num = arr[i][j]
                rows[i][num] = true
                cols[j][num] = true
                kans[(i/3*3) + (j/3)][num] = true
            }
        }
    }

    var check = false
    fun solve(index: Int) {
        if (check) return
        if (index == zeroPos.size) {
            if (!check) {
                check = true
                arr.forEach { println(it.joinToString("")) }
            }
            return
        }

        val pos = zeroPos[index]


        for (num in 1..9) {
            if (rows[pos.y][num] || cols[pos.x][num] || kans[(pos.y/3*3) + (pos.x/3)][num]) continue

            arr[pos.y][pos.x] = num
            rows[pos.y][num] = true
            cols[pos.x][num] = true
            kans[(pos.y/3*3) + (pos.x/3)][num] = true

            solve(index+1)

            arr[pos.y][pos.x] = 0
            rows[pos.y][num] = false
            cols[pos.x][num] = false
            kans[(pos.y/3*3) + (pos.x/3)][num] = false
        }
    }
    solve(0)
}