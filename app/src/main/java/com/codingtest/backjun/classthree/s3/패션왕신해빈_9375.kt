package com.codingtest.backjun.classthree.s3

fun main() {
    repeat(readln().toInt()) {

        val map = mutableMapOf<String, Int>()
        repeat(readln().toInt()) {
            val clothes = readln().split(" ")
            map.put(clothes[1], map.getOrDefault(clothes[1], 0) + 1)
        }
        var sum = 1
        map.values.forEach {
            sum *= it + 1
        }
        println(sum - 1)
    }
}