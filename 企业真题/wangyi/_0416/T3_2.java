package 企业真题.wangyi._0416;

import java.util.*;



class Edge {
    int N = (int) (2e5 + 10);
    int[] pre = new int[N];
    long[] a = new long[N];
    int n, m;

    int u, v;
    int w;
    void init()
    {
        for (int i = 1; i <= n; i++)
            pre[i] = i;
    }

    int find(int x)
    {
        if (x == pre[x])
            return x;
        return pre[x] = find(pre[x]);
    }

    int kruskal(ArrayList<Edge> Edges)
    {
        int ans = 0, sum = 0;
        Collections.sort(Edges,new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.w - o2.w;
            }
        });
        for (int i = 0; i < m; i++)
        {
            int u = Edges.get(i).u, v = Edges.get(i).v, w = Edges.get(i).w;
            int fu = find(u), fv = find(v);
            if (fu != fv)
            {
                pre[fu] = fv; ans += w;
                sum++;
            }
        }
        if (sum != n - 1)
            return -1;
        return ans;
    }
}

public class T3_2 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = (int) (2e5 + 10);
        long[] a = new long[N];

        int n = sc.nextInt(); // 点数
        int m = sc.nextInt();// 边数
        Edge e=new Edge();
        e.init();
        for (int i = 1; i <= n; i++) {
           a[i]= sc.nextInt();;
        }
        long ans = 0;
        List edges_li=new ArrayList<Edge>();
        for (int i = 0; i < m; i++)
        {
            int w =0;
            int u = sc.nextInt();
            int v = sc.nextInt();
            long  a1 = a[u],a2 = a[v];
            int two = 0, five = 0;

            while (a1%2 == 0) {
                two++;
                a1 /= 2;
            }
            while (a2 % 2 == 0) {
                two++;
                a2 /= 2;
            }
            a1 = a[u];
            a2 = a[v];
            while (a1 % 5 == 0) {
                five++;
                a1 /= 5;
            }
            while (a2 % 5 == 0) {
                five++;
                a2 /= 5;
            }
            w =Math.min(two, five);


            Edge temp=new Edge();
            temp.u=u;
            temp.v=v;
            temp.w=w;
            edges_li.add(temp);
            ans += w;
            // cout << w << endl;
        }
        int minege = e.kruskal((ArrayList<Edge>) edges_li);
        //cout << minege << endl;
        System.out.println(ans - minege);
    }

}
