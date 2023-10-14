package com.codingtest.backjun.classtwo.s5

import java.math.BigInteger

// 수학적 사고력이 필요한 문제.

fun main() {
    var N = readln().toInt()
    var result = 0
    var value = BigInteger("1")

    while (N > 1) {
        value *= BigInteger(N.toString())
        while (value % BigInteger("10") == BigInteger("0")) {
            result++
            value /= BigInteger("10")
        }
        N--
    }
    print(result)
}
// 5의 개수 * 짝수 = 0의 개수인데 짝수는 많으므로 5의 개수만 생각해주면 된다.
fun 팩토리얼0의개수_개선된코드() {
    val num = readLine()!!.toInt()
    println(num / 5 + num / 25 + num / 125)
}

