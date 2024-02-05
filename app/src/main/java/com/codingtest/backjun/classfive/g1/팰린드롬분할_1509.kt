package com.codingtest.backjun.classfive.g1

/**
 * 못품
 * dp 2번 써야 되는 문제
 * 복잡하다.
 */
fun main() {


    val str = readln()
    val arr = CharArray(str.length+1)
    for (i in 1 until arr.size) {
        arr[i] = str[i-1]
    }
    val size = arr.size
    val dp = Array(size) {BooleanArray(size)}

    for (i in 1 until size) dp[i][i] = true
    for (i in 1 until size-1)
        if (arr[i] == arr[i+1]) dp[i][i+1] = true

    /**
     * 문자열 start 부터 start+글자수까지 펠린드롬인지 체크
     * 체크 방식
     *      1. 맨 앞과 맨 뒤 문자가 같은 경우
     *      2. 맨 앞과 뒤를 뺀 문자가 팰린드롬인 경우
     */
    for (len in 2 until  size) {
        for (start in 1 until size-len) {
            if (arr[start] == arr[start+len] && dp[start+1][len+start-1] ) {
                dp[start][start+len] = true
            }
        }
    }

    /**
     * minP[end] = end 인덱스의 문자열까지 분할 개수 최솟값
     * minP[0] = 0 고정이기 때문에 문자열은 1부터 시작한다.
     *
     * 만약 start ~ end가 팰린드롬일 경우
     *      현재 minP[end] 와 (start 이전의 경우 즉 minP[start-1]에 + 현재 팰린드롬) 중 최소값
     *
     * ex) CCABA
     *      - end = 5, start = 3 -> ABA 인 경우
     *        minP[start-1] 즉 CC인 경우 1과 + 현재 문자열 팰린드롬 1
     */
    val minP = IntArray(size)
    for (end in 1 until size) {
        minP[end] = Int.MAX_VALUE
        for (start in 1 .. end) {
            if (dp[start][end]) {
                minP[end] = Math.min(minP[end], minP[start-1] + 1)
            }
        }
    }
    print(minP[size-1])
}