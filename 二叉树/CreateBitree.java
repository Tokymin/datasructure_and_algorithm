package 二叉树;

import java.util.*;

class node{
    String data;
    node leftchild;
    node rightchild;
    public node(String i,node left,node right){
        this.data = i;
        this.leftchild = left;
        this.rightchild = right;
    }
    public node(String s){
        this(s,null,null);
    }
    public node(){
    }
}


public class CreateBitree {
    static int i=0;
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        String s = scn.nextLine();
        String[] s1 = s.trim().split(" ");

        node root = new node();
        root = creat(root,s1);

        look(root);     //前序遍历
    }
    //递归创建
    public static node creat(node node,String[] s1){
        String temp = s1[i++];
        if(temp.equals("#")){
            return null;
        }else{
            node = new node(temp);
            node.leftchild = creat(node.leftchild,s1);
            node.rightchild = creat(node.rightchild,s1);
        }
        return node;
    }
    //前序遍历
    public static void look(node node){
        if(node!=null){
            System.out.print(node.data);
            look(node.leftchild);
            look(node.rightchild);
        }
    }
//————————————————
//    版权声明：本文为CSDN博主「babylove_BaLe」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/babylove_BaLe/article/details/78533828


}
