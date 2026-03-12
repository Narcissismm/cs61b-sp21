package deque;

import java.util.Iterator;

// 声明这是一个接口，并且泛型是 T
public interface Deque<T> extends Iterable<T> {

    // 下面只列出方法名字（不需要写大括号和具体逻辑）
    void addFirst(T item);

    void addLast(T item);

    // CS61B 经常要求在这里写一个 default 的 isEmpty()
    // 这样你的 ArrayDeque 和 LinkedListDeque 里面甚至都不用写 isEmpty() 方法了
    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int index);

    // 规定必须实现迭代器
    Iterator<T> iterator();

    // 规定必须重写 equals 方法
    boolean equals(Object o);
}