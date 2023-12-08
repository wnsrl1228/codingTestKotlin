package com.codingtest.backjun.classfour.g4

/**
 * dfs 풀이
 *    - 방문여부는 알파벳이 26가지 밖에 없기 때문에
 *      BooleanArray(26)
 *      visited[arr[0][0] - 'A'] = true 이런식으로
 *
 */
fun main() = with(System.`in`.bufferedReader()) {

    val (R, C) = readLine().split(" ").map { it.toInt() }

    val arr = Array(R) { readLine().toCharArray()}

    val dx = listOf(0, 1, 0, -1)
    val dy = listOf(-1, 0, 1, 0)
    val visited = BooleanArray(26)

    var max = 0
    fun dfs(y: Int, x: Int, count: Int) {
        if (max < count) {
            max = count
        }
        for (i in dx.indices) {
            val nextY = y + dy[i]
            val nextX = x + dx[i]

            if (nextY < 0 || nextY >= R || nextX < 0 || nextX >= C) {
                continue
            }
            if (!visited[arr[nextY][nextX]-'A']) {
                visited[arr[nextY][nextX]-'A'] = true
                dfs(nextY, nextX, count + 1)
                visited[arr[nextY][nextX]-'A'] = false
            }
        }
    }

    visited[arr[0][0] - 'A'] = true
    dfs(0, 0, 1)

    print(max)
}

/**
 * https://noguen.com/boj-swift-ps/1987-swift 참고
 * 비트마스킹 풀이
 *      - 알파벳이 26개, 2^26 = 26자리의 비트 사용
 * 예를 들어 A인 경우 0
 *         B인 경우 1 0     : 비트연산 왼쪽으로 한 칸
 *         C인 경우 1 0 0   : 비트연산 왼쪽으로 두 칸
 *         D인 경우 1 0 0 0 : 비트연산 왼쪽으로 세 칸
 *                      .
 *                      .
 *                      .
 *         로 각 비트자리마다 알파벳 위치를 지정시킨다.
 *         그리고 비트 연산 or과 and를 적절히 사용하여 알파벳 방문여부를 파악한다.
 *         - and 연산의 결과가 0인 경우 == 중복된 알파벳 X       ex) 1010 and 0101 = 0000 -> 0
 *         - or 연산으로 현재까지 방문 비트에 지금 방문비트를 추가  ex) 1100 or 10000 = 11100
 *
 */
fun 알파벳_비트마스킹_풀이() = with(System.`in`.bufferedReader()) {

    val (R, C) = readLine().split(" ").map { it.toInt() }

    val arr = Array(R) { readLine().toCharArray()}

    val dx = listOf(0, 1, 0, -1)
    val dy = listOf(-1, 0, 1, 0)

    val alp = Array(R) {IntArray(C)} // 해당 위치의 비트
    var max = 0
    fun dfs(y: Int, x: Int, count: Int, bit: Int) {
        if (alp[y][x] == bit) return
        alp[y][x] = bit
        if (max < count) {
            max = count
        }
        for (i in dx.indices) {
            val nextY = y + dy[i]
            val nextX = x + dx[i]

            if (nextY < 0 || nextY >= R || nextX < 0 || nextX >= C) {
                continue
            }
            val maskingBits = 1 shl arr[nextY][nextX] - 'A'

            // 하나도 비트가 겹치치 않으면 == 방문하지 않은 알파벳인 경우
            if (bit and maskingBits == 0) {
                dfs(nextY, nextX, count + 1, bit or maskingBits)
            }
        }
    }

    dfs(0, 0, 1, 1 shl arr[0][0]-'A')

    print(max)
}