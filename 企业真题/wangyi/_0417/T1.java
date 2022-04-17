package 企业真题.wangyi._0417;
//2222年，中国足球队再次参加亚洲区预选赛冲击参加世界杯的资格。
//中国队所在的小组包含六支队伍，小组的前两名出线晋级世界杯。
//2222年的亚洲预选赛积分规则如下:
//1.-场比赛进球多者获胜，胜者积3分，负者积0分;进球数相同为平
//局，双方各得1分;
//2小组排名规则:
//a)积分多者排前;
//b)积分相同，则净胜球(进球数失球数)多者排前;
//c) 如果净胜球也相同，则进球数多者排前;
//d)如果进球数也相同，则队伍名称字典序小的排前;
//目前最后一轮的3场比赛刚刚结束，中国球迷想知道根据各队积分情况
//(未更新最后一轮比赛结果且未排序)，以及最后一 轮3场比赛的结
//果，你能帮他们更新得到最终的积分排行榜吗?
//输入描述:
//输入的第一-行是一个正整数工(0< T <= 10)，表示有T组测试数
//据。
//每组测试数据包含9行,前面六行描述中国队所在小组的积分榜情
//况，每行的格式为"name pointagoala_for
//goals_against", 分别表示队伍名称，队伍积分(0 <=
//points<= 30)，进球数(0 <= goals_for <= 50)，丢球数
//(0<= goals_against <=50)。 其中每个队伍名称name由长度不
//超过20的字符(只包含大小写字母)组成，保证六支队伍的名称不
//同。
//最后三行是最后一轮比赛三场比赛的结果，每行的格式为"name0
//name1 goals_ for_ name0 goala_ for_ name1”,分别表示对赛
//的双方(一定在前面的积分榜出现过)，以及双方的进球数(0 <=
//goalB_ for_ name0， goals_ for_ name1<= 5)。
//输出描述:
//每个测试数据输出七行，前六行表示最后的积分榜,每行的格式为
//"namepoints goals_ for goal3_ against", 分别表示队伍名
//称，队伍积分，进球数，丢球数，请按排名规则进行排名输出。最后
//一行输出“END"。

/*
input：
2
China 18 16 7
Oman 12 9 10
Japan 24 12 3
Iran 20 11 6
Thailand 7 9 17
Vietam 0 6 20
Iran Japan 0 3
China Oman 2 0
Thailand Vietam 1 1
Indonesia 4 13 12
Cyprus 4 14 17
Singapore 7 18 20
Cambodia 18 14 8
Sikkim 17 13 14
China 25 12 13
Indonesia China 2 1
Cyprus Singapore 3 0
Cambodia Sikkim 3 3
 */

/*
output：
Japan 27 15 3
China 21 18 7
Iran 20 11 9
Oman 12 9 12
Thailand 8 10 18
Vietam 1 7 21

END
China 25 13 15
Cambodia 19 17 11
Sikkim 18 16 17
Indonesia 7 15 13
Cyprus 7 17 17
Singapore 7 18 23
END
*/

import java.util.*;

public class T1 {
    private static int compareStr(String a, String b) {
        if (a.charAt(0) != b.charAt(0)) return b.charAt(0) - a.charAt(0);
        int len = Math.min(a.length(), b.length());
        int i;
        for (i = 0; i < len; i++) {
            if (a.charAt(i) == b.charAt(i)) continue;
            return b.charAt(i) - a.charAt(i);
        }
        if (i == a.length()) return -1;
        if (i == b.length()) return 1;
        return 0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();//表示有2组测试数据，没组包含9行
        List<List<Map.Entry<String, ArrayList<Integer>>> > result_li=new ArrayList<List<Map.Entry<String, ArrayList<Integer>>>>();
        for (int l=0;l<T;l++) {
            List<Map.Entry<String, ArrayList<Integer>>> list;
            // 第一组测试数据
            HashMap<String, ArrayList<Integer>> map_points = new HashMap<>();
            for (int i = 0; i < 6; i++) {
                String name_contry = sc.next();
                int points = sc.nextInt();
                int goals_for = sc.nextInt();
                int goals_against = sc.nextInt();
                ArrayList<Integer> p = new ArrayList<>();
                p.add(points);
                p.add(goals_for);
                p.add(goals_against);
                map_points.put(name_contry, p);
            }
            for (int i = 0; i < 3; i++) {//比赛结果
                String name_0 = sc.next();
                String name_1 = sc.next();
                int goals_for_name0 = sc.nextInt();
                int goals_for_name1 = sc.nextInt();

                ArrayList<Integer> temp = map_points.get(name_0);
                temp.set(1, temp.get(1) + goals_for_name0);//进球数
                temp.set(2, temp.get(2) + goals_for_name1);//丢球数球数

                ArrayList<Integer> temp2 = map_points.get(name_1);
                temp2.set(1, temp2.get(1) + goals_for_name1);//进球数
                temp2.set(2, temp2.get(2) + goals_for_name0);//丢球数球数

                if (goals_for_name0 > goals_for_name1) {//name0 胜出
                    temp.set(0, temp.get(0) + 3);//丢球数球数
                } else if (goals_for_name0 < goals_for_name1) {
                    temp2.set(0, temp2.get(0) +3);//丢球数球数
                } else {
                    temp.set(0, temp.get(0) + 1);//各的1分
                    temp2.set(0, temp2.get(0) + 1);//各的1分
                }
                map_points.replace(name_0, temp);
                map_points.replace(name_1, temp2);
            }
            list = new ArrayList<Map.Entry<String, ArrayList<Integer>>>(map_points.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
                @Override
                public int compare(Map.Entry<String, ArrayList<Integer>> o1, Map.Entry<String, ArrayList<Integer>> o2) {
                    if (o1.getValue().get(0) < o2.getValue().get(0)) {
                        return 1;
                    } else if (o1.getValue().get(0) == o2.getValue().get(0)) {
                        // 净球数
                        if ((o1.getValue().get(1) - o1.getValue().get(2)) < (o2.getValue().get(1) - o2.getValue().get(2))) {
                            return 1;
                        } else if ((o1.getValue().get(1) - o1.getValue().get(2)) == (o2.getValue().get(1) - o2.getValue().get(2))) {
                            return compareStr(o1.getKey(), o2.getKey());
                        } else {
                            return -1;
                        }
                    } else {
                        return -1;
                    }
                }
            });
            result_li.add(list);
        }
        for (int j=0;j<T;j++) {
            for (int i = 0; i < result_li.get(j).size(); i++) {
                System.out.print( result_li.get(j).get(i).getKey() + " ");
                System.out.print( result_li.get(j).get(i).getValue().get(0) + " ");
                System.out.print( result_li.get(j).get(i).getValue().get(1) + " ");
                System.out.print( result_li.get(j).get(i).getValue().get(2));
                System.out.println();
            }
            if(j==T-1){
                System.out.print("END");
            }else {
                System.out.println("END");
            }
        }
    }
}
