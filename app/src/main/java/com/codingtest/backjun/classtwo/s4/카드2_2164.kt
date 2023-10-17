package com.codingtest.backjun.classtwo.s4

import java.util.LinkedList

fun main() {
    val list = LinkedList<Int>()
    for (i in 1..readln().toInt()) {
        list.add(i)
    }
    while (list.size > 1) {
        list.removeAt(0)
        if (list.size == 1) break
        list.add(list.removeAt(0))
    }
    print(list[0])
}