package com.codingtest.backjun.classtwo.s4


fun main() = with(System.`in`.bufferedReader()){
    val (h, w) = readLine().split(" ").map { it.toInt() }

    val list = List(h) { readLine() }

    var min = h*w
    for (i in 0..h-8) {
        for (j in 0..w - 8) {

            var count = 0
            for (x in i until i+8) {
                for (y in j until j+8) {
                    if ((x+y) % 2 ==0 ) {
                        if (list[x][y] != 'W') count++
                    } else {
                        if (list[x][y] == 'W') count++
                    }
                }
            }
            min = count.coerceAtMost(64 - count).coerceAtMost(min)
        }
    }
    print(min)
}