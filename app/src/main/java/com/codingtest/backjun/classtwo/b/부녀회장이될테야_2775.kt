package com.codingtest.backjun.classtwo.b

fun main() {
//    repeat(readln().toInt()) {
//        val k = readln().toInt()
//        val n = readln().toInt()
//        println(dp(k, n))
//    }
    부녀회장_다른풀이()
}

fun dp(k: Int, n:Int): Int { //층 호수
    if (n == 1) return 1
    if (k == 0) return n
    return dp(k, n-1) + dp(k-1, n)
}

fun 부녀회장_다른풀이() {
    val mat = Array(15) {IntArray(15)}
    for (i in 0 until 15) {
        mat[0][i] = i+1
        mat[i][0] = 1
    }

    for (i in 1 until 15) {
        for (j in 1 until 15) {
            mat[i][j] = mat[i-1][j] + mat[i][j-1]
        }
    }

    repeat(readln().toInt()) {
        val k = readln().toInt()
        val n = readln().toInt() - 1
        println(mat[k][n])
    }
}