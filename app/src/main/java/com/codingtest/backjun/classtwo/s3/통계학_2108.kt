package com.codingtest.backjun.classtwo.s3

import java.util.PriorityQueue

fun main() {
    val n = readln().toInt()
    val arr = mutableListOf<Int>()
    repeat(n) { arr.add(readln().toInt()) }
    arr.sort()
    println(Math.round(arr.sum() / n.toFloat()))
    println(arr[n/2])

    val eachCount = arr.groupingBy { it }.eachCount()
    val max = eachCount.values.max()
    val pq = PriorityQueue<Int>()
    for (i in eachCount.keys) {
        if (eachCount[i] == max) pq.add(i)
    }
    if (pq.size > 1) {
        pq.poll()
        println(pq.poll())
    } else {
        println(pq.poll())
    }

    println(arr.last() - arr.first())
}