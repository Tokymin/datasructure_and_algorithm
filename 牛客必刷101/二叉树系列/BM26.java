package 牛客必刷101.二叉树系列;
// 求二叉树的层序遍历
// 题目的主要信息: .
//●将给定二叉树按行从上到下、从左到右的顺序输出
//●输出到一个二维数组中,数组中每行就是二叉树的一层

import 二叉树.BTNode;

import javax.swing.tree.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BM26 {
    //方法一:非递归(推荐使用)
    //具体做法:
    //二叉树的层次遍历就是按照从上到下每行，然后每行中从左到右依次遍历，得到的二叉树的元素值。对于层次遍历，我们通常会使用队列来辅助:
    //因为队列是一种先进先出的数据结构，我们依照它的性质，如果从左到右访问完一行节点, 并在访问的时候依次把它们的子节点加入队列，那
    //么它们的子节点也是从左到右的次序，且排在本行节点的后面，因此队列中出现的顺序正好是层次遍历。
    //那我们解决这道题目的思路就有了:
    //●step1: 首先判断二叉树是否为空，空树没有遍历结果。
    //●step2: 建立辅助队列，根节点首先进入队列。不管层次怎么访问，根节点一定是第一个，那它肯定排在队伍的最前面。
    //●step3: 每次进入一层，统计队列中元素的个数。因为每当访问完-层，下一层作为这一 层的子节点，一定都加入队列，而再下一层还没
    //有加入，因此此时队列中的元素个数就是这一层的元素 个数。
    //●step4: 每次遍历这一层这么多的节点数，将其依次从队列中弹出，然后加入这一行的一维数组中，如果它们有子节点，依次加入队列排
    //队等待访问。
    //●step5:访问完这一层的元素后，将这个一维数组加入二维数组中，再访问下一层。

    public class Solution {
        public ArrayList<ArrayList<Integer>> levelOrder(BTNode root) {// 二层Arraylist 的套法
            ArrayList<ArrayList<Integer>> res = new ArrayList();
            if (root == null)
                return res; //如果是空，则直接返回空vector
            Queue<BTNode> q = new ArrayDeque<BTNode>(); //队列存储，进行层次遍历 // 新建一个队列的方法
            q.add(root);// 队列和list一样都是add
            while (!q.isEmpty()) {
                ArrayList<Integer> row = new ArrayList();  //记录二叉树的某一行
                int n = q.size();
                //因先进入的是根节点，故每层结点多少，队列大小就是多少
                for (int i = 0; i < n; i++) {
                    BTNode cur = q.poll();
                    row.add(cur.getdata());
                    //若是左右孩子存在，则存入左右孩子作为下一个层次
                    if (cur.getlchild() != null)
                        q.add(cur.getlchild());
                    if (cur.getrchild() != null)
                        q.add(cur.getrchild());
                }
                res.add(row); //每一层加入输出
            }
            return res;
        }

    }
}
