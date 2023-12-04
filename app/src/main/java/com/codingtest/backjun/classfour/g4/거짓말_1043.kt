package com.codingtest.backjun.classfour.g4

import java.io.StreamTokenizer

/**
 * find-union 문제
 */
lateinit var root: IntArray
lateinit var knownPeople: BooleanArray
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input() // 사람 수
    val M = input() // 파티 수

    knownPeople = BooleanArray(N+1) // 진실을 알고 있는지 여부
    val arr = Array(M) { arrayListOf<Int>() }
    root = IntArray(N+1) {i-> i}

    // 진실을 아는 사람 체크
    repeat(input()) {
        knownPeople[input()] = true
    }

    // 파티에 있는 사람들끼리 union-find 진행
    repeat(M) { index ->
        for (i in 0..<input()) {
            val p = input()
            if (i != 0) {
                union(arr[index].last(), p)
            }
            arr[index].add(p)
        }
    }

    // 부모가 진실을 알 경우 본인도 진실을 알도록 처리
    for (i in root.indices) {
        if (knownPeople[root[i]])
            knownPeople[i] = true
    }

    // 다시 파티를 돌며 거짓여부 카운트
    var result = 0
    for (a in arr) {
        var isFake = false
        for (j in a) {
            if (knownPeople[j]) {
                isFake = true
                break
            }
        }
        if (!isFake) result++
    }
    print(result)
//    거짓말_개선된풀이()
}

fun find(num: Int): Int {
    if (root[num] == num)
        return num
    return find(root[num])
}

fun union(x: Int, y: Int) {
    val xx = find(x)
    val yy = find(y)

    // root가 진실을 알 경우
    if (knownPeople[xx] || knownPeople[yy]) {
        knownPeople[xx] = true
        knownPeople[yy] = true
    }
    // 방향 기준 정해줘야 에러 안뜸
    if (yy > xx) root[yy] = xx
    else root[xx] = yy
}

fun 거짓말_개선된풀이() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input() // 사람 수
    val M = input() // 파티 수

    knownPeople = BooleanArray(N+1) // 진실을 알고 있는지 여부
    val count = IntArray(N+1)       // 포함된 파티 개수
    root = IntArray(N+1) {i-> i}

    // 진실을 아는 사람 체크
    repeat(input()) {
        knownPeople[input()] = true
    }

    // 파티에 있는 사람들끼리 union-find 진행
    repeat(M) { index ->
        var before = 0
        for (i in 0..<input()) {
            val p = input()
            if (i != 0) {
                union2(before, p)
            }
            before = p
        }
        // 현재 파티에 진실을 아는 사람이 없으면 카운트
        val f = find2(before)
        if (!knownPeople[f]) {
            count[f] += 1
        }
    }

    // 부모가 진실을 알 경우 본인도 진실을 알도록 처리
    // 그게 아닌 경우 파티 카운트
    var result = 0
    for (i in root.indices) {
        if (knownPeople[root[i]]) {
            knownPeople[i] = true
        } else {
            result += count[i]
        }
    }
    print(result)
}

fun find2(num: Int): Int {
    if (root[num] == num)
        return num
    return find2(root[num])
}

fun union2(x: Int, y: Int) {
    val xx = find2(x)
    val yy = find2(y)

    // root가 진실을 알 경우
    if (knownPeople[xx] || knownPeople[yy]) {
        knownPeople[xx] = true
        knownPeople[yy] = true
    }
    // 방향 기준 정해줘야 에러 안뜸
    if (yy > xx) root[yy] = xx
    else root[xx] = yy
}
