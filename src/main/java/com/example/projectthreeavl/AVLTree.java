package com.example.projectthreeavl;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
     static  ArrayList<StudentRecord> students = new ArrayList<>();


    // AVL tree node class
    class TreeNode {

        TreeNode left;
        TreeNode right;
        // pointer to student in student database
        TawjhiDS.Node pointer;
        int height;
        boolean deleted;
        TawjhiDS.Node head;
        TawjhiDS.Node tail;

        public TreeNode(TawjhiDS.Node pointer) {
            this.pointer = pointer;
            height = 1;
            this.left = null;
            this.right = null;
            this.deleted=false;
            this.head = this.tail = pointer;
        }

        public int getHeight() {
            return height;
        }
    }

    private TreeNode root;



    public TreeNode getRoot() {
        return root;
    }

    // helper function to get the height of node
    private int height(TreeNode treeNode) {
        if (treeNode == null)
            return 0;
        return treeNode.height;
    }

    // helper function returns maximum of two integers
    // اعلى رقم بين رقمين التري
    private int max(int x, int y) {
        if (x > y) return  x ;
        return y;
    }

    // helper function returns balance factor of node
    private int getBalance(TreeNode treeNode){
        if (treeNode==null)
            return 0;
        return height(treeNode.left)-height(treeNode.right);
    }

    // A utility function to left rotate subtree rooted with node
    private TreeNode leftRotate(TreeNode node){
        TreeNode right=node.right;
        TreeNode left=right.left;

        // Perform rotation
        right.left=node;
        node.right=left;

        // Update heights
        // يتغير قيمة الطول
        node.height=1+max(height(node.left),height(node.right));
        right.height=1+max(height(right.left),height(right.right));
        // return new root
        return right;
    }

    // A utility function to right rotate subtree rooted with node
    private TreeNode rightRotate(TreeNode node){
        TreeNode left=node.left;
        TreeNode right=left.right;
        // perform rotation
        left.right=node;
        node.left=right;

        // update heights
        node.height=1+max(height(node.left),height(node.right));
        left.height=1+max(height(left.left),height(left.right));
        // return new root
        return left;
    }

    // Method finds the min node (leftmost node)
    //اقل عنصر بالتري  ليف ليف ليف
    private TreeNode minNode(TreeNode node)
    {
        TreeNode current = node;

        // find the leftmost leaf
        while (current.left != null)
            current = current.left;

        return current;
    }

    public void insertBySeatNumber(TawjhiDS.Node node) {
        this.root=insert(this.root,node,1);
    }



    public void insertByGrade(TawjhiDS.Node node) {
        this.root = insert(this.root, node, 2);
    }

    private TreeNode insert(TreeNode treeNode, TawjhiDS.Node pointer, int type) {
        if (treeNode == null) {
            TreeNode newNode = new TreeNode(pointer);
            return newNode;
        }

        // BST insert operation  binary search tree
        if (pointer.compare(treeNode.pointer, type) < 0) {
            treeNode.left = insert(treeNode.left, pointer, type);
        } else if (pointer.compare(treeNode.pointer, type) > 0) {
            treeNode.right = insert(treeNode.right, pointer, type);
        } else {
            // When grade is equal, add the pointer to the linked list within the node
            if (pointer.student.getGrade() == treeNode.pointer.student.getGrade()) {
                if (type == 2) {
                    TawjhiDS.Node head = treeNode.pointer;
                    TawjhiDS.Node curr = head;
                    curr.next = pointer;
                }
            }
        }

        // set height of ancestor
        treeNode.height = 1 + max(height(treeNode.left), height(treeNode.right));

        // get balance factor of ancestor node in order to check if it's unbalanced
        int balanceFactor = getBalance(treeNode);

        if (balanceFactor > 1 && pointer.compare(treeNode.left.pointer, type) < 0) {
            return rightRotate(treeNode);
        }
        if (balanceFactor < -1 && pointer.compare(treeNode.right.pointer, type) > 0) {
            return leftRotate(treeNode);
        }
        if (balanceFactor > 1 && pointer.compare(treeNode.left.pointer, type) > 0) {
            treeNode.left = leftRotate(treeNode.left);
            return rightRotate(treeNode);
        }
        if (balanceFactor < -1 && pointer.compare(treeNode.right.pointer, type) < 0) {
            treeNode.right = rightRotate(treeNode.right);
            return leftRotate(treeNode);
        }
        return treeNode;
    }



    public ArrayList<StudentRecord> getStudentsByGrade(double grade) {

        int size=0;
        TreeNode current = root;
        while (current != null) {

            TawjhiDS.Node node = current.pointer;
            while (node != null) {
                if ( (current.pointer.student.getGrade() == grade) && (current.pointer.visited == false)) {

                    while (size <20) {
                        students.add(node.student);
                        current.pointer.visited = true;


                        node = node.next;
                        size++;
                    }


                }
                else if (current.pointer.student.getGrade() < grade) {
                    current = current.right;
                    break;
                } else {
                    current = current.left;
                    break;
                }
            }
        }



        return students;
    }

    public ArrayList<StudentRecord> getAllStudentsByGrade(double grade, ArrayList<StudentRecord> studentRecords) {
        ArrayList<StudentRecord> matchingStudents = new ArrayList<>();
        for (StudentRecord student : studentRecords) {
            if (student.getGrade() == grade) {
                matchingStudents.add(student);
            }
        }
        return matchingStudents;
    }


    public void delete(int studentSeatNumber){

        deleteById(this.root,studentSeatNumber);
    }

    private TreeNode deleteById(TreeNode root, int studentSeatNumber)
    {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;

        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (studentSeatNumber < root.pointer.student.getSeatNum())
            root.left = deleteById(root.left, studentSeatNumber);

            // If the key to be deleted is greater than the
            // root's key, then it lies in right subtree
        else if (studentSeatNumber > root.pointer.student.getSeatNum())
            root.right = deleteById(root.right, studentSeatNumber);

            // if key is same as root's key, then this is the node
            // to be deleted
        else
        {
            root.deleted=true;
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = max(height(root.left), height(root.right)) + 1;

        return root;
    }


    public StudentRecord findId(int studentSeatNumber){

        return searchById(root,studentSeatNumber);
    }



    private StudentRecord searchById(TreeNode root, int studentSeatNumber) {
        if (root==null)
            return null;
        if (studentSeatNumber==root.pointer.student.getSeatNum())
            if (!root.deleted)
                return root.pointer.student;
            else
                return null;
        if (studentSeatNumber<root.pointer.student.getSeatNum())
            return searchById(root.left,studentSeatNumber);
        else
            return searchById(root.right,studentSeatNumber);
    }


    public TawjhiDS.Node findIdForNode(int studentSeatNumber){

        return searchByIdForNode(root,studentSeatNumber);
    }

    private TawjhiDS.Node searchByIdForNode(TreeNode root, int studentSeatNumber) {
        if (root==null)
            return null;
        if (studentSeatNumber==root.pointer.student.getSeatNum())
            if (!root.deleted)
                return root.pointer;
            else
                return null;
        if (studentSeatNumber<root.pointer.student.getSeatNum())
            return searchByIdForNode(root.left,studentSeatNumber);
        else
            return searchByIdForNode(root.right,studentSeatNumber);
    }



    public StudentRecord updateStudentRecord(TawjhiDS.Node node){

        return updateStudentRecord(root,node);
    }



    private StudentRecord updateStudentRecord(TreeNode root, TawjhiDS.Node node) {
        if (root==null)
            return null;
        if (node.student.getSeatNum()==root.pointer.student.getSeatNum())
            if (!root.deleted) {
                root.pointer.student.setBranch(node.student.getBranch());
                root.pointer.student.setGrade(node.student.getGrade());
                return root.pointer.student;
            }
            else
                return null;
        if (node.student.getSeatNum()<root.pointer.student.getSeatNum())
            return searchById(root.left,node.student.getSeatNum());
        else
            return searchById(root.right,node.student.getSeatNum());
    }

    public int height() {
        if (root == null) {
            return 0;
        }
        return root.height;
    }


    public String  inorder(TreeNode treeNode){
        String txt="";
        if (treeNode.left!=null){
            txt+=inorder(treeNode.left);
        }
        if (!treeNode.deleted) {
            txt += treeNode.pointer.toString() + "\n";
        }
        if (treeNode.right!=null){
            txt+=inorder(treeNode.right);
        }
        return txt;
    }
}
