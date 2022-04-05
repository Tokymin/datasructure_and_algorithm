package 牛客必刷101.链表;
// 删除有序链表中重复的元素-II
// 题目的主要信息:

//●在一个非降序的链表中，存在重复的节点，删除该链表中重复的节点
//●重复的节点一个元素也不保留

//方法一:直接比较删除(推荐使用)

import 牛客必刷101.链表.ListNode;

import java.util.HashMap;
import java.util.Map;

//具体做法:
//这是一个升序链表，重复的节点都连在一起，我们就可以很轻易地比较到重复的节点，然后去删除。
//●step1: 给链表前加上表头，方便可能的话删除第一一个节点。
//●step2: 遍历链表，每次比较相邻两个节点，如果遇到了两个相邻结点相同，则新开内循环将这一段所有的相同都
//遍历过去。
//●step3:在step2中这一连串相同的节点前的节点直接连上后续第一个不相同值的节点。
//●step4:返回时去掉添加的表头。
public class BM16 {
    public class Solution1 {

        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) //空链表
                return null;
            ListNode res = new ListNode(0);
            res.next = head; //在链表前加一个表头
            ListNode cur = res;
            while (cur.next != null && cur.next.next != null) {
                if (cur.next.data == cur.next.next.data) { //遇到相邻两个结点值相同
                    int temp = cur.next.data;
                    while (cur.next != null && cur.next.data == temp) //将所有相同的都跳过
                        cur.next = cur.next.next;
                } else
                    cur = cur.next;
            }
            return res.next; //返回时去掉表头
        }
    }


    //方法二:哈希表(扩展思路) 链表无序时的做法
    //具体做法:
    //这道题幸运的是链表有序，我们可以直接与旁边的元素比较，然后删除重复。那我们扩展一一点，万一遇到的链表无序
    //呢?我们这里给出一种通用的解法，有序无序都可以使用，即使用哈希表来统计是否重复。
    //●step1:遍历一次链表用哈希表记录每个结点值出现的次数。
    //●step 2:在链表前加一个结点值为0的表头，方便可能的话删除表头元素。
    //●step3:再次遍历该链表，对于每个结点值检查哈希表中的计数，只留下计数为1的，其他情况都删除。
    //●step 4:返回时去掉增加的表头。
    public class Solution2 {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) //空链表
                return null;
            Map<Integer, Integer> mp = new HashMap<>(); //创建一个HashMap的方法
            ListNode cur = head;
            while (cur != null) { //遍历链表统计每个结点值出现的次数
                if (mp.containsKey(cur.data))//以链表节点的值作为key
                    mp.put(cur.data, (int) mp.get(cur.data) + 1);//次数作为value
                else
                    mp.put(cur.data, 1);
                cur = cur.next;
            }
            // 开始遍历工作
            ListNode res = new ListNode(0);
            res.next = head; //在链表前加一个表头
            cur = res;
            while (cur.next != null) { //再次遍历链表
                if (mp.get(cur.next.data) != 1) //如果结点值计数不为1
                    cur.next = cur.next.next; //删去该结点
                else
                    cur = cur.next;
            }
            return res.next; //去掉表头
        }
    }
}
