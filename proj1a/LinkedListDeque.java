public class LinkedListDeque<T> {
    private int size;
    private StuffNode sentinel;

    private class StuffNode {
        public StuffNode prev;
        public T item;
        public StuffNode next;
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new StuffNode();
        sentinel.item = null;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T item) {
        size += 1;
        StuffNode L = new StuffNode();
        L.item = item;
        L.prev = sentinel;
        L.next = sentinel.next;
        sentinel.next.prev = L;
        sentinel.next = L;
    }
    
    public void addLast(T item) {
        size += 1;
        StuffNode L = new StuffNode();
        L.item = item;
        L.prev = sentinel.prev;
        L.next = sentinel;
        sentinel.prev.next = L;
        sentinel.prev = L;
    }
    
    public  boolean isEmpty() {
        if (sentinel.prev == sentinel){
            return true;
        }
        return false;
    }
    
    public int size() {
        return size;
    }

    public void printDeque() {
        StuffNode L = sentinel.next;
        for (int i = 0; i < size; i++){
            System.out.print(L.item + " ");
            L = L.next;
        }
    }
    
    public T removeFirst() {
        if (isEmpty()){
            return null;
        }
        size -= 1;
        T temp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return temp;
    }

    public T removeLast() {
        if (isEmpty()){
            return null;
        }
        size -= 1;
        T temp = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return temp;
    }

    public T get(int index) {
        if (index > size - 1 || index < 0){
            return null;
        }
        StuffNode L = sentinel.next;
        int i = 0;
        while (i < index){
            L = L.next;
            i++;
        }
        return L.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1 || index < 0){
            return null;
        }
        if (index == 0){
            return sentinel.next.item;
        }else {
            T temp = removeFirst();
            T re = getRecursive(index - 1);
            addFirst(temp);
            return re;
        }
    }
}
