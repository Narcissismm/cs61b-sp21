package deque;
import java.util.Iterator;
import java.util.Comparator;

// 1. 使用 extends 继承 ArrayDeque，直接拥有它的所有能力
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> defaultcomparator;
    public MaxArrayDeque(Comparator<T> c){
        super();
        defaultcomparator=c;
    }
    public T max(){
        return max(defaultcomparator);
    }
    public T max(Comparator<T> c){
        T max=this.get(0);
        if(this==null) return null;
        Iterator<T> iter=this.iterator();
        while(iter.hasNext()){
            T t=iter.next();
            if(t==null) return max;
            if(c.compare(max,t)<0) max=t;
        }
        return max;
    }
}