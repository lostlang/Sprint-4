package ru.sber.generics

import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*

// 1.
fun <V, V2> compare(p1: Pair<V, V2>, p2: Pair<V, V2>): Boolean =  p1 == p2


// 2.
fun <T: Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int = anArray.filter { it > elem }.size


// 3.
class Sorter<T: Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}


// 4.
class Stack<T> {
    val stack = mutableListOf<T>()
    fun isEmpty() = stack.isEmpty()
    fun push(stackElement: T) = stack.add(stackElement)
    fun pop(): T = stack.removeLast()
}