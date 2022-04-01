package 链表;
// 头插法创造链表的节点

import java.util.*;

//链表节点
class Node {
    protected Node next;
    protected int data;
    public Node(int data) {   //构造器来赋值
        this.data = data;
        this.next = null;
    }
    public Node() {
    }
}

class operation {
    public Node Initlink(Node node) {  //初始化节点
        node = new Node();
        node.next = null;
        return node;
    }

    public Node createlink(Node node) {    //头插法创建链表
        Scanner scanner = new Scanner(System.in);
        int data = scanner.nextInt();
        while (data != 999) {              //输入999结束
            Node pNode = new Node(data);
            pNode.next = node.next;
            node.next = pNode;
            data = scanner.nextInt();
        }
        return node;
    }

    public void Printlink(Node L) {       //打印输出链表
        Node node = L.next;
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }

    }

}

public class Create {
    public static void main(String[] args) {
        operation op = new operation();
        Node node = new Node();
        Node n = op.Initlink(node);
        Node res = op.createlink(n);
        op.Printlink(res);
    }
}
