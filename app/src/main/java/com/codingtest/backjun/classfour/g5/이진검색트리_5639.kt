package com.codingtest.backjun.classfour.g5

/**
 * 매번 입력된 노드를 이진탐색으로 알맞은 위치에 넣어준다.
 * 그 다음 완성된 이진트리를 후위 순회하여 결과를 얻는다.
 */
fun main() = with(System.`in`.bufferedReader()) {


    class Node(val number: Int, var left: Node?, var right: Node?, var parent: Node?)
    val root = Node(readLine().toInt(), null, null, null)
    while (true) {

        val num = readLine()?.toInt() ?: break

        fun recursion(root: Node?, parent: Node?, num: Int) {

            if (root == null) {
                if (num < parent!!.number) {
                    parent.left = Node(num, null, null, parent)
                } else {
                    parent.right = Node(num, null, null, parent)
                }
                return
            }
            // 왼쪽
            if (num < root.number) {
                recursion(root.left, root, num)
            } else {
                // 오른쪽
                recursion(root.right, root, num)
            }
        }

        recursion(root, null, num)
    }


    val sb = StringBuilder()
    fun postOrder(node: Node?) {
        if (node == null)
            return
        postOrder(node.left)
        postOrder(node.right)
        sb.appendLine(node.number)
    }
    postOrder(root)
    print(sb)

//    이진검색트리_배열풀이()
}

/**
 *  전위 순회한 값의 배열 위치를 이용한 풀이
 *  ex) 50 [30 24 5 28 45] [98 52 60]
 *      50보다 작은 값들은 왼쪽, 큰 값은 오른쪽 자식이 된다.
 *      따라서 매번 재귀로 구간을 나눠서 후위순회를 해주면 된다.
 */
fun 이진검색트리_배열풀이() = with(System.`in`.bufferedReader()) {


    val arr = ArrayList<Int>()
    while (true) {
        val num = readLine()?.toInt() ?: break
        arr.add(num)
    }

    val sb = StringBuilder()

    fun postOrder(start: Int, end: Int) {
        if (start > end)
            return

        // 왼쪽 자식 범위 구하기
        var mid = start+1
        while (mid <= end && arr[start] > arr[mid]) {
            mid++
        }

        postOrder(start+1, mid-1) // 왼쪽 자식
        postOrder(mid, end)                 // 오른쪽 자식
        sb.appendLine(arr[start])
    }

    postOrder(0, arr.size-1)
    print(sb)
}
