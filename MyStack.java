// import java.util.EmptyStackException;
// //Stack class
// public class MyStack{
//     //Node class
//     private static class Node{
//         CrashCase data;//for storing object of CrashCase
//         Node next;
//         public Node(CrashCase data){this.data=data;}
//     }
//     private int size;//size of stack
//     private Node top;//top of stack
//     private String name;//name of stack
//     public MyStack(String name){this.name=name;}
//     public boolean isEmpty(){
//         return size==0;
//     }
//     public void push(CrashCase data){
//         Node n=new Node(data);
//         n.next=top;
//         top=n;
//         size++;
//     }
//     public CrashCase pop(){
//         if(isEmpty())throw new EmptyStackException();
//         Node n=top;
//         top=top.next;
//         size--;
//         return n.data;
//     }
//     public CrashCase peek(){
//         if(isEmpty())throw new IllegalArgumentException();
//         return top.data;
//     }

//     public int getSize() {
//         return size;
//     }
//     public String getName() {
//         return name;
//     }
// }
