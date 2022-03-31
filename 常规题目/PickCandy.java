package 常规题目;

import java.util.Scanner;


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
            // 如果左边在递增，每次增加
        }
        return n;
    }
}


public class PickCandy {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String[] str=sc.next().split(",");
        int[][] arr=new int[str.length][2];
        for(int i=0;i<str.length;i++){
            arr[i][0]=Integer.valueOf(str[i]);
            arr[i][1]=1;
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
