package com.codingtest.backjun.classfive.g5

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun i(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = i()
    var flat = -1 // 산성으로 바뀌는 인덱스
    var check = true
    val arr = IntArray(N) { i ->
        val a = i()
        if (check && a > 0) {
            check = false
            flat = i
        }
        a
    }

    // 용액이 2개인 경우
    if (N == 2) {
        print("${arr[0]} ${arr[1]}")
        return
    }
    // 전체 용액이 알칼리성 용액인 경우
    if (flat == -1) {
        print("${arr[N-2]} ${arr[N-1]}")
        return
    }
    // 전체 용액이 산성 용액인 경우
    if (flat == 0) {
        print("${arr[0]} ${arr[1]}")
        return
    }


    // 초기 최솟값을 용앵이 섞이지 않았을 경우의 최소값으로 잡음
    var resultLeft: Int
    var resultRight: Int

    val leftMin = if (flat <= 1) Int.MAX_VALUE else arr[flat-1] + arr[flat-2]
    val rightMin = if (flat == N-1) Int.MAX_VALUE else arr[flat] + arr[flat+1]

    var minValue = if (Math.abs(leftMin) <= Math.abs(rightMin)) {
        resultLeft = arr[flat-2]
        resultRight = arr[flat-1]
        leftMin
    } else {
        resultLeft = arr[flat]
        resultRight = arr[flat+1]
        rightMin
    }

    var left = flat - 1   // 알칼리 인덱스
    var right = flat      // 산성 인덱스

    while (left >= 0 && right < N) {

        val num = arr[left] + arr[right]
        if (num == 0) {
            print("${arr[left]} ${arr[right]}")
            return
        }

        // 최솟값 비교
        if (Math.abs(num) < Math.abs(minValue)) {
            resultLeft = arr[left]
            resultRight = arr[right]
            minValue = num
        }

        // 산성이 더 큰 경우
        if (num > 0) {
            left--
        }
        // 알칼리가 더 큰 경우
        else {
            right++
        }
    }
    print("${resultLeft} ${resultRight}")

//    용액_다른풀이()
}

/**
 * 위 방식은 포인터가 안에서 밖으로 이동하지만
 * 아래 방식은 반대로 포인터가 밖에서 안으로 이동한다.
 * 따라서 예외 상황을 신경써주지 않아도 된다.
 */
fun 용액_다른풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun i(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = i()

    val arr = IntArray(N) { i() }

    var left = 0
    var right = N-1
    var minValue = Int.MAX_VALUE

    var resultLeft = 0
    var resultRight = 0
    while (left < right) {

        val num = arr[left] + arr[right]
        if (num == 0) {
            print("${arr[left]} ${arr[right]}")
            return
        }

        if (Math.abs(num) < Math.abs(minValue)) {
            resultLeft = arr[left]
            resultRight = arr[right]
            minValue = num
        }

        if (Math.abs(arr[left] + arr[right-1]) < Math.abs(arr[left+1] + arr[right])) {
            right--
        } else {
            left++
        }
    }
    print("${resultLeft} ${resultRight}")
}