package 牛客必刷101.二叉树系列;
//二叉树的镜像
//题目的主要信息:

//●将二叉树镜像， 即将其所有左右子树交换.
//我们可以考虑自底向.上依次交换二叉树的左右结点。


import 二叉树.BTNode;

public class BM33 {
    //方法一:递归(推荐使用)
    //具体做法:
    //因为我们需要将二叉树镜像，意味着每个左右子树都会交换位置，如果我们从上到下对遍历到的节点交换位置,但是它们后面的节点无法跟着
    //他们一起被交换，因此我们可以考虑自底向.上对每两个相对位置的节点交换位置，这样往上各个子树也会被交换位置。
    //自底向上的遍历方式，我们可以采用后序递归的方法:

    //●step1: 先深度最左端的节点，遇到空树返回。
    //●step2: 然后进入子树的最右端。
    //●step3: 再返回到父问题，交换父问题两个节点的值。

    public class Solution {
        public BTNode Mirror(BTNode pRoot) {
            if (pRoot == null) //空树返回
                return null;
            BTNode left = Mirror(pRoot.getlchild());  //先递归子树
            BTNode right = Mirror(pRoot.getrchild());
            pRoot.setlchild(right); //交换
            pRoot.setrchild(left);
            return pRoot;
        }
    }
}
