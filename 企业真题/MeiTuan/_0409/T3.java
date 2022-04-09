package 企业真题.MeiTuan._0409;

import java.util.*;
//第一行两个正整数n和m，分别表示地图上的地点数（地点从1到n进行标号），和地图中包含的道路数。
//接下来2行，每行m个正整数，其中第1行第 i 个数为u，其中第2行第 i 个数为v，表示地点u和地点v之间有一条直接的通路。（保证无重边，无自环）
//接下来一个正整数q，表示小美想询问的次数。
//接下来q行，每行两个正整数u和v，表示一次询问：地点u和地点v之间是否有一条直接的通路呢？
//
//(题中道路为双向路，数字间两两有空格隔开)
//
//n<=10000, m<=10000, q<=300
//
//1<=u<=n, 1<=v<=n, u≠v

//输出q行，每行分别对应一次询问的回答。
//每行输出Yes或者No表示存在或者不存在一条直接的道路。

class solution3 {
    public String findPath(int[][] C, int u, int v, int m) {
//        int[][] res=floy(C, m);
        if(C[u-1][v-1]==1||C[v-1][u-1]==1){
            return "Yes"; //No
        }else {
            return "No"; //No
        }
    }
//    public static int[][] path;
//    public static int[][] floy(int[][] C, int n) {
//        path = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                path[i][j] = -1;
//            }
//        }
//        for (int k = 0; k < n; k++) {
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    if (C[i][k] != Integer.MAX_VALUE && C[k][j] != Integer.MAX_VALUE && C[i][k] + C[k][j] < C[i][j]) {
//                        C[i][j] = C[i][k] + C[k][j];
//                        path[i][j] = k;
//                    }
//
//                }
//            }
//        }
//        return C;
//    }

}

//4 5
//1 2 1 3 1
//2 1 3 2 4
//4
//1 2
//2 4
//2 3
//1 4
public class T3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        solution3 s = new solution3();
        int n = sc.nextInt();
        int m = sc.nextInt();
        if (n==0||m==0){
            return;
        }


        int u[] = new int[m];
        int v[] = new int[m];
        for (int i = 0; i < m; i++) {
            u[i] = sc.nextInt();
        }
        for (int j = 0; j < m; j++) {
            v[j] = sc.nextInt();
        }
        int q = sc.nextInt();
        String res[] = new String[q];

        int[][] C = new int[m][m];
        for (int j = 0; j < m; j++) {
            C[u[j]-1][v[j]-1] = 1;
        }


        for (int j = 0; j < q; j++) {
            int u_ = sc.nextInt();
            int v_ = sc.nextInt();
            res[j] = s.findPath(C, u_, v_, m);

        }
        for (int j = 0; j < q; j++) {
            System.out.println(res[j]);

        }
    }


}

