package 递归_回溯;

import static jdk.nashorn.internal.objects.NativeMath.max;

import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;

public class LatinNumber {
    static HeapNode[] Heap;

    class Node {
        Node Parent;//父节点；
        int Ctime;//运行到当前节点所用
        int Path[];//节点对应的解空间树的路径，即到该节点为止的策略记录
        int T[];//在本策略下的每台机器的运行时间
        int Time; //本策略的总执行时间，为每台机器运行时间的最大值
        int length;//本节点的深度,即当前处理的作业
        int Level;//length+1

        Node(Node par, int path[], int _T[], int _Time, int _length) {
            Parent = par;
            Path = path;
            T = _T;
            Time = _Time;
            length = _length;
            Level = length + 1;
        }
    }

    class HeapNode {


    }

//    public Node bestDispatch(int n, int k, int t[]) {
//        Node root = null, x = null, p, result = null; //root:根节点
//        int time = getBestTime(n, k, t);//利用贪心法获得最优解作为剪枝函数
//
//        /* 根节点的初始化*/
//        root.Parent = null;
//        root.Level = 1;
//        root.Ctime = 0;
//        root.T = new int[n];
//        root.Time = 0;
//        root.Path = new int[n];
//        root.length = 0;
//
//
//        addheap(root); // 将根节点加入最小堆
//        int l=1;
//        while (!Heap.isEmpty()) {
//            p = deleteMinHeap(); // p为完成时间最早的点，优先级最高，从最小堆中删除该节点
//
//            for (int i = 1; i <= k; i++) { //k个机器，拓展p的k个子节点
//                /* 创建新节点 */
//                x = newNode(p.Path, p.length + 1);
//                x.Path[x.length] = i;
//                x.T[i] = x.T[i] + t[x.length];
//                x.Time = (int) max(x.T); // 取k台机器的最大值，本算法最终得到的解
//                // x.Level=x.length+1;//默认
//                if (x.length == n) {//当x为叶子节点时
//                    if (x.Time < time) { // 如果x的执行时间更优
//                        time = x.Time;// 更新
//                        result = x;
//                    }
//                } else {//x是非叶子节点
//                    if (x.Time < time) {  // 如果x的执行时间更优加入最小堆，否则不加入，剪枝
//                        addheap(x);
//                    }
//                }
//            }
//        }
//        return result;
//    }

    private int getBestTime(int n, int k, int time[]) {
        // 利用贪心规则，将任务按所用时间降序排列，先安排给多台机器
        int bestTime;//最优解
        int[] machines = new int[k + 1]; //机器2
        int[] time2 = new int[n + 1]; // time2数组是倒序的

        Arrays.sort(time);// 排序
        for (int i = 1; i < time2.length; i++) {
            time2[i] = time[time.length - i];
        }
        int b = 0;
        int temp = 1;
        for (int i = 0; ; i++) {
            for (int j = 1; j <= k; j++) {
                if (machines[j] == 0 && temp != n + 1) {
                    machines[j] = time2[temp];
                    temp++;
                }
                if (machines[j] != 0) {
                    b = -1;
                    machines[j] -= 1;
                }
            }
            if (b == 0) {
                bestTime = i;
                break;
            }
            b = 0;
        }
        return bestTime;
    }

    private void addheap(Node root) {

    }

    public static void main(String[] args) {
        BBKnapsack abc = new BBKnapsack();
        double[] v1 = {6, 3, 5, 4, 6, 10, 12, 8, 9, 14, 11, 4, 6, 19, 20, 17};
        double[] w1 = {2, 2, 6, 5, 4, 4, 6, 7, 8, 10, 5, 2, 3, 5, 7, 8};
        double c1 = 50;
        int n1 = v1.length - 1;
        int[] x1 = new int[n1 + 1];
        double bestx1;
        abc.n = n1;
        bestx1 = abc.knapsack(v1, w1, c1, x1);
        System.out.println("输出背包容量c1= " + c1);
        System.out.println("输出初值v  ");
        for (int i = 1; i <= n1; i++)
            System.out.print(v1[i] + "  ");
        System.out.println();
        System.out.println("输出初值w  ");
        for (int i = 1; i <= n1; i++)
            System.out.print(w1[i] + "  ");
        System.out.println();
        System.out.println("输出解空间和最优值 ");
        for (int i = 1; i <= n1; i++)
            System.out.print(x1[i] + "  ");
        System.out.println();
        System.out.println(" 最优值= " + bestx1);

    }
}


