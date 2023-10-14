package com.codingtest.backjun.classtwo.s5

fun main() {
    val map = mutableMapOf<Person, Int>()
    repeat(readln().toInt()) {
        val (w, h) = readln().split(" ").map {it.toInt()}
        map.put(Person(w,h), 1)
    }

    for (p1 in map.keys) {
        for (p2 in map.keys) {
            if (p1.weight < p2.weight && p1.height < p2.height) map[p1] = map[p1]!!.plus(1)
        }
        print("${map[p1]} ")
    }
}

class Person(var height: Int, var weight: Int)

fun 덩치_개선된코드() {
    List(readLine()!!.toInt()) { readLine()!!.split(" ").map { it.toInt() } }
        .let{ i -> // i = [[55, 185], [58, 183], [88, 186], [60, 175], [46, 155]]
            i.forEach { j ->
                // it는 현재, j는 비교대상
                print("${i.count{it[0]>j[0]&&it[1]>j[1]}+1} ")
            }
        }
}