package 牛客必刷101.链表;
//链表中的节点每k个一组翻转
//给定一个链表，从头开始每k个作为1组，将每组的链表结点翻转
// 组与组之间的位置不变
// 如果最后链表末尾剩余不足k个元素，则不翻转，直接放在最后

//递归做法


import 牛客必刷101.链表.ListNode;

public class BM3 {
// 1、终止条件 2、返回值 3、本级任务
public class Solution {
    public ListNode reverseKGroup (ListNode head, int k) {
        ListNode tail = head; //找到每次翻转的尾部
        for(int i = 0; i < k; i++){ //遍历k次到尾部
            if(tail == null) //如果不足k到了链表尾，直接返回，不翻转
                return head;
            tail = tail.next;
        }
        ListNode pre = null; //翻转时需要的前序和当前节点
        ListNode cur = head;
        while(cur != tail){ //在到达当前段尾节点前
            ListNode temp = cur.next; //翻转
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        head.next = reverseKGroup(tail, k); //当前尾指向下一段要翻转的链表
        return pre;
    }
}

}
