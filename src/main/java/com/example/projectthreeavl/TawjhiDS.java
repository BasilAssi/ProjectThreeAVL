package com.example.projectthreeavl;

import java.util.ArrayList;

public class TawjhiDS {


    // Linked list node
    class Node {
        TawjhiDS.Node next;
        TawjhiDS.Node prev;
        StudentRecord student;
        boolean visited;

        public Node(StudentRecord studentRecord) {
            this.student = studentRecord;
            this.next = null;
            this.prev = null;
            visited = false;
        }

        // Compare method by type
        // Type=1 Compare By Id  (seat number)

        // Type=2 Compare By Name    (Grade)
        public int compare(TawjhiDS.Node node, int type) {
            switch (type) {
                case 1:
                    return compareBySeatNumber(node);
                case 2:
                    return compareByGrade(node);
            }
            return 0;
        }

        // Compare student ids
        private int compareBySeatNumber(TawjhiDS.Node node) {
            if (this.student.getSeatNum() == node.student.getSeatNum())
                return 0;
            if (this.student.getSeatNum() < node.student.getSeatNum())
                return -1;
            return 1;
        }


        // Compare student Grade
        private int compareByGrade(TawjhiDS.Node node) {
            if (this.student.getGrade() == node.student.getGrade())
                return 0;
            if (this.student.getGrade() > node.student.getGrade())
                return 1;
            return -1;
        }

        @Override
        public String toString() {
            return this.student.toString();
        }
    }





    // header node for linked list
    TawjhiDS.Node head;
    // AVL tree sorted by student id
    AVLTree idSortedAVLTree;
    // AVL tree sorted by student name
    AVLTree gradeSortedAVLTree;

    public TawjhiDS() {
        this.head = null;
        idSortedAVLTree = new AVLTree();
        gradeSortedAVLTree = new AVLTree();
    }

    /**
     * Inserts student at the end of student database linked list Student pointer is
     * also linked into AVL trees ,studentId branch grade
     *
     * @return
     */
    public String insert(int studentId, String branch , float grade) {
        // check if student id exists in the id sorted AVL tree
        // لما بدنا نضيف نفحص اذا الاي دي موجود
        if (findID(studentId) != null) {
            // RUN.msg.setStyle("-fx-text-fill: red");
            return "Insert error: Student ID-" + studentId + " already exists";
        } else {
            // create student instance
            StudentRecord student = new StudentRecord(studentId,branch ,grade);

            Node node = new Node(student);
            // if list is empty insert the student as a first element
            // إذا كانت القائمة فارغة ، أدخل الطالب كعنصر أول
            if (head == null) {
                head = node;
                node.next = node;
                node.prev = node;
            } else {
                Node current = head;
                // Find the last linked list item and insert the new instance as the last item
                while (current.next != head) {
                    current = current.next;
                }
                current.next = node;
                node.prev = current;
                node.next = head;
                head.prev = node;
            }
            // add pointer to AVL trees
            idSortedAVLTree.insertBySeatNumber(node);
            gradeSortedAVLTree.insertByGrade(node);
            //  RUN.msg.setStyle("-fx-text-fill: green");
            return "Student ID-" + studentId + "inserted";
        }
    }


    public String update(int studentId, String branch , float grade) {
        // check if student id exists in the id sorted AVL tree
        // لما بدنا نضيف نفحص اذا الاي دي موجود
        if (findID(studentId) == null) {
            // RUN.msg.setStyle("-fx-text-fill: red");
            return "Update error: Student ID-" + studentId + " does not exists";
        } else {
            // create student instance


            StudentRecord student = new StudentRecord(studentId,branch ,grade);

            TawjhiDS.Node node = new TawjhiDS.Node(student);
            // if list is empty insert the student as a first element
            // إذا كانت القائمة فارغة ، أدخل الطالب كعنصر أول
            if (head == null) {
                head = node;
                node.next = node;
                node.prev = node;
            } else {
                TawjhiDS.Node current = head;
                // Find the last linked list item and insert the new instance as the last item
                while (current.next != head) {
                    current = current.next;
                }
                current.next = node;
                node.prev = current;
                node.next = head;
                head.prev = node;
            }
            // add pointer to AVL trees
            idSortedAVLTree.updateStudentRecord(node);
            gradeSortedAVLTree.updateStudentRecord(node);
            //  RUN.msg.setStyle("-fx-text-fill: green");
            return "Student ID-" + studentId + " Updated";
        }
    }


    public ArrayList getAll(double grade){

        return  gradeSortedAVLTree.getAllStudentsByGrade(grade , gradeSortedAVLTree.getStudentsByGrade(grade));
    }


    /**
     * Delete method for student id Student is deleted from linked list and marked
     * as deleted in the AVL trees (lazy delete)
     *
     * studentId
     *
     * @return
     */
    public String delete(int studentSeatNumber) {
        // check if student id exists in id sorted AVL tree
        if (findID(studentSeatNumber) == null) {
            return "Delete error: Student ID-" + studentSeatNumber + " does not exist";
        } else {
            Node current = head;
            Node prev = null;
            // find the node in the linked list
            while (current.student.getSeatNum() != studentSeatNumber) {
                prev = current;
                current = current.next;
                if (current == head) {
                    break;
                }
            }
            // Deleting first node
            if (current == head) {
                if (head.next == head) {
                    head = null;
                } else {
                    head = current.next;
                    head.prev = prev;
                    prev.next = head;
                }
            } else {
                prev.next = current.next;
                current.next.prev = prev;
            }
        }

        // delete from id sorted AVL tree
        idSortedAVLTree.delete(studentSeatNumber);
        // delete from name sorted AVL tree
        gradeSortedAVLTree.delete(studentSeatNumber);
        return "Student ID-" + studentSeatNumber + " deleted from TawjhiDS";
    }


    /**
     * Method finds student by id in the id sorted AVL tree
     *
     *  studentId
     * @return Student
     */
    public  StudentRecord findID(int studentId) {

        return idSortedAVLTree.findId(studentId);
    }


    public Node findIDForNode(int studentId) {

        return idSortedAVLTree.findIdForNode(studentId);
    }




    /**
     * Method returns the linked list data id sorted AVL tree data inorder grade
     * sorted AVL tree data inorder
     *
     * @return
     */
    public String print() {
        TawjhiDS.Node current = head;
        String result = "";
        result += "-----Printing linked list-----\n";
        while (current != null && current.visited== false) {
            current.visited=true;
            result += current.student.toString() + "\n";
            current = current.next;
        }

        result += "-----Printing id sorted AVL Tree-----\n";
        result += idSortedAVLTree.inorder(idSortedAVLTree.getRoot());

        result += "-----Printing grade sorted AVL Tree-----\n";
        result += gradeSortedAVLTree.inorder(gradeSortedAVLTree.getRoot());
        return result;
    }



    public int heightForAVLTreeBySeatNumber() {
       return idSortedAVLTree.height();
    }

    public int heightForAVLTreeByGrade() {
        return gradeSortedAVLTree.height();
    }



}
