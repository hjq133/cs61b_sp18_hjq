//这是一个circle array with generic type
public class ArrayDeque<T> {
    private int size;  //在add，remove方法中 +/- 1,可以保证return size的高效率
    private T[] items;
    private int RFACTOR; //每次增大size时的倍数
    private double usageRatio; //数组利用率
    private int nextLast; //设置array head标识
    private int nextFirst; //设置array end标识 可以加快add，remove的效率
    
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;  //位置是任意的，不必在一个特殊的位置
        RFACTOR = 2;   //每次增大size时的倍数
        usageRatio = 0.25;
    }

    //对一些有可能超出range的index进行调整，因为这是一个circle！
    private int fixIndex(int a) {
        if (a > items.length - 1) {
            return a - items.length;
        }
        if (a < 0){
            return a + items.length;
        }
        return a;
    }

    //根据check结果选择是否 增加/缩小 内存
    private void memoryCheck() {
        if (nextLast == nextFirst) {
            resize((items.length * RFACTOR));
        } else if ((((double) size / (double) items.length) <= usageRatio) && items.length >= 16) {
            resize(items.length / 2);  // 根据作业要求，当数组总长度>16且利用率小于0.25时，数组长度减半
        }
    }

    // resize方法，创建一个符合内存要求的新数组，然后把原数组copy过去
    // 根据nextFirst与nextLast标识位置的不同（谁在前谁在后），分为两种copy方法
    // 把copy的数组第一个数放在目标数组的[1]位置，其余依次排后
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (nextFirst >= nextLast) {
            System.arraycopy(items, nextFirst + 1, a, 1, items.length - nextFirst - 1);
            System.arraycopy(items, 0, a, items.length - nextFirst, nextLast);
        } else {
            System.arraycopy(items, nextFirst + 1, a, 1, size);
        }
        nextFirst = 0;
        nextLast = size + 1;
        items = a;
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        index += (nextFirst + 1);  //【nextFirst+1】为第一个数所在位置，
        index = fixIndex(index);  // 调整实际index，以防超出范围
        return items[index];
    }
    
    public void addFirst(T item) {
        memoryCheck();
        items[nextFirst] = item;
        size += 1;
        nextFirst -= 1;
        nextFirst = fixIndex(nextFirst);
    }

    public void addLast(T item) {
        memoryCheck();
        items[nextLast] = item;
        size += 1;
        nextLast += 1;
        nextLast = fixIndex(nextLast);
    }

    public boolean isEmpty() {
        return size == 0;  // 注意不能用nextFirst == nextLast代替
    }                      // 因为会有nextFirst在最末尾，nextLast = 0的情况

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        nextFirst += 1;
        nextFirst = fixIndex(nextFirst);
        T temp = items[nextFirst];
        items[nextFirst] = null;
        memoryCheck();
        return temp;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        nextLast -= 1;
        nextLast = fixIndex(nextLast);
        T temp = items[nextLast];
        items[nextLast] = null;
        memoryCheck();
        return temp;
    }

    public void printDeque() {
        int last;
        if (nextFirst >= nextLast) {
            last = nextLast + items.length;
        } else {
            last = nextLast;
        }
        for (int index = (nextFirst + 1); index < last; index++) {
            System.out.print(items[fixIndex(index)] + " ");
        }
    }

}
