package com.example.projectthreeavl;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

class SecondView {
    static int i =0;
    static int j =0;
    static int seatNumber;

    public SecondView() {
    }

    public static  Pane SecondViewPane() {

        AnchorPane root = new AnchorPane();
        root.setPadding(new Insets(10));

        Label seatNumberLabel = new Label("Seat Number");
        seatNumberLabel.setFont(new Font("System Bold", 12));
        seatNumberLabel.setLayoutX(68);
        seatNumberLabel.setLayoutY(59);
        seatNumberLabel.setPrefHeight(40);
        seatNumberLabel.setPrefWidth(87);

        Label branchLabel = new Label("Branch");
        branchLabel.setLayoutX(68);
        branchLabel.setLayoutY(116);
        branchLabel.setPrefHeight(28);
        branchLabel.setPrefWidth(87);

        Label averageLabel = new Label("Average");
        averageLabel.setFont(new Font("System Bold", 12));
        averageLabel.setLayoutX(68);
        averageLabel.setLayoutY(160);
        averageLabel.setPrefHeight(28);
        averageLabel.setPrefWidth(87);
        averageLabel.setUnderline(true);

        TextField seatNumberField = new TextField();
        seatNumberField.setLayoutX(179);
        seatNumberField.setLayoutY(67);

        ComboBox<String> branchComboBox = new ComboBox<>();
        branchComboBox.setLayoutX(178);
        branchComboBox.setLayoutY(118);
        branchComboBox.setPrefWidth(150);
        branchComboBox.getItems().add("Scientific");
        branchComboBox.getItems().add("Literary");


        TextField averageField = new TextField();
        averageField.setLayoutX(178);
        averageField.setLayoutY(161);

        TextArea resultArea = new TextArea();
        resultArea.setLayoutX(60);
        resultArea.setLayoutY(330);
        resultArea.setPrefHeight(214);
        resultArea.setPrefWidth(444);

        Button insertButton = new Button("Insert");
        insertButton.setLayoutX(402);
        insertButton.setLayoutY(27);
        insertButton.setPrefHeight(40);
        insertButton.setPrefWidth(103);

        Button updateButton = new Button("Update");
        updateButton.setLayoutX(402);
        updateButton.setLayoutY(93);
        updateButton.setPrefHeight(40);
        updateButton.setPrefWidth(103);


        Button deleteButton = new Button("Delete");
        deleteButton.setLayoutX(402);
        deleteButton.setLayoutY(154);
        deleteButton.setPrefHeight(40);
        deleteButton.setPrefWidth(103);
        deleteButton.setText("Delete");


        Button searchButton = new Button();
        searchButton.setLayoutX(72);
        searchButton.setLayoutY(210);
        searchButton.setPrefHeight(40);
        searchButton.setPrefWidth(78);
        searchButton.setText("Search");


        Button backButton = new Button();
        backButton.setLayoutX(145);
        backButton.setLayoutY(261);
        backButton.setPrefHeight(50);
        backButton.setPrefWidth(87);
        backButton.setText("Back");

        Button nextButton = new Button();
        nextButton.setLayoutX(274);
        nextButton.setLayoutY(262);
        nextButton.setPrefHeight(50);
        nextButton.setPrefWidth(94);
        nextButton.setText("Next");

        Button heightButton = new Button();
        heightButton.setLayoutX(166);
        heightButton.setLayoutY(210);
        heightButton.setPrefHeight(40);
        heightButton.setPrefWidth(87);
        heightButton.setText("print height");

        Button treeButton = new Button();
        treeButton.setLayoutX(274);
        treeButton.setLayoutY(210);
        treeButton.setPrefHeight(40);
        treeButton.setPrefWidth(94);
        treeButton.setText("print Tree");

        Button gradeButton = new Button();
        gradeButton.setLayoutX(402);
        gradeButton.setLayoutY(210);
        gradeButton.setPrefHeight(40);
        gradeButton.setPrefWidth(103);
        gradeButton.setText("Get All of Grade");


        AnchorPane anchorPane = new AnchorPane();

        anchorPane.setPrefHeight(549);
        anchorPane.setPrefWidth(603);


        insertButton.setOnAction(e->{
            int seatNumber = Integer.parseInt(seatNumberField.getText());
            String branch  = branchComboBox.getSelectionModel().getSelectedItem().trim();
            float grade = Float.parseFloat(averageField.getText());

            String str = Main.tawjhiDS.insert(seatNumber, branch, grade);
            JOptionPane.showMessageDialog(null, "" + str);
        });

        searchButton.setOnAction(e->{

            int seatNumber = Integer.parseInt(seatNumberField.getText());

            StudentRecord studentRecord = Main.tawjhiDS.findID(seatNumber);
            if(studentRecord != null)
                JOptionPane.showMessageDialog(null, "Student exist  -->" +studentRecord.toString());
            else {
                JOptionPane.showMessageDialog(null, "Student does not  exist  with ID" +seatNumber);
            }

        });


        nextButton.setOnAction(e->{



            if(i <1) {
                 seatNumber = Integer.parseInt(seatNumberField.getText());
                i++;
            }
            TawjhiDS.Node node = Main.tawjhiDS.findIDForNode(seatNumber);
            TawjhiDS.Node curr= node.next;

            seatNumber = curr.student.getSeatNum();

            if(curr != null) {

                JOptionPane.showMessageDialog(null, "Student exist  -->" + curr.student.toString());
            }else {
                JOptionPane.showMessageDialog(null, "Student does not  exist  with ID" +seatNumber);
            }

        });



        backButton.setOnAction(e->{



            if(j <1) {
                seatNumber = Integer.parseInt(seatNumberField.getText());
               j++;
            }
            TawjhiDS.Node node = Main.tawjhiDS.findIDForNode(seatNumber);
            TawjhiDS.Node curr= node.prev;

            seatNumber = curr.student.getSeatNum();

            if(curr != null) {

                JOptionPane.showMessageDialog(null, "Student exist  -->" + curr.student.toString());
            }else {
                JOptionPane.showMessageDialog(null, "Student does not  exist  with ID" +seatNumber);
            }

        });



        deleteButton.setOnAction(e->{
            int seatNumber = Integer.parseInt(seatNumberField.getText());

            String str = Main.tawjhiDS.delete(seatNumber);
            JOptionPane.showMessageDialog(null, "" + str);



        });


        updateButton.setOnAction(e->{
            int seatNumber =-1;
            float grade=0;
            String branch = "";
            String str ="Oops ! ! !, Fill Data First ";
            if(seatNumberField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please Enter the Seat Number ");
            }
            if(branchComboBox.getSelectionModel().getSelectedItem()== null){
                JOptionPane.showMessageDialog(null, "Please Select the branch ");
            }
            if(averageField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please Enter the Grade ");
            }

            if(!seatNumberField.getText().isEmpty()){
                 seatNumber = Integer.parseInt(seatNumberField.getText());
            }

            if(branchComboBox.getSelectionModel().getSelectedItem() != null){
                branch  = branchComboBox.getSelectionModel().getSelectedItem();            }

            if(!averageField.getText().isEmpty()){
                 grade = Float.parseFloat(averageField.getText());
            }
            if(branchComboBox.getSelectionModel().getSelectedItem() != null  && !averageField.getText().isEmpty()) {
                str = Main.tawjhiDS.update(seatNumber, branch.trim(), grade);
            }
            JOptionPane.showMessageDialog(null, "" + str);

        });
//hi

        gradeButton.setOnAction(e->{
            resultArea.setText("");
            float grade=0;
            ArrayList<StudentRecord> students = new ArrayList<>();
            if(averageField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, " Please Enter the grade");
            }else {
                grade = Float.parseFloat(averageField.getText());
            }

            students = Main.tawjhiDS.getAll(grade);

            for(StudentRecord st : students) {
                System.out.println(st);
//                resultArea.appendText(students + "");
            }
            JOptionPane.showMessageDialog(null, "" + students);
        });


        treeButton.setOnAction(e->{
            resultArea.setText(Main.tawjhiDS.print());
        });

        heightButton.setOnAction(e->{
            int heightSeat = Main.tawjhiDS.heightForAVLTreeBySeatNumber();
            int heightGrade = Main.tawjhiDS.heightForAVLTreeByGrade();
            JOptionPane.showMessageDialog(null, "The height for Avl tree by Seat Number: " + heightSeat);
            JOptionPane.showMessageDialog(null, "The height for Avl tree by Grade: " + heightGrade);
        });


        anchorPane.getChildren().addAll(seatNumberLabel, branchLabel, averageLabel, resultArea, seatNumberField, branchComboBox,
                averageField, insertButton, updateButton, deleteButton, searchButton, backButton, nextButton, heightButton,
                treeButton, gradeButton);

       return anchorPane;
    }



}