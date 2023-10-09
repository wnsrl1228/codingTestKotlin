package com.codingtest.backjun.classtwo.b

fun main() {
    val (p1, p2) = readln().split(" ").map { it.toInt() }
    var gcd = 0
    if (p1 >= p2) gcd =gcd(p1, p2)
    else gcd = gcd(p2, p1)

    println(gcd)
    println((p1*p2)/gcd)

}
fun gcd(N: Int, R: Int): Int {
    var n = N
    var r = R
    while (r != 0) {
        val tmp = r
        r = n % r
        n = tmp
    }
    return n
}