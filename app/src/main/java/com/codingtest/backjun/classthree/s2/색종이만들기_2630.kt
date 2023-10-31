package com.codingtest.backjun.classthree.s2

fun main() = with(System.`in`.bufferedReader()){
    val N = readLine().toInt()
    val arr = Array(N) { IntArray(N) }
    repeat(N) { index ->
        val line = readLine().split(" ").map {it.toInt()}
        for (i in line.indices) {
            arr[index][i] = line[i]
        }
    }
    var w_count = 0
    var b_count = 0
    fun div_map(x: Int, y: Int, n: Int) {

        var tmp = 0
        for (i in x until x+n) {
            for (j in y until y+n) {
                if (arr[i][j] == 1) {
                    tmp++
                }
            }
        }
        if (tmp == 0) {
            w_count++
        } else if (tmp == n*n) {
            b_count++
        } else {
            div_map(x, y, n/2)
            div_map(x+n/2, y, n/2)
            div_map(x, y+n/2, n/2)
            div_map(x+n/2, y+n/2, n/2)
        }
    }
    div_map(0,0,N)
    println(w_count)
    println(b_count)
}

