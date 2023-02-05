//package com.example.projectthreeavl;
//
//import java.util.ArrayList;
//
//public class StudentDatabase {
//
//	// Linked list node
//	 class Node {
//		Node next;
//		Student student;
//
//		public Node(Student student) {
//			this.student = student;
//			this.next = null;
//		}
//
//		// Compare method by type
//		// Type=1 Compare By Id
//		// Type=2 Compare By Name
//		public int compare(Node node, int type) {
//			switch (type) {
//			case 1:
//				return compareById(node);
//			case 2:
//				return compareByName(node);
//			}
//			return 0;
//		}
//
//		// Compare student ids
//		private int compareById(Node node) {
//			if (this.student.getStudentId() == node.student.getStudentId())
//				return 0;
//			if (this.student.getStudentId() < node.student.getStudentId())
//				return -1;
//			return 1;
//		}
//
//		// Compare student names
//		private int compareByName(Node node) {
//			return (this.student.getName().compareTo(node.student.getName()));
//		}
//
//		@Override
//		public String toString() {
//			return this.student.toString();
//		}
//	}
//
//
//	// header node for linked list
//	Node head;
//	// AVL tree sorted by student id
//	AVLTree idSortedAVLTree;
//	// AVL tree sorted by student name
//	AVLTree nameSortedAVLTree;
//
//	public StudentDatabase() {
//		this.head = null;
//		idSortedAVLTree = new AVLTree();
//		nameSortedAVLTree = new AVLTree();
//	}
//
//	/**
//	 * Inserts student at the end of student database linked list Student pointer is
//	 * also linked into AVL trees ,studentId firstName lastName studentClass address
//	 * college
//	 *
//	 * @return
//	 */
//	public String insert(int studentId, String firstName, String lastName, String studentClass, String address,
//			String college) {
//		// check if student id exists in the id sorted AVL tree
//		// لما بدنا نضيف نفحص اذا الاي دي موجود
//		if (findID(studentId) != null) {
//			RUN.msg.setStyle("-fx-text-fill: red");
//			return "Insert error: Student ID-" + studentId + " already exists";
//		} else {
//			// create student instance
//			Student student = new Student(studentId, lastName, firstName, studentClass, address, college);
//			Node node = new Node(student);
//			// if list is empty insert the student as a first element
//			// إذا كانت القائمة فارغة ، أدخل الطالب كعنصر أول
//			if (head == null) {
//				head = node;
//			} else {
//				Node current = head;
//				// Find the last linked list item and insert the new instance as the last item
//				// اذا مش فارغة
//				// ابحث عن آخر عنصر قائمة مرتبط وأدخل المثيل الجديد كعنصر أخير
//				while (current.next != null) {
//					current = current.next;
//				}
//				current.next = node;
//			}
//			// add pointer to AVL trees
//			idSortedAVLTree.insertById(node);
//			nameSortedAVLTree.insertByName(node);
//			RUN.msg.setStyle("-fx-text-fill: green");
//			return "Student ID-" + studentId + "inserted";
//		}
//	}
//
//	/**
//	 * Delete method for student id Student is deleted from linked list and marked
//	 * as deleted in the AVL trees (lazy delete)
//	 *
//	 * studentId
//	 *
//	 * @return
//	 */
//	public String delete(int studentId) {
//		// check if student id exists in id sorted AVL tree
//		if (findID(studentId) == null) {
//			return "Delete error: Student ID-" + studentId + " does not exist";
//		} else {
//			Node current = head;
//			Node prev = null;
//			// find the node in the linked list
//			while (current.student.getStudentId() != studentId) {
//				prev = current;
//				current = current.next;
//			}
//			// Deleting first node
//			if (current == head) {
//				head = current.next;
//			} else {
//				prev.next = current.next;
//			}
//
//			// delete from id sorted AVL tree
//			idSortedAVLTree.delete(studentId);
//			// delete from name sorted AVL tree
//			nameSortedAVLTree.delete(studentId);
//			return "Student ID-" + studentId + " deleted from database";
//		}
//	}
//
//	/**
//	 * Method finds student by id in the id sorted AVL tree
//	 *
//	 *  studentId
//	 * @return Student
//	 */
//	public Student findID(int studentId) {
//		return idSortedAVLTree.findId(studentId);
//	}
//
//	/**
//	 * Method finds student by name in the name sorted AVL tree
//	 *
//	 *  firstName
//	 *  lastName
//	 * @return Student
//	 */
//	public Student findName(String firstName, String lastName) {
//		return nameSortedAVLTree.findName(firstName, lastName);
//	}
//
//	/**
//	 * Method calculates the number of students by years
//	 *
//	 * @return
//	 */
//	public ArrayList<Frequency> yearsList() {
//		// initialize map to store year and number of students
//		ArrayList<Frequency> yearsFrequency = new ArrayList<>();
//		ArrayList<String> years = new ArrayList<>();
//
//		Node current = head;
//		// iterate through student database
//		while (current != null) {
//			Student student = current.student;
//			// if map contains the college increment the number of students
//			if (years.contains(student.getStudentClass())) {
//				int index = years.indexOf(student.getStudentClass());
//				Integer count = findFrequency(yearsFrequency, student.getStudentClass());
//				yearsFrequency.get(index).count = count + 1;
//			}
//			// else add the college into map
//			else {
//				years.add(student.getStudentClass());
//				yearsFrequency.add(new Frequency(student.getStudentClass()));
//			}
//			current = current.next;
//		}
//		return yearsFrequency;
//	}
//
//	/**
//	 * Method calculates the number of students by colleges
//	 *
//	 * @return
//	 */
//	public ArrayList<Frequency> collegeList() {
//		// initialize map to store college and number of students
//		ArrayList<Frequency> collegeFrequency = new ArrayList<>();
//		ArrayList<String> colleges = new ArrayList<>();
//
//		Node current = head;
//		// iterate through student database
//		while (current != null) {
//			Student student = current.student;
//			// if map contains the college increment the number of students
//			if (colleges.contains(student.getCollege())) {
//				int index = colleges.indexOf(student.getCollege());
//				Integer count = findFrequency(collegeFrequency, student.getCollege());
//				collegeFrequency.get(index).count = count + 1;
//			}
//			// else add the college into map
//			else {
//				colleges.add(student.getCollege());
//				collegeFrequency.add(new Frequency(student.getCollege()));
//			}
//			current = current.next;
//		}
//		return collegeFrequency;
//	}
//
//	private Integer findFrequency(ArrayList<Frequency> frequencies, String description) {
//		for (Frequency frequency : frequencies) {
//			if (frequency.description.equals(description))
//				return frequency.count;
//		}
//		return 0;
//	}
//
//	/**
//	 * Method returns the linked list data id sorted AVL tree data inorder name
//	 * sorted AVL tree data inorder
//	 *
//	 * @return
//	 */
//	public String print() {
//		Node current = head;
//		String result = "";
//		result += "-----Printing linked list-----\n";
//		while (current != null) {
//			result += current.student.toString() + "\n";
//			current = current.next;
//		}
//
//		result += "-----Printing id sorted AVL Tree-----\n";
//		result += idSortedAVLTree.inorder(idSortedAVLTree.getRoot());
//
//		result += "-----Printing name sorted AVL Tree-----\n";
//		result += nameSortedAVLTree.inorder(nameSortedAVLTree.getRoot());
//		return result;
//	}
//
//	/**
//	 * Method returns name sorted AVL tree data inorder to save into file
//	 *
//	 * @return
//	 */
//	public String printFile() {
//		String result = nameSortedAVLTree.inorder(nameSortedAVLTree.getRoot());
//		return result;
//	}
//
//	class Frequency {
//		private String description;
//		private int count;
//
//		public Frequency(String description) {
//			this.description = description;
//			this.count = 1;
//		}
//
//		public String getDescription() {
//			return description;
//		}
//
//		public int getCount() {
//			return count;
//		}
//	}
//}
