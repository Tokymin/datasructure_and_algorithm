package 牛客必刷101.链表;
//删除有序链表中重复的元素-I
// 题目主要信息:
//●给定一个从小到大排好序的链表
//●删去链表中重复的元素,每个值只留下一个节点
//方法:遍历删除(推荐使用)

import 牛客必刷101.链表.ListNode;

//具体做法:
//既然相同的元素只留下一个，我们留下哪-一个最好呢?当然是遇到的第一个元素了!因为第-一个元素直接就与前面的链
//表节点连接好了，前面就不用管了，只需要跳过后面重复的元素，连接第一个不重复的元素就可以了，在链表中连接后
//面的元素总比连接前面的元素更方便嘛，因为不能逆序访问。
//●step1:判断链表是否为空链表，空链表不处理直接返回。
//●step2:使用一个指针遍历链表，如果指针当前节点与下一个节点的值相同，我们就跳过下一个节点，当前节点直
//接连接下个节点的后一位。
//●step3: 如果当前节点与下一个节点值不同，继续往后遍历。
//●step4:循环过程中每次用到了两个节点值，要检查连续两个节点是否为空。
public class BM15 {
    public class Solution {
        public ListNode deleteDuplicates (ListNode head) {
            if(head == null) //空链表
                return null;
            ListNode cur = head; //遍历指针
            while(cur != null && cur.next != null){ //指针当前和下一位不为空
                if(cur.data == cur.next.data) //如果当前与下一位相等则忽略下一位
                    cur.next = cur.next.next;
                else //否则指针正常遍历
                    cur = cur.next;
            }
            return head;
        }
    }

}
