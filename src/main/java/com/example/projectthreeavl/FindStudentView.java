//package com.example.projectthreeavl;
//
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
//
//public class FindStudentView {
//    GridPane gridPane = new GridPane();
//    Stage stage = new Stage();
//
//    public FindStudentView() {
//
//    }
//
//    Stage buildView(int type) {
//        stage.setTitle("Find Student");
//
//        gridPane.setVgap(5);
//        gridPane.setHgap(5);
//        gridPane.setPadding(new Insets(10, 10, 10, 10));
//        gridPane.setStyle("-fx-background-color: #9dbfe5");
//        TextField studentIdTextField=new TextField();
//        TextField firstNameTextField=new TextField();
//        TextField lastNameTextField=new TextField();
//        if (type == 1) {
//            Label studentIdLabel = new Label("Student ID: ");
//
//            gridPane.add(studentIdLabel, 0, 0);
//            gridPane.add(studentIdTextField, 1, 0);
//        } else {
//            Label studentFirstNameLabel = new Label("Student First Name: ");
//            Label studentLastNameLabel = new Label("Student Last Name: ");
//
//            gridPane.add(studentFirstNameLabel, 0, 0);
//            gridPane.add(firstNameTextField, 1, 0);
//            gridPane.add(studentLastNameLabel, 0, 1);
//            gridPane.add(lastNameTextField, 1, 1);
//        }
//
//        Button insertButton = new Button("Find Student");
//        insertButton.setStyle("-fx-background-color: #64d597");
//        Button closeButton = new Button("Close");
//        closeButton.setStyle("-fx-background-color: #f35858");
//
//        gridPane.add(insertButton, 0, 3);
//        gridPane.add(closeButton, 1, 3);
//
//        Scene scene = new Scene(gridPane, 330, 150);
//
//        stage.setScene(scene);
//
//        insertButton.setOnAction(e -> {
//
//
//            Student s;
//            if (type==1) {
//                int studentId = Integer.valueOf(studentIdTextField.getText());
//                s= RUN.studentDatabase.findID(studentId);
//            }
//            else {
//                String firstName=firstNameTextField.getText();
//                String lastName=lastNameTextField.getText();
//                s=RUN.studentDatabase.findName(firstName,lastName);
//            }
//            if (s == null) {
//                RUN.msg.setText("Student not found in the database");
//            } else {
//                RUN.displayTextArea.setText(s.toString());
//            }
//            stage.close();
//        });
//
//        closeButton.setOnAction(e -> {
//            stage.close();
//        });
//        return stage;
//    }
//}
