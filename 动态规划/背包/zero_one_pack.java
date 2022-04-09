package 动态规划.背包;

import java.util.Scanner;

public class zero_one_pack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 输入物品数量和背包容积  N，V，用空格隔开，分别表示物品数量和背包容积。
        int n = sc.nextInt(); // 物品个数
        int m = sc.nextInt(); // 背包容积

        // 输入每件物品的体积和价值
        int[] v = new int[n + 1];// 价值
        int[] w = new int[n + 1];// 重量
        for (int i = 1; i <= n; i++) { //每行两个整数 vi,wi，用空格隔开，分别表示第 i件物品的体积和价值。
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        // df
        int[][] f = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                // 如果背包容积放得下物品i，就比较是保持原来的物品不变的价值大还是添加这个物品i刚好放满背包时的得到的价值大，保持背包空间容积的不浪费
                // 如果背包容积放得下物品i，就保持物品不变
                if(j >= v[i]){
                    f[i][j] = Math.max(f[i-1][j], f[i-1][j-v[i]]+w[i]);
                }else{
                    f[i][j] = f[i-1][j];
                }
            }
        }
        // 输出最大价值
        System.out.println(f[n][m]);
    }

}


