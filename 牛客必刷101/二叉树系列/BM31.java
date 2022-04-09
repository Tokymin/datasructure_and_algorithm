package 牛客必刷101.二叉树系列;
//对称的二叉树

//判断一棵二叉树是否是镜像，即判断二叉树是否是轴对称图形

import 二叉树.BTNode;

import java.util.LinkedList;
import java.util.Queue;

public class BM31 {

    //方法一:递归(推荐使用)

    //具体做法:

    //前序遍历的时候我们采用的是“根左右”的遍历次序，如果这棵二叉树是对称的，即相应的左右节点交换位置完全没有问题，那我们是不是可以
    //尝试“根右左”遍历，按照轴对称图像的性质，这两种次序的遍历结果应该是一样的。不同的方式遍历两次,将结果拿出来比较看起来是一种可
    //行的方法，但也仅仅可行，太过于麻烦。我们不如在遍历的过程就结果比较了。
    //
    // 依据前序递归的次序,我们先访问根节点，然后递归地进入左子节点和右子节点，如果要采用“根右左”的顺序，那就应该是递归地进入右子节点，
    // 然后进入左子节点。我们可以准备两个指针啊，遍历的时候“根左右”走左边的时候“根右左”走右边，“根左右”走右边的时候“根右左”走左边，
    // 这样刚好可以同步遍历比较。

    //●step1:终止条件:当进入子问题的两个节点都为空，说明都到了叶子节点，且是同步的，因此结束本次子问题，返回true;当进入子问
    //题的两个节点只有一个为空，或是元素值不相等，说明这里的对称不匹配，同样结束本次子问题，返回false。
    //●step2:返回值:每一级将子问题是否匹配的结果往上传递。
    //●step3:本级任务:每个子问题，需要按照上述思路，“根左右”走左边的时候“根右左”走右边，“根左右”走右边的时候“根右左”走左
    //边，一起进入子问题，需要两边都是匹配才能对称。
    public class Solution1 {
        boolean recursion(BTNode root1, BTNode root2) {
            if (root1 == null && root2 == null) //可以两个都为空
                return true;
            //只有一个为空或者节点值不同，必定不对称
            if (root1 == null || root2 == null || root1.getdata() != root2.getdata())
                return false;
            //每层对应的节点进入递归比较
            return recursion(root1.getlchild(), root2.getrchild()) && recursion(root1.getrchild(), root2.getlchild());
        }
        boolean isSymmetrical(BTNode pRoot) {
            return recursion(pRoot, pRoot);
        }
    }

    //方法二:层次遍历(扩展思路)
    //具体做法:

    //除了递归以外，我们还可以观察，对称的二叉树每-层都是回文的情况，即两边相互对应相等，有节点值的对应节点值，没有节点的连空节点
    //都是对应着的呢。那我们从左往右遍历--层(包括空节点)，和从右往左遍历--层(包括空节点)，是不是就是得到--样的结果了。(注: 必
    //须包含空节点，因为空节点乱插入会导致不同，如题干第二个图所示)。
    //这时候二叉树每一层的遍历， 我就需要用到了层次遍历。层次遍历从左往右经过第-层后，怎么进入第二层?我们可以借助队列一--一个先进
    //先出的容器，在遍历第一层的时候，将第-层节点的左右节点都加入到队列中，因为加入队列的顺序是遍历的顺序且先左后右，也就导致了我
    //从队列出来的时候也是下-层的先左后右，正好----对应。更巧的是，如果我们要从右到左遍历-层，加入队列后也是先右后左，简直完美对
    //应!

    //而且我们不需要两个层次遍历都完整地遍历二叉树，只需要一半就行了 ，从左往右遍历左子树，从右往左遍历右子树，各自遍历- -半相互比
    //对，因为遍历到另- -半都已经检查过了。

    //●step1: 首先判断链表是否为空，空链表直接就是对称。
    //●step2: 准备两个队列，分别作为从左往右层次遍历和从右往左层次遍历的辅助容器，初始第一 个队列加入左节点，第二个队列加入右节点。
    //●step 3:循环中每次从队列分别取出一个节点,如果都为空，暂时可以说是对称的，进入下- -轮检查;如果某-一个 为空或是两个节点值不
    //同，那必定不对称。其他情况暂时对称，可以依次从左往右加入子节点到第一一个队列， 从右往左加入子节点到第二个队列。(这里 包括空
    //节点)
    //●step 4:遍历结束也没有检查到不匹配，说明就是对称的。
    public class Solution {
        boolean isSymmetrical(BTNode pRoot) {
            if(pRoot == null) //空链表为对称的
                return true;
            //辅助队列用于从两边层次遍历
            Queue<BTNode> q1 = new LinkedList<BTNode>();
            Queue<BTNode> q2 = new LinkedList<BTNode>();
            q1.offer(pRoot.getlchild());
            q2.offer(pRoot.getrchild());
            while(!q1.isEmpty() && !q2.isEmpty()){
                BTNode left = q1.poll(); //分别从左边和右边弹出节点
                BTNode right = q2.poll();
                //都为空暂时对称
                if(left == null && right == null)
                    continue;
                //某一个为空或者数字不相等则不对称
                if(left == null || right == null || left.getdata() != right.getdata())
                    return false;
                q1.offer(left.getlchild()); //从左往右加入队列
                q1.offer(left.getrchild());
                q2.offer(right.getrchild()); //从右往左加入队列
                q2.offer(right.getlchild());
            }
            return true; //都检验完都是对称的
        }
    }
}
