package 牛客必刷101.二叉树系列;
//二叉树中和为某一值的路径(一)
//题目的主要信息:
//●给定一个二叉树root和一个值sum，判断是否有从根节点到叶子节点的节点值之和等于sum的路径
//, 路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
//●路径只能从父节点到子节点，不能从子节点到父节点

import 二叉树.BTNode;

import java.util.Stack;

public class BM29 {
    //方法一:递归(推荐使用)
    //具体做法:

    //既然是检查从根到叶子有没有一条等于目标值的路径，那肯定需要从根节点遍历到叶子，我们可以在根节点每次往下一层的时候，将sum减去
    //节点值，最后检查是否完整等于0.而遍历的方法我们可以选取二叉树常用的递归先序遍历,因为每次进入一个子节点，更新sum值以后，相当
    //于对子树查找有没有等于新目标值的路径，因此这就是子问题，递归的三段式为:

    //●终止条件:每当遇到节点为空，意味着过了叶子节点，返回。每当检查到某个节点没有子节点，它就是叶子节点，此时sum减去叶子节点
    //值刚好为0，说明找到了路径。
    //●返回值:将子问题中是否有符合新目标值的路径层层往上返回。
    //●本级任务:每一级需要检查是否到了叶子节点，如果没有则递归地进入子节点，同时更新sum值减掉本层的节点值。

    //整个过程如下:
    //●step1: 每次检查遍历到的节点是否为空节点，空节点就没有路径。
    //●step 2:再检查遍历到是否为叶子节点，且当前sum值等于节点值,说明可以刚好找到。
    //●step 3:检查左右子节点是否可以有完成路径的，如果任意一条路径可以都返回true,因此这里选用两个子节点递归的或。
    public class Solution1 {
        public boolean hasPathSum(BTNode root, int sum) {
            if (root == null) //空结点找不到路径
                return false;
            //叶子结点，且路径和为sum
            if (root.getlchild() == null && root.getrchild() == null && sum - root.getdata() == 0)
                return true;
            //递归进入子结点
            return hasPathSum(root.getlchild(), sum - root.getdata()) || hasPathSum(root.getrchild(), sum - root.getdata());
        }
    }


    //方法_ C:非递归(扩展思路)

    //具体做法:
    //在二叉树中能够用递归解决的问题，很多时候我们也可以用非递归来解决。这里遍历过程也可以使用栈辅助，进行dfs遍历，检查往下的路径中
    //是否有等于sum的路径和。
    //注意，这里仅是dfs,而不是先序遍历，左右节点的顺序没有关系，因为每次往下都是单独添加某个节点的值相加然后继续往下，因此左右节点
    //谁先遍历不管用。 所以dfs 和先序的区别是在左右节点的顺序上

    //●step1:首先检查空节点，空树没有路径。
    //●step 2:使用两个栈同步遍历，-一个栈记录节点，辅助深度优先搜索，另一个栈跟随记录到该节点为止的路径和(C+ +中可以在一一个栈中
    //嵌套pair实现)。根节点及根节点值先进栈。
    //●step3:遍历的时候每次弹出两个栈中的内容，判断是否是叶子节点且路径和是否等于目标值。
    //●step4: 没有到叶子节点就将左右子节点(如果有)加入栈中，并跟随加入路径和。
    //●step5:如果遍历结束也没有找到路径和，则该二叉树中没有。
    public class Solution2 {
        public boolean hasPathSum(BTNode root, int sum) {
            if (root == null) //空节点找不到路径
                return false;
            Stack<BTNode> s1 = new Stack<BTNode>(); //栈辅助深度优先遍历  建立一个栈的方法
            Stack<Integer> s2 = new Stack<Integer>(); //跟随s1记录到相应节点为止的路径和
            s1.push(root);
            s2.push(root.getdata());
            while (!s1.isEmpty()) {
                BTNode temp = s1.pop(); //弹出相应节点
                int cur_sum = s2.pop(); //弹出到该点为止的当前路径和
                //叶子节点且当前路径和等于sum
                if (temp.getlchild() == null && temp.getrchild() == null && cur_sum == sum)
                    return true;
                if (temp.getlchild() != null) { //左节点及路径和入栈
                    s1.push(temp.getlchild());
                    s2.push(cur_sum + temp.getlchild().getdata());
                }
                if (temp.getrchild() != null) { //右节点及路径和入栈
                    s1.push(temp.getrchild());
                    s2.push(cur_sum + temp.getrchild().getdata());
                }
            }
            return false;
        }
    }
}
