package 企业真题.rongyao._0421;
import java.util.*;
//https://blog.csdn.net/zhilamou7549/article/details/107092998
import java.util.Arrays;

public class T3 {
    private static boolean result = false;
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0,1,0,1,1},
                {0,0,1,0,0},
                {1,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}};
        boolean[] visited = new boolean[matrix.length];
        for(int i = 0; i < matrix.length; i++)
            for(int j = 0; j < matrix[0].length; j++){
                //节点i到j可达则进行深度优先搜索
                if(matrix[i][j] != 0){
                    Arrays.fill(visited, false);
                    visited[j] = true;
                    dfs(matrix, i, j, visited);
                }
                if(result){
                    System.out.println("存在环路");
                    return;
                }
            }
        System.out.println("不存在环路");
    }

    private static void dfs(int[][] matrix, int start, int cur_node, boolean[] visited){
        for(int col = 0; col < matrix.length; col++){
            //当前节点可达且未访问过
            if(matrix[cur_node][col] != 0 && !visited[col]){
                if(col == start){
                    result = true;
                    return;
                }
                //记录访问过的节点
                visited[col] = true;
                dfs(matrix, start, col, visited);
                visited[col] = false;
            }
        }
    }
}


/*
* C++ code
*
* //#include <bits/stdc++.h>
#include <vector>
#include <set>
#include<string>
#include <iostream>
#include <regex>
using namespace std;

typedef long long ll;
typedef unsigned long long ull;

void dfs(int v, vector<bool>& visited, vector<bool>& instack, vector<int>& stack, set<vector<int>>& cycle, const vector<vector<int>>& graph)
{
    visited[v] = true;
    //如果v已经在搜索路径上，再次回到了v, 说明在 stack中从v到栈顶这一段组成了环
    if (instack[v])
    {
        auto start = find(stack.begin(), stack.end(), v);
        vector<int> tmp(start, stack.end());
        cycle.emplace(tmp);
        return;
    }
    stack.emplace_back(v);
    instack[v] = true;
    for (int w : graph[v])
    {
        dfs(w, visited, instack, stack, cycle, graph);
    }
    instack[v] = false;
    stack.pop_back();
}

void helper(const vector<vector<int>>& graph, set<vector<int>>& cycle, int MAXN)
{
    vector<bool> instack(MAXN, false);
    vector<bool> visited(MAXN, false);
    vector<int> stack;
    for (int i = 0; i < MAXN; ++i)
    {
        if (!visited[i])
        {
            dfs(i, visited, instack, stack, cycle, graph);
        }
    }
}

int main(void) {
    //输入格式：MAXN节点以内，编号0~MAXN-1,链式输入,以非数字，非->符号分隔 1->2;2->3;...
    string str;
    const int MAXN = 100;
    while (true)
    {
        getline(cin, str);
        if (str.empty()) break;
        regex re(R"((\d+)->(\d+))");
        vector<vector<int>> graph(MAXN);
        for (sregex_token_iterator it(str.begin(), str.end(), re), end; it != end; it++)
        {
            string s = *it;
            smatch m;
            if (regex_match(s, m, re))
            {
                int u = stoi(m[1]);
                int v = stoi(m[2]);
                graph[u].push_back(v);
            }
        }
        set<vector<int>> cycle;
        helper(graph, cycle, MAXN);
        if (cycle.empty()) printf("-\n");
        else
        {
            for (auto& cl : cycle)
            {
                for (auto& i : cl) printf("%d->", i);
                printf("%d\n", cl.front());
            }
        }
    }
    return 0;
}
*
*
* */