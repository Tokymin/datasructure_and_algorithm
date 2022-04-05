package 牛客必刷101.链表;
//两个元素值递增的链表，单个链表的长度为n .
//合并这两个链表并使新链表中的节点仍然是递增排序的


//step 1:判断空链表的情况，只要有一个链表为空，那答案必定就是另一个链表了，就算另一个链表也为空。
//step 2:新建一个空的表头后面连接两个链表排序后的结点。
//step 3:遍历两个链表都不为空的情况，取较小值添加在新的链表后面，每次只把被添加的链表的指针后移。
//step 4:遍历到最后肯定有一个链表还有剩余的结点，它们的值将大于前面所有
//的，直接连在新的链表后面即可。

import 牛客必刷101.链表.ListNode;

public class BM4 {
    public class Solution1 {
        public ListNode Merge(ListNode list1, ListNode list2) {
            if (list1 == null) //一个已经为空了，直接返回另一个
                return list2;
            if (list2 == null)
                return list1;
            ListNode head = new ListNode(0); //加一个表头
            ListNode cur = head;
            while (list1 != null && list2 != null) { //两个链表都要不为空
                if (list1.data <= list2.data) { //取较小值的结点
                    cur.next = list1;
                    list1 = list1.next; //只移动取值的指针
                } else {
                    cur.next = list2;
                    list2 = list2.next; //只移动取值的指针
                }
                cur = cur.next; //指针后移
            }
            if (list1 != null) //哪个链表还有剩，直接连在后面
                cur.next = list1;
            else
                cur.next = list2;
            return head.next; //返回值去掉表头
        }
    }
//    step 1: 每次比较两个链表当前结点的值，然后取较小值的链表指针往后，另-一个
//    不变送入递归中。
//    step 2:递归回来的结果我们要加在当前较小值的结点后面，相当于不断在较小值
//    后面添加结点。
//    step3:递归的终止是两个链表为空。

    public class Solution2 {// 递归思路
        public ListNode Merge(ListNode list1,ListNode list2) {
            if(list1 == null) //一个已经为空了，返回另一个
                return list2;
            if(list2 == null)
                return list1;
            if(list1.data <= list2.data){ //先用较小的值的结点
                list1.next = Merge(list1.next, list2); //递归往下
                return list1;
            }else{
                list2.next = Merge(list1, list2.next); //递归往下
                return list2;
            }
        }
    }
}
