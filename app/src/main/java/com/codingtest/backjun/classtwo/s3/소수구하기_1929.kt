package com.codingtest.backjun.classtwo.s3

fun main() {
    val (M, N) = readln().split(" ").map { it.toInt() }
    val sb = StringBuilder()
    for (i in M..N) {
        if (isPrime(i)) sb.appendLine(i)
    }
    print(sb)

}
fun isPrime(n : Int) : Boolean {
    if (n < 2) return false
    for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}

fun 소수구하기_에라토스테네스의체_풀이() {
    val (M, N) = readln().split(" ").map { it.toInt() }
    val sb = StringBuilder()

    val prime = BooleanArray(N+1) // false
    // 2부터 N까지 돌면서 각 수의 배수를 체크하여 배제시킴
    for (i in 2..N) {
        if (prime[i]) {
            continue
        }
        if (i >= M) {
            sb.appendLine(i)
        }
        var j = i + i
        while (j <= N) {
            prime[j] = true
            j += i
        }
    }

    print(sb)
}