package 企业真题.MeiTuan._0402;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T3 {
    List<String> sub = new ArrayList<String>();
    List<String> findAllSubstrings(String str, int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 1; j <= length - i; j++) {
                sub.add(str.substring(i, i + j));
            }
        }
        return sub;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String core = sc.next();//核心思想
        if(str.equals("")||core.equals("")){
            System.out.println(0);
        }
        T3 t3 = new T3();
        List<String> zichuan=t3.findAllSubstrings(str,str.length());// 所有字串
//        System.out.println(zichuan.toString());
        int count=0;
        for (int i=0;i<zichuan.size();i++){
            if (zichuan.get(i).contains(core)){
                count++;
            }
        }
        System.out.println(count);
    }
}
