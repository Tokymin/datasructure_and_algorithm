package 牛客必刷101.二叉树系列;
//二叉搜索树与双向链表

//题目的主要信息:
//●将二叉搜索树转化成递增序的双向链表
//●不能添加新的结点，要在原结点基础上添加链表链接
//●返回链表中的第一个节点的指针
//●二叉树节点的左右指针看成双向链表的前后指针


import 二叉树.BTNode;

import java.util.Stack;

public class BM30 {
    //方法一:递归中序遍历(推荐使用)
    //具体做法:
    //二叉搜索树的每个节点值大于它的左子节点，且大于全部左子树的节点值，小于它右子节点，且小于全部右子树的节点值。因此最左端的元素
    //一定最小,最右端的元素一定最大， 符合“左中右”的特性，因此二叉搜索树的中序遍历就是一个递增序列，我们只要对它中序遍历就可以组装
    //称为递增双向链表。

    //●step1: 创建两个指针，一个指向题目中要求的链表头(head) ，一个指向当前遍历的前一-结点(pre)。
    //●step2:首先递归到最左，初始化head与pre。
    //●step3: 然后处理中间根节点，依次连接pre与当前结点,连接后更新pre为当前节点。
    //●step4:最后递归进入右子树，继续处理。
    //●step5:递归出口即是节点为空则返回。

    public class Solution1 {
        public BTNode head = null;  //返回的第一个指针，即为最小值，先定为null
        public BTNode pre = null;   //中序遍历当前值的上一位，初值为最小值，先定为null

        public BTNode Convert(BTNode pRootOfTree) {
            if (pRootOfTree == null)
                return null;        //中序递归，叶子为空则返回
            Convert(pRootOfTree.getlchild()); //首先递归到最左最小值
            if (pre == null) {       //找到最小值，初始化head与pre
                head = pRootOfTree;
                pre = pRootOfTree;
            } else {       //当前结点与上一结点建立连接，将pre设置为当前值
                pre.setrchild(pRootOfTree);
                pRootOfTree.setlchild(pre);
                pre = pRootOfTree;
            }
            Convert(pRootOfTree.getrchild());
            return head;
        }
    }

    //方法二:非递归中序遍历(扩展思路)
    //具体做法:

    //二叉树中序遍历除了递归方法，我们还可以尝试非递归解法，与常规的非递归中序遍历几乎相同，只是增加了连接节点。

    //●step1: 创建两个指针，一个指向题目中要求的链表头(head) ，一个指向当前遍历的前一 -结点(pre), 创建- 一个布尔型变量,标记是否
    //是第一次到最左，因为第一-次到最左就是链表头。
    //●step 2:判断空树不能连接。
    //●step3:初始化一个栈辅助中序遍历。
    //●step4: 依次将父节点加入栈中，直接进入二叉树最左端。
    //●step5: 第一次进入最左，初始化head与pre,然后进入它的根节点开始连接。
    //●step6: 最后将右子树加入栈中，栈中依次就弹出“左中右”的节点顺序，直到栈为空。

    public class Solution2 {
        public BTNode Convert(BTNode pRootOfTree) {
            if (pRootOfTree == null)
                return null;
            Stack<BTNode> s = new Stack<BTNode>(); //设置栈用于遍历
            BTNode head = null;
            BTNode pre = null;
            boolean isFirst = true; //确认第一个遍历到最左，即为首位
            while (pRootOfTree != null || !s.isEmpty()) {
                while (pRootOfTree != null) {  //直到没有左节点
                    s.push(pRootOfTree);
                    pRootOfTree = pRootOfTree.getlchild();
                }
                pRootOfTree = s.pop();
                if (isFirst) {  //最左元素即表头
                    head = pRootOfTree;
                    pre = head;
                    isFirst = false;
                } else {   //当前结点与上一结点建立连接，将pre设置为当前值
                    pre.setrchild(pRootOfTree);
                    pRootOfTree.setlchild(pre);
                    pre = pRootOfTree;
                }
                pRootOfTree = pRootOfTree.getrchild();
            }
            return head;
        }
    }
}
