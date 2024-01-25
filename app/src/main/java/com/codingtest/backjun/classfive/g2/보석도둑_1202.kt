package com.codingtest.backjun.classfive.g2

import java.io.StreamTokenizer
import java.util.PriorityQueue

/**
 * 못 품
 * 그리드 + 우선순위 큐
 *   - 보석의 무게는 오름차순, 가치는 내림차순으로 큐에 담음
 *   - 가방은 오름차순으로 정렬
 *   - 작은 가방부터 하나씩 진행
 *          - 보석의 가치가 내림차순인 temp 큐를 생성
 *          - 보석 무게가 가방 무게보다 적거나 같을 때 temp 큐에 담음
 *          - temp 큐에서 가장 가치가 큰 보석을 가방에 담아줌
 **/
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    data class Jewelry(val weight: Int, val price: Int)
    val N = input() // 보석 개수
    val K = input() // 가방 개수
    val pq = PriorityQueue<Jewelry>(compareBy<Jewelry> {it.weight}.thenByDescending { it.price })
    val bags = IntArray(K)
    // 각 보석의 정보
    repeat(N) {
        val M = input() // 무게
        val V = input() // 가격
        pq.add(Jewelry(M, V))
    }

    // 각 가방의 정보
    repeat(K) { i ->
        val C = input() // 가방에 담을 수 있는 최대 무게
        bags[i] = C
    }
    bags.sort()

    var sum = 0L
    val temp = PriorityQueue<Int>(compareByDescending { it })

    // 모든 가방에 대해서
    for (i in 0 until K) {

        // 보석 무게가 가방 무게보다 적거나 같을 때 temp 큐에 담는다.
        while (pq.isNotEmpty() && bags[i] >= pq.peek().weight) {
            temp.add(pq.poll().price)
        }
        // 가방 무게보다 적은 보석 중 가장 큰 가치의 보석을 추가
        if (temp.isNotEmpty()) {
            sum += temp.poll()
        }
        // 더 이상 가방에 넣은 보석이 없는 경우
        if (temp.isEmpty() && pq.isEmpty())
            break
    }
    print(sum)
}
/**
 * 무게 M, 가격 V일 때
 * 각각 C의 무게를 담을 수 있는 K개의 가방
 * 가방에는 하나의 보석만 가능
 */