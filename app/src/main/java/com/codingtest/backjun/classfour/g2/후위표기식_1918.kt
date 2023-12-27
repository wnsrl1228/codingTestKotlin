package com.codingtest.backjun.classfour.g2

import java.util.Stack

/**
 * 스택 활용 문제
 *
 * 경우의 수
 *       A*B+C -> AB*C+  // 우선순위가 낮은 연산자가 들어오면 그보다 큰 우선순위를 가진 연산자를 스택에서 빼서 붙임
 *       A/B/C -> AB/C/  // 우선순위가 같은 연산자가 들어오면 같은 우선순위를 가진 연산자를 스택에서 빼서 붙임
 *       A+B*C -> ABC*+  // 우선순위가 높은 연산자가 들어오면 그냥 스택에 넣음
 *       괄호의 경우 우선순위 레벨을 압도적으로 올려줌
 *
 *  ex)  A*((B+C)*D)*(A+C)
 *     = ABC+D**AC+*
 */
fun main() = with(System.`in`.bufferedReader()){
    val str = readLine()
    data class Node(val num: Char, var level: Int)
    val stack = Stack<Node>()
    val sb = StringBuilder()

    var level = 0

    for(c in str) {
        if (c == '+' || c == '-') {
            if (stack.isEmpty()) stack.add(Node(c, level))
            else {
                // 스택에 우선순위가 더 높거나 같은 연산자가 있을 경우
                if (stack.peek().level >= level) {
                    while (stack.isNotEmpty() && stack.peek().level >= level) {
                        sb.append(stack.pop().num)
                    }
                }
                stack.add(Node(c, level))
            }
        } else if (c == '*' || c == '/') {
            if (stack.isEmpty()) stack.add(Node(c, level+1))
            else {
                // 스택에 우선순위가 더 높거나 같은 연산자가 있을 경우
                if (stack.peek().level >= level+1) {
                    while (stack.isNotEmpty() && stack.peek().level >= level+1) {
                        sb.append(stack.pop().num)
                    }
                }
                stack.add(Node(c, level+1))
            }
        } else if (c == '(') {
            level+=100
        } else if (c == ')') {
            level-=100
        } else {
            sb.append(c)
        }

    }
    // 스택에 남아있는 연산자 붙여줌
    while (stack.isNotEmpty()) {
        sb.append(stack.pop().num)
    }
    print(sb.toString())
}