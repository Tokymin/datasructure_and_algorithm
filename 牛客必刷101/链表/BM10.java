package 牛客必刷101.链表;

import 牛客必刷101.链表.ListNode;

//两个链表的第一个公共结点
//题目中的信息:
//●两个链表含有公共结点或没有，有公共结点则返回第1公共结点指针
//●单链表，无循环
//●没有公共节点返回空
//方法1:双指针长度比较法(推荐使用)
//具体做法:
//如果两个链表有公共节点，那么它们后半部分都是相同的，我们要找的也就是后半部分的第一个节点，只要遍历两个指
//针同时抵达第1个相同的节点， 我们就找到了它。
//●step1:单独的遍历两个链表，得到各自的长度。
//●step2:求得两链表的长度差n,其中较长的链表的指针从头先走n步。
//●step3: 两链表指针同步向后遍历，遇到第1个相同的节点就是第1个公共结点。
public class BM10 {
    public class Solution1 {
        public int ListLenth(ListNode pHead){  //计算链表长度的函数
            ListNode p = pHead;
            int n = 0;
            while(p != null){
                n++;
                p = p.next;
            }
            return n;
        }
        public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
            int p1 = ListLenth(pHead1);
            int p2 = ListLenth(pHead2);
            if(p1 >= p2){  //当链表1更长时，链表1指针先走p1-p2步
                int n = p1 - p2;
                for(int i = 0; i < n; i++){
                    pHead1 = pHead1.next;
                }
                //两个链表同时移动，直到有公共结点时停下
                while((pHead1 != null) && (pHead2 != null) && (pHead1 != pHead2)){
                    pHead1 = pHead1.next;
                    pHead2 = pHead2.next;
                }
            }
            else{  //反之，则链表2先行p2-p1步
                int n = p2 - p1;
                for(int i = 0; i < n; i++){
                    pHead2 = pHead2.next;
                }
                //两个链表同时移动，直到有公共结点时停下
                while((pHead1 != null) && (pHead2 != null) && (pHead1 != pHead2)){
                    pHead1 = pHead1.next;
                    pHead2 = pHead2.next;
                }
            }
            return pHead1;//随便返回一个公共节点
        }
    }


    //由上种方法长度差的思路，不同上述一个指针先走,另1个指针后走， 仅需将两个链表连在一起，两个指针同步走。易知
    //将两个链表连在一起长度都相等，对于遍历两个链表的两个指针，公共部分走的步数是一样的， 非公共部分因都走了两
    //个链表，因此也是相同的，所以绕了一圈，第一个相同的结点便是第一个公共结点。
    //●step1: 判断链表情况，其中有一个为空，则不能有公共结点,返回null。
    //●step2:两个链表都从表头开始同步依次遍历。
    //●step3:不需要物理上将两个链表连在一起，仅需指针在一个链表的尾部时直接跳到另一个链表的头部。
    //●step4: 根据上述说法，第一个相同的结点便是第一个公共结点。
    public class Solution2 {
        public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
            //其中有一个为空，则不能有公共结点，返回null
            if(pHead1 == null || pHead2 == null)
                return null;
            ListNode p1 = pHead1;
            ListNode p2 = pHead2;
            while(p1 != p2){ //相当于遍历两次两个链表所有值
                p1 = p1 == null ? pHead2 : p1.next;
                p2 = p2 == null ? pHead1 : p2.next;
            }
            return p1;
        }
    }
}
