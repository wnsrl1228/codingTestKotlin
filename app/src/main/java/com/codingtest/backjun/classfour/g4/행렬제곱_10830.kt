package com.codingtest.backjun.classfour.g4

import java.io.StreamTokenizer

/**
 * 행렬곱셈 + 제곱 문제
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{

    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input()
    input()
    var B = nval.toLong()

    val arr = Array(N) { IntArray(N) {input()} }

    fun matrixMultiplication(matrix1: Array<IntArray>, matrix2: Array<IntArray>): Array<IntArray>  {
        val tResult =  Array(N) {IntArray(N)}
        for (i in 0 until N) {
            for (j in 0 until N) {
                for (l in 0 until N) {
                    tResult[i][j] += matrix1[i][l] * matrix2[l][j]
                }
                tResult[i][j] %= 1000
            }
        }

        return tResult
    }

    var current = arr.copyOf()
    // 단위 행렬: 곱했을 때 자기자신이 나옴
    var result = Array(N) { i->
        IntArray(N) { j->
            if (i==j) 1
            else 0
        }
    }

    var square = 1L
    /**
     * while로 제곱을 늘려가면서 최종적으로 B를 0으로 만들면 됨
     * ex) B = 10 일 경우 1 2 4 8 -> B = 2,  1 2
     */
    while (B != 0L) {
        square*=2
        if (square <= B) {
            current = matrixMultiplication(current, current)
        } else if (square > B) {
            B -= square/2
            square = 1L
            result = matrixMultiplication(result, current)
            current = arr.copyOf()
        }
        if (square == B) {
            result = matrixMultiplication(result, current)
            break
        }
    }
    for (rows in result) {
        for (col in rows) {
            print("$col ")
        }
        println()
    }
//    행렬제곱_재귀풀이()
}

/**
 * 재귀 top-down 방식
 */
fun 행렬제곱_재귀풀이() = StreamTokenizer(System.`in`.bufferedReader()).run{

    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input()
    input()
    var B = nval.toLong()

    val arr = Array(N) { IntArray(N) {input() % 1000} }


    fun matrixMultiplication(matrix1: Array<IntArray>, matrix2: Array<IntArray>): Array<IntArray>  {
        val tResult =  Array(N) {IntArray(N)}
        for (i in 0 until N) {
            for (j in 0 until N) {
                for (l in 0 until N) {
                    tResult[i][j] += matrix1[i][l] * matrix2[l][j]
                }
                tResult[i][j] %= 1000
            }
        }

        return tResult
    }

    fun solve(ex: Long, a: Array<IntArray>): Array<IntArray> {

        if (ex == 1L) {
            return a
        }

        var result = solve(ex / 2, a)

        // 제곱
        result = matrixMultiplication(result, result)

        if (ex % 2 == 1L) {
            result = matrixMultiplication(result, arr)
        }

        return result
    }

    val result = solve(B, arr)

    for (rows in result) {
        for (col in rows) {
            print("$col ")
        }
        println()
    }
}