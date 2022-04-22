package 企业真题.Ali._0422;
// leetcode1574. 删除最短的子数组使剩余数组有序
//
//给你一个整数数组 arr ，请你删除一个子数组（可以为空），使得 arr 中剩下的元素是 非递减 的。
//
//一个子数组指的是原数组中连续的一个子序列。
//
//请你返回满足题目要求的最短子数组的长度。
//
//示例 1：
//
//输入：arr = [1,2,3,10,4,2,3,5]
//输出：3
//解释：我们需要删除的最短子数组是 [10,4,2] ，长度为 3 。剩余元素形成非递减数组 [1,2,3,3,5] 。
//另一个正确的解为删除子数组 [3,10,4] 。
//
//示例 2：
//
//输入：arr = [5,4,3,2,1]
//输出：4
//解释：由于数组是严格递减的，我们只能保留一个元素。所以我们需要删除长度为 4 的子数组，要么删除 [5,4,3,2]，要么删除 [4,3,2,1]。
//
//示例 3：
//
//输入：arr = [1,2,3]
//输出：0
//解释：数组已经是非递减的了，我们不需要删除任何元素。
//
//示例 4：
//
//输入：arr = [1]
//输出：0



//租题目描述
//小红拿到了一个数组a,她想删掉其中一个连续的子数组，使得剩下的部分严格递增。
//小红想知道，自己最少删掉多少个数?
//输入描述:
//第一行输入一个正整数n,代表数组的长度。
//第二行输入n个正整数2，代表小红拿到的数组。
//1<n< 200000
//1<a≤109
//输出描述:
//一个整数， 代表删除的最少的数。
//示例1输入输出示例仅供调试，后台判题数据般不包含示例
//输入
//示例1输入输出示例仅供调试，后台判题数据般不包含示例
//输入
//131245
//输出
//2
//说明
//删除前两个数即可。最终生成的数组是[1.2.4,51，满足严格递增。
//示列2输入输出示例仅供调试，后台判题数据一般不包含示例
//输入
//D
//6
//123345
//输出
//1




public class T3 {
}

//C++ code

/*
*
#include <set>
#include<string>
#include "math.h"
#include <iostream>
#include <regex>
#include <vector>
using namespace std;
class Solution {

public:
    int findLengthOfShortestSubarray(vector<int>& arr) {

        // right表示左边数组的右边界
        int right = 0, n = arr.size(), res = 0, i = 1;
        // 从头开始的递增序列
        while (i < n && arr[i] >= arr[right]) {

            right = i;
            i++;
        }
        // res初始化为后面所有的长度（即只留下左边的有序数组，后边全删除，这是最大的res值）
        res = n - 1 - right;

        int left = i;
        // 找到尾部的递增有序序列的起点left
        for (int i = left + 1; i < n; i++) {

            if (arr[i] >= arr[i - 1])
                continue;
            else
                left = i;
        }

        // [left,n-1]是非递减有序的
        // 可以从右边的有序数组中找某个点i开始接入左边的有序数组中形成新的完整的有序数组
        // 这样只需要删除中间的子数组即可，找到删除最小的情况
        for (int i = n - 1; i >= left; i--) {

            while (right >= 0 && arr[i] < arr[right])
                right--;
            res = min(res, i - right - 1);
        }
        if (right>0)// 多加了一个判断，right>0 保证删除第一个元素时，right=-1 下面越界
        {
            if (arr[left] == arr[right]) // 这里就是看有没有相邻的比如1 1 这种情况
            {
                res = res + 1;
            }
        }

        return res;

    }
};
int main() {

    Solution s = Solution();
    vector<int> arr;
    int n;
    cin >> n;
    for (int i = 0; i <n;i++) {
        int t;
        cin >> t;
        arr.push_back(t);
    }

    arr.push_back(3);
    arr.push_back(1);
    arr.push_back(2);
    arr.push_back(4);
    arr.push_back(5);
    int res=s.findLengthOfShortestSubarray(arr);
    cout<<res;
}
*/