package com.codingtest.backjun.classtwo.s4

fun main() {
    val sb = StringBuilder("")
    val map = mutableMapOf<Int, Boolean>()
    readln()
    readln().split(" ").map { it.toInt() }.forEach { i ->
        map[i] = true
    }
    readln()
    readln().split(" ").map { it.toInt() }.forEach{ i ->
        if (map[i] != null) sb.append("1\n")
        else sb.append("0\n")
    }
    print(sb)
//    수찾기_이진탐색풀이()
}

fun 수찾기_이진탐색풀이() {

    val list = mutableListOf<Int>()
    readln()
    readln().split(" ").map { it.toInt() }.forEach{ i ->
        list.add(i)
    }
    list.sort()
    val sb = StringBuilder("")

    readln()
    readln().split(" ").map { it.toInt() }.forEach{ i ->
        sb.appendLine(binarySearch(list, i))
    }
    print(sb)
}
fun binarySearch(list: MutableList<Int>, t : Int) : Int {
    var left = 0
    var right = list.size -1
    while(left <= right){
        val mid = (left+right)/2
        if(t == list[mid]){
            return 1
        }else if(t < list[mid]){
            right = mid-1
        }else{
            left = mid +1
        }
    }
    return 0
}