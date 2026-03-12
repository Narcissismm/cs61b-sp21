package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T> {
    IntNode dummyHead;
    IntNode dummyTail;
    int size;
    public class IntNode {
        public T item;
        public IntNode next;
        public IntNode prev;
        public IntNode(T i, IntNode n,IntNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }
    public LinkedListDeque(){
        dummyHead=new IntNode(null,null,null);
        dummyTail=new IntNode(null,null,null);
        dummyHead.next=dummyTail;
        dummyTail.prev=dummyHead;
        size=0;
    }
    public void addFirst(T item){
        IntNode node=new IntNode(item,dummyHead.next,dummyHead);
        dummyHead.next.prev=node;
        dummyHead.next=node;
        size++;
    }
    public void addLast(T item){
        IntNode node=new IntNode(item,dummyTail,dummyTail.prev);
        dummyTail.prev.next=node;
        dummyTail.prev=node;
        size++;
    }

    public int size(){
        return size;
    }
    public void printDeque(){
        IntNode p=dummyHead.next;
        while(p!=dummyTail){
            System.out.println(p.item);
            p=p.next;
        }
    }
    public T removeFirst(){
        if(isEmpty()) return null;
        IntNode deleteNode=dummyHead.next;
        dummyHead.next=dummyHead.next.next;
        dummyHead.next.prev.prev=null;
        dummyHead.next.prev.next=null;
        dummyHead.next.prev=dummyHead;
        size--;
        return deleteNode.item;
    }
    public T removeLast(){
        if (isEmpty()) return null;
        IntNode deleteNode=dummyTail.prev;
        dummyTail.prev=deleteNode.prev;
        deleteNode.prev.next=dummyTail;
        deleteNode.prev=null;
        deleteNode.next=null;
        size--;
        return deleteNode.item;
    }
    public T get(int index){
        if(index>size-1) return null;
        IntNode p=dummyHead.next;
        while(index>0){
            p=p.next;
            index--;
        }
        return p.item;
    }
    public Iterator<T> iterator(){
        return new LinkDequeIterator();
    }
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null) return false;
        if(!(o instanceof Deque)) return false;
        Deque<?> other = (Deque<?>) o;
        if(size!=other.size()) return false;
        Iterator<T> iter1 = this.iterator();
        Iterator<?> iter2 = other.iterator();
        while(iter1.hasNext()&&iter2.hasNext()){
            T item1 = iter1.next();
            Object item2 = iter2.next();
            if(item1==null){
                if(item2!=null) return false;
            }else{
                if(!item1.equals(item2)) return false;
            }

        }
        return true;
    }
    private T getRecursive(int index, IntNode p){
        if(p==dummyTail||index<0) return null;
        if(index==0) return p.item;
        p=p.next;
        return getRecursive(index-1,p);
    }
    public T getRecursive(int index){
        return getRecursive(index,dummyHead.next);
    }
    private class LinkDequeIterator implements Iterator<T> {
        private IntNode node;

        public LinkDequeIterator() {
            node = dummyHead.next; // first 是 SLList 的头节点
        }
        @Override
        public boolean hasNext() {
            return (node!=dummyTail);
        }

        @Override
        public T next() {
            T i=node.item;
            node=node.next;
            return i;
        }
    }


}
