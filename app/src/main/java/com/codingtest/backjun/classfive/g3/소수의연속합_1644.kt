package com.codingtest.backjun.classfive.g3

import kotlin.math.sqrt

/**
 * 에라토스테네스의 체 + 투 포인터
 *      - n이하의 소수만 따로 구해줌
 *      - 투 포인터로 합이 n인 경우의 개수를 구해줌
 */
fun main() {

    val num = readln().toInt()

    val arr = BooleanArray(num+1) {true}
    arr[0] = false
    arr[1] = false
    fun findPrime(){

        for (i in 2 .. sqrt(num.toFloat()).toInt()) {
            if (arr[i]) {
                for (j in i*i..num step i) {
                    arr[j] = false
                }
            }
        }
    }
    findPrime()

    val list = mutableListOf<Int>()
    for (i in 0..num) {
        if (arr[i]) list.add(i)
    }

    var result = 0
    var left = 0
    var right  = 0
    var sum = 0
    while (left <= right) {

        if (sum < num) {
            if (right == list.size)
                break
            sum += list[right]
            right++
        } else if (sum >= num) {
            if (sum == num) result++
            sum -= list[left]
            left++
        }
    }
    print(result)
}