package 牛客必刷101.链表;
//删除链表的倒数第n个节点
//题目的主要信息:
//●给定一个链表，要删除链表倒数第n个节点，并返回链表的头
//●题目保证链表长度- -定大于等于n
//方法1: 双指针(推荐使用)
//具体做法:
//我们无法逆序遍历链表，就很难得到链表的倒数第n个元素，那我们可以试试反过来考虑，如果当前我们处于倒数第n
//的位置上，即距离链表尾的距离是n,那我们假设双指针指向这两个位置，二者同步向前移动，当前面个指针到了链表
//头的时候，两个指针之间的距离还是n。虽然我们没有办法让指针逆向移动，但是我们刚刚这个思路却可以正向实施:
//●step1:给链表添加一个表头，处理删掉第一个元素时比较方便。
//●step2: 准备一个快指针，在链表上先走n步。
//●step 3:准备慢指针指向原始链表头，代表当前元素，前序节点指向添加的表头，这样两个指针之间相距就是一-直
//都是n。
//●step4:快慢指针同步移动，当快指针到达链表尾部的时候，慢指针正好到了倒数n个元素的位置。

import 牛客必刷101.链表.ListNode;

public class BM9 {
    public class Solution1 {

        public ListNode removeNthFromEnd (ListNode head, int n) {
            ListNode res = new ListNode(-1); //添加表头
            res.next = head;
            ListNode cur = head; //当前节点
            ListNode pre = res; //前序节点
            ListNode fast = head;
            while(n != 0){ //快指针先行n步
                fast = fast.next;
                n--;
            }
            //快慢指针同步，快指针到达末尾，慢指针就到了倒数第n个位置
            while(fast != null){
                fast = fast.next;
                pre = cur;
                cur = cur.next;
            }
            pre.next = cur.next; //删除该位置的节点
            return res.next; //返回去掉头
        }
    }


    // 长度统计法（思路扩展）
    public class Solution2 {
        public ListNode removeNthFromEnd (ListNode head, int n) {
            int length = 0; //记录链表长度
            ListNode res = new ListNode(-1); //添加表头
            res.next = head;
            ListNode cur = head; //当前节点
            ListNode pre = res; //前序节点
            while(cur != null){ //找到链表长度
                length++;
                cur = cur.next;
            }
            cur = head; //回到头部
            for(int i = 0; i < length - n; i++){ //从头遍历找到倒数第n个位置
                pre = cur;
                cur = cur.next;
            }
            pre.next = cur.next; //删去倒数第n个节点
            return res.next; //返回去掉头节点
        }
    }
}
