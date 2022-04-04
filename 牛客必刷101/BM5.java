package 牛客必刷101;

//合并k个已排序的链表
//给定k个排好序的升序链表
//将这k个链表合并成一一个大的升序链表，并返回这个升序链表的头

//归并排序是什么?简单来说就是将一个数组每次划分成等长的两部分，对两部分进行排序
//即是子问题。对子问题继续划分，直到子问题只有1个元素。还原的时候呢，将每个子问
//题和它相邻的另一个子问题利用上述双指针的方式，1个与1个 合并成2个，2个与2个合并
//成4个，因为这每个单独的子问题合并好的都是有序的，直到合并成原本长度的数组。

import java.util.ArrayList;

public class BM5 {
    public class Solution {
        public ListNode Merge(ListNode list1, ListNode list2) { //两个链表合并
            if(list1 == null) //一个已经为空了，直接返回另一个
                return list2;
            if(list2 == null)
                return list1;
            ListNode head = new ListNode(0); //加一个表头
            ListNode cur = head;
            while(list1 != null && list2 != null){ //两个链表都要不为空
                if(list1.data <= list2.data){ //取较小值的结点
                    cur.next = list1;
                    list1 = list1.next; //只移动取值的指针
                }else{
                    cur.next = list2;
                    list2 = list2.next; //只移动取值的指针
                }
                cur = cur.next; //指针后移
            }
            if(list1 != null) //哪个链表还有剩，直接连在后面
                cur.next = list1;
            else
                cur.next = list2;
            return head.next; //返回值去掉表头
        }



        ListNode divideMerge(ArrayList<ListNode> lists, int left, int right){ //划分合并区间

            if(left > right)

                return null;

            else if(left == right) //中间一个的情况

                return lists.get(left);

            int mid = (left + right) / 2; //从中间分成两段，再将合并好的两段合并

            return Merge(divideMerge(lists, left, mid), divideMerge(lists, mid + 1, right));

        }



        public ListNode mergeKLists(ArrayList<ListNode> lists) {

            return divideMerge(lists, 0, lists.size() - 1);

        }

    }
}
