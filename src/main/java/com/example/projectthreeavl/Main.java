package com.example.projectthreeavl;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.control.Label;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;



public class Main extends Application {

    Stage stage;
    Button selectButton;

    static String filePath;
    static String fname;
     int selectedValue =-1;

     static TawjhiDS tawjhiDS;


    String msg = "";



    CheckBox literaryCheckbox = new CheckBox("Literary");
    CheckBox scientificCheckbox = new CheckBox("Scientific");

    Label labelMsg =  new Label("");
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage =stage;

        AnchorPane root = new AnchorPane();

        selectButton = new Button("Select");
        selectButton.setLayoutX(218.0);
        selectButton.setLayoutY(230.0);
        selectButton.setPrefHeight(61.0);
        selectButton.setPrefWidth(138.0);
        selectButton.setTextAlignment(TextAlignment.CENTER);
        selectButton.setFont(Font.font("System Bold", 14.0));



        literaryCheckbox.setLayoutX(302.0);
        literaryCheckbox.setLayoutY(176.0);
        literaryCheckbox.setFont(Font.font("System Bold", 20.0));
        //literaryCheckbox.selectedProperty().setValue(true);


        scientificCheckbox.setLayoutX(149.0);
        scientificCheckbox.setLayoutY(176.0);
        scientificCheckbox.setFont(Font.font("System Bold", 20.0));


        labelMsg.setLayoutX(225.5);
        labelMsg.setLayoutY(183);



        Label branchLabel = new Label("Choose branch");
        branchLabel.setLayoutX(149.0);
        branchLabel.setLayoutY(97.0);
        branchLabel.setPrefHeight(62.0);
        branchLabel.setPrefWidth(276.0);
        branchLabel.setFont(Font.font(36.0));

        Label welcomeLabel = new Label("Welcome to My Project");
        welcomeLabel.setLayoutX(141.0);
        welcomeLabel.setLayoutY(21.0);
        welcomeLabel.setPrefHeight(44.0);
        welcomeLabel.setPrefWidth(297.0);
        welcomeLabel.setFont(Font.font(26.0));

        root.getChildren().addAll(selectButton, literaryCheckbox, scientificCheckbox, branchLabel, welcomeLabel);



        setupBindings();
        Scene scene = new Scene(root,600 ,400);
        stage.setScene(scene);
        stage.show();
    }



    void setupBindings() {
        literaryCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (scientificCheckbox.isSelected()) {
                    setSelectedValue(2);
                } else {
                    setSelectedValue(0);
                }
            } else {
                if (scientificCheckbox.isSelected()) {
                    setSelectedValue(1);
                } else {
                    setSelectedValue(-1);
                }
            }
        });

        scientificCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (literaryCheckbox.isSelected()) {
                    setSelectedValue(2);
                } else {
                    setSelectedValue(1);
                }
            } else {
                if (literaryCheckbox.isSelected()) {
                    setSelectedValue(0);
                } else {
                    setSelectedValue(-1);
                }
            }
        });



        FileChooser fileChooser = new FileChooser();

        selectButton.setOnAction(e -> {

            try {

             if(selectedValue == -1){
                JOptionPane.showMessageDialog(null, "Please Select the Branch");
              }else if( selectedValue > -1) {


                    File selectedFile = fileChooser.showOpenDialog(stage);

                    String File = selectedFile.getPath();

                    readFile(File);
                    JOptionPane.showMessageDialog(null, "File loaded");

                    Pane pane1 = SecondView.SecondViewPane();
                    Scene sc = new Scene(pane1, 600, 600);
                    stage.setScene(sc);
                    stage.setTitle("Second View ");


                }
            }catch (Exception ex) {
               JOptionPane.showMessageDialog(null, "Error reading file");
           }
        });



    }



    private void readFile(String filename) throws IOException  {
        //try{
            System.out.println("File Start Reading ");
            File file = new File(filename); // creates a new file instance
            Scanner scan = new Scanner(file);
            String line = "";
            filePath = file.getParent();
            fname = file.getName();
            tawjhiDS = new TawjhiDS();

        System.out.println(selectedValue);
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                String[] columns = line.split(",");



                if(selectedValue == 2) {
                    if (columns.length == 3) {
                        tawjhiDS.insert(Integer.valueOf(columns[0]), columns[1].trim(), Float.parseFloat(columns[2]));
                    }
                }

                if(selectedValue == 1){

                    if (columns.length == 3) {
                        if(columns[1].trim().equals("Scientific")) {
                            tawjhiDS.insert(Integer.valueOf(columns[0]), columns[1].trim(), Float.parseFloat(columns[2]));
                            System.out.println(Integer.valueOf(columns[0])+ ","+columns[1].trim()+ ","+ Float.parseFloat(columns[2]));
                        }
                    }
                }

                if(selectedValue == 0){

                    if (columns.length == 3) {
                        if(columns[1].trim().equals("Literary")) {
                            tawjhiDS.insert(Integer.valueOf(columns[0]), columns[1].trim(), Float.parseFloat(columns[2]));
                        }
                    }
                }


            }
            System.out.println("finsished reading ");
           // msg.setStyle("-fx-text-fill: green");


//        }catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Please select file !!!");
//        }
    }

    public int getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(int selectedValue) {
        this.selectedValue = selectedValue;
    }
}
