package com.codingtest.backjun.classfour.s3

fun main() {
    val sb = StringBuilder()
    val (N, M) = readln().split(" ").map { it.toInt() }
    var arr = readln().split(" ").map { it.toInt() }.sorted()

    for (i in arr.indices) {

        fun recursion(result: String, index: Int, count: Int) {

            if (count == M) {
                sb.appendLine(result)
                return
            }

            for (j in index..<N) {
                recursion("$result ${arr[j]}", j, count+1)
            }
        }
        recursion(arr[i].toString(), i, 1)
    }
    print(sb)
}