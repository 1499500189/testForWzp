import java.util.HashSet;
import java.util.Set;

/**
 * @author
 * @date 2021 年 11 月 29 日
 */
public class test {


    public static void main(String[] args) {


        Set<Integer>  set = new HashSet<>();
        boolean add = set.add(1);
        boolean add1 = set.add(1);
        System.out.println(add);
        System.out.println(add1);

        System.out.println("+______+_+ ");

        test test = new test();
        int[] ints = new int[]{1,2,3,5,1,1,1,1,1};
        boolean b = test.containsDuplicate(ints);
        System.out.println(b);

    }
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int x : nums) {
            if (!set.add(x)) {
                return true;
            }
        }
        return false;
    }
}
