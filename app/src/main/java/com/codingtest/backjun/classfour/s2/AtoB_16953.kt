package com.codingtest.backjun.classfour.s2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val st = StringTokenizer(readLine())
    var A = st.nextToken().toInt()
    var B = st.nextToken().toInt()

    var count = 0
    while (A < B) {
        count++
        B /= if (B % 2 == 0) 2
        else if (B % 10 == 1) 10
        else break
    }
    print(if (B == A) count+1 else -1)
}