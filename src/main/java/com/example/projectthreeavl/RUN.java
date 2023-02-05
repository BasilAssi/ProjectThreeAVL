//package com.example.projectthreeavl;
//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Rectangle2D;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.stage.FileChooser;
//import javafx.stage.Screen;
//import javafx.stage.Stage;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class RUN extends Application {
//
//	Stage stage;
//	GridPane gridPane;
//	double width, height;
//	static TextArea displayTextArea = new TextArea();
//	static Label msg = new Label("");
//
//	static String filePath;
//	static String fname;
//
//	static StudentDatabase studentDatabase;
//	/**
//	 * Menu components
//	 */
//	MenuItem openFileMenuItem = new MenuItem("Load Data From File");
//	MenuItem saveFileMenuItem = new MenuItem("Write To File");
//
//	MenuItem addStudentMenuItem = new MenuItem("Add Student");
//	MenuItem findIdMenuItem = new MenuItem("Find Student ID");
//	MenuItem findNameMenuItem = new MenuItem("Find Student Name");
//	MenuItem deleteStudentMenuItem = new MenuItem("Delete Student");
//	MenuItem yearsListMenuItem = new MenuItem("Years List");
//	MenuItem collegesListMenuItem = new MenuItem("Colleges List");
//	MenuItem printMenuItem = new MenuItem("Print");
//	MenuItem exitMenuItem = new MenuItem("Exit");
//
//	Menu fileMenu = new Menu("File");
//	Menu operationsMenu = new Menu("Operations");
//	Menu listMenu = new Menu("Lists");
//	Menu exitMenu = new Menu("Exit");
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		stage = primaryStage;
//		gridPane = setupScreen();
//		setupBindings();
//		Scene scene = new Scene(gridPane, width, height);
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("Students Database");
//		primaryStage.show();
//
//	}
//
//	private GridPane setupScreen() {
//		GridPane root = new GridPane();
//
//		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
//		width = 600;
//		height = 350;
//
//		GridPane mainGrid = new GridPane();
//
//		mainGrid.setVgap(5);
//		mainGrid.setHgap(5);
//		GridPane.setMargin(mainGrid, new Insets(10, 10, 10, 10));
//		root.setStyle("-fx-background-color: #9dbfe5");
//
//		/** Grid for editor section **/
//		GridPane displayGrid = new GridPane();
//		displayGrid.setVgap(5);
//		displayGrid.setHgap(5);
//		displayGrid.setMinHeight(300);
//		displayTextArea.setMinHeight(250);
//		displayTextArea.setEditable(false);
//
//		displayGrid.add(msg, 0, 1);
//		displayGrid.add(displayTextArea, 0, 2);
//		mainGrid.add(setupMenus(), 0, 0, 2, 1);
//		mainGrid.add(displayGrid, 0, 1);
//
//		root.add(mainGrid, 0, 0);
//		return root;
//	}
//
//	MenuBar setupMenus() {
//		MenuBar menuBar = new MenuBar();
//		menuBar.setStyle("-fx-background-color: #93e0b6");
//		fileMenu.getItems().addAll(openFileMenuItem, saveFileMenuItem);
//		operationsMenu.getItems().addAll(addStudentMenuItem, findIdMenuItem, findNameMenuItem, deleteStudentMenuItem);
//		listMenu.getItems().addAll(yearsListMenuItem, collegesListMenuItem, printMenuItem);
//		exitMenu.getItems().addAll(exitMenuItem);
//		menuBar.getMenus().addAll(fileMenu, operationsMenu, listMenu, exitMenu);
//		operationsMenu.setDisable(true);
//		listMenu.setDisable(true);
//		saveFileMenuItem.setDisable(true);
//
//		return menuBar;
//	}
//
//	void setupBindings() {
//
//		FileChooser fileChooser = new FileChooser();
//
//		openFileMenuItem.setOnAction(e -> {
//			File selectedFile = fileChooser.showOpenDialog(stage);
//			msg.setText("");
//
//			try {
//				String File = selectedFile.getPath();
//				readFile(File);
//				operationsMenu.setDisable(false);
//				listMenu.setDisable(false);
//				saveFileMenuItem.setDisable(false);
//				//msg.setText("File Loaded");
//			} catch (Exception ex) {
//				msg.setStyle("-fx-text-fill: red");
//				msg.setText("Error reading file");
//			}
//		});
//
//		saveFileMenuItem.setOnAction(e -> {
//			try {
//				saveFile();
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		});
//
//		exitMenuItem.setOnAction(e -> {
//			System.exit(1);
//		});
//
//		addStudentMenuItem.setOnAction(e -> {
//			AddStudentView addStudentView = new AddStudentView();
//			Stage addStage = addStudentView.buildView();
//			addStage.show();
//			displayTextArea.setText("");
//		});
//
//		deleteStudentMenuItem.setOnAction(e -> {
//			DeleteStudentView deleteStudentView = new DeleteStudentView();
//			Stage deleteStage = deleteStudentView.buildView();
//			deleteStage.show();
//			displayTextArea.setText("");
//		});
//
//		findIdMenuItem.setOnAction(e -> {
//			FindStudentView findStudentView = new FindStudentView();
//			Stage findStage = findStudentView.buildView(1);
//			displayTextArea.setText("");
//			findStage.show();
//		});
//
//		findNameMenuItem.setOnAction(e -> {
//			FindStudentView findStudentView = new FindStudentView();
//			Stage findStage = findStudentView.buildView(2);
//			displayTextArea.setText("");
//			findStage.show();
//		});
//
//		yearsListMenuItem.setOnAction(e -> {
//			ArrayList<StudentDatabase.Frequency> years = studentDatabase.yearsList();
//			String txt = "";
//			for (StudentDatabase.Frequency frequency : years) {
//				txt += frequency.getDescription() + ":" + frequency.getCount() + "\n";
//			}
//			displayTextArea.setText(txt);
//		});
//
//		collegesListMenuItem.setOnAction(e -> {
//			ArrayList<StudentDatabase.Frequency> college = studentDatabase.collegeList();
//			String txt = "";
//			for (StudentDatabase.Frequency frequency : college) {
//				txt += frequency.getDescription() + ":" + frequency.getCount() + "\n";
//			}
//			displayTextArea.setText(txt);
//		});
//
//		saveFileMenuItem.setOnAction(e -> {
//			try {
//				writeFile();
//			} catch (IOException ex) {
//				msg.setStyle("-fx-text-fill: red");
//				msg.setText("Error saving file");
//			}
//		});
//		printMenuItem.setOnAction(e -> {
//			writeDisplayArea();
//		});
//
//	}
//
//	public static void writeDisplayArea() {
//		displayTextArea.setText(studentDatabase.print());
//	}
//
//	private void readFile(String filename) throws IOException {
//		File file = new File(filename); // creates a new file instance
//		Scanner scan = new Scanner(file);
//	//	FileReader fr = new FileReader(file); // reads the file
//		//BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
//	//	StringBuffer sb = new StringBuffer(); // constructs a string buffer with no characters
//		String line="";
//		filePath = file.getParent();
//		fname = file.getName();
//		studentDatabase = new StudentDatabase();
//
//		while (scan.hasNextLine()) {
//			line= scan.nextLine();
//			String[] columns = line.split(";");
//			if (columns.length == 6) {
//				studentDatabase.insert(Integer.valueOf(columns[0]), columns[1], columns[2], columns[3], columns[4],
//						columns[5]);
//			}
//		}
//		msg.setStyle("-fx-text-fill: green");
//		msg.setText("File loaded");
//	}
//
//	private void writeFile() throws IOException {
//		BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"));
//		String result = studentDatabase.printFile();
//		writer.write(result);
//		writer.close();
//		msg.setStyle("-fx-text-fill: green");
//		msg.setText("File saved");
//	}
//
//	private void saveFile() throws IOException {
//		File output = new File((filePath + "\\" + fname));
//		FileWriter fw = new FileWriter(output);
//		// fw.write(studentDatabase.toFileString());
//		fw.close();
//		msg.setStyle("-fx-text-fill: green");
//		msg.setText("File Saved");
//
//	}
//}
