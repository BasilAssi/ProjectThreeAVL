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
//public class DeleteStudentView {
//    GridPane gridPane = new GridPane();
//    Stage stage = new Stage();
//
//    public DeleteStudentView() {
//
//    }
//
//    Stage buildView() {
//        stage.setTitle("Delete Student");
//
//        gridPane.setVgap(5);
//        gridPane.setHgap(5);
//        gridPane.setPadding(new Insets(10, 10, 10, 10));
//        gridPane.setStyle("-fx-background-color: #9dbfe5");
//        Label studentIdLabel = new Label("Student ID: ");
//
//        TextField studentIdTextField = new TextField();
//
//        gridPane.add(studentIdLabel, 0, 0);
//        gridPane.add(studentIdTextField, 1, 0);
//
//        Button insertButton = new Button("Delete Student");
//        Button closeButton = new Button("Close");
//
//        gridPane.add(insertButton, 0, 1);
//        insertButton.setStyle("-fx-background-color: #64d597");
//        gridPane.add(closeButton, 1, 1);
//        closeButton.setStyle("-fx-background-color: #f35858");
//
//        Scene scene = new Scene(gridPane, 330,100);
//
//        stage.setScene(scene);
//
//        insertButton.setOnAction(e -> {
//
//            int studentId=Integer.valueOf(studentIdTextField.getText());
//
//            String message=RUN.studentDatabase.delete(studentId);
//            RUN.msg.setText(message);
//            stage.close();
//        });
//
//        closeButton.setOnAction(e->{
//            stage.close();
//        });
//        return stage;
//    }
//}
