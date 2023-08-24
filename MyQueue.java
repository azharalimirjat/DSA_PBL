import javax.management.openmbean.InvalidOpenTypeException;

public class MyQueue {
    private static class Node{
         CrashCase data;
         Node next=this;
         Node pre=this;
        public Node(CrashCase data){this.data=data;}
        public Node(CrashCase data, Node pre, Node next){
            this.data=data;
            this.pre=pre;
            this.next=next;
        }
    }
    private int size=0;
    private final Node head=new Node(null);
    public void add(CrashCase data) {
        head.pre.next=new Node(data,head.pre,head);
        head.pre=head.pre.next;
        size++;
    }
    public CrashCase remove() {
        if(isEmpty())throw new InvalidOpenTypeException("Queue is empty!");
        CrashCase temp=head.next.data;
        head.next=head.next.next;
        size--;
        return temp;
    }
    public CrashCase first() {
        if(isEmpty())throw new InvalidOpenTypeException("Queue is empty!");
        return head.next.data;
    }
    public int size() {
        return size;
    }
    public MyQueue copy(){
        MyQueue queue=new MyQueue();
        for(Node n=head.next;n!=head;n=n.next)queue.add(n.data);
        return queue;
    }//for making copy of queue
    public boolean isEmpty(){
        return size==0;
    }
}