class zuijiatiaozhengwenti {
    // 参考自https://blog.csdn.net/a1439775520/article/details/90731102
    // 利用回溯法解决的而非分支限界。没有剪枝过程，也没有其他限制的判断条件
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] a = new int[k + 1];
        int[] num = new int[n + 1];
        int[] num1 = new int[n + 1]; // 这是
        for (int i = 1; i < num.length; i++) {
            num[i] = sc.nextInt();
        }
        Arrays.sort(num);
        for (int i = 1; i < num1.length; i++) {
            num1[i] = num[num.length - i];
        }
        int b = 0;
        int temp = 1;
        for (int i = 0; ; i++) {
            for (int j = 1; j <= k; j++) {
                if (a[j] == 0 && temp != n + 1) {
                    a[j] = num1[temp];
                    temp++;
                }
                if (a[j] != 0) {
                    b = -1;
                    a[j] -= 1;
                }
            }
            if (b == 0) {
                System.out.println(i);
                break;
            }
            b = 0;
        }
    }

}

class SubSetSum {
    /*子集和问题*/
    public static void search(int[] S, int Z) {
        int n = S.length - 1;
        int[] track = new int[S.length];
        int ts = 0;                                // ts表示考虑第i个整数时前面已选取的整数和
        int rs = 0;                                // rs表示考虑第i个整数时第i个及后面所有的整数和，初始为集合元素总和
        for (int i = 1; i < S.length; i++)
            rs += S[i];
        dfs(S, Z, n, ts, rs, track, 1);
    }

    private static void dfs(int[] S, int M, int n, int ts, int rw, int[] track, int i) {
        // 到达一个叶子结点，根据结点中tw的值判定是否为解
        if (i > n) {
            if (ts == M)
                print(track, S);
        } else {                                                    // 没有找完所有整数
            // 左孩子结点剪枝，选取满足条件的整数W[i]
            if (ts + S[i] <= M) {
                track[i] = 1;                                        // 表示选取第i个整数
                dfs(S, M, n, ts + S[i], rw - S[i], track, i + 1);    // 判断下一个整数
            }

            // 有孩子结点剪枝，剪出不可能存在解的结点
            if (ts + rw - S[i] >= M) {
                track[i] = 0;                                        // 不选取第i个整数
                dfs(S, M, n, ts, rw - S[i], track, i + 1);            // 判断下一个整数
            }
        }
    }

