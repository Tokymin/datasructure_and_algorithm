package 企业真题.MeiTuan;

import java.util.Scanner;

public class T1 {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int red_in = sc.nextInt();
        int blue_in = sc.nextInt();

        int[] ball = new int[n];
        String tmp = sc.nextLine();
        String ballStr = sc.nextLine();

        StringBuilder sb = new StringBuilder();
        sb.append(ballStr.charAt(0));
        int blue_need = 0, red_need = 0;
        for(int i=1; i<n; ){
            if(ballStr.charAt(i) != sb.charAt(sb.length()-1)){
                sb.append(ballStr.charAt(i));
                i++;
                continue;
            }else{
                if(sb.charAt(sb.length()-1) == 'r'){
                    sb.append("b");
                    blue_need++;
                } else{
                    sb.append("r");
                    red_need++;
                }
            }
        }

        if(blue_need <= blue_in && red_need <= red_in)
            System.out.println(sb.length());
        else{
//            int need_red = Math.max(red_need-red_in, 0);
            System.out.printf("%d %d\n", Math.max(red_need-red_in, 0), Math.max(blue_need-blue_in,0) );
        }

    }
}