package com.codingtest.backjun.classfour.s3


fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }

    for (i in 1..N) {

        fun recursion(result: String, num: Int, count: Int) {

            if (count == M) {
                println(result)
                return
            }
            for (j in num..N) {
                recursion("$result $j", j, count+1)
            }
        }

        recursion(i.toString(), i, 1)
    }
}