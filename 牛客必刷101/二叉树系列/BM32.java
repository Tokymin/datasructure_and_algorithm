package 牛客必刷101.二叉树系列;
//合并二叉树

//题目的主要信息:
//●合并. (相加)二叉树位置相同的结点
//●缺少的节点用另1棵树来补,若都缺则返回NULL

//方法1:递归前序遍历(推荐使用)
//具体做法:
//要将一棵二叉树的节点与另一棵二叉树相加合并，肯定需要遍历两棵二叉树，那我们可以考虑同步遍历两棵二叉树，这样就可以将每次遍历到
//的值相加在一起。遍历的方式有多种，这里推荐前序递归遍历。

//●step 1:首先判断t1与t2是否为空,若为则用另一个代替，若都为空,返回的值也是空。
//●step 2:然后依据前序遍历的特点，优先访问根节点，将两个根点的值相加创建到新树中。
//●step 3:两棵树再依次同步进入左子树和右子树。

import 二叉树.BTNode;

import java.util.LinkedList;
import java.util.Queue;

public class BM32 {
    public class Solution1 {
        public BTNode mergeTrees(BTNode t1, BTNode t2) {
            //若只有一个节点返回另一个，两个都为null自然返回null
            if (t1 == null)
                return t2;
            if (t2 == null)
                return t1;
            //根左右的方式递归
            BTNode head = new BTNode(t1.getdata() + t2.getdata());
            BTNode headleft = mergeTrees(t1.getlchild(), t2.getlchild());
            head.setlchild(headleft);
            BTNode headright = mergeTrees(t1.getrchild(), t2.getrchild());
            head.setrchild(headright);
            return head;
        }
    }

    //方法二:非递归层次遍历(扩展思路)
    //具体做法:
    //除了递归的遍历以外，非递归的层次遍历，也可以实现两棵树同步遍历节点相加，重点是两棵树从根节点开始每个节点是同步走的。

    //●step1: 首先判断t1与t2是否为空，若为则用另一个代替，若都为空，返回的值也是空。
    //●step2: 使用三个辅助队列，第一个队列q用于暂存合并后的二叉树的层次遍历结点，第二个队列q1用于暂存t1的层次遍历结点，第三个队
    //列q2用于暂存t2的层次遍历结点。
    //●step3:两棵树同步层次遍历，先将根节点加入队列中，同时根节点优先合并。
    //●step4: 每次从队列分别弹出一个元素，判断分别二者的左右子结点是否存在,若是都存在，则相加合并,若是只存在-一个则连接该存在
    //的结点，若是都不存在则连接null。

    public class Solution {
        public BTNode mergeTrees(BTNode t1, BTNode t2) {
            //若只有一个节点返回另一个，两个都为null自然返回null
            if (t1 == null)
                return t2;
            if (t2 == null)
                return t1;
            BTNode head = new BTNode(t1.getdata() + t2.getdata()); //合并根节点
            Queue<BTNode> q = new LinkedList<BTNode>(); //连接后的树的层次遍历节点
            Queue<BTNode> q1 = new LinkedList<BTNode>(); //分别存两棵树的层次遍历节点
            Queue<BTNode> q2 = new LinkedList<BTNode>();
            q.offer(head);
            q1.offer(t1);
            q2.offer(t2);
            while (!q1.isEmpty() && !q2.isEmpty()) { //不递归的都用一个while循环
                BTNode node = q.poll();
                BTNode node1 = q1.poll();
                BTNode node2 = q2.poll();
                BTNode left1 = node1.getlchild();
                BTNode left2 = node2.getlchild();
                BTNode right1 = node1.getrchild();
                BTNode right2 = node2.getrchild();
                if (left1 != null || left2 != null) {
                    if (left1 != null && left2 != null) { //两个左节点都存在
                        BTNode left = new BTNode(left1.getdata() + left2.getdata());
                        node.setlchild(left);
                        q.offer(left);  //新节点入队列
                        q1.offer(left1);
                        q2.offer(left2);
                    } else if (left1 != null) //只连接一个节点
                        node.setlchild(left1);
                    else
                        node.setlchild(left2);
                }
                if (right1 != null || right2 != null) {
                    if (right1 != null && right2 != null) { //两个右节点都存在
                        BTNode right = new BTNode(right1.getdata() + right2.getdata());
                        node.setrchild(right);
                        q.offer(right); //新节点入队列
                        q1.offer(right1);
                        q2.offer(right2);
                    } else if (right1 != null)  //只连接一个节点
                        node.setrchild(right1);
                    else
                        node.setrchild(right2);
                }
            }
            return head;
        }
    }
}
