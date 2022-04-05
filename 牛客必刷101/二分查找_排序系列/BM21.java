package 牛客必刷101.二分查找_排序系列;
//旋转数组的最小数字
//题目的主要信息:

//●有一个长度为n的非降序数组，把一个数组最开始的若千个元素“平移”到数组的末尾，变成一个旋转数组,输入就是这个旋转数组
//●找到这个旋转数组的最小值

//方法一:二分法(推荐使用)
//具体做法:
//因为旋转数组将原本有序的数组分成了两部分有序的数组，因为在原始有序数组中，最小的元素一定是在首位, 旋转后
//无序的点就是最小的数字。我们可以将旋转前的前半段命名为A,旋转后的前半段命名为B,旋转数组即将AB变成了BA。

//可以依旧利用二分的思想，分情况讨论:
//●step1: 双指针指向旋转后数组的首尾，作为区间端点。
//●step2: 若是区间中点值大于区间右界值，则最小的数字一 定在中点右边。
//●step3:若是区间中点值等于区间右界值，则是不容易分辨最小数字在哪半个区间，比如[1,1,1,0,1], 应该逐个缩减右界;
//●step4:若是区间中点值小于区间右界值，则最小的数字一定在中点左边。
//●step5:通过调整区间最后即可锁定最小值所在。

public class BM21 {
    public class Solution1 {
        public int minNumberInRotateArray(int [] array) {
            int left = 0;
            int right = array.length - 1;
            while(left < right){
                int mid = (left + right) / 2;
                if(array[mid] > array[right]) //最小的数字在mid右边
                    left = mid + 1;
                else if(array[mid] == array[right]) //无法判断，一个一个试
                    right--;
                else //最小数字要么是mid要么在mid左边
                    right = mid;
            }
            return array[left];
        }
    }

    // 更简单的做法是直接遍历
    public class Solution2 {
        public int minNumberInRotateArray(int [] array) {
            int res = array[0]; //数组一定有元素，初始化为第一个数
            for(int i = 1; i < array.length; i++) //遍历数组
                res = Math.min(res, array[i]); //每次维护最小值
            return res;
        }
    }
}
