package 牛客必刷101.二叉树系列;

import 二叉树.BTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static 二叉树.CreateTree.preCreat;

public class BM25 {
    public static class Solution {
        public void postorder(List<Integer> list, BTNode root) {
            if (root == null) //遇到空节点则返回
                return;
            postorder(list, root.getlchild()); //先去左子树
            postorder(list, root.getrchild()); //最后去右子树
            list.add(root.getdata()); //再访问根节点
        }

        public int[] postorderTraversal(BTNode root) {
            List<Integer> list = new ArrayList(); //添加遍历结果的数组
            postorder(list, root); //递归中序遍历
            int[] res = new int[list.size()]; //返回的结果
            for (int i = 0; i < list.size(); i++)
                res[i] = list.get(i);
            return res;
        }
    }

    public static void main(String[] args) {
        BTNode tree = new BTNode();
        tree = preCreat(tree);
        Solution s = new Solution();
        int[] res = s.postorderTraversal(tree);
        System.out.println(Arrays.toString(res));
    }
}
