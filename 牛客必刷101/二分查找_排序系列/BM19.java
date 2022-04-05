package 牛客必刷101.二分查找_排序系列;
//寻找峰值

//题目主要信息:
//●给定一个长度为n的数组，返回其中任何一个峰值的索引
//●峰值元素是指其值严格大于左右相邻值的元素
//●数组两个边界可以看成是最小，nums|-1| = nums|n| = -∞
//●峰值不存在平的情况，即相邻元素不会相等

//方法:二分查找(推荐使用)
//具体做法:
//因为数组边界看成最小值，因此只要不断地往高处走，一定会有波峰，最大值两边一定比它小。 那可以考虑二分查找。
//●step1:二分查找首先从数组首尾开始，每次取中间值，直到首尾相遇。
//●step 2:如果中间值的元素大于它右边的元素，说明往右是向下，我们不一定会遇到波峰，但是那就往左收缩区
//●step3: 如果中间值小于右边的元素，说明此时往右是向上，向上一定能有波峰，那我们往右收缩区间。
//●step4:最后区间收尾相遇的点一 定就是波峰。
public class BM19 {
    public class Solution {
        public int findPeakElement (int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while(left < right){ //二分法
                int mid = (left + right) / 2;
                if(nums[mid] > nums[mid + 1])//右边是往下，不一定有坡峰
                    right = mid;
                else//右边是往上，一定能找到波峰
                    left = mid + 1;
            }
            return right; //其中一个波峰
        }

    }
}
