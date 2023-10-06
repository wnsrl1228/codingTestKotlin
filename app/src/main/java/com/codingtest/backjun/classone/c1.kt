package com.codingtest.backjun.classone

import java.lang.Exception

// import java.util.*

fun main() {
//    C1_1000_AB()
//    C1_1001_AB()
//    C1_1008_AB()
//    C1_1152_단어의개수()
//    C1_1157_단어공부()
//    C1_1157_단어공부("다른방법")
//    C1_1330_두수비교하기()
//    C1_2438_별찍기1()
//    C1_2439_별찍기2()
//    C1_2475_검증수()
//    C1_2557_HelloWorld()
//    C1_2562_최댓값()
//    C1_2562_최댓값("다른방법")
//    C1_2577_숫자의개수()
//    C1_2577_숫자의개수()
//    C1_2577_숫자의개수("다른방법")
//    C1_2675_문자열반복()
//    C1_2739_구구단()
//    C1_2741_N찍기()
//    C1_2753_윤년()
//    C1_2884_알람시계()
//    C1_2920_음계()
//    C1_2920_음계("다른풀이")
//    C1_3052_나머지()
//    C1_8958_OX퀴즈()
//    C1_9498_시험성적()
//    C1_10171_고양이()
//    C1_10172_개()
//    C1_10250_ACM호텔()
//    C1_10809_알파벳찾기()
//    C1_10818_최소최대()
//    C1_10869_사칙연산()
//    C1_10871_X보다작은수()
//    C1_10950_AB3()
//    C1_10951_AB4()
//    C1_10952_AB5()
//    C1_10998_AB()
//    C1_11654_아스키코드()
//    C1_11720_숫자의합()
//    C1_25083_새싹()
//    C1_27866_문자와문자열()
}


fun C1_1000_AB() {
    val sc = System.`in`.bufferedReader()
    print(sc.readLine().split(" ").map {it.toInt()}.sum())
}

fun C1_1001_AB() {
    print(readln().split(" ").map {it.toInt()}.reduce {a1, a2 -> a1 -a2})
}

fun C1_1008_AB() {
    print(readln().let {it[0].toString().toDouble() / it[2].toString().toDouble()})
}

fun C1_1152_단어의개수() {
    val input = readln().trim()
    if (input.isBlank()) {
        print(0)
    } else {
        print(input.split(" ").size)
    }
}

fun C1_1157_단어공부() {
    val eachCount = readln().uppercase().groupingBy { it }.eachCount()
    val max = eachCount.maxBy { it.value }
    var count = 0

    if (eachCount.count { it.value == max.value } >= 2) print("?")
    else print(max.key)
}

fun C1_1157_단어공부(다른방법 : String) {
    val G = IntArray(26)
    readln().uppercase().forEach {
        G[it - 'A']++
    }
    val max = G.maxOrNull()!!
    if (G.count {it == max} > 1) print("?")
    else print('A' + G.indexOf(max))
}

fun C1_1330_두수비교하기() {
    val (a, b) = readln().split(" ").map { it.toInt() }
    print(if (a > b) ">" else if (a < b) "<" else "==")
}

fun C1_2438_별찍기1() {
    var n = readln().toInt()
    var i = 1
    repeat(n) {
        println("*".repeat(i++))
    }
}

fun C1_2439_별찍기2() {
    val n = readln().toInt()
    var i = n - 1
    repeat(n) {
        println(" ".repeat(i--) + "*".repeat(it + 1))
    }
}

fun C1_2475_검증수() {
    val arr = readln().split(" ").map {it.toInt()}
    var sum = 0

    for (i in arr) {
        sum += i*i
    }
    print(sum%10)
}

fun C1_2557_HelloWorld() {
    print("Hello World!")
}

fun C1_2562_최댓값() {
    val arr = mutableListOf<Int>()
    repeat(9) {
        arr.add(readln().toInt())
    }
    val max = arr.max()
    println(max)
    println(arr.indexOf(max)+1)
}

fun C1_2562_최댓값(다른방법: String) {
    List(9) { readln().toInt() }.run { val max = max(); print("${max}\n${indexOf(max)+1}") }
}

fun C1_2577_숫자의개수() {
    val eachCount = (readln().toInt() * readln().toInt() * readln().toInt()).toString().split("").groupingBy { it }.eachCount()

    for (i in 0..9) {
        if (eachCount.containsKey(i.toString())) {
            println(eachCount[(i.toString())])
        } else {
            println(0)
        }
    }
}

fun C1_2577_숫자의개수(다른방법: String) {
    val n = (readln().toInt() * readln().toInt() * readln().toInt())
    val arr = IntArray(10) {0}
    n.toString().forEach {
        arr[it - '0']++
    }
    arr.forEach { println(it) }
}

fun C1_2675_문자열반복() {
    val n = readln().toInt()

    repeat(n) {
        val (count, str) = readln().split(" ")
        var result = StringBuffer("")
        str.forEach {
            result.append(it.toString().repeat(count.toInt()))
        }
        println(result)
    }
}

fun C1_2739_구구단() {
    val n = readln().toInt()
    for (i in 1..9) {
        println("$n * $i = ${n*i}")
    }
}

fun C1_2741_N찍기() {
    val n = readln().toInt()
    repeat(n) {
        println(it+1)
    }
}

fun C1_2753_윤년() {
    val n = readln().toInt()

    if ((n%4 == 0 && n%100 != 0) || n%400 == 0) print(1)
    else print(0)
}

