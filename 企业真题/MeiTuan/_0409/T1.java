package 企业真题.MeiTuan._0409;

//小团和小美在玩数圈游戏，游戏规则是这样的：给出一个数字，小美需要立刻说出这个数字包含的圈圈的数量。
// 因为小团提问的频率非常快，小美希望你能帮她用程序来计算出来。
//例如，对于数字0，包含1个圈，对于数字2，包含0个圈，对于数字8，包含2个圈。

//我们给出对圈的认定标准如下表:
//输入一行仅包含一个十进制整数n
//对于80% 的数据，n<=100000
//对于20% 的数据，n<=1000000000
//输出仅包含一个正整数，表示十进制数字n中的圈圈数量

import java.util.*;

class solution1 {
    public int f() {
        return 0;
    }


}

public class T1 {

    public static void main(String[] args) {
        solution1 s = new solution1();
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        if ( Integer.parseInt(n)<0){//
            return;
        }

        int[] circles = new int[]{1, 0, 0, 0, 0, 0, 1, 0, 2, 1};
//        for(int i=0;i<10;i++){
//
//        }
        int[] index = new int[n.length()];
        for (int i = 0; i < index.length; i++) {
            index[i] = Integer.parseInt(n.split("")[i]);
        }
        int sum = 0;
        for (int i = 0; i < index.length; i++) {
            sum = sum + circles[index[i]];
        }
        System.out.println(sum);
    }

}


