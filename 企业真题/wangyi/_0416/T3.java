package 企业真题.wangyi._0416;
//小红拿到了-个n个节点、m条边的无向连通图，每个节点的
//小红拿到了-个n个节点、m条边的无向连通图，每个节点的权值已知。
//权值已知.小红删掉一条边时， 可以获得连接该边的两个节点“权值乘积
//小红删掉一条边时，可以获得连接该边的两个节点“权值乘积末尾0数量”的价值。例如，-条边连接的两个点权值是50和
//末尾0数量“的价值.例如，-条边连接的两个点权值是50和60,那么小红删掉这条边获得的价值为3。
//60，那么小红删掉这条边获得的价值为3。小红想知道，在保证这张图连通的情况下，最多可以通过删边
//小红想知道，在保证这张图连通的情况下，最多可以通过删边获得多少价值?
//获得多少价值？


import java.util.Scanner;

class Solution {
    public static int m, n;
    public static int max = 100;
    public int countStr(String longStr) {
        int count = 0;
        while (longStr.charAt(longStr.length() - 1) == '0') {
            count++;
            longStr = longStr.substring(0, longStr.length() - 1);
        }
        return count;
    }
    //DFS递归
    public void DFS(int x, boolean visited[], int t[][]) {
        if (visited[x] == true)
            return;
        visited[x] = true;
        for (int i = 0; i < n; i++) {
            if (t[x][i] > 0)
                DFS(i, visited, t);
        }
    }

    public boolean ConnectedJudge(int[][] V)//基于Kosaraju算法
    {
        boolean visited1[] = new boolean[max];
        DFS(0, visited1, V);
        for (int i = 0; i < n; i++) {
            if (visited1[i] == false) {
                return false;
            }
        }
        int reverse[][] = new int[max][max];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                reverse[i][j] = V[j][i];
            }
        }
        boolean visited2[] = new boolean[max];
        DFS(0, visited2, reverse);
        for (int i = 0; i < n; i++) {
            if (visited2[i] == false && visited1[i] == false) {
                return false;
            }
        }
        return true;
    }
}

public class T3 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Scanner sc = new Scanner(System.in);
        solution.n = sc.nextInt(); // 点数
        solution.m = sc.nextInt();// 边数
        int m = solution.m;
        int n = solution.m;
        int[] weight = new int[n];//权值
        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
        }
        int[][] nerboor = new int[n][n];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            nerboor[u - 1][v - 1] = 1;// 输入的1 2 3和邻接矩阵的index之间要减1
            nerboor[v - 1][u - 1] = 1;
        }
        int maxvalue = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nerboor[i][j] == 1) {
                    String str = Integer.toString(weight[i] * weight[j]);// 使用字符串的方式判断末尾几个0
                    if (solution.ConnectedJudge(nerboor) && maxvalue < solution.countStr(str)) {
                        maxvalue = solution.countStr(str);
                    }
                    nerboor[i][j] = 0;// 删除节点,就是把邻接矩阵中的1置为0
                    nerboor[j][i] = 0;
                }
            }
        }
        System.out.println(maxvalue);
    }


}
