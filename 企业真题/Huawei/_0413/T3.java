package 企业真题.Huawei._0413;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class T3 {
    /**
     * 集合是否能分成相等两部分(找到和为整个数组和的一半)
     *
     * @param nums 数组
     * @return 布尔值
     */
    public static int[] canPartition(int[] nums) {
        int res[]=new int[2];
        if (nums == null || nums.length < 1) {
            res[1]=1;
            return res;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            res[1]=-1;
            return res;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = Math.max(dp[i], dp[i - num] + num);
                System.out.println(dp[i]);
                if (dp[i] == target) {
//                    return dp;

                    res[1]=1;
                    res[2]=target;
                    return res;
                }
            }
        }
        res[1]=-1;
        return res;
    }

    public static boolean findGroup(int[] arr, int n, int sum) {
        if (sum == 0 && n == 0) {
            return true;
        } else if (n <= 0) {
            return false;
        }
        if (n > 0) {
            for (int i = 0; i < arr.length; i++) {
                int[] temp = Arrays.copyOfRange(arr, i + 1, arr.length);
//                int[] temp = arr[i+1,arr];
                return findGroup(temp, n - 1, sum - arr[i]) || findGroup(temp, n, sum);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int bags = sc.nextInt();
        int[] numbers = new int[bags];
        for (int i = 0; i < bags; i++) {
            numbers[i] = sc.nextInt();
        }
        int res[] = canPartition(numbers);
        int[] arr1 = new int[bags];
        int[] arr2 = new int[bags];
        if (res[0]==1) { //能够平分
//            System.out.println(res[1]);
//            String str=findExp(numbers,res[1]);
//            String[] strs=str.split("");
//            System.out.println(strs);
//
//            for (int i=0;i<(bags-strs.length);i++){
//                if(){
//
//                }
//            }

        }

//        System.out.println(flag);


    }
//5
//7 4 5 3 3


    private static String findExp(int[] a, final int SUM) {
        ArrayList<String> result = new ArrayList<String>();    //存放的是所有找到的表达式
        Arrays.sort(a);    //对初始数据进行排序，便于算法的实现
        ArrayList<Integer> l = new ArrayList<Integer>();    //存放的是当前找到的合法的元素，这些元素的和小于SUM
        find(a, SUM, 0, l, result);    //通过递归的方式进行查找
        return result.get(0);
    }


    //递归函数，每一次的动作很简单，
    //在已经找到的n个元素的基础上，寻找第n+1个元素，
    private static void find(int[] a, final int SUM, int cur, ArrayList<Integer> l, ArrayList<String> result) {
        int beg = l.size() == 0 ? 0 : l.get(l.size() - 1) + 1;    //当前元素的查找范围的起始位置
        for (int i = beg; i < a.length; i++) {    //从起始位置到结束位置，查找合适的元素
            cur += a[i];    //在前面元素的和的基础上，加上当前元素
            if (cur < SUM) {    //如果仍然小于SUM，证明当前元素(第n+1个)合法，继续寻找第n+2个元素
                l.add(i);
                find(a, SUM, cur, l, result);
                cur -= a[i];    //消除第i个元素的影响，为了试验第i+1个元素做准备
                l.remove(l.size() - 1);    //消除第i个元素的影响，为了试验第i+1个元素做准备
            } else if (cur == SUM) {    //如果等于SUM，证明找到了表达式
                StringBuilder s = new StringBuilder();
                for (int x : l) {    //构造表达式，并存入result
                    s.append(a[x]);
                    s.append(" ");
                }
                s.append(a[i]);
//                s.append(" = ");
//                s.append(SUM);
                result.add(s.toString());

                break;    //回溯到上一个状态
            } else {    //如果大于SUM，也回溯到上一个状态
                break;
            }
        }
    }

}
