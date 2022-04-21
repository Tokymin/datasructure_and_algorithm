package 企业真题.ZhongXin;

import java.util.Scanner;

// 小红拿到了一个长度为n的数组，她每次操作可以对数组中任意- -个数
//加一或者减一，最终需要使得任意相邻两个数的乘积为偶数。小红想知
//道最小的操作次数是多少。
//输入描述:
//输入仅有一行，先是一个正整数n,代表小红拿到的数组长度。然后
//是n个正整数ai,代表小红拿到的数组。
//2≤n≤200000
//1≤a≤109
//输出描述:
//一个整数，代表最小的操作次数。

//输入
//3111
//输出
//1
//说明
//数组长度为3，数组是[1,1,1] 。小红将第二个数加一即可，数
//组变成[1,2,1], 满足“任意相邻两个数的乘积为偶数”，只需要
//一次操作。
public class T3 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        int ans = 0;
        for(int i=1; i<n; i++){
            if(arr[i] % 2 != 0 && arr[i-1] % 2 != 0){
                arr[i] += 1;
                ans ++;
            }
        }
        System.out.println(ans);
    }
}
