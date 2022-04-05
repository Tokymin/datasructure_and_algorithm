package 牛客必刷101.二分查找_排序系列;

// 二维数组中的查找

//题目的主要信息:
//●矩阵的行元素和列元素都是有序的，从左到右递增，从上到下递增，完全递增元素不会有重复
//●找到矩阵中有没有给定元素即可

//方法一:二分查找(推荐使用)
//具体做法:
//既然矩阵里面的元素是有序且无重复的，我们可以好好利用一下。
//首先看四个角，左上与右下必定为最小值与最大值，而左下与右，上就有规律了:左下元素大于它上方的元素，小于它右
//方的元素，右上元素与之相反。我们可以在查找时使用二分法:

//●step1: 首先获取矩阵的两个边长，判断特殊情况。
//●step2:首先以左下角为起点，若是它小于目标元素，则往右移动去找大的，若是他大于目标元素，则往上移动去.
//找小的。
//●step3:若是移动到了矩阵边界也没找到，说明矩阵中不存在目标值。

public class BM18 {
    public boolean Find(int target, int [][] array) {
        if(array.length == 0)  //优先判断特殊
            return false;
        int n = array.length;
        if(array[0].length == 0)
            return false;

        int m = array[0].length;
        for(int i = n - 1, j = 0; i >= 0 && j < m; ){ //从最左下角的元素开始往左或往上
            if(array[i][j] > target)   //元素较大，往上走
                i--;
            else if(array[i][j] < target) //元素较小，往右走
                j++;
            else
                return true;
        }
        return false;
    }
}
