package 企业真题.MeiTuan;


import java.util.*;

class solution {
    public int findbottom(int a[], int n) {
        int left = 0;
        int right = n - 1;
        int result = 1;
        while (left < right - 1) {
            int mid = left + ((right - left) >> 1);
            if (a[mid] <= a[mid + 1]) {
                if (a[mid] <= a[mid - 1]) {
                    result = mid;
                    break;
                } else {
                    right = mid;
                }
            } else {
                left = mid;
            }
        }
        return result;
    }
    public int findPeakElement(int nums[], int n) {
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

class Solution2 {
    public long f(int a[], int n) {
        int[] left_up_sum = new int[100005];
        int[] right_down_sum = new int[100005];
        int[] left_up = new int[100005];
        int[] right_down = new int[100005];

        if (n == 1 || n == 2) {
            return 0;
        }
        //前缀和
        left_up_sum[0] = 0;
        left_up[0] = a[0];
        for (int i = 1; i < n; ++i) {
            left_up[i] = a[i] > left_up[i - 1] ? a[i] : left_up[i - 1] + 1;
            left_up_sum[i] = left_up_sum[i - 1] + left_up[i] - a[i];
        }
        //从右往左
        right_down[n - 1] = a[n - 1];
        right_down_sum[n - 1] = 0;
        right_down_sum[n] = 0;
        for (int i = n - 2; i >= 0; --i) {
            right_down[i] = a[i] > right_down[i + 1] ? a[i] : right_down[i + 1] + 1;
            right_down_sum[i] = right_down_sum[i + 1] + right_down[i] - a[i];
        }
        //计算res
        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            res = Math.min(left_up_sum[i] + right_down_sum[i + 1] + Math.max(right_down[i + 1] + 1 - left_up[i], 0), res);
        }
        return res;
    }

}


public class T2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//数组长度
        if (n == 0) {
            return;
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int item = sc.nextInt();//数组元素
            arr[i] = item;
        }
        Solution2 s = new Solution2();
//        int res = s.findbottom(arr, n);//返回的波谷的数组下标
//        int res2 = s.findPeakElement(arr, n);//返回的波峰数组下标
//        System.out.println(arr[res2] - arr[res] + 1);
        long res=s.f(arr, n);
        System.out.println(res);
    }
}