// very easy , just draw the picture and you will see it

public class LinkedListDeque<T> {
    private int size;
    private StuffNode sentinel;  // should be private! not public!

    /* define StuffNode class*/
    private class StuffNode {
        private T item;
        private StuffNode prev;
        private StuffNode next;

        /* define value constructor of */
        public StuffNode(T x, StuffNode p, StuffNode n) {
            item = x;
            prev = p;
            next = n;
        }
    }

    /* define the constructor for LinkedListDeque*/
    public LinkedListDeque() {
        size = 0;
        sentinel = new StuffNode(null, sentinel, sentinel);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        size += 1;
        StuffNode L = new StuffNode(item, sentinel, sentinel.next);
        sentinel.next.prev = L;
        sentinel.next = L;
    }
    
    public void addLast(T item) {
        size += 1;
        StuffNode L = new StuffNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = L;
        sentinel.prev = L;
    }
    
    public  boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    
    public int size() {
        return size;
    }

    public void printDeque() {
        StuffNode L = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(L.item + " ");
            L = L.next;
        }
    }
    
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T temp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return temp;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T temp = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return temp;
    }

    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        StuffNode L = sentinel.next;
        int i = 0;
        while (i < index) {
            L = L.next;
            i++;
        }
        return L.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        if (index == 0) {
            return sentinel.next.item;
        } else {
            T temp = removeFirst();
            T re = getRecursive(index - 1);
            // recursive function ,first remove, cache the item, then add
            addFirst(temp);
            return re;
        }
    }
}
