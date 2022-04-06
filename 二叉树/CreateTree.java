package 二叉树;

import java.util.Scanner;

public class CreateTree {
    // 这个创建的方法是基于前序遍历的，也就是根左右，并且还要算上最后一排叶子节点的0
    // 例如：一棵1，2，3，4，5，6，7的完全二叉树，其加上叶子节点的前序遍历是124005003600700 -->BM23 [1, 2, 4, 5, 3, 6, 7]
    // 按照这个输入进去就创建好了，其中序遍历是[4, 2, 5, 1, 6, 3, 7]  -->BM24
    // 后续遍历 [4, 5, 2, 6, 7, 3, 1] -->BM25
    // 层次遍历 [1]
                //[2, 3]
                //[4, 5, 6, 7] -->BM26
    public static BTNode preCreat(BTNode btnode) {
        Scanner in = new Scanner(System.in);
//        System.out.println("输入结点的值");
        int value = in.nextInt();
        if (value != 0) {
            btnode = new BTNode();
            btnode.setdata(value);
            //以下两行是核心代码
            btnode.setlchild(preCreat(btnode.getlchild()));
            btnode.setrchild(preCreat(btnode.getrchild()));
        } else
            //这个是一定要有的，确定最终的结束结点
            btnode = null;
        return btnode;
    }

    public static void visit(BTNode btnode) {
        if (btnode != null)
            System.out.print(btnode.getdata() + " ,");
    }

    public static void preorder(BTNode btnode) {
        if (btnode != null) {
            visit(btnode);
            preorder(btnode.getlchild());
            preorder(btnode.getrchild());
        }
    }

    public static void inorder(BTNode btnode) {
        if (btnode != null) {

            inorder(btnode.getlchild());
            visit(btnode);
            inorder(btnode.getrchild());
        }
    }

    public static void main(String[] args) {
        BTNode tree = new BTNode();
        tree = preCreat(tree);
        inorder(tree);
    }
}
