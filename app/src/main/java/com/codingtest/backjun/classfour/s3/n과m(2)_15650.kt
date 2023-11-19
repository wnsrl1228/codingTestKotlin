package com.codingtest.backjun.classfour.s3

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }

    for (i in 1..N) {

        fun recursion(result: String, num: Int, count: Int) {

            if (count == M) {
                println(result)
                return
            }
            for (i in num+1..N) {
                recursion("$result $i", i, count+1)
            }
        }

        recursion(i.toString(), i, 1)
    }
}