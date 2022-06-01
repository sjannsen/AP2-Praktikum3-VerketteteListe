package our_list

class SimpleListByDelegation: SimpleListForAnything<Any> {

    private val data = mutableListOf<Any>()
    override fun addFirst(o: Any) {
        data.add(0, o)
    }

    override fun addLast(o: Any) {
        data.add(o)
    }

    override fun contains(o: Any) = data.contains(o)

    override fun size() = data.size

    override fun getFirst() = data[0]

    override fun get(n: Int) = data[n]

    override fun removeObject(o: Any) {
        data.remove(o)
    }

    override fun isEmpty() = data.isEmpty()

}