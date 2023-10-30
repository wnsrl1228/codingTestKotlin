package com.codingtest.backjun.classthree.s2

fun main() {
    val str = readln()

    val numArr = str.split("[\\+\\-]".toRegex()).map { it.toInt() }

    var sum = numArr[0]
    var tmp = 0 // - 뒤에 +가 온 경우 임시 저장값
    var index = 1
    var isMinus = false // 앞의 연산이 - 인지 여부
    for (c in str) {
        if (c == '-') {
            if (!isMinus) {
                isMinus = true
                tmp += numArr[index]
            } else {
                sum -= tmp
                tmp = 0
                sum -= numArr[index]
            }
            index++

        } else if (c == '+') {
            if (!isMinus) {
                sum += numArr[index]
            } else {
                tmp += numArr[index]
            }
            index++
        }
    }
    if (isMinus) {
        sum -= tmp
    } else {
        sum += tmp
    }
    print(sum)
//    잃어버린괄호_개선된코드()
}

/**
 * -뒤에 +를 묶어줘야 최솟값을 구할 수 있음
 * 따라서 -뒤 +값을 모두 -와 같이 처리해주면 됨
 */
fun 잃어버린괄호_개선된코드() {
    var str = readln()
    var tmp = ""
    str += "L" // 문자열 마지막 표시
    var isMinus = false
    var answer = 0

    for (i in str.indices) {

        if (str[i] == '-' || str[i] == '+' || str[i] == 'L') {
            if (isMinus) {
                answer -= tmp.toInt()
            } else {
                answer += tmp.toInt()
            }
            tmp = ""
            if (str[i] == '-')
                isMinus = true
            continue
        }
        tmp += str[i]
    }
    print(answer)
}