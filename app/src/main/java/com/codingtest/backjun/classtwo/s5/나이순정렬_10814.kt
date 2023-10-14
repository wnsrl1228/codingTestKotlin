package com.codingtest.backjun.classtwo.s5

fun main() {
    val sb = StringBuilder("")
    List(readln().toInt()) { index ->
        val (year, name) = readln().split(" ")
        Person10814(year.toInt(), name, index)
    }.let { i ->
        i.sortedWith(compareBy( {it.year}, {it.index})).forEach { j ->
            sb.append("${j.year} ${j.name}\n")
        }
    }
    print(sb)
}
class Person10814(
    val year: Int,
    val name: String,
    val index: Int
)

fun 나이순정렬_개선된코드() {
    val sb = StringBuilder("")
    List(readln().toInt()) {  readln().split(" ") }
        .sortedBy { it[0].toInt() }
        .forEach { i ->
            sb.append("${i[0]} ${i[1]}\n")
        }
    print(sb)
}