package com.codingtest.backjun.classfour.g2

import java.math.BigInteger

/**
 *
 * F(n) = F(n-1) + F(n-2)                                      // F(n-1) = F(n-2) + F(n-3)
 *      = F(n-2) + F(n-3) + F(n-2)    =>  2F(n-2) + F(n-3)     // 2F(n-2) = 2F(n-3) + 2F(n-4)
 *      = 2F(n-3) + 2F(n-4) + F(n-3)  =>  3F(n-3) + 2F(n-4)    // 3F(n-3) = 3F(n-4) + 3F(n-5)
 *      = 3F(n-4) + 3F(n-5) + 2F(n-4) =>  5F(n-4) + 3F(n-5)    // 5F(n-4) = 5F(n-5) + 5F(n-6)
 *      = 5F(n-5) + 5F(n-6) + 3F(n-5) =>  8F(n-5) + 5F(n-6)
 *
 *      규칙이 생김 -> F의 앞에 2,3,5,8 - 1,2,3,5
 *      해당 숫자는 피보나치와 동일한 규칙
 *      따라서 해당 숫자를 F() 형식으로 변환할 수 있음
 *
 *      피보나치 규칙
 *      0 1 2 3 4 5 6 7  8
 *      0 1 1 2 3 5 8 12 21
 *
 * F(n) = F(n-1) + F(n-2)     => F(2)F(n-1) + F(1)F(n-2)
 *      =  2F(n-2) + F(n-3)   => F(3)F(n-2) + F(2)F(n-3)
 *      =  3F(n-3) + 2F(n-4)  => F(4)F(n-3) + F(3)F(n-4)
 *      =  5F(n-4) + 3F(n-5)  => F(5)F(n-4) + F(4)F(n-5)
 *      =  8F(n-5) + 5F(n-6)  => F(6)F(n-5) + F(5)F(n-6)
 *
 *      이제 숫자를 k로 대입하면
 *
 *      F(n) = F(k+1)*F(n-k) + F(k)*F(n-k-1)
 *      라는 식이 완성된다.
 *
 *      이제 n에 임의 수 n이 짝수인 경우 2k 을 대입
 *              -> F(2k) = F(k+1)*F(k) + F(k)*F(k-1)
 *              k에 k/2 대입해서 2k -> k로 만들어줌
 *              -> F(k) = F((k+2)/2)*F(k/2) + F(k/2)F((k-2)/2)
 *                      = F(k/2){F((k+2)/2) + F((k-2)/2)}
 *
 *      임의 수 n이 홀수인 경우 2k+1 을 대입
 *              -> F(2k+1) = F(k+1)*F(k+1) + F(k)*F(k)
 *              k에 (k-1)/2 대입해서 2k+1 -> k로 만들어줌    // 2k+1 = k
 *              -> F(k) = F((k+1)/2)*F((k+1)/2) + F((k-1)/2)*F((k-1)/2)
 *                      = F((k+1)/2)^2 + F((k-1)/2)^2
 *
 *      모든 식이 완성되었다.
 *      f(n) n=0 일 경우 0
 *           n=1 일 경우 1
 *           짝수 F(k/2){F((k+2)/2) + F((k-2)/2)}
 *           홀수 F((k+1)/2)^2 + F((k-1)/2)^2
 *
 *
 *
 */
fun main() {
    var n = readln().toLong()

    val div = 1000000007L

    val map = HashMap<Long, Long>()

    map[0] = 0
    map[1] = 1
    map[2] = 1

    fun solve(num: Long): Long {

        if (map.containsKey(num)) return map[num]!!

        var result = 0L
        // 짝수일 경우 = F(k/2){F((k+2)/2) + F((k-2)/2)}
        if (num % 2L == 0L) {
            val a = solve(num/2) % div
            val b = solve((num+2)/2) % div
            val c = solve((num-2)/2) % div
            result = a * ((b+c) % div) % div
        }
        // 홀수일 경우 = F((k+1)/2)^2 + F((k-1)/2)^2
        else {
            val a = solve((num+1)/2) % div
            val b = solve((num-1)/2) % div
            result = ((a*a)% div + (b*b)% div) % div
        }

        map[num] = result
        return result
    }
    print(solve(n))
}
