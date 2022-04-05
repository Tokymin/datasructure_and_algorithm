package 牛客必刷101.链表;
//链表中倒数最后k个结点

//题目的主要信息:
//●一个长度为n的链表，返回原链表中从倒数第k个结点至尾节点的全部节点
//●如果该链表长度小于k，请返回一个长度为0的链表
//方法1:快慢双指针(推荐使用)

//具体做法:
//我们无法逆序遍历链表，就很难得到链表的倒数第k个元素，那我们可以试试反过来考虑，如果当前我们处于倒数第k
//的位置上，即距离链表尾的距离是k,那我们假设双指针指向这两个位置，二者同步向前移动，当前面个指针到了链表
//头的时候，两个指针之间的距离还是k。虽然我们没有办法让指针逆向移动，但是我们刚刚这个思路却可以正向实施:
//●step1: 准备一个快指针，从链表头开始，在链表_上先走k步。
//●step2: 准备慢指针指向原始链表头，代表当前元素，则慢指针与快指针之间的距离一 直都是k。
//●step3: 快慢指针同步移动，当快指针到达链表尾部的时候，慢指针正好到了倒数k个元素的位置。

import 牛客必刷101.链表.ListNode;

public class BM8 {
    public class Solution {
        public ListNode FindKthToTail(ListNode pHead, int k) {
            int n = 0;
            ListNode fast = pHead;
            ListNode slow = pHead;
            for (int i = 0; i < k; i++) {  //快指针先行k步
                if (fast != null)
                    fast = fast.next;
                else //达不到k步说明链表过短，没有倒数k
                    return slow = null;
            }
            //快慢指针同步，快指针先到底，慢指针指向倒数第k个
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
            return slow;
        }
    }
}
