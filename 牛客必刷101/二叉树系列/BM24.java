package 牛客必刷101.二叉树系列;
//二叉树的中序遍历
//●给定一颗二叉树的根节点，输出其前序遍历的结果

//方法一:递归(推荐使用)
//具体做法:

//什么是二叉树的中序遍历，简单来说就是“左根右”，展开来说就是对于一棵二叉树，我们优先访问它的左子树，等到
//左子树全部节点都访问完毕，再访问根节点，最后访问右子树。同时访问子树的时候，顺序也与访问整棵树相同。
//从上述对于中序遍历的解释中，我们不难发现它存在递归的子问题，根节点的左右子树访问方式与原本的树相同，可以
//看成一颗树进行中序遍历，因此可以用递归处理:

//●终止条件:当子问题到达叶子节点后，后一个不无识别结果， 因此遇到空节点就返回。
//●返回值:每次处理完子问题后，就是将子问题访问过的元素返回，依次存入了数组中。
//●本级任务:每个子问题优先访问左子树的子问题，等到左子树的结果返回后，再访问自己的根节点，然后进入右
//子树。

//因此处理的时候，过程就是:

//●step1: 准备数组用来记录遍历到的节点值，Java可以用List, C++可以直接用vector。
//●step2:从根节点开始进入递归，遇到空节点就返回，否则优先进入左子树进行递归访问。
//●step3: 左子树访问完毕再回到根节点访问。
//●step4:最后进入根节点的右子树进行递归。

import 二叉树.BTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static 二叉树.CreateTree.preCreat;

public class BM24 {
    public static class Solution {
        public void inorder(List<Integer> list, BTNode root){
            if(root == null) //遇到空节点则返回
                return;
            inorder(list, root.getlchild()); //先去左子树
            list.add(root.getdata()); //再访问根节点
            inorder(list, root.getrchild()); //最后去右子树
        }
        public int[] inorderTraversal (BTNode root) {
            List<Integer> list = new ArrayList(); //添加遍历结果的数组
            inorder(list, root); //递归中序遍历
            int[] res = new int[list.size()]; //返回的结果
            for(int i = 0; i < list.size(); i++)
                res[i] = list.get(i);
            return res;
        }
    }
    public static void main(String[] args) {
        BTNode tree = new BTNode();
        tree = preCreat(tree);
        Solution s=new Solution();
        int[] res=s.inorderTraversal(tree);
        System.out.println(Arrays.toString(res));
    }
}
