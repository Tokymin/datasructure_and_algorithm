package 牛客必刷101.二叉树系列;
//二叉树的前序遍历(dfs)
//题目的主要信息:
//●给定一颗二叉树的根节点，输出其前序遍历的结果

import 二叉树.BTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static 二叉树.CreateTree.preCreat;

public class BM23 {
    //方法1:递归(推荐使用)
    //具体做法:
    //什么是二叉树的前序遍历?简单来说就是“根左右”，展开来说就是对于一颗二叉树优先访问其根节点，然后访问它的
    //左子树，等左子树全部访问完了再访问其右子树，而对于子树也按照之前的访问方式，直到到达叶子节点。
    //从上述前序遍历的解释中我们不难发现，它存在递归的子问题:每次访问一个节点之后，它的左子树是一个要前序遍历
    //的子问题，它的右子树同样是一个要前序遍历的子问题。那我们可以用递归处理:

    //●终止条件:当子问题到达叶子节点后，后一个不管左右都是空，因此遇到空节点就返回。
    //●返回值:每次处理完子问题后，就是将子问题访问过的元素返回，依次存入了数组中。
    //●本级任务:每个子问题优先访问这棵子树的根节点，然后递归进入左子树和右子树。

    //因此处理的时候，过程就是:
    //●step1:准备数组用来记录遍历到的节点值，Java可以用List，C++可以直接用vector。
    //●step2:从根节点开始进入递归，遇到空节点就返回，否则将该节点值加入数组。
    //●step3:依次进入左右子树进行递归。
    public static class Solution {
        public void preorder(List<Integer> list, BTNode root) {
            if (root == null) //遇到空节点则返回
                return;
            list.add(root.getdata()); //先遍历根节点
            preorder(list, root.getlchild()); //再去左子树
            preorder(list, root.getrchild()); //最后去右子树
        }

        public int[] preorderTraversal(BTNode root) {
            List<Integer> list = new ArrayList(); //添加遍历结果的数组
            preorder(list, root); //递归前序遍历
            int[] res = new int[list.size()]; //返回的结果
            for (int i = 0; i < list.size(); i++)
                res[i] = list.get(i);
            return res;
        }
    }


    public static void main(String[] args) {
        BTNode tree = new BTNode();
        tree = preCreat(tree);
        Solution s=new Solution();
        int[] res=s.preorderTraversal(tree);
        System.out.println(Arrays.toString(res));
    }
}