fun C1_2884_알람시계() {
    val (h, m) = readln().split(" ").map { it.toInt() }

    if (m >= 45) {
        print("$h ${m-45}")
    } else if (h == 0) {
        print("23 ${60-(45-m)}")
    } else print("${h-1} ${60-(45-m)}")
}

fun C1_2920_음계() {
    var isAsc = true
    var isDes = true
    readln().split(" ").map { it.toInt() }.forEachIndexed {index, i ->
        if (isAsc) {
            if (i != index+1) isAsc = false
        }
        if (isDes) {
            if (i != 8-index) isDes = false
        }
    }
    if (isAsc) print("ascending")
    else if (isDes) print("descending")
    else print("mixed")
}

fun C1_2920_음계(다른풀이: String) {
    println(
        when (readln()) {
            "1 2 3 4 5 6 7 8" -> "ascending"
            "8 7 6 5 4 3 2 1" -> "descending"
            else -> "mixed"
        }
    )
}

fun C1_3052_나머지() {
    val map = mutableMapOf<Int, Boolean>()
    repeat(10) {
        val re = readln().toInt() % 42
        map[re] = true
    }
    print(map.keys.size)
}

fun C1_8958_OX퀴즈() {
    repeat(readln().toInt()) {
        var sum = 0
        var cur = 0
        readln().forEach { c ->
            if (c == 'O') {
                sum += ++cur
            } else {
                cur = 0
            }
        }
        println(sum)
    }
}

fun C1_9498_시험성적() {
    when(readln().toInt()) {
        in 90..100 -> print("A")
        in 80..89 -> print("B")
        in 70..79 -> print("C")
        in 60..69 -> print("D")
        else -> print("F")
    }
}

fun C1_10171_고양이() {
    println("\\    /\\")
    println(" )  ( ')")
    println("(  /  )")
    println(" \\(__)|")
}

fun C1_10172_개() {
    print("""|\_/|
|q p|   /}
( 0 )""${'"'}\
|"^"`    |
||_/=\\__|""")
}

fun C1_10250_ACM호텔() {
    repeat(readln().toInt()) {
        val (H, W, N) = readln().split(" ").map { it.toInt() }
        // 6 12 10
        var X = N / H
        if (N%H != 0) X++
        var Y = if (N%H == 0) H else N % H
        println(Y.toString() + String.format("%02d", X))
    }
}

fun C1_10809_알파벳찾기() {
    val arr = IntArray(26) {-1}
    readln().forEachIndexed() { i, c ->
        if (arr[c - 'a'] == -1) {
            arr[c - 'a'] = i
        }
    }
    print(arr.joinToString(" ")) // 기록
}

fun C1_10818_최소최대() {
    /* min, max 사용.
        readln().split(" ").map{it.toInt()}
        print("${a.min()} ${a.max()}")}
     */
    val N = readln().toInt()
    var arr = readln().split(" ").map { it.toInt() }.toIntArray()

    quickSort(arr, 0, arr.size-1)

    print("${arr[0]} ${arr[arr.size-1]}")
}
fun quickSort(arr: IntArray, left: Int, right: Int) {
    val n = arr[(left+right)/2]  // 1
    var pl = left         // 0
    var pr = right          // 1

    while (pl < pr) {
        while (arr[pl] < n) pl++
        while (arr[pr] > n) pr--
        if (pl <= pr) {
            val tmp = arr[pl]
            arr[pl] = arr[pr]
            arr[pr] = tmp
            pl++
            pr--
        }
    }
    if (left < pr) quickSort(arr, left, pr)
    if (right > pl)quickSort(arr, pl, right)
}

fun C1_10869_사칙연산() {
    val (a,b) = readln().split(" ").map { it.toInt() }
    println(a+b)
    println(a-b)
    println(a*b)
    println(a/b)
    println(a%b)
}

fun C1_10871_X보다작은수() {
    val (N, X) = readln().split(" ").map { it.toInt() }
    print(readln().split(" ").filter { it.toInt() < X }.joinToString(" "))
}

fun C1_10950_AB3() {
    repeat(readln().toInt()) {
        println(readln().split(" ").sumOf { it.toInt() })
    }
}

fun C1_10951_AB4() {
    while (true) {
        try {
            println(readln().split(" ").sumOf { it.toInt() })
        } catch (e: Exception) {
            break
        }
    }
}

fun C1_10952_AB5() {
    while (true) {
        readln().split(" ").sumOf { it.toInt() }.let {
            if (it == 0) return
            else println(it)
        }
    }
}

fun C1_10998_AB() {
    print(readln().split(" ").reduce {a1, a2 -> (a1.toInt() * a2.toInt()).toString()})
}

fun C1_11654_아스키코드() {

    print(readln()[0].code) // 아스키코드 변환 메서드가 존재한다.

    readln().let {
      if (it[0].isDigit()) {
          print(it[0].toString().toInt() + 48)
      } else {
          print(it[0].toInt())
      }
    }
}

fun C1_11720_숫자의합() {
//    readln()
//    print(readln().sumOf { it.toString().toInt() })

}

fun C1_25083_새싹() {
    print("""         ,r'"7
r`-_   ,'  ,/
 \. ". L_r'
   `~\/
      |
      |""")
}

fun C1_27866_문자와문자열() {
    val s = readln()
    print(s[readln().toInt() - 1])
}