package 企业真题.MeiTuan._0402;

import java.util.*;

class solution4 {
    private static ArrayList <Integer>tmpArr = new ArrayList<>();
    private static ArrayList <Integer>res = new ArrayList<>();

    public static void combine(int index,int k,int []arr) {
        if(k == 1){
            for (int i = index; i < arr.length; i++) {
                tmpArr.add(arr[i]);
                System.out.println(tmpArr.toString());
                tmpArr.remove((Object)arr[i]);
            }
        }else if(k > 1){
            for (int i = index; i <= arr.length - k; i++) {
                tmpArr.add(arr[i]);
                combine(i + 1,k - 1, arr);
                tmpArr.remove((Object)arr[i]);
            }
        }else{
            return ;
        }
    }

    public <T> List<List<T>> fixedGrouping(List<T> source, int n) {

        if (null == source || source.size() == 0 || n <= 0)
            return null;
        List<List<T>> result = new ArrayList<List<T>>();

        int sourceSize = source.size();
        int size = (source.size() / n) + 1;
        for (int i = 0; i < size; i++) {
            List<T> subset = new ArrayList<T>();
            for (int j = i * n; j < (i + 1) * n; j++) {
                if (j < sourceSize) {
                    subset.add(source.get(j));
                }
            }
            if(subset.size()>0){
                result.add(subset);
            }
        }
        return result;
    }


}

public class T4 {
    public static void main(String[] args) {
//
        int[] com = {1, 1, 3, 4, 5, 6, 7, 8};
//        int k = 3;
//        if (k > com.length || com.length <= 0) {
//            return;
//        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();



        solution4 s4 = new solution4();
//        s4.fixedGrouping(0, k, com);
//        System.out.println(res.toString());


    }


}
