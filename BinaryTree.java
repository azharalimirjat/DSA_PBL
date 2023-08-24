public class BinaryTree {
    Object root;
    BinaryTree left,right;
    public BinaryTree(Object root){this.root=root;}
    public BinaryTree(Object root, BinaryTree left, BinaryTree right){
        this.root=root;
        this.left=left;
        this.right=right;
    }

    public Object getRoot() {
        return root;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public BinaryTree getRight() {

        return right;
    }
    //for getting the smallest value from the tree
    public String getSmallest(){
        BinaryTree temp=this;//assigning the actual tree to temp so that can be traversed
        int leftSmall,rightSmall,temporary;
        String[] parts=String.valueOf(temp).split(",");//because it has two parts cases before comma and month after comma
        leftSmall=Integer.parseInt(parts[0]);
        rightSmall=Integer.parseInt(parts[0]);
        String monthLeft=parts[1],monthRight=parts[1];
        while (temp.left!=null){
            parts=String.valueOf(temp.left.root).split(",");
            temporary=Integer.parseInt(parts[0]);
            if(temporary<leftSmall){
                leftSmall=temporary;
                monthLeft=parts[1];
            }
            temp=temp.left;
        }
        temp=this;
        while (temp.right!=null){
            parts=String.valueOf(temp.right.root).split(",");
            temporary=Integer.parseInt(parts[0]);
            if(temporary<rightSmall){
                rightSmall=temporary;
                monthRight=parts[1];
            }
            temp=temp.right;
        }
        if(leftSmall<rightSmall)return leftSmall+", crashes in "+monthLeft;
        return rightSmall+", crashes in "+monthRight;
    }
    public String getLargest(){
        BinaryTree temp=this;
        int leftLarge,rightLarge,temporary;
        String[] parts=String.valueOf(temp).split(",");
        leftLarge=Integer.parseInt(parts[0]);
        rightLarge=Integer.parseInt(parts[0]);
        String monthLeft=parts[1],monthRight=parts[1];
        while (temp.left!=null){
            parts=String.valueOf(temp.left.root).split(",");
            temporary=Integer.parseInt(parts[0]);
            if(temporary>leftLarge){
                leftLarge=temporary;
                monthLeft=parts[1];
            }
            temp=temp.left;
        }
        temp=this;
        while (temp.right!=null){
            parts=String.valueOf(temp.right.root).split(",");
            temporary=Integer.parseInt(parts[0]);
            if(temporary>rightLarge){
                rightLarge=temporary;
                monthRight=parts[1];
            }
            temp=temp.right;
        }
        if(leftLarge>rightLarge)return leftLarge+", crashes in "+monthLeft;
        return rightLarge+", crashes in "+monthRight;
    }

    public void add(String side, Object noOfCases){
        BinaryTree temp=this;
        if(side.equals("left")){
            while (true) {
                if (temp.left == null) {
                    temp.left = new BinaryTree(noOfCases);
                    return;
                }
                temp = temp.left;
            }
        }
        else if(side.equals("right")){
            while (true) {
                if (temp.right == null) {
                    temp.right = new BinaryTree(noOfCases);
                    return;
                }
                temp = temp.right;
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder s=new StringBuilder();
        if(left!=null)s.append(left).append(" , ");
        s.append(root).append("\t");
        if(right!=null)s.append(right).append("\t");
        return s+"";
    }
    public int size(){
        return 1+(left!=null? left.size() : 0)+(right!=null? right.size() : 0);
    }
}
