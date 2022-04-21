package 企业真题.ZhongXin;


import java.util.Scanner;
// ■题目描述
//小红拿到了一个字符串，她希望把其中的元音字母都变成小写，辅音字
//母都变成大写。你能帮帮她吗?
//元音字母只有'a'、'e'. 中o'。u这五种，其他的都是辅音字母。
//输入描述:
//一个只包含大小写字母的字符串。长度不超过200000
//输出描述:
//小红处理过后的字符串。
// 输入
//AGfaeGUh
//输出
//aGFaeGuH
class Solution {

    public void lowYuanHirFu(String str) {
        String[] vowels = new String[]{"A", "E", "I", "O", "U"}; //元音

        str = str.toUpperCase();
        for (int i = 0; i < vowels.length; i++) {
            str = str.replace(vowels[i], vowels[i].toLowerCase());
        }
        System.out.println(str);
    }
}

public class T2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        if(str.equals(""))
            return;
        Solution s = new Solution();
        s.lowYuanHirFu(str);
    }
}
