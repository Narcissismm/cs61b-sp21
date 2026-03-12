package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>,Iterable<T> {
    // 核心成员变量
    private T[] items;
    private int size;
    private int head;
    private int tail;


    public ArrayDeque() {
        items = (T[]) new Object[8];
        size=0;
        head=7;
        tail=0;

    }
    public ArrayDeque(int s) {
        items = (T[]) new Object[s];
        size=0;
        head=s-1;
        tail=0;

    }


    private void resize(int capacity) {
        T newArray[]=(T[]) new Object[capacity];
        for(int i=0;i<size;i++){
            int s=(head+i+1)%(items.length);
            newArray[i]=items[s];
        }
        items=newArray;
        newArray=null;
        head=items.length-1;
        tail=size;
    }

    public void addFirst(T item) {
        if(size==items.length){
            resize(2*size);
        }
        items[head]=item;
        head=(head-1+items.length)%items.length;
        size++;
    }

    public void addLast(T item) {
        if(size==items.length){
            resize(2*size);
        }
        items[tail]=item;
        tail=(tail+1+items.length)%items.length;
        size++;
    }


    public int size() {
        return size;
    }

    public void printDeque() {
        if(isEmpty()) return;
        for(int i=0;i<size;i++){
            int s=(head+i+1)%(items.length);
            System.out.println(items[s]);
        }
    }

    public T removeFirst() {
        if(isEmpty()) return null;
        head=(head+1+items.length)%items.length;
        size--;
        T m=items[head];
        items[head] = null;
        if(items.length >= 16&&size<items.length*0.25){
            resize(items.length/2);
        }
        return m;
    }

    public T removeLast() {
        if(isEmpty()) return null;
        tail=(tail-1+items.length)%items.length;
        size--;
        T m=items[tail];
        items[tail] = null;
        if(items.length >= 16&&size<items.length*0.25){
            resize(items.length/2);
        }
        return m;
    }

    public T get(int index) {
        if(index<0||index>=size) return null;
        return items[(index+head+1)%items.length];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T> {
        private int i;
        public ArrayDequeIterator() {
            i=0;
        }

        @Override
        public boolean hasNext() {
            return (i<size);
        }
        public T next() {
            T m=items[(i+head+1)%items.length];
            i++;
            return m;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == null) return true;
        if (o == null) return false;
        if (!(o instanceof Deque)) return false;
        Deque<?> other = (Deque<?>) o;
        Iterator<T> iter1 = this.iterator();
        Iterator<?> iter2 = other.iterator();
        if (iter1 == null) {
            if (iter2 != null) return false;
        } else while (iter1.hasNext() && iter2.hasNext()) {
            if (!iter1.next().equals(iter2.next())) return false;
        }
        return true;
    }
}
