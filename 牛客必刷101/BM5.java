package 牛客必刷101;

//合并k个已排序的链表
//给定k个排好序的升序链表
//将这k个链表合并成一一个大的升序链表，并返回这个升序链表的头

//归并排序是什么?简单来说就是将一个数组每次划分成等长的两部分，对两部分进行排序
//即是子问题。对子问题继续划分，直到子问题只有1个元素。还原的时候呢，将每个子问
//题和它相邻的另一个子问题利用上述双指针的方式，1个与1个 合并成2个，2个与2个合并
//成4个，因为这每个单独的子问题合并好的都是有序的，直到合并成原本长度的数组。

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;


public class BM5 {
    // 解决思路1 归并排序的思想
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



    // 我们也可以准备k个指针，每次比较得出k个数字中的最小值。利用Java提供的PriorityQueue或者C+ + ssSLT提供的优
    //先队列，它是一种参照堆排序的容器，容器中的元素是有序的，如果是小顶堆，顶部元素就是最小的，每次可以直接取
    //出最小的元素，而每次插入的成本根据堆排序，就是O(log2n)。也就是每次该容器中有k个元素，我们可以直接拿出
    //最小的元素，再插入下一个元素，相当于每次都是链表的k个指针在比较大小。
    //●step1: 不管是Java还是C++都需要重载比较方法，构造一个比较链表节点大小的小顶堆。
    //●step2:先遍历k个链表头，将不是空节点的节点加入优先队列。
    //●step3:每次依次弹出优先队列中的最小元素，将其连接在合并后的链表后面，然后将这个节点在原本链表中的后
    //一个节点(如果不为空的话)加入队列，类似上述双指针的过程。
    public class Solution2 {

        public ListNode mergeKLists(ArrayList<ListNode> lists) {
            Queue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.data - v2.data); //小顶堆,在括号里的是比较方法
            for(int i = 0; i < lists.size(); i++){ //遍历所有链表第一个元素
                if(lists.get(i) != null) //不为空则加入小顶堆
                    pq.add(lists.get(i)); // 这里它其实只是把链表的头节点加入了队列当中而已，因为虽然这是一个存着ListNode的数组
                // 实际上它只是放了个头节点进去而已。
            }
            ListNode res = new ListNode(-1); //加一个表头
            ListNode head = res;
            while(!pq.isEmpty()){ //直到小顶堆为空
                ListNode temp = pq.poll(); //取出最小的元素
                head.next = temp; //连接
                head = head.next;
                if(temp.next != null) //每次取出链表的后一个元素加入小顶堆
                    pq.add(temp.next);
            }
            return res.next; //去掉表头
        }
    }

}
