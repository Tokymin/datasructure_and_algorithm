package 企业真题.wangyi._0417;

import java.util.*;

public class T3 {
    private static int ans = Integer.MAX_VALUE;
    private static List<String> maps = new LinkedList<>();
    private static int[] dx = new int[]{1, 0, -1, 0};
    private static int[] dy = new int[]{0, 1, 0, -1};
    private static int[][] vis;
    private static int n, m, k;
    private static int dfs(int x, int y, int ex, int ey, int now){
        if(x == ex && y == ey) return now;
        if(ans != Integer.MAX_VALUE) {
            if(now + Math.abs(ex-x) + Math.abs(ey-y) > ans)
                return Integer.MAX_VALUE;
        }
        for(int i = 0; i < 4; i ++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if(vis[nx][ny] == 1) continue;
            char tmp = maps.get(nx).charAt(ny);
            if(tmp == 'W') continue;
            if((now + 1) % k == 0 && maps.get(nx).charAt(ny) == 'M') continue;
            vis[nx][ny] = 1;
            int res = dfs(nx, ny, ex, ey, now + 1);
            if(res != Integer.MAX_VALUE) {
                ans = Math.min(ans, res);
            }
            vis[nx][ny] = 0;
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List<Integer> result_li=new ArrayList<Integer>();
        int T = sc.nextInt();
        while(T-- > 0){
            n = sc.nextInt();
            m = sc.nextInt();
            k = sc.nextInt();
            vis = new int[n][m];
            maps = new LinkedList<>();
            String tt = sc.nextLine();

            int sx = 0, sy = 0;
            int ex = 0, ey = 0;

            ans = Integer.MAX_VALUE;

            for(int i = 0; i < n; i ++) {
                String line = sc.nextLine();
                maps.add(line);
                for(int j = 0; j < m; j ++) {
                    if(i >= n) continue;
                    if(maps.get(i).charAt(j) == 'S') {
                        sx = i;
                        sy = j;
                    } else if(maps.get(i).charAt(j)  == 'E') {
                        ex = i;
                        ey = j;
                    }
                }
            }

            for(int[] v : vis){
                Arrays.fill(v, 0);
            }
            vis[sx][sy] = 1;
            ans=dfs(sx, sy, ex, ey, 0);
            result_li.add(ans);
        }
        for (int i=0;i<T;i++){
            if(result_li.get(i) == Integer.MAX_VALUE) {
                System.out.println("-1");
            } else {
                System.out.println(((result_li.get(i) % k) == 0 ? (result_li.get(i)/k) : (result_li.get(i)/k + 1)));
            }
        }
    }
}


