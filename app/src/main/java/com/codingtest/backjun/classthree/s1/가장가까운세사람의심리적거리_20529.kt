package com.codingtest.backjun.classthree.s1



fun main() = with(System.`in`.bufferedReader()) {

    fun dist(s1: String, s2: String): Int{
        var count = 0
        for (i in 0 until 4) {
            if (s1[i] != s2[i]) count++
        }
        return count
    }

    var t = readLine().toInt()
    while (t-- > 0) {
        val n = readLine().toInt()
        val mbti = readLine().split(" ")
        if (n > 16*16) {
            println(0)
            continue
        }

        var min = Int.MAX_VALUE
        for (i in 0..<n-2) {
            for (j in i+1..<n-1) {
                for (l in j + 1..<n) {
                    min = Math.min(min, dist(mbti[i], mbti[j]) + dist(mbti[i], mbti[l]) + dist(mbti[j], mbti[l]))
                }
            }
        }
        println(min)
    }
}
// INTJ -> ENTJ ENTJ
//         1

// a  b c
// a b 하나 차이남
// a c 두개 차이
// b c 하나가 차이가 ?

// ㅁ

/** 거리만 구하면 된다. -> mbti가 뭔지는 중요하지 않다.
 * 0 0 3 4 3  n*n
 * 0 0 3 4 3
 * 3 3 0 1 2          // aaaa aaab aabc
 * 4 4 1 0 1  // 2 4   aaab aaaa aaab
 * 3 3 2 1 0
 *
 *
 *
 *
 *  aaaa bbbb abab 4 2 2
 */