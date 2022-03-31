package 二叉树;

import java.util.*;


// 二叉树的前序遍历

public class BitreeOrderTraversal {
    private static List<TreeNode> nodeList = null;

    // 构建一个二叉树的节点
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 前序
    public static void preorder(List<Object> list, TreeNode root) {
        if (root == null) return;
        //先遍历根节点
        list.add(root.val);
        preorder(list, root.left);
        preorder(list, root.right);

    }

    public static int[] preorderTraversal(TreeNode root) {
        // 添加遍历结果的数组
        List<Object> list = new ArrayList<>();
        //递归前序遍历
        preorder(list, root);
        //返回
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = (int) list.get(i);
        return res;

    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param root TreeNode类
     * @return int整型一维数组
     */
    public static void inorder(List<Integer> list, TreeNode root) {
        if (root == null) return;
        //先遍历根节点
        inorder(list, root.left);
        list.add(root.val);
        inorder(list, root.right);

    }

    public static int[] inorderTraversal(TreeNode root) {
        // write code here
        // 添加遍历结果的数组
        List<Integer> list = new ArrayList<>();
        //递归前序遍历
        inorder(list, root);
        //返回
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        return res;
    }

    // 后序
    public static void postorder(List<Integer> list, TreeNode root) {
        if (root == null) return;

        postorder(list, root.left);
        postorder(list, root.right);
        list.add(root.val);//最后遍历根节点

    }

    public static int[] postorderTraversal(TreeNode root) {
        // write code here
        // 添加遍历结果的数组
        List<Integer> list = new ArrayList<>();
        //递归前序遍历
        postorder(list, root);
        //返回
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        return res;
    }


    public static List<Object> list = new ArrayList<Object>();

    //————————————————
//    版权声明：本文为CSDN博主「呼声很高」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/hu2535357585/article/details/99174623
    public static void main(String[] args) {
//        System.out.println("HelloWorld");
        // 先构造一颗二叉树
//        Scanner in = new Scanner(System.in);
//        String str = in.next();// 一行 {1,#,2,3}
//        String[] strings = str.substring(1, str.length()-1).split(",");
//        if (strings[0].equals("#")) return;
//        Integer[] list = new Integer[strings.length];
//
//        // 用什么来存输入的值呢？用一个LinkedList
//        nodeList = new LinkedList<TreeNode>();
//        for (int i = 0; i < strings.length; i++) {
//            if (strings[i].equals("#")) list[i] = 0;
//            else {
//                list[i] = Integer.parseInt(strings[i]);
//            }
//        }
//        Integer[] l = new Integer[]{1, 2, null, null, 3};
        int[] l = new int[]{1, 2, 3, 4, 5};
//        LinkedList<Integer> inputList = new LinkedList<Integer>(Arrays.asList(l));
//        TreeNode treeNode = initBinTree(l);// 完全二叉树的构建
//        int[] res = inorderTraversal(treeNode);
//        System.out.println(Arrays.toString(res));
    }
}





// 层次遍历
class LevelOrder {
    /**
     * @param root TreeNode类
     * @return int整型ArrayList<ArrayList <>>
     */
    public ArrayList<ArrayList<Integer>> levelOrder(BitreeOrderTraversal.TreeNode root) {
        // write code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (root == null) return res; // 空直接返回空数据
        // 队列存储，进行层次遍历
        Queue<BitreeOrderTraversal.TreeNode> q = new ArrayDeque<BitreeOrderTraversal.TreeNode>();
        q.add(root);
        while(!q.isEmpty()){
            // 记录二叉树的某一行
            ArrayList<Integer> row=new ArrayList<>();
            int n=q.size();
            // 因为进入的是根节点。所以每层节点多少，队列的大小就是多少
            for(int i=0;i<n;i++){
                BitreeOrderTraversal.TreeNode cur=q.poll();
                row.add(cur.val);
                //若是左右孩子存在，则存入左右孩子作为下一层次
                if(cur.left!=null){
                    q.add(cur.left);
                }
                if(cur.right!=null){
                    q.add(cur.right);
                }
                res.add(row);//每一层加入输出
            }
        }
        return res;
    }
}
