package com.codingtest.backjun.classthree.g4

import java.util.PriorityQueue
import java.util.TreeMap

fun main() = with(System.`in`.bufferedReader()){

    val sb = StringBuilder()
    repeat(readLine().toInt()) {

        val pqMin = PriorityQueue<Int>()
        val pqMax = PriorityQueue<Int>(reverseOrder())

        val pqMinTmp = PriorityQueue<Int>()
        val pqMaxTmp = PriorityQueue<Int>(reverseOrder())
        var size = 0
        repeat(readLine().toInt()) {
            val (command, num) = readLine().split(" ")

            if (command == "I") {
                pqMin.add(num.toInt())
                pqMax.add(num.toInt())
                size++
            } else if (command == "D" && size != 0){
                if (num == "1") {
                    while (pqMaxTmp.isNotEmpty() && pqMaxTmp.peek() == pqMax.peek()) {
                        pqMaxTmp.poll()
                        pqMax.poll()
                    }
                    pqMinTmp.add(pqMax.poll())
                } else if (num == "-1") {
                    while (pqMinTmp.isNotEmpty() && pqMinTmp.peek() == pqMin.peek()) {
                        pqMinTmp.poll()
                        pqMin.poll()
                    }
                    pqMaxTmp.add(pqMin.poll())
                }
                size--

            }
        }
        if (size == 0) {
            sb.appendLine("EMPTY")
        } else {
            while (pqMaxTmp.isNotEmpty() && pqMaxTmp.peek() == pqMax.peek()) {
                pqMaxTmp.poll()
                pqMax.poll()
            }
            while (pqMinTmp.isNotEmpty() && pqMinTmp.peek() == pqMin.peek()) {
                pqMinTmp.poll()
                pqMin.poll()
            }
            sb.appendLine("${pqMax.poll()} ${pqMin.poll()}")
        }
    }
    print(sb)
}

/**
 * TreeMap은 균형 이진 검색 트리으로 각 노드의
 *      왼쪽 노드는 작은 키 값을 가지고
 *      오른쪽 노드는 큰 키 갑슬 가진다.
 *
 *      따라서 first = 최솟값, last = 최댓값 이다
 */
fun 이중우선순위큐_treeMap풀이() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    repeat(readLine().toInt()) {

        val treeMap = TreeMap<Int, Int>()
        repeat(readLine().toInt()) {
            val (command, num) = readLine().split(" ")

            if (command == "I") {
                treeMap[num.toInt()] = treeMap.getOrDefault(num.toInt(), 0) + 1
            } else {
                if (num == "1" && treeMap.isNotEmpty()) {
                    val key = treeMap.lastKey()
                    if (treeMap[key] == 1) {
                        treeMap.remove(key)
                    } else {
                        treeMap[key] = treeMap[key]!! - 1
                    }
                } else if (num == "-1" && treeMap.isNotEmpty()) {
                    val key = treeMap.firstKey()
                    if (treeMap[key] == 1) {
                        treeMap.remove(key)
                    } else {
                        treeMap[key] = treeMap[key]!! - 1
                    }
                }
            }
        }
        if (treeMap.isEmpty()) sb.appendLine("EMPTY")
        else sb.appendLine("${treeMap.lastKey()} ${treeMap.firstKey()}")
    }
    print(sb)
}