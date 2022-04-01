package 递归;

class RuMen {
    //1、这个函数是想干啥？
    // 定义一个算阶乘的函数
    private int f(int n) {
        // 2、明确知道啥时候递归就结束了，比如n=1时，就是1.或者n=2时就是2
        if (n <= 2) {
            return n;// 包含了以上两种情况
        }
        // 第三要素：找出函数的等价关系式-->目的是为了让自己的问题不断变小。比如f(n)=n*f(n-1),此时就变小了
        return f(n - 1) * n;
    }
}

class Q2 {
    // 斐波拉契数列
    // //1、这个函数是想干啥？ f(n)=f(n-1)+f(n-2) n>=2 从第n项开始算起
    private int f(int n) {
        // 2、明确知道啥时候递归就结束了，比如n=1时，就是1.或者n=2时就是2
        if (n <= 2) {
            return 1;// 包含了以上两种情况
        }
        // 第三要素：找出函数的等价关系式-->目的是为了让自己的问题不断变小。比如f(n)=n*f(n-1),此时就变小了
        return f(n - 1) + f(n - 2);
    }

}

class Q3 {
    // 小青蛙跳台阶
    //小青蛙一次可以跳1阶也可2，求跳上一个n阶的总共有多少种跳法
    // //1、这个函数是想干啥？
    // 我的分析： 全部都右一种 全1 的。第二是要看奇数偶数。偶数的话还要加上一种全2的情况.
    //但是是要考虑递归的，那不就是假设现在已经到了一个台阶f(n)了，其实这个f(n)要么是通过f(n-1)跳1级上来的，要么是通过f(n-2)跳两级上来的，所以
    // 也是和斐波那契数列相同

    private int f(int n) {
        // 2、明确知道啥时候递归就结束了，比如n=1时，就是1.或者n=2时就是2
        if (n <= 2) {// 要切记不能让n一直递归到小于0的情况了
            return 1;// 包含了以上两种情况
        }
        // 第三要素：找出函数的等价关系式-->目的是为了让自己的问题不断变小。比如f(n)=n*f(n-1),此时就变小了
        return f(n - 1) + f(n - 2);
    }

}


class Q4 {
    // 反转单链表
    //1-2-3-4 --> 4-3-2-1
    // //1、这个函数是想干啥？
    //但是是要考虑递归的，f(node) 传一个 节点进来 它和 其他的 f(node->next) 有什么关系吗？如何找呢，还是一样的划分，划分到最后会越来越小
    // 去看看求f(node) 和它减少一个 node的问题有什么关系
//链表节点
    class Node {
        protected Node next;
        protected int data;

        public Node(int data) {   //构造器来赋值
            this.data = data;
            this.next = null;
        }

        public Node() {
        }
    }

    private Node reverseList(Node head) {
        // 2、明确知道啥时候递归就结束了，比如node==null 时，就是null,还有一种就是当这个链表只剩下两个时候，就直接是node->
        if (head == null || head.next == null) {//
            return head;
        }
        // 3、第三要素：找出函数的等价关系式-->目的是为了让自己的问题不断变小。
        // 链表的 这个像数字规律题目一样好找。但是我们可以先尝试一下 只进行一遍 2->3->4的反转。发现和1-2-3-4相比，也就是差把2的重新指向1，然
        //后把1的指向空就行了

        // 3.1 先递归反转
        Node newlist = reverseList(head.next);
        // 3.2 改变1，2 节点的指向
        Node t1 = head.next;// 先获取节点2
        t1.next = head;// 让2的next指向2
        head.next = null; // 1的next指向1
        // 3.3 返回调整后的链表
        return newlist;
    }
}

public class DiGui {
    public static void main(String[] args) {

    }


}
