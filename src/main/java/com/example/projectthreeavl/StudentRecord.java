package com.example.projectthreeavl;




public class StudentRecord implements Comparable<StudentRecord> {

    private int seatNum;
    private String branch;
    private double grade;

    public StudentRecord(int seatNum, String branch, double grade) {
        this.seatNum = seatNum;
        this.branch = branch;
        this.grade = grade;
    }

    public int getSeatNum() {
        return seatNum;
    }


    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }


    public String getBranch() {
        return branch;
    }


    public void setBranch(String branch) {
        this.branch = branch;
    }


    public double getGrade() {
        return grade;
    }


    public void setGrade(double grade) {
        this.grade = grade;
    }


    public boolean isLiterary() {
        return branch.equals("Literary");
    }




    public boolean isScientific() {
        return branch.equals("scientific");
    }

    public String toString() {

        return seatNum + "," + branch + "," + grade;
    }

    /*
     * Comparing by grade .....
     */
    @Override
    public int compareTo(StudentRecord obj) {

        if (grade == obj.grade)
            return 0;
        else if (grade > obj.grade )
            return 1;
        return -1;

    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StudentRecord)
            return (int)seatNum == ((StudentRecord) obj).getSeatNum();
        return false;
    }

}
