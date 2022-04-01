package 常规题目;

import java.util.Scanner;
// 一群孩子做游戏，现在请你根据游戏得分来发糖果，要求如下：
//
//         1. 每个孩子不管得分多少，起码分到一个糖果。
//         2. 任意两个相邻的孩子之间，得分较多的孩子必须拿多一些糖果。(若相同则无此限制)
//
//         给定一个数组 代表得分数组，请返回最少需要多少糖果。
//
//         要求: 时间复杂度为 空间复杂度为
//
//         数据范围：  ，

//牛客的函数版本
class Solution {
    public int candy(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return n;//就只返回一个孩子的一个糖果
        }
        int[] nums = new int[n];//用来装最后的糖果
        for (int i=0;i<n;i++){
            nums[i]=1;
        }
        // 从左到右遍历 fast-template
        for(int i=1;i<arr.length;i++){
            // 如果左边在递增，每次增加一个
            if(arr[i]>arr[i+1]){
                nums[i]=nums[i]+1;
            }
        }
        //记录糖果的总数
        int res =nums[arr.length-1];
        // 从右到左遍历
        for(int i=arr.length-2;i>=0;i--){
            // 如果左边更大但是糖果数更小
            if(arr[i]>arr[i+1]&&nums[i]<nums[i+1])
                nums[i]=nums[i+1]+1;
            //累加和
            res+=nums[i];
        }
        return res;
    }
}


public class PickCandy {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String[] str=sc.next().split(",");
        int[][] arr=new int[str.length][2];
        for(int i=0;i<str.length;i++){
            arr[i][0]=Integer.valueOf(str[i]);// 记录分
            arr[i][1]=1;//记录最后分得的糖果
        }
        for(int i=1;i<str.length;i++){
            if(arr[i][0]>arr[i-1][0]){
                arr[i][1]=arr[i-1][1]+1;
            }
        }
        int count=arr[str.length-1][1];
        for(int i=str.length-2;i>=0;i--){
            if(arr[i][0]>arr[i+1][0]){
                arr[i][1]=Math.max(arr[i][1],arr[i+1][1]+1);
            }
            count+=arr[i][1];
        }
        System.out.println(count);
    }

}
