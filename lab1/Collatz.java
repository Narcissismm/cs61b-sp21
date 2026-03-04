/** Class that prints the Collatz sequence starting from a given number.
 *  @author YOUR NAME HERE
 */
public class Collatz {

    /** Fixed implementation of nextNumber! */
    public static int nextNumber(int n) {
        if (n % 2 == 0) {
            return n / 2;
        } else { // 修正1：else不能带条件，直接用else即可（或改为else if (n % 2 == 1)）
            return 3 * n + 1;
        } // 修正2：补充缺失的闭合大括号
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

