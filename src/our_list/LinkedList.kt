package our_list

import java.lang.IndexOutOfBoundsException

//AUFGABE 1 Grundlagen zur einfach verketteten Liste


class LinkedList<T> : Iterable<T> {
    data class Node<T>(val data: T, var next: Node<T>?)

    private var first: Node<T>? = null

    //AUFGABE 2 Grundlegende Methoden implementieren

    //AUFGABE 2.1 Basismethoden
    fun isEmpty() = first == null

    fun addFirst(data: T) {
        val newNode = Node(data, first)
        first = newNode
    }

    fun getFirst(): T {
        if (isEmpty())
            return null as T
        else
            return get(0)
    }

    fun clear() {
        first = null
    }

    //AUFGABE 2.2 Methoden mit Iterationen

    fun size(): Int {
        var runPointer = first
        var counter = 0
        while (runPointer != null) {
            counter++
            runPointer = runPointer.next
        }
        return counter
    }

    fun get(index: Int): T {
        if (index < 0) throw IndexOutOfBoundsException()
        var runPointer = first
        var counter = 0
        while (counter < index && runPointer != null) {
            runPointer = runPointer.next
            counter++
        }
        return runPointer?.data ?: throw NoSuchElementException()
    }

    fun getOrNull(index: Int): T {
        if (index < 0) throw IndexOutOfBoundsException()
        var runPointer = first
        var counter = 0
        while (counter < index && runPointer != null) {
            runPointer = runPointer.next
            counter++
        }
        return runPointer?.data ?: return null as T
    }

    fun addLast(data: T) {
//        if (first == null)
//            addFirst(data)

        if (isEmpty())
            addFirst(data)

        var runPointer = first

        while (runPointer?.next != null)
            runPointer = runPointer.next

        runPointer?.next = Node(data, null)

    }


    //AUFGABE 3 Funktionen höherer Ordnung implementieren

    //AUFGABE 3.1 Begriffsdefinition
    //A higher-order function is a function that takes functions as parameters, or returns a function.

    //AUFGABE 3.2 ForEach

    inner class ElementIterator : Iterator<T> {
        private var runPointer = first
        override fun hasNext(): Boolean = runPointer != null
        override fun next(): T {
            val resultat = runPointer?.data ?: throw NoSuchElementException()
            runPointer = runPointer?.next
            return resultat
        }
    }

    override fun iterator(): Iterator<T> = ElementIterator()

    fun forEach(action: (T) -> Unit) {
        var runPointer = first
        if (first != null)
            while (runPointer?.next != null) {
                action(runPointer.data)
                runPointer = runPointer.next
            }
    }

    //AUFGABE 3.3 Weitere Funktionen höherer Ordnung


    //a)
    fun sizeWithForEachMethod(): Int {
        var counter = 0
        forEach { counter++ }
        return counter
    }

    //b)
    fun countWhere(condition: (T) -> Boolean): Int {
        var runPointer = first
        var completesCondition = 0
        if (first != null)
            while (runPointer?.next != null) {
                if (condition(runPointer.data))
                    completesCondition++
                runPointer = runPointer.next
            }
        return completesCondition
    }

}

//AUFGABE 4

//AUFGABE 4.1 Begriffsdefinition
//O(1)      Laufzeit ist unabhängig von der Problemgröße
//O(n)      n-fache Problemgröße benötigt n-fache Zeit (z.B. eine einfache Schleife)
//O(n^2)    n-fache Problemgröße benötigt n^2-fache Zeit (z.B. eine doppelt-verschachtelte Schleife)


//LAUFZEITEFFIZIENZ (O-Notation)

// addFirst(data: T)                O(1)
// addLast(data: T)                 O(n)
// size()                           O(n)
// getFirst()                       O(1)
// get(index: Int)                  O(n)
// forEach(action: (T) -> Unit)     O(n^2) bzw. abhängig von Sekundärfunktion