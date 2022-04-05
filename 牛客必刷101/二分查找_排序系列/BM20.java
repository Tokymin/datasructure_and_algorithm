package 牛客必刷101.二分查找_排序系列;
//数组中的逆序对

//题目的主要信息:
//●在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成1个逆序对。
//●输入一个数组，求一个数组的全部逆序对，答案对1000000007取模
//●保证输入的数组中没有的相同的数字

//方法一:归并排序(推荐使用)
//具体做法:
//因为我们在归并排序过程中会将数组划分成最小为2个元素的子数组，然后依次比较子数组的长度，这里我们也可以用
//相同的方法统计逆序对。我们主要有三个阶段:

//●step1: 划分阶段:将待划分区间从中点划分成两部分;
//●step2: 排序阶段:使用归并排序递归地处理子序列，同时统计逆序对;
//●step3: 合并阶段:将排好序的子序列合并，同时累加逆序对。

//因为在归并排序中，右边大于左边时，它大于了左边的所有子序列，基于这个性质我们可以不用每次加1来统计，减少
//运算次数。

//我们在统计逆序的时候，使用方法一的归并思想，就是利用排序好的部分直接获取逆序个数，而不是一个一个地比较，
// 这样就像是把前面逆序对的个数累加起来，与前缀和类似。

public class BM20 {
    public class Solution {
        public int mod = 1000000007;
        public int mergeSort(int left, int right, int [] data, int [] temp){
            if (left >= right)    // 停止划分
                return 0;
            int mid = (left + right) / 2; //取中间
            int res = mergeSort(left, mid, data, temp) + mergeSort(mid + 1, right, data, temp); //左右划分
            res %= mod;  //防止溢出
            int i = left, j = mid + 1;
            for (int k = left; k <= right; k++)
                temp[k] = data[k];// temp数组里面是装的具体的数值，每次比较小段里面的数，就加入temp.而data数组是对应的统计数 的
            for (int k = left; k <= right; k++) {
                if (i == mid + 1)
                    data[k] = temp[j++];
                else if (j == right + 1 || temp[i] <= temp[j])
                    data[k] = temp[i++];
                else { //左边比右边大，答案增加
                    data[k] = temp[j++];
                    res += mid - i + 1; // 统计逆序对
                }
            }
            return res % mod;
        }

        public int InversePairs(int [] array) {
            int n = array.length;
            int[] res = new int[n];
            return mergeSort(0, n - 1, array, res);
        }

    }



    // 拓展：前缀和 (差分)算法
    //给定一个整数数组 nums，求出数组从索引 i 到 j (i ≤ j) 范围内元素的总和，包含 i, j 两点。
    //示例：
    //给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
    //sumRange(0, 2) -> 1
    //sumRange(2, 5) -> -1
    //sumRange(0, 5) -> -3
    //说明:
    //你可以假设数组不可变。
    //会多次调用 sumRange 方法。
    //Related Topics 动态规划
    //一维前缀
    //b[i]=Σa[j]
    //b[i]= b[i- 1]+ a[i]
    //二维前缀
    //b[x][y] =ΣΣa[i][j]
    //b[x][y]= b[x - 1][y] + b[x][y-1]-b[x-1][y- 1] + b[x][y]

}
