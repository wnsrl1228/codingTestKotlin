package com.codingtest.backjun.classthree.s2

/**
 * 두 풀이 모두 nlogn 의 복잡도를 가지지만, map 사용시 연산 속도 훨씬 더 빠르다.
 */
fun main() {
    val n = readln().toInt()
    val sortedArr = Array(n) {Pair(0,0)}

    readln().split(" ").map { it.toInt() }
        .forEachIndexed {i, v ->
            sortedArr[i] = Pair(v, i)
        }

    sortedArr.sortBy { it.first }

    val resultArr = IntArray(n)
    var value = 0
    resultArr[sortedArr[0].second] = value  // 가장 작은 값은 무조건 0이 들어감

    for (i in 1 until n) {
        // 값이 같은 경우 카운팅되지 않는다.
        if (sortedArr[i-1].first == sortedArr[i].first) {
            resultArr[sortedArr[i].second] = value
        } else {
            resultArr[sortedArr[i].second] = ++value
        }
    }
    print(resultArr.joinToString(" "))
}

fun 죄표압축_map사용() {
    val n = readln().toInt()
    val sortedArr = IntArray(n)
    val originalArr = IntArray(n)

    val arr = readln().split(" ").map { it.toInt() }
        .forEachIndexed {i, v ->
            sortedArr[i] = v
            originalArr[i] = v
        }
    sortedArr.sort()

    var count = -1
    val map = HashMap<Int, Int>()
    for (i in sortedArr.indices) {
        if (!map.containsKey(sortedArr[i])) {
            map[sortedArr[i]] = ++count
        }
    }

    val sb = StringBuilder()
    for (num in originalArr) {
        sb.append(map[num]).append(' ')
    }

    print(sb)
}