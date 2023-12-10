package com.codingtest.backjun.classfour.g4

/**
 * 못 품
 * - 주의
 *   다음 퀸 위치를 dx,dy로 지정해주면 풀기 힘들어진다.
 *      0 2 0 2 0
 *      2 0 0 0 2
 *      0 0 1 0 0
 *      2 0 0 0 2
 *      0 2 0 2 0
 *   이렇게 하면 안 됨
 *
 * 백 트래킹 문제
 *  - row 기준으로 체스를 하나씩 놓아주면 된다.
 */
fun main() = with(System.`in`.bufferedReader()){

    val n = readLine().toInt()

    val rows = IntArray(n)

    var result = 0
    fun backTracking(count: Int) {

        if (count == n) {
            result++
            return
        }

        for (i in 0..<n) {
            rows[count] = i // y,x

            var isPossible = true
            for (i in 0..<count) {
                if (rows[i] == rows[count] || Math.abs(count - i) == Math.abs(rows[count] - rows[i])) {
                    isPossible = false
                    break
                }
            }
            if (isPossible) {
                backTracking(count+1)
            }
        }

    }
    backTracking(0)
    print(result)
//    nqueen_대각선_방문여부체크()
}

/**
 * 대각선도 visited 배열로 체크하여 for문을 줄일 수 있다.
 *      - 다만 규칙을 찾기 힘듦
 *      y,x 가 주어졌을때  (y+x), (y-x+N) 을 true처리하면 된다.
 */
fun nqueen_대각선_방문여부체크() = with(System.`in`.bufferedReader()){

    val n = readLine().toInt()

    val visited = BooleanArray(n)
    val visitedCrossMinus = BooleanArray(n * 2) { false }
    val visitedCrossPlus = BooleanArray(n * 2) { false }

    var result = 0
    fun backTracking(count: Int) {

        if (count == n) {
            result++
            return
        }

        for (i in 0..<n) {

            if (!(visited[i] || visitedCrossMinus[count-i+n] || visitedCrossPlus[i + count])) {
                visited[i] = true
                visitedCrossMinus[count-i+n] = true
                visitedCrossPlus[i + count] = true
                backTracking(count+1)
                visited[i] = false
                visitedCrossMinus[count-i+n] = false
                visitedCrossPlus[i + count] = false
            }
        }

    }
    backTracking(0)
    print(result)
}