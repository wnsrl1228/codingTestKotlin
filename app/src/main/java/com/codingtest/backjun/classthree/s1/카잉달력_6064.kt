package com.codingtest.backjun.classthree.s1

// https://zoosso.tistory.com/280 참고
fun main() = with(System.`in`.bufferedReader()) {

    fun GCD(a: Int, b: Int): Int {
        // 나누어 떨어질 때 까지
        var x = a
        var y = b
        while (x % y != 0) {
            val temp = x % y
            x = y
            y = temp
        }
        return y
    }
    fun LCM(a: Int, b: Int): Int {
        // a > b보다 크도록 배치
        var x = a
        var y = b
        if (a < b) {
            val temp = x
            x = y
            y = temp
        }
        return x * y / GCD(x, y)
    }

    val sb = StringBuilder()
    repeat(readLine().toInt()) {
        var (M, N, x, y) = readLine().split(" ").map {it.toInt()}

        val max = LCM(M, N);
        var ix = 0
        var iy = 0

        while (true) {
            val temp_x = M * ix + x;
            val temp_y = N * iy + y;

            if (temp_x > max || temp_y > max) {
                sb.appendLine(-1)
                break
            }
            if (temp_x == temp_y) {
                sb.appendLine(temp_x)
                break
            } else {
                if (temp_x < temp_y) ix++
                else iy++
            }
        }
    }
    print(sb)
}