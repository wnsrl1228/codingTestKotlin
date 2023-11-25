package com.codingtest.backjun.classfour.s1

fun main() = with(System.`in`.bufferedReader()){
    val N = readLine().toInt()
    val map = mutableMapOf<String, Node>()
    repeat(N) {
        val (name, left, right) = readLine().split(" ")

        if (!map.containsKey(name)) {
            map[name] = Node(name, null, null)
        }
        if (!map.containsKey(left)) {
            map[left] = Node(left, null, null)
        }
        if (!map.containsKey(right)) {
            map[right] = Node(right, null, null)
        }
        map[name]!!.left = if (left == ".") null else map[left]

        map[name]!!.right = if (right == ".") null else map[right]
    }

    fun preOrder(node: Node?) {
        if (node == null)
            return
        print(node.name)
        preOrder(node.left)
        preOrder(node.right)
    }
    fun inOrder(node: Node?) {
        if (node == null)
            return
        inOrder(node.left)
        print(node.name)
        inOrder(node.right)
    }
    fun postOrder(node: Node?) {
        if (node == null)
            return
        postOrder(node.left)
        postOrder(node.right)
        print(node.name)
    }
    preOrder(map["A"])
    println()
    inOrder(map["A"])
    println()
    postOrder(map["A"])

}
class Node(val name: String, var left: Node?, var right: Node?)