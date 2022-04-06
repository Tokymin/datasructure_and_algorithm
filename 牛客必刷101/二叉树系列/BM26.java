package 牛客必刷101.二叉树系列;
// 求二叉树的层序遍历
// 题目的主要信息:
//●将给定二叉树按行从上到下、从左到右的顺序输出
//●输出到一个二维数组中,数组中每行就是二叉树的一层

import 二叉树.BTNode;

import javax.swing.tree.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

import static 二叉树.CreateTree.preCreat;

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

    public static class Solution1 {
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
    public static void main(String[] args) {
        BTNode tree = new BTNode();
        tree = preCreat(tree);
        Solution2 s = new Solution2();
        ArrayList<ArrayList<Integer>> res = s.levelOrder(tree);
        for (int i=0;i< res.size();i++)
            System.out.println((res.get(i)));
    }


    //方法二:递归(扩展思路)
    //具体做法:
    //既然二叉树的前序、中序、后序遍历都可以轻松用递归实现，树型结构本来就是递归喜欢的形式，那我们的层次遍历是不是也可以尝试用递归
    //来试试呢?按行遍历的关键是每一行的深度对应了它输出在二维数组中的深度，即深度可以与二维数组的下标对应，那我们递归的时候记录深
    //度就可以了啊。

    //●step1:首先判断二叉树是否为空，空树没有遍历结果。
    //●step2:使用递归进行层次遍历输出，每次递归记录当前二叉树的深度，每当遍历到一个节点，如果为空直接返回。
    //●step3:如果遍历的节点不为空,输出二维数组中一维数组的个数(即代表了输出的行数)小于深度，说明这个节点应该是新的一层，我
    //们在二维数组中增加一个一维数组，然后再加入二叉树元素。
    //●step4: 如果不是step3的情况说明这个深度我们已经有了数组，直接根据深度作为下标取出数组，将元素加在最后就可以了。
    //●step5: 处理完这个节点，再依次递归进入左右节点，同时深度增加。因为我们进入递归的时候是先左后右，那么遍历的时候也是先左后
    //右，正好是层次遍历的顺序。

    //再来看看这个递归过程中三段式:
    //●终止条件:遍历到了空节点，就不再继续，返回。
    //●返回值:将加入的输出数组中的结果往上返回。
    //●本级任务:处理按照_上述思路处理非空节点，并进入该节点的子节点作为子问题。
    public static class Solution2 {
        ArrayList<ArrayList<Integer> > res = new ArrayList(); //记录输出
        void traverse(BTNode root, int depth) {
            if(root != null){
                if(res.size() < depth){  //新的一层
                    ArrayList<Integer> row = new ArrayList();
                    res.add(row);
                    row.add(root.getdata());
                }else{ //读取该层的一维数组，将元素加入末尾
                    ArrayList<Integer> row = res.get(depth - 1);
                    row.add(root.getdata());
                }
            }
            else
                return;
            traverse(root.getlchild(), depth + 1); //递归左右时深度记得加1
            traverse(root.getrchild(), depth + 1);
        }
        public ArrayList<ArrayList<Integer>> levelOrder (BTNode root) {
            if(root == null)
                return res; //如果是空，则直接返回
            traverse(root, 1); //递归层次遍历
            return res;
        }
    }
}
