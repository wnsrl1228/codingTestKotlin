package com.codingtest.backjun.classfour.s2

fun main() {

    val sb = StringBuilder()
    val (N, M) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }.sorted()

    val map = mutableMapOf<String, Boolean>()
    val visited = BooleanArray(N)

    for (i in arr.indices) {
        fun recursion(result: String, count: Int) {

            if (count == M) {
                if (map.containsKey(result)) return
                map[result] = true
                sb.appendLine(result)
                return
            }

            for (i in 0..<N) {
                if (!visited[i]) {
                    visited[i] = true
                    recursion("$result ${arr[i]}", count+1)
                    visited[i] = false
                }
            }

        }
        visited[i] = true
        recursion(arr[i].toString(), 1)
        visited[i] = false
    }
//    main2()
}

fun n과m9_다른풀이() {

    val sb = StringBuilder()
    val (N, M) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }.sorted()

    val nums = IntArray(M)
    val visited = BooleanArray(N)

    fun recursion(count: Int) {

        if (count == M) {
            nums.forEach { sb.append("$it ") }
            sb.appendLine()
            return
        }
        var prev = -1
        for (i in 0..<N) {
            if (!visited[i] && prev != arr[i]) {
                visited[i] = true
                nums[count] = arr[i]
                recursion(count+1)
                visited[i] = false
                prev = arr[i]
            }
        }
    }
    recursion(0)
    print(sb)
}
