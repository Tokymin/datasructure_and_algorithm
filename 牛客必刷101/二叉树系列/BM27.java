package 牛客必刷101.二叉树系列;
//按之字形顺序打印二叉树
// 题目的主要信息:
//●给定一个二叉树，返回该二叉树的之字形层序遍
//●第一层从左向右，下一层从右向左，一直这样交替

//方法一:非递归层次遍历(推荐使用)

//具体做法:

//按照层次遍历按层打印二叉树的方式，每层分开打印，然后对于每一层利用flag标记，第-层为false,之后每到一层取反- -次，如果该层的
//flag为true,则记录的数组整个反转即可。
//但是难点在于如何每层分开存储，从哪里知晓分开的时机?在层次遍历的时候，我们通常会借助队列(queue) ,事实上，队列中的值大有玄
//机，让我们一起来看看:当根节点进入队列时，队列长度为1,第-层结点数也为1;若是根节点有两个子节点，push进队列后，队列长度为2,
//第二层结点数也为2;若是根节点一个子节点，push进队列后，队列长度为为1，第二层结点数也为1.由此，我们可知，每层的结点数等于进入
//该层时队列长度，因为刚进入该层时，这一层每个结点都会push进队列，而_上-一层的结点都出去了。

//●step1: 首先判断二叉树是否为空，空树没有打印结果。
//●step2: 建立辅助队列，根节点首先进入队列。不管层次怎么访问，根节点-定是第一一个，那它肯定排在队伍的最前面，初始化flag变量
//●step3: 每次进入一层，统计队列中元素的个数，更改flag变量的值。因为每当访问完一层，下一层作为这一层的子节点，-定都加入队
//列，而再下一层还没有加入，因此此时队列中的元素个数就是这一层的元素个数。
//●step4: 每次遍历这一层这么多的节点数，将其依次从队列中弹出，然后加入这一行的一 维数组中，如果它们有子节点，依次加入队列排
//队等待访问。
//●step5: 访问完这一层的元素后，根据flag变量决定将这个-维数组直接加入二维数组中还是反转后再加入，然后再访问下一层。

import 二叉树.BTNode;

import java.util.*;

public class BM27 {
    public class Solution {
        public ArrayList<ArrayList<Integer>> Print(BTNode pRoot) {
            BTNode head = pRoot;
            ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
            if (head == null)
                return res; //如果是空，则直接返回空list
            Queue<BTNode> temp = new LinkedList<BTNode>(); //队列存储，进行层次遍历
            // 不知这个Linklist 和new ArrayDeque<BTNode>(); 有什么关系
            temp.offer(head);
            BTNode p;
            boolean flag = true;
            while (!temp.isEmpty()) {
                ArrayList<Integer> row = new ArrayList<Integer>();  //记录二叉树的某一行
                int n = temp.size();
                flag = !flag; //奇数行反转，偶数行不反转
                //因先进入的是根节点，故每层结点多少，队列大小就是多少
                for (int i = 0; i < n; i++) {
                    p = temp.poll();
                    row.add(p.getdata());
                    //若是左右孩子存在，则存入左右孩子作为下一个层次
                    if (p.getlchild() != null)
                        temp.offer(p.getlchild());
                    if (p.getrchild() != null)
                        temp.offer(p.getrchild());
                }
                if (flag)  //奇数行反转，偶数行不反转
                    Collections.reverse(row);
                res.add(row);
            }
            return res;
        }
    }

    // 方法二:双栈法(扩展思路)
    //具体做法:
    //方法一用到了反转函数，反转我们能想到什么?肯定是先进后出的栈!我们可以利用两个栈遍历这棵二叉树，第一个栈S1从根结点开始记录第
    //一层，然后依次遍历两个栈,遍历第一个栈时遇到的子节点依次加入第二个栈S2中， 即是第二层，而遍历第二个栈S2的时候因为 是先进后出，
    //因此就是逆序的，再将第二个栈S2的子节点依次加入第一- 个栈S1中，于是原本的逆序在第一- 个栈S1中又变回了正序，如果反复交替直到两个栈
    //都空为止。
    //●step1: 首先判断二叉树是否为空，空树没有打印结果。
    //●step2: 建立两个辅助栈，每次依次访问第-一个栈S1与第二个栈S2,根节点先进入S1.
    //●step 3:依据依次访问的次序，S1必定记录的是奇数层，访问节点后，将它的子节点(如果有)依据先左后右的顺序加入s2,这样S2在访
    //问的时候根据栈的先进后出原理就是右节点先访问，正好是偶数层需要的从右到左访问次序。偶数层则正好相反，要将子节点(如果有)
    //依据先右后左的顺序加入S1,这样在S1访问的时候根据栈的先进后出原理就是左节点先访问，正好是奇数层需要的从左到右访问次序。
    //●step4: 每次访问完- -层，即一个栈为空，则将- 维数组加入二维数组中，并清空以便下一-层用来记录。
    public class Solution2 {

        public ArrayList<ArrayList<Integer>> Print(BTNode pRoot) {
            BTNode head = pRoot;
            ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
            if (head == null)
                return res; //如果是空，则直接返回空list
            Stack<BTNode> s1 = new Stack<BTNode>();
            Stack<BTNode> s2 = new Stack<BTNode>();//建立一左一右两个栈
            s1.push(head); //放入第一次，root节点
            while (!s1.isEmpty() || !s2.isEmpty()) {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                while (!s1.isEmpty()) { //遍历奇数层
                    BTNode node = s1.pop();
                    temp.add(node.getdata()); //记录奇数层
                    if (node.getlchild() != null)  //奇数层的子结点加入偶数层
                        s2.push(node.getlchild());
                    if (node.getrchild() != null)
                        s2.push(node.getrchild());
                }
                if (temp.size() != 0)  //数组不为空才添加
                    res.add(new ArrayList<Integer>(temp));
                temp.clear(); //清空本层数据
                while (!s2.isEmpty()) { //遍历偶数层
                    BTNode node = s2.pop();
                    temp.add(node.getdata());  //记录偶数层
                    if (node.getrchild() != null)  //偶数层的子结点加入奇数层
                        s1.push(node.getrchild());
                    if (node.getlchild() != null)
                        s1.push(node.getlchild());
                }
                if (temp.size() != 0) //数组不为空才添加
                    res.add(new ArrayList<Integer>(temp));
                temp.clear(); //清空本层数据
            }
            return res;
        }
    }
}
