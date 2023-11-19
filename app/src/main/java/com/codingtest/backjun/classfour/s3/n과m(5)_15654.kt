package com.codingtest.backjun.classfour.s3

fun main() {
    val sb = StringBuilder()
    val (N, M) = readln().split(" ").map { it.toInt() }
    var arr = readln().split(" ").map { it.toInt() }.sorted()

    val map = mutableMapOf<Int,Boolean>()
    arr.forEach { i ->
        map[i] = false
    }
    for (i in arr) {

        fun recursion(result: String, num: Int, count: Int) {

            if (count == M) {
                sb.appendLine(result)
                return
            }
            for (j in arr) {
                if (map[j] == false) {
                    map[j] = true
                    recursion("$result $j", j, count+1)
                    map[j] = false
                }
            }
        }
        map[i] = true
        recursion(i.toString(), i, 1)
        map[i] = false
    }
    print(sb)
}