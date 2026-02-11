package javalucproject;

import java.util.ArrayList;

class BinaryTreeNode{
    int data;
	BinaryTreeNode leftChild = null;
	BinaryTreeNode rightChild = null;
    BinaryTreeNode parent = null;
    
    public BinaryTreeNode(int d){
        data = d;
    }
    
    public BinaryTreeNode addChild(int d){
        BinaryTreeNode n = new BinaryTreeNode(d);
        n.setParent(this);
		 if(leftChild != null && rightchild != null)
			 return null;
        if(leftChild == null)
				leftChild = n;
		else
				rightChild = n;			
        return n;
    }
    
    public BinaryTreeNode getLeftChild(){
        return leftChild;
    }
	public BinaryTreeNode getRightChild(){
        return rightChild;
    }
    
    public void setParent(BinaryTreeNode p){
        parent = p;
    }
    
    public BinaryTreeNode getParent(){
        return parent;
    }
}


public class TreeExercise {
    public static void main(String[] args){
        //create root node
		TreeNode root = new TreeNode(1);
        //add children to root node
		TreeNode child1 = root.addChild(2);
		TreeNode child2 = root.addChild(3);
        //add children to child nodes
		TreeNode child11 = child1.addChild(4);
		TreeNode child12 = child1.addChild(5);
		
		TreeNode child21 = child2.addChild(6);
		TreeNode child22 = child2.addChild(7);
		
        //call methods
		preOrderTraversal(root);
    }
    
    //write a method to implement the preorder traversal 
    //pseudocode from the slides
	public static void preOrderTraversal(BinaryTreeNode node){
		if(node == null)
			return;
		System.out.print(node.data + " ");
		
		preOrderTraversal(node.getLeftChild)
		preOrderTraversal(node.getRightChild)
    }
	public static void inOrderTraversal(BinaryTreeNode node){
		if(node == null)
			return;
		
		inOrderTraversal(node.getLeftChild)
		System.out.print(node.data + " ");
		inOrderTraversal(node.getRightChild)
    }
    //write a method implement the postorder traversal 
    //pseudocde from the slides
    
    //write a method that given a node, finds the depth of that node
    
    //write a method that given the root node, finds the size of tree
    //aka how many nodes the tree has
}
