package com.codingtest.backjun.classfour.g4

import java.util.Stack

/**
 * 문자열 + stack 응용 문제
 * - 내가 푼 풀이는 반례 처리가 많아 까다로움
 * - 개선된 풀이는 간단하다.
 */
fun main() {
    val strArr = readln().toCharArray()
    val bomb = readln()

    class Node(var arrIndex:Int, var bombIndex: Int)
    val stack = Stack<Node>()

    /**
     * 폭탄문자열일 경우 stack에 넣어주며 다 모일 경우 삭제해준다.
     */
    for (i in strArr.indices) {

        /**
         * 스택이 비어있고, 문자가 폭탄의 첫 글자면 stack에 추가
         */
        if (stack.isEmpty()) {
            if (strArr[i] == bomb[0]) {
                stack.add(Node(i, 0))
            }
        } else {
            /**
             * 스택이 비어있지 않을 때
             * if 스택 마지막 값의 다음 폭탄문자열이 현재 문자랑 같으면
             *      stack에 추가
             * else
             *      if 문자가 폭탄의 첫 글자면 stack에 추가
             *      else stack 초기화(더 이상 폭탈문자열이 될 수 없는 조건)
             */
            val node = stack.peek()
            if (strArr[i] == bomb[node.bombIndex+1]) {
                stack.add(Node(i, node.bombIndex+1))
            } else {
                if (strArr[i] == bomb[0]) {
                    stack.add(Node(i, 0))
                } else {
                    stack.clear()
                }
            }
        }

        // 폭탄 문자열이 스택에 완성된 경우
        if (stack.isNotEmpty() && stack.peek().bombIndex == bomb.length-1) {
            for (i in 0..<bomb.length) {
                strArr[stack.pop().arrIndex] = '-'
            }
        }
    }

    val sb = StringBuilder()
    for (i in strArr) {
        if (i != '-') sb.append(i)
    }
    print(if (sb.isBlank()) "FRULA" else sb.toString())
}

/**
 * 모든 문자를 stack에 넣고 폭탄일 경우에만 제거해준다.
 */
fun 문자열폭발_개선된코드() {
    val str = readln()
    val bomb = readln()
    val sb = StringBuilder()

    fun hasBomb(): Boolean {
        val diff = sb.length - bomb.length
        if (diff < 0) return false

        for (i in bomb.indices) {
            if (sb[diff+i] != bomb[i]) return false
        }
        return true
    }
    str.forEach { c ->
        sb.append(c)
        while (hasBomb()) {
            sb.delete(sb.length - bomb.length, sb.length)
        }
    }
    print(sb.ifBlank { "FRULA" })
}