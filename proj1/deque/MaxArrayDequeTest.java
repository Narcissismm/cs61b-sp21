package deque;

import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    // 1. 自定义比较器 A：用于整数比较（比大小）
    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a.compareTo(b); // 默认的升序比较
        }
    }

    // 2. 自定义比较器 B：用于字符串比较（比谁的名字长）
    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return a.length() - b.length(); // 长度相减，正数说明 a 更长
        }
    }

    // 3. 自定义比较器 C：用于字符串比较（按字母表顺序）
    private static class StringAlphabetComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return a.compareTo(b); // String 自带的字母表顺序比较
        }
    }
    private static class CharComparator implements Comparator<Character> {

        public int compare(Character a, Character b) {
            return a-b; // 长度相减，正数说明 a 更长
        }
    }
    @Test
    public void testBasicMax() {
        // 传入整数比较器作为“默认裁判”
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntComparator());

        // 测试空队列是否乖乖返回 null
        assertNull("Empty deque should return null", mad.max());

        // 塞入几个数字
        mad.addLast(10);
        mad.addFirst(5);
        mad.addLast(20);
        mad.addFirst(15);

        // 此时队列是: [15, 5, 10, 20]
        // 期望的最大值应该是 20
        assertEquals("Max should be 20", (Integer) 20, mad.max());
    }

    @Test
    public void testMultipleComparators() {
        // 传入“按长度比较”作为默认裁判
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new StringLengthComparator());

        mad.addLast("apple");      // 长度 5
        mad.addLast("watermelon"); // 长度 10
        mad.addLast("banana");     // 长度 6
        mad.addLast("zebra");      // 长度 5

        // 情况 1：使用构造函数里的默认裁判（比长度）
        // watermelon 名字最长，应该赢
        assertEquals("watermelon", mad.max());

        // 情况 2：临时换裁判，使用“按字母表顺序”比较
        // 字母表里 z 开头的 zebra 应该赢
        Comparator<String> alphabetCmp = new StringAlphabetComparator();
        assertEquals("zebra", mad.max(alphabetCmp));
    }
    @Test
    public void testCharComparators() {
        MaxArrayDeque<Character> mad = new MaxArrayDeque<>(new CharComparator());

        mad.addLast('a');      // ASCII 97
        mad.addLast('z');      // ASCII 122
        mad.addLast('m');      // ASCII 109
        mad.addLast('b');      // ASCII 98

        // 使用默认比较器，应该返回 'z'
        assertEquals((Character) 'z', mad.max());
    }
}
