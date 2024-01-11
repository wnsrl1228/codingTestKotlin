package com.codingtest.backjun.classfive.g3


import java.io.StreamTokenizer

/**
 * 못 품
 *  이분 탐색 or 투 포인터 or map
 *
 *  풀이를 보면 쉽지만 막상 처음 문제를 접하면 방법이 생각이 나지 않는다.
 *
 * 이분탐색 풀이
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Long {
        nextToken()
        return nval.toLong()
    }
    val T = input()
    val aArr = LongArray(input().toInt()) {input()}
    val bArr = LongArray(input().toInt()) {input()}

    val aSum = arrayListOf<Long>()
    val bTreeMap = hashMapOf<Long, Long>() // 합, 개수

    // A의 부 배열의 합을 모두 구한다.
    for (i in aArr.indices) {
        var sum = 0L
        for (j in i until aArr.size) {
            sum += aArr[j]
            aSum.add(sum)
        }
    }

    // B의 부 배열의 합을 모두 구한다.
    // A와 다르게 개수 중복을 허용하지 않는다.
    for (i in bArr.indices) {
        var sum = 0L
        for (j in i until bArr.size) {
            sum += bArr[j]
            bTreeMap[sum] = bTreeMap.getOrDefault(sum, 0) + 1
        }
    }

    // 이진 탐색
    val keys = bTreeMap.keys.toLongArray().sorted() // 이진 탐색을 위해 정렬
    fun binarySearch(num: Long): Long {

        var left = 0
        var right = keys.size-1

        while (left <= right) {
            var mid = (right+left)/2

            if (keys[mid] == num) {
                return bTreeMap[num]!! // 해당 원소 개수를 반환
            } else if (keys[mid] < num) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return 0
    }

    // A의 부분 배열의 합 + B의 부분 배열의 합 = T 가 되는 경우를 이진탐색으로 찾는다.
    var result = 0L
    for (num in aSum) {
        val b = binarySearch(T-num)
        result += b
    }
    print(result)

//    두배열의합_개선된풀이()
//    두배열의합_투포인터풀이()
}

/**
 * 투 포인터 풀이
 */
fun 두배열의합_투포인터풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Long {
        nextToken()
        return nval.toLong()
    }
    val T = input()
    val aArr = LongArray(input().toInt()) {input()}
    val bArr = LongArray(input().toInt()) {input()}

    val aSum = arrayListOf<Long>()
    val bSum = arrayListOf<Long>()

    // A의 부 배열의 합을 모두 구한다.
    for (i in aArr.indices) {
        var sum = 0L
        for (j in i until aArr.size) {
            sum += aArr[j]
            aSum.add(sum)
        }
    }

    // B의 부 배열의 합을 모두 구한다.
    for (i in bArr.indices) {
        var sum = 0L
        for (j in i until bArr.size) {
            sum += bArr[j]
            bSum.add(sum)
        }
    }

    // 투 포인터
    var result = 0L
    var left = 0            // A
    var right = bSum.size-1 // B

    aSum.sort()
    bSum.sort()

    while (left < aSum.size && right >= 0) {
        val sum = aSum[left] + bSum[right]

        // T와 같을 경우
        if (sum == T) {
            var at = 0L
            var bt = 0L
            val tLeft = aSum[left]
            while (left < aSum.size && tLeft == aSum[left]) { // 중복 숫자 처리
                left++
                at++
            }
            val tRight = bSum[right]
            while (right > -1 && tRight == bSum[right]) { // 중복 숫자 처리
                right--
                bt++
            }
            result += at * bt
        } else if (sum < T) {
            left++
        } else if (sum > T) {
            right--
        }
    }

    print(result)
}

/**
 * map을 이용한 풀이
 */
fun 두배열의합_개선된풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val T = input()
    val aArr = LongArray(input()) {input().toLong()}
    val bArr = LongArray(input()) {input().toLong()}

    val aTreeMap = hashMapOf<Long, Long>()

    for (i in aArr.indices) {
        var sum = 0L
        for (j in i until aArr.size) {
            sum += aArr[j]
            aTreeMap[sum] = aTreeMap.getOrDefault(sum, 0) + 1
        }
    }

    var result = 0L
    for (i in bArr.indices) {
        var sum = 0L
        for (j in i until bArr.size) {
            sum += bArr[j]
            result += aTreeMap.getOrDefault(T-sum, 0)
        }
    }
    print(result)
}
