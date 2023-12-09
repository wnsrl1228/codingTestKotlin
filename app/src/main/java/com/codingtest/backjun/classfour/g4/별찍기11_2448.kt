package com.codingtest.backjun.classfour.g4


/**
 * 못 품
 * 규칙만 찾으면 재귀로 쉽게 풀 수 있었음
 *  - 큰 삼각형 3개가 반복되는 구조이다 (위 가운데, 왼쪽아래, 오른쪽 아래)
 *    해당 삼각형의 시작 지점을 재귀로 줄여나간뒤
 *    가장 작은 삼각형에 도달하면 *을 넣어준다.
 *
 */
fun main() {

    val N = readln().toInt()
    val map = Array(N) {CharArray(N*2) {' '} }

    // 높이 = N
    // 너비 = N * 2

    fun solve(n: Int, y: Int, x: Int) {

        if (n == 3) {
            map[y][x + 2] = '*'
            map[y+1][x + 1] = '*'
            map[y+1][x + 3] = '*'
            for (i in 0..4) {
                map[y+2][x+i] = '*'
            }
            return
        }
        solve(n/2, y, x + n/2)        // 위 가운데
        solve(n/2, y + n/2, x)        // 왼쪽아래
        solve(n/2, y + n/2, x + n) // 오른쪽 아래
    }

    solve(N, 0, 0)

    val sb = StringBuilder()
    for (row in map) {
        for (i in row) {
            sb.append(i)
        }
        sb.appendLine()
    }
    print(sb)
}