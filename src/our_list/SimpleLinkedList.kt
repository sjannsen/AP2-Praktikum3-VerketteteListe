package our_list.our_list

import our_list.SimpleListForAnything
import java.lang.IndexOutOfBoundsException

class SimpleLinkedList <E> : SimpleListForAnything <E>, Iterable<E> {

    class Node <E> (val data: E, var next: Node<E>?)

    private var firstNode: Node<E>? = null


    inner class ElementIterator: Iterator<E> {
        private var runPointer = firstNode
        override fun hasNext(): Boolean = runPointer != null
        override fun next(): E {
            val resultat = runPointer?.data ?: throw NoSuchElementException()
            runPointer = runPointer?.next
            return resultat
        }
    }
    override fun iterator(): Iterator<E> = ElementIterator()


    override fun addFirst(o: E) {
        val newNode = Node(o, firstNode)
        firstNode = newNode
    }

    override fun addLast(o: E) {
//        if (firstNode == null)
//            addFirst(o)

        if(isEmpty())
            addFirst(o)

        var runPointer = firstNode

        while (runPointer?.next != null)
            runPointer = runPointer.next

        runPointer?.next = Node(o, null)

    }

    override fun contains(o: E): Boolean {
        var runPointer = firstNode
        while (runPointer != null) {
            if(runPointer.data == o)
                return true
            runPointer = runPointer.next
            }

        return false
    }

    override fun size(): Int {
        var runPointer = firstNode
        var counter = 0
        while (runPointer != null) {
            counter++
            runPointer = runPointer.next
        }
        return counter
    }

    override fun getFirst(): E = get(0)

    override fun get(n: Int): E {
        if (n < 0) throw IndexOutOfBoundsException()
        var runPointer = firstNode
        var counter = 0
        while (counter < n && runPointer != null) {
            runPointer = runPointer.next
            counter++
        }
        return runPointer?.data ?: throw IndexOutOfBoundsException()
    }

    override fun removeObject(o: E) {
        var runPointer = firstNode

        if (runPointer?.data == o) {
            firstNode = firstNode?.next
        } else {
            while (runPointer?.next?.data != o) {
                runPointer = runPointer?.next
            }

            runPointer?.next = runPointer?.next?.next
        }
    }

    override fun isEmpty() = firstNode == null

}