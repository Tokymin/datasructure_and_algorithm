package 递归_回溯;
//0-1背包问题的分支限界解法

    class MaxHeap {                      //Min-heap impmentation
        static HeapNode[] Heap;  //Pointer to the heap array
        static int size;     //Maximum size of the heap
        static int n;        //Number of intents now in heapheapsoet
        public MaxHeap(HeapNode[] h,int num,int max)//constructor
        { Heap=h;n=num;size=max;buildheap();}
        public int heapsize()//return current size of the heap
        {  return n;}
        public static boolean isLeaf(int pos)//true if pos is a leaf position
        { return(pos>=n/2)&&(pos<n);}
        public static void Assert_notFalse(boolean p,String q)
        {if(!p)System.out.println((String)q);}
        public static double key( HeapNode [] q,int p)
        {  return q[p].upperProfit;}
        //return position for left child of pos
        public static int leftchild(int pos)
        { Assert_notFalse(pos<n/2,"position has no left child");
            return 2*pos+1;
        }
        //return position for right child of pos
        public static int rightchild(int pos)
        {Assert_notFalse(pos<(n-1)/2,"position has no right child");
            return 2*pos+2;
        }
        public static int parent(int pos)//return position for parent
        {Assert_notFalse(pos>0,"position has no parent");
            return (pos-1)/2;
        }
        public static  void buildheap() //Heapify contents of Heap
        {  for(int i=n/2-1;i>=0;i--)siftdown(i);}

        public static void swap(HeapNode[] q,int i,int j)
        {
            HeapNode temp;
            temp=q[i];q[i]=q[j];q[j]=temp;}

        private static void siftdown(int pos) //put intent in itscorrent place
        {Assert_notFalse((pos>=0) && (pos<n),"illegal heap position ");
            while(! isLeaf(pos))
            {
                int j=leftchild(pos);
                if((j<(n-1))&&(key(Heap,j)<key(Heap,j+1)))
                    j++;// j is now index of child with greater value
                if(key(Heap,pos)>=key(Heap,j)) return;// Done
                swap(Heap,pos,j);
                pos=j;//Move down
            }
        }
        public static void insert(HeapNode val) //Insert value into heap
        {
            Assert_notFalse(n<size,"Heap is full ");
            int curr=n++;
            Heap[curr]=val;      //start t end of heap
            //Now sift up until curr's parent's key<curr's key
            while((curr!=0)&&(key(Heap,curr)>key(Heap,parent(curr))))
            {
                swap(Heap,curr,parent(curr));
                curr=parent(curr);
            }
        }
        public static HeapNode removemax()  //remove minimum value
        {
            Assert_notFalse(n>0,"Removing from empty heap ");
            swap(Heap,0,--n);//swap minimum with last value
            if(n!=0)     //Not on last intent
                siftdown(0); //Put new heap root val in corrent place
            return Heap[n];
        }
        //Remove intent at specified position
        public static HeapNode remove(int pos)
        {
            Assert_notFalse((pos>0)&&(pos<n),"illegal heap position ");
            swap(Heap,pos,--n);//swap with last value
            if(n!=0)     //Not on last intent
                siftdown(pos);//put new heap root val in corrent place
            return Heap[n];
        }
        public static void outMaxHeap()
        {
            for(int i=0;i<=n-1;i++)
                System.out.print(Heap[i].upperProfit+"  ");
            System.out.println();
        }

        static void heapsort()  //heapsort
        {
            System.out.println("建最大堆之后排序");

            for(int i=1;i<size-1;i++) //now sort
                System.out.print(removemax().upperProfit+"  ");
            System.out.println( );    //removemax places min value at end of heap
        }
    }// class 递归_回溯.MaxHeap

    class BBnode {
        BBnode parent;            // 父结点
        boolean leftChild;        // 左儿子结点标志
        BBnode(BBnode par,boolean ch)
        {
            parent=par;
            leftChild=ch;
        }
    }

    class HeapNode implements Comparable {
        BBnode liveNode;         //  活结点
        double upperProfit;      //  结点的价值上界
        double profit;           //  结点所相应的价值
        double weight;           //  结点所相应的重量
        int level;               //  活结点在子集树中所处的层序号


        HeapNode(BBnode node ,double up,double pp,double ww,int lev) {
            liveNode=node;
            upperProfit=up;
            profit=pp;
            weight=ww;
            level=lev;
        }

        public int compareTo(Object x) {
            double xup=((HeapNode) x).upperProfit;
            if(upperProfit<xup) return -1;
            if(upperProfit==xup) return 0;
            return 1;
        }
    }

    class Element implements Comparable {
        int id;           //    编号
        double d;         //    单位重量价值
        Element(int idd,double dd)
        {
            id=idd;
            d=dd;
        }
        public int compareTo(Object x) {
            double xd=((Element) x).d;
            if(d<xd) return -1;
            if(d==xd) return 0;
            return 1;
        }
        public boolean equals(Object x) {return d==((Element) x).d;}
    }

    class BBKnapsack {
        static double c;       // 背包容量
        static int  n;         // 物品总数
        static double []w;     // 物品重量数组
        static double []p;     // 物品价值数组
        static double  cw;     // 当前重量
        static double  cp;     // 当前价值
        static int [] bestx;   // 最优解
        static MaxHeap heap;   // 活结点优先队列

        private static double bound(int i)
        {                        //计算结点所相应价值的上界
            double cleft=c-cw;      //剩余容量
            double b=cp;            //价值上界
            //以物品单位重量价值递减序装填剩余容量
            while(i<=n && w[i]<=cleft)
            {
                cleft-=w[i];
                b+=p[i];
                i++;
            }

            //装填剩余容量装满背包
            if(i<=n)b+=p[i]/w[i]*cleft;
            return b;
        }
        private static void addLiveNode(double up,double pp,double ww,int lev,BBnode par,boolean ch)
        {  //将一个新的活结点插入到子集树的最大堆中
            BBnode b=new BBnode(par,ch);
            HeapNode node=new HeapNode(b,up,pp,ww,lev);
            heap.insert(node);
            //  System.out.println("新点价值上界   "+node.upperProfit+"  ");

        }
        private static double bbKnapsack(){   //优先队列分支界限法，返回最大价值，bestx 返回最优值
            //初始化
            BBnode enode=null;
            int i=1;
            double bestp=0.0;       //当前最优值
            double up=bound(1);     //价值上界
            //搜索子集空间树
            while(i!=n+1)
            {    //非叶结点
                //检查当前扩展结点的左儿子结点
                double wt=cw+w[i];
                if(wt<=c)
                {  //左儿子结点为可行结点
                    if(cp+p[i]>bestp)
                        bestp=cp+p[i];
                    addLiveNode(up,cp+p[i],cw+w[i],i+1,enode,true);
                    //heap.outMaxHeap();
                }
                up=bound(i+1);
                //检查当前扩展结点的右儿子结点
                if(up>=bestp)
                    //右子树可能含最优解
                    addLiveNode(up,cp,cw,i+1,enode,false);
                //取下一扩展结点

                HeapNode node=(HeapNode)heap.removemax();
                enode=node.liveNode;
                cw=node.weight;
                cp=node.profit;
                up=node.upperProfit;
                i=node.level;

            }
            //构造当前最优解
            for(int j=n;j>0;j--)
            {
                bestx[j]=(enode.leftChild)?1:0;
                enode=enode.parent;
            }
            return cp;
        }

        public static double knapsack(double[] pp,double []ww,double cc,int []xx) {    //返回最大价值，bestx返回最优解
            c=cc;
            int n=pp.length-1;
            //定义依单位重量价值排序的物品数组
            Element []q=new Element[n];
            double ws=0.0;      //装包物品重量
            double ps=0.0;      //装包物品价值
            for(int i=1;i<=n;i++)
            {
                q[i-1]=new Element(i,pp[i]/ww[i]);
                ps+=pp[i];
                ws+=ww[i];
            }
            if(ws<=c)//所有物品装包
            {
                for(int i=1;i<=n;i++)
                    xx[i]=1;
                return ps;
            }
            //依单位重量价值升序排序
            mergeSort(q,0,n-1);
            // 初始化类数据成员

            p=new double[n+1];
            w=new double[n+1];
            for(int i=1;i<=n;i++)  //转换为降序
            {
                p[i]=pp[q[n-i].id];
                w[i]=ww[q[n-i].id];
            }

            cw=0.0;
            cp=0.0;
            bestx=new int[n+1];
            HeapNode []h1=new HeapNode[n+1];
            heap=new MaxHeap(h1,0,n);
            //调用 bbKnapsack 求问题的最优解
            double maxp=bbKnapsack();
            for(int i=1;i<=n;i++)
                xx[q[n-i].id]=bestx[i];
            return maxp;
        }
        public static void mergeSort(Element [] a,int left,int right)
        {
            Element b[]=new Element[a.length];
            if(left<right){//至少有2个元素
                int i=(left+right)/2;//取中点
                mergeSort(a,left,i);
                mergeSort(a,i+1,right);
                merge(a,b,left,i,right);//合并到数组 b
                copy(a,b,left,right);//复制回数组 a
            }
        }
        public static void copy(Element[] a,Element[] b,int i,int j)
        {
            int k;
            for(k=i;k<=j;k++)
                a[k]=b[k];
        }
        public static void merge(Element [] c,Element [] d,int l,int m,int r)
        {//合并c[l:m]和c[m+1:r]到d[l:m]
            int i=l,
                    j=m+1,
                    k=l;
            while((i<=m)&&(j<=r))
                if(c[i].d-c[j].d<=0)
                    d[k++]=c[i++];
                else d[k++]=c[j++];
            if(i>m)
                for(int q=j;q<=r;q++)
                    d[k++]=c[q];
            else
                for(int q=i;q<=m;q++)
                    d[k++]=c[q];
        }

    }

    public  class Pack0_1{
        public static void main(String args[]) {
            BBKnapsack abc=new BBKnapsack();
            double[] v1 ={6,3,5,4,6,10,12,8,9,14,11,4,6,19,20,17};
            double[] w1 ={2,2,6,5,4,4,6,7,8,10,5,2,3,5,7,8};
            double c1=50;
            int n1=v1.length-1;
            int[] x1=new int[n1+1];
            double bestx1;
            abc.n=n1;
            bestx1=abc.knapsack(v1,w1,c1,x1);
            System.out.println("输出背包容量c1= "+c1);
            System.out.println("输出初值v  ");
            for(int i=1;i<=n1;i++)
                System.out.print(v1[i]+"  ");
            System.out.println();
            System.out.println("输出初值w  ");
            for(int i=1;i<=n1;i++)
                System.out.print(w1[i]+"  ");
            System.out.println();
            System.out.println("输出解空间和最优值 ");
            for(int i=1;i<=n1;i++)
                System.out.print(x1[i]+"  ");
            System.out.println();
            System.out.println(" 最优值= "+bestx1);

        }
    }



