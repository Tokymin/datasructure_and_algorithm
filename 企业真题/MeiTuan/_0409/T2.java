package 企业真题.MeiTuan._0409;


//小团成了游戏中的营长，他打算给游戏中的士兵们排一个整齐的队。目标是让这些士兵排成整齐的一列，
// 从左到右士兵们的身高逐渐增加，当两个士兵的身高一样的时候，就按照他们名字的字典序进行排列，因为小团觉得这样排起来最美观。
//
//形式化地，给出n个士兵的身高（因为是在游戏中，身高可能很离谱）与名字（仅包含小写英文字母，并且我们保证两个不同士兵的名字不同）
// ，要将他们按照身高从小到大排序，如果身高相同则按照名字的字典序排序。
//字典序：在英文字典中，排列单词的顺序是先按照第一个字母以升序排列（即a、b、c……z 的顺序）；如果第一个字母一样，那么比较第二个、
// 第三个乃至后面的字母。如果比到最后两个单词不一样长（比如，sigh 和 sight），那么把短者排在前。

// 第一行一个正整数n，表示士兵数
//
//第二行n个空格隔开的正整数h[1,2,…n]，h[i]表示第 i 个士兵的高度。
//
//第三行n个空格隔开的字符串s[1,2,…n], s[i]表示第 i 个士兵的名字，注意，士兵的名字仅包含小写英文字母且没有重复的名字。
//
//n<=50000，h[i]<=300 , s[i]仅包含小写英文字母且长度不大于10

import java.util.*;

//class solution2 {
//    public int f() {
//        return 0;
//    }
//}
//4
//176 170 176 176
//beta tom alpha bamma

public class T2 {
    private static int compareStr(String a, String b){
        if(a.charAt(0) != b.charAt(0)) return b.charAt(0) - a.charAt(0);
        int len = Math.min(a.length(), b.length());
        int i=0;
        for(i=0; i<len; i++){
            if(a.charAt(i) == b.charAt(i)) continue;
            return b.charAt(i) - a.charAt(i); // a 小 得到-1， a 大 得到1
        }
        if(i == a.length()) return -1;  //a  win
        if(i == b.length()) return 1;  // b win

        return 0; //相等
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] high = new int[n];
        for(int i=0; i<n; i++){
            high[i] = sc.nextInt();
        }
        String[] name = new String[n];
        for(int i=0; i<n; i++){
            name[i] = sc.next();
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (int i=0; i<n; i++) {
            map.put(name[i], high[i]);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //身高相同，比较名字
                if(o1.getValue().equals(o2.getValue())){
                    return compareStr(o2.getKey(), o1.getKey());
                }
//                System.out.println(o1.getValue());
//                System.out.println(o2.getValue());

                return o1.getValue() - o2.getValue();



            }
        });

//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.printf("%s ", entry.getKey());
//        }
        for (int i=0;i<list.size();i++){
            System.out.printf("%s ", list.get(i).getKey());
        }
        System.out.println();

    }
    //
//tom alpha bamma beta

    //tom bamma alpha beta
}

