package 牛客必刷101.链表;
//链表的奇偶重排
//题目主要信息:
//●给定一个链表，将奇数位的节点依次连在前半部分，偶数位的节点依次连在后半部分
//●返回连接后的链表头
//方法:双指针(推荐使用)

//具体做法:
//如下图所示，第一个节点是奇数位，第二个节点是偶数，第二个节点后又是奇数位，因此可以断掉节点1和节点2之间的
//连接，指向节点2的后面即节点3，如红色箭头。如果此时我们将第一一个节点指向第三个节点，就可以得到那么第三个节
//点后为偶数节点，因此我们又可以断掉节点2到节点3之间的连接,指向节点3后-一个节点即节点4，如蓝色箭头。那么
//我们再将第二个节点指向第四个节点，又回到刚刚到情况了。
//●step1: 判断空链表的情况，如果链表为空，不用重排。
//●step2: 使用双指针odd和even分别遍历奇数节点和偶数节点,并给偶数节点链表一个头。 odd 奇数  even 偶数
//●step3: 上述过程，每次遍历两个节点，且even在后面，因此每轮循环用even检查后两个元素是否为NULL,如
//果不为再进入循环进行上述连接过程。
//●step4: 将偶数节点头接在奇数最后一个节点后，再返回头部。

import 牛客必刷101.链表.ListNode;

public class BM14 {
    public class Solution {
        public ListNode oddEvenList (ListNode head) {
            if(head == null) //如果链表为空，不用重排
                return head;
            ListNode even = head.next; //even开头指向第二个节点，可能为空
            ListNode odd = head; //odd开头指向第一个节点
            ListNode evenhead = even; //指向even开头
            while(even != null && even.next != null){
                odd.next = even.next; //odd连接even的后一个，即奇数位
                odd = odd.next; //odd进入后一个奇数位
                even.next = odd.next; //even连接后一个奇数的后一位，即偶数位
                even = even.next; //even进入后一个偶数位
            }
            odd.next = evenhead; //even整体接在odd后面
            return head;
        }
    }
}