    private static void print(int[] track, int[] W) {
        for (int i = 1; i < track.length; i++) {
            if (track[i] == 1) {
                System.out.print(W[i] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] set = {0, 11, 24, 13, 7};                            // 数组的下标0位置不用
        search(set, 31);
    }
}

class latiNode {
    int level;
    latiNode parent;
    int number;

    latiNode(int l, latiNode par, int num) {
        level = l;
        parent = par;
        number = num;
    }
}

class Latin4d {
    /*4阶 Latin方是一个4X4的方格，在它的每个方格内填入1,2,3或4，并使得每个数字在每行、每列都恰好出现一次。用回溯法求出所有第一行为1,2,3,4的所有4阶Latin方。*/
    public static void main(String[] args) {
        int[][] set = {{1, 2, 3, 4}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}; // 初识状态
        place(set);
    }

    private static void place(int[][] set) {
        int n = 12;// 总共16个数，但是4个已经初始化，还需要遍历的是16-4层

        latiNode root = new latiNode(0, null, 0);// 根节点无数字，根节点代指[1][0]点，可填数字为2,3,4

        dfs(set, root, 0);
    }

    private static void dfs(int[][] set, latiNode par, int level) {
        HashSet canplace = null; //最多为4

        // 1.它自己当前的位置如何得知，可由循环记录,实际上是level
        int position_colum = level / 4;
        int position_row = level % 4;

        for (int i = 0; i < 4; i++) {// 判断有几个可填
            if (set[i][position_colum] == 0 || (position_colum == i)) break;// 当有元素还未填或是遍历到自己时，跳过
            else {
                canplace.add(4 - set[i][position_colum]);
            }

        }
        for (int j = 0; j < 4; j++) {// 判断有几个可填
            if (set[position_row][j] == 0 || (j == position_row)) break;// 当有元素还未填或是遍历到自己时，跳过
            else {
                canplace.add(set[j][position_row]);
            }
        }
        // 2. 根据canplace数组的大小拓展节点
        level++;
        for (Object element : canplace) {
            latiNode node = new latiNode(level, par, (int) element);
        }
        if (level > 12) {// 到达一个叶子结点
            print(set);
        } else {

        }


    }

    private static void print(int[][] set) {
        int[] temp = new int[12];
        int k = 0;
        for (int i = 2; i < 4; i++) {
            for (int j = 2; j < 4; j++)
                temp[k] = set[i][j];
            k++;
        }
        System.out.print(temp);
        System.out.println();
    }


}

class Latin9d {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] m = new int[6][6];
    public static int[][] zimu = new int[6][6];
    public static Point[][] po = new Point[6][6];

    public static void init() {        //初始化
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 6; i++) {
            String temp = sc.nextLine();
            for (int j = 0; j < 6; j++) {
                m[i][j] = temp.charAt(j) - '0';
                zimu[i][j] = -1;                //先将矩阵的每个字母赋-1，表示此位置未放字母。
            }
        }
        for (int i = 0; i < 6; i++) {
            int index = 0;                //表示0~5这六种颜色
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    if (m[j][k] == i) {
                        po[i][index++] = new Point(j, k);        //表示第i种颜色的各个点位置
                    }
                }
            }
        }
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            char[] temp = sc.next().toCharArray();
            int x = temp[0] - '0';
            int y = temp[1] - '0';
            zimu[x][y] = temp[2] - 'A';                //设置字母数组初始的字母位置
        }
    }

    public static void DFS(int index) {            //遍历这36个点
        if (index == 36) {
            print();
            return;
        }
        int x = index / 6;
        int y = index % 6;
        if (zimu[x][y] == -1) {
            for (int i = 0; i < 6; i++) {
                if (checkR(x, y, i) && checkC(x, y, i)) {        //检测这个点是否与行列重复和与分组的字母重复
                    zimu[x][y] = i;
                    DFS(index + 1);
                    zimu[x][y] = -1;
                }
            }

        } else DFS(index + 1);    //表示此位置已有字母，进行下一个位置
    }

    static int count = 1;

    public static void print() {
        char[] numc = {'A', 'B', 'C', 'D', 'E', 'F'};
        System.out.println(count++);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(numc[zimu[i][j]] + " ");
            }
            System.out.println();
        }
    }

    public static boolean checkR(int row, int col, int c) {    //检测是否与分组字母重复
        int corow = m[row][col];        //corow代表c的颜色
        for (int i = 0; i < 6; i++) {    //遍历此颜色里边的各个点
            int x = po[corow][i].x;
            int y = po[corow][i].y;
            if (zimu[x][y] == c) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkC(int row, int col, int c) {    //检测是否行列重复
        for (int i = 0; i < 6; i++) {
            if (zimu[i][col] == c)
                return false;
            if (zimu[row][i] == c)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        init();
        DFS(0);
    }

}


//此题总结：其实考察时暴力深搜，不过条件太过于复杂，刚开始没看懂咋做，此题精华是，po数组，表示颜色位置。




