package com.codingtest.backjun.classfour.g5

import java.io.StreamTokenizer

/**
 * 브루트포스 + 백트래킹
 * 브루트포스하면 시간제한 걸릴 줄 알았는데 안 걸림.
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val M = input()

    class Node(val y: Int, val x: Int)
    val oneArr = ArrayList<Node>()
    val twoArr = ArrayList<Node>()

    // 1과 2의 좌표값 얻기
    for (i in 0..<N) {
        for (j in 0..<N) {
            val input = input()
            if (input == 1) {
                oneArr.add(Node(i,j))
            } else if (input == 2) {
                twoArr.add(Node(i,j))
            }
        }
    }

    var min = Int.MAX_VALUE
    val temp =  IntArray(M)
    val visited = BooleanArray(twoArr.size)

    // 백트래킹으로 치킨집이 M개일 경우 집과의 거리를 모두 구해준다.
    fun recursion(n: Int, c: Int) {

        // 치킨집이 M개일 경우
        if (c == M) {
            var tmin = 0
            for (one in oneArr) {
                var oneMin = Int.MAX_VALUE
                for (twoIndex in temp) {
                    oneMin = Math.min(
                        oneMin,
                        Math.abs(one.y - twoArr[twoIndex].y) + Math.abs(one.x - twoArr[twoIndex].x)
                    )
                }
                tmin += oneMin
            }
            min = Math.min(min, tmin)
            return
        }

        for (i in n..<twoArr.size) {
            if (!visited[i]) {
                visited[i] = true
                temp[c] = i
                recursion(i+1, c+1)
                visited[i] = false
            }
        }

    }
    recursion(0, 0)
    print(min)

//    치킨배달_개선된풀이()
}

/**
 * 위 풀이랑 방식은 동일하지만
 * 집과 치킨집의 거리만 미리 구해줘서 중복계산을 줄여준다.
 */
fun 치킨배달_개선된풀이() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val M = input()

    class Node(val y: Int, val x: Int)
    val oneArr = ArrayList<Node>()
    val twoArr = ArrayList<Node>()

    // 1과 2의 좌표값 얻기
    for (i in 0..<N) {
        for (j in 0..<N) {
            val input = input()
            if (input == 1) {
                oneArr.add(Node(i,j))
            } else if (input == 2) {
                twoArr.add(Node(i,j))
            }
        }
    }

    // 집과 치킨집의 모든 거리 구하기
    val dist = Array(oneArr.size) { i ->
        IntArray(twoArr.size) { j ->
            Math.abs(oneArr[i].y - twoArr[j].y) + Math.abs(oneArr[i].x - twoArr[j].x)
        }
    }

    var min = Int.MAX_VALUE
    val visited = BooleanArray(twoArr.size)

    // 백트래킹으로 치킨집이 M개일 경우 집과의 거리를 모두 구해준다.
    fun recursion(n: Int, c: Int) {

        // 치킨집이 M개일 경우 거리 비교
        if (c == M) {
            var tmin = 0
            for (i in dist.indices) {
                var minDist = Int.MAX_VALUE
                for (j in dist[i].indices) {
                    if (visited[j]) {
                        minDist = Math.min(minDist, dist[i][j])
                    }
                }
                tmin += minDist
            }
            min = Math.min(min, tmin)
            return
        }

        for (i in n..<twoArr.size) {
            if (!visited[i]) {
                visited[i] = true
                recursion(i+1, c+1)
                visited[i] = false
            }
        }

    }
    recursion(0, 0)
    print(min)
}
