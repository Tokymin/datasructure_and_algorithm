package 企业真题.Huawei._0413;

import java.util.*;

public class T1 {

    public static class Server {
        public int sid;
        public int cpuCnt;
        public int memSize;

        public Server(int sid, int cpuCnt, int memSize) {
            this.sid = sid;
            this.cpuCnt = cpuCnt;
            this.memSize = memSize;
        }
    }

    static PriorityQueue<Server> q;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        sc.nextLine();
        int[][] CPU = new int[M][5];
        for (int i = 0; i < M; i++) {
            String[] str = sc.nextLine().split(",");
            CPU[i][0] = Integer.parseInt(str[0]);
            CPU[i][1] = Integer.parseInt(str[1]);
            CPU[i][2] = Integer.parseInt(str[2]);
            CPU[i][3] = Integer.parseInt(str[3]);
            CPU[i][4] = Integer.parseInt(str[4]);
        }
        int[] target = new int[6];
        for (int i = 0; i < 6; i++) {
            target[i] = sc.nextInt();
        }
        if (target[1] == 1) {//建立最小堆，分为策略1和2来建立。
            q = new PriorityQueue<>((o1, o2) -> {
                if (o1.cpuCnt == o2.cpuCnt) {
                    if (o1.memSize == o2.memSize) {
                        return o1.sid - o2.sid;
                    } else return o1.memSize - o2.memSize;
                } else {
                    return o1.cpuCnt - o2.cpuCnt;
                }
            });
        } else if (target[1] == 2) {
            q = new PriorityQueue<>((o1, o2) -> {
                if (o1.memSize == o2.memSize) {
                    if (o1.cpuCnt == o2.cpuCnt) {
                        return o1.sid - o2.sid;
                    } else return o1.cpuCnt - o2.cpuCnt;
                } else {
                    return o1.memSize - o2.memSize;
                }
            });
        }
        for (int i = 0; i < M; i++) {//将符合规定的入堆
            if (CPU[i][1] >= target[2] && CPU[i][2] >= target[3] && (target[4] == 9 ||
                    CPU[i][3] == target[4]) && (target[5] == 2 || CPU[i][4] == target[5])) {
                q.add(new Server(CPU[i][0], CPU[i][1], CPU[i][2]));
            }
        }

        int n = Math.min(q.size(), target[0]);//合格
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = q.poll().sid;
        }
        Arrays.sort(res);
        System.out.print(n);
        for (int i = 0; i < n; i++) {
            System.out.print(" " + res[i]);
        }
    }

}

