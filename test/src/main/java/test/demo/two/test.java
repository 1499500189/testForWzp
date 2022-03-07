package test.demo.two;

import java.util.Arrays;

/**
 * @author
 * @date 2022 年 03 月 04 日
 */
public class test {
    //给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
    //示例 1：
    //输入：nums = [-4,-1,0,3,10]
    //输出：[0,1,9,16,100]
    //解释：平方后，数组变为 [16,1,0,9,100]
    //排序后，数组变为 [0,1,9,16,100]
    //
    //示例 2：
    //输入：nums = [-7,-3,2,3,11]
    //输出：[4,9,9,49,121]
    //
    //提示：
    //
    //    1 <= nums.length <= 104
    //    -104 <= nums[i] <= 104
    //    nums 已按 非递减顺序 排序
    //
    //
    //
    //进阶：
    //
    //    请你设计时间复杂度为 O(n) 的算法解决本问题

    public static void main(String[] args) {


    }
    public int[] sortedSquares(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
             nums[i] =      nums[i]*nums[i];

        }
        Arrays.sort(nums);
        return  nums;
    }
   /* 方法二：双指针
            思路与算法
    方法一没有利用「数组 nums\textit{nums}nums 已经按照升序排序」这个条件。显然，如果数组 nums\textit{nums}nums 中的所有数都是非负数，那么将每个数平方后，数组仍然保持升序；如果数组 nums\textit{nums}nums 中的所有数都是负数，那么将每个数平方后，数组会保持降序。
    这样一来，如果我们能够找到数组 nums\textit{nums}nums 中负数与非负数的分界线，那么就可以用类似「归并排序」的方法了。具体地，我们设 neg\textit{neg}neg 为数组 nums\textit{nums}nums 中负数与非负数的分界线，也就是说，nums[0]\textit{nums}[0]nums[0] 到 nums[neg]\textit{nums}[\textit{neg}]nums[neg] 均为负数，而 nums[neg+1]\textit{nums}[\textit{neg}+1]nums[neg+1] 到 nums[n−1]\textit{nums}[n-1]nums[n−1] 均为非负数。当我们将数组 nums\textit{nums}nums 中的数平方后，那么 nums[0]\textit{nums}[0]nums[0] 到 nums[neg]\textit{nums}[\textit{neg}]nums[neg] 单调递减，nums[neg+1]\textit{nums}[\textit{neg}+1]nums[neg+1] 到 nums[n−1]\textit{nums}[n-1]nums[n−1] 单调递增。
   由于我们得到了两个已经有序的子数组，因此就可以使用归并的方法进行排序了。具体地，使用两个指针分别指向位置 neg\textit{neg}neg 和 neg+1\textit{neg}+1neg+1，每次比较两个指针对应的数，选择较小的那个放入答案并移动指针。当某一指针移至边界时，将另一指针还未遍历到的数依次放入答案。
    代码*/

    class Solution {
        public int[] sortedSquares(int[] nums) {
            int n = nums.length;
            int negative = -1;
            for (int i = 0; i < n; ++i) {
                if (nums[i] < 0) {
                    negative = i;
                } else {
                    break;
                }
            }

            int[] ans = new int[n];
            int index = 0, i = negative, j = negative + 1;
            while (i >= 0 || j < n) {
                if (i < 0) {
                    ans[index] = nums[j] * nums[j];
                    ++j;
                } else if (j == n) {
                    ans[index] = nums[i] * nums[i];
                    --i;
                } else if (nums[i] * nums[i] < nums[j] * nums[j]) {
                    ans[index] = nums[i] * nums[i];
                    --i;
                } else {
                    ans[index] = nums[j] * nums[j];
                    ++j;
                }
                ++index;
            }

            return ans;
        }
    }

 /*   复杂度分析

    时间复杂度：O(n)O(n)O(n)，其中 nnn 是数组 nums\textit{nums}nums 的长度。

    空间复杂度：O(1)O(1)O(1)。除了存储答案的数组以外，我们只需要维护常量空间。

    方法三：双指针

            思路与算法

    同样地，我们可以使用两个指针分别指向位置 000 和 n−1n-1n−1，每次比较两个指针对应的数，选择较大的那个逆序放入答案并移动指针。这种方法无需处理某一指针移动至边界的情况，读者可以仔细思考其精髓所在。

    代码*/

    class Solution2 {
        public int[] sortedSquares(int[] nums) {
            int n = nums.length;
            int[] ans = new int[n];
            for (int i = 0, j = n - 1, pos = n - 1; i <= j;) {
                if (nums[i] * nums[i] > nums[j] * nums[j]) {
                    ans[pos] = nums[i] * nums[i];
                    ++i;
                } else {
                    ans[pos] = nums[j] * nums[j];
                    --j;
                }
                --pos;
            }
            return ans;
        }
    }

 /*   复杂度分析

    时间复杂度：O(n)O(n)O(n)，其中 nnn 是数组 nums\textit{nums}nums 的长度。

    空间复杂度：O(1)O(1)O(1)。除了存储答案的数组以外，我们只需要维护常量空间。

*/
}
