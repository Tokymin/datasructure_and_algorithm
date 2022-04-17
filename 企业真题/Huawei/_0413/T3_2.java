package 企业真题.Huawei._0413;

import java.util.*;
import java.util.Arrays;

public class T3_2 {
    static List<Integer> l1 = new ArrayList(),l2 = new ArrayList();
    static boolean get = false;
    static int[] arr ;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = sc.nextInt();
        }
        // 输入完毕 放入方法
        device(0,0,0);
        // 如果无输出 则输出-1
        if(get==false){
            System.out.println(-1);
        }
    }
    public static void device(int sum1,int sum2,int index){
        if(get==true){
            return;
        }
        if(index== arr.length){
            if(sum1==sum2){
                get = true;
                System.out.println(sum1);
                for (int i = 0; i < l1.size(); i++) {
                    System.out.print(l1.get(i)+" ");

                }
                System.out.println();
                for (int i = 0; i < l2.size(); i++) {
                    System.out.print(l2.get(i)+" ");

                }
                System.out.println();
            }
            return;
        }
        // 先放sum1
        l1.add(arr[index]);
        device(sum1+arr[index],sum2,index+1);
        l1.remove(l1.size()-1);
        // 再弄sum2
        l2.add(arr[index]);
        device(sum1,sum2+arr[index],index+1);
        l2.remove(l2.size()-1);
    }

}

