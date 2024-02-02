package com.codingtest.backjun.classfive.g2

import java.io.StreamTokenizer

/**
 * 못 품
 * 기하학 문제
 *   - ccw 알고리즘을 이용해야 된다.
 *   - ccw 알고리즘은 벡터의 외적을 이용한 알고리즘이다.
 *   - 유튜브 참고 https://www.youtube.com/watch?v=iIDgR5uFy9o&t=870s
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Long {
        nextToken()
        return nval.toLong()
    }
    data class Point(val x: Long, val y: Long)
    val A =  Point(input(), input())
    val B =  Point(input(), input())
    val C =  Point(input(), input())
    val D =  Point(input(), input())

    fun getVector(a: Point, b: Point) : Point {
        return Point(b.x - a.x, b.y - a.y)
    }

    fun ccw(a: Point, b: Point) : Long{
        val cross =  a.x * b.y - a.y * b.x // 두 벡터의 외적
        if (cross > 0)
            return 1
        else if (cross == 0L)
            return 0
        else
            return -1
    }

    val AB = getVector(A, B)
    val AC = getVector(A, C)
    val AD = getVector(A, D)

    val ABxAC = ccw(AB, AC)
    val ABxAD = ccw(AB, AD)

    val CD = getVector(C, D)
    val CA = getVector(C, A)
    val CB = getVector(C, B)

    val CDxCA = ccw(CD, CA)
    val CDxCB = ccw(CD, CB)


    val r1 = ABxAC * ABxAD
    val r2 = CDxCA * CDxCB

    if (r1 <= 0 && r2 <= 0) {
        if (r1 == 0L && r2 == 0L) {  // 직선에 있는 경우
            if (Math.max(A.x, B.x) >= Math.min(C.x, D.x) // 겹치는 경우
                && Math.min(A.x, B.x) <= Math.max(C.x, D.x)
                && Math.max(A.y, B.y) >= Math.min(C.y, D.y)
                && Math.min(A.y, B.y) <= Math.max(C.y, D.y)) {
                print(1)
            } else {
                print(0)
            }
        } else {
            print(1)
        }
    } else {
        print(0)
    }
}