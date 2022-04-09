package 二叉树;

public class BTNode {
    //定义一个二叉树的结点
    private int data;
    private BTNode lchild;
    private BTNode rchild;
    //构造函数
    public BTNode(){
    }
    //新的构造函数，其实也可以简写
    public BTNode(int data){
        this.data=data;
        this.lchild=null;
        this.rchild=null;
    }
    public BTNode(int data ,BTNode lchild ,BTNode rchild){
        this.data=data;
        this.lchild=lchild;
        this.rchild=rchild;
    }
    //以下就是data lchild rchild的set，get函数
    public void setdata(int data){
        this.data=data;
    }
    public int  getdata(){
        return data;
    }
    public void setlchild(BTNode lchild ){
        this.lchild=lchild;
    }
    public BTNode getlchild(){
        return this.lchild;
    }
    public void setrchild(BTNode rchild ){
        this.rchild=rchild;
    }
    public BTNode getrchild(){
        return this.rchild;
    }
}
