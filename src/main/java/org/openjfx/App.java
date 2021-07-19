package org.openjfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;


/**
 * JavaFX App
 */
public class App extends Application {
    // -- WINDOW DRAG & RESIZE VARIABLES --
    private static double xOffset = 0;
    private static double yOffset = 0;



    // -- WINDOW DRAG --
    public void dragWindowEvent(MouseEvent e, Stage stage) {

                if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                    xOffset = stage.getX() - e.getScreenX();
                    yOffset = stage.getY() - e.getScreenY();
                }
                if (e.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
                    if(stage.isMaximized()) {
                        stage.setMaximized(false);
                        stage.setHeight(950);
                        stage.setWidth(1800);
                        stage.setX(e.getScreenX() + xOffset);
                        stage.setY(e.getScreenY() + yOffset);
                    } else {
                        stage.setX(e.getScreenX() + xOffset);
                        stage.setY(e.getScreenY() + yOffset);
                     }
                }


    }

    // -- WINDOW OPTION ACTIONS --
    public void windowOptionActions(ActionEvent e, Stage stage) {
        Button button = (Button) e.getSource();

        //-- BUTTONS CLICKED --
        // -- close --
            if(button.getId().equals("close-button")) {
                stage.close();
            }
            //-- maximize --
            if(button.getId().equals("max-button") && stage.isMaximized()) {
                stage.setMaximized(false);
                return;
            }
            if(button.getId().equals("max-button") && !stage.isMaximized()) {
                stage.setMaximized(true);
                System.out.println(stage.getHeight());
                System.out.println(stage.getWidth());
                stage.setHeight(stage.getHeight() - 40);
                return;
            }
            //-- minimize --
            if(button.getId().equals("min-button")) {
                stage.setIconified(true);
            }



    }

    // -- BUTTON ACTIONS --
    public void importButtonAction(ActionEvent e, Stage stage) { //curFileTable, importField också
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel files", "*.xlsx"));

        try {
            File selectedFile = fileChooser.showOpenDialog(stage);
            String path = selectedFile.getAbsolutePath();
            System.out.println(path);
            //filemanager.getFile(path);
        }catch(NullPointerException ex) {
            System.out.println("no file selected");

        }


    }

    //-- BOTTOM MENU BUTTON ACTIONS --


    // -- CHECKBOX ACTIONS --
    public void topFiveCheckboxAction(ActionEvent e, TableView table, CheckBox checkBox, CheckBox checkBoxMulti) {
        if(checkBox.isSelected()) {
            System.out.println("checked");
            checkBoxMulti.setVisible(true);
        }
        if(!checkBox.isSelected()) {
            System.out.println("unchecked");
            checkBoxMulti.setSelected(false);
            checkBoxMulti.setVisible(false);
        }
    }
    public void topFiveMultipleCheckboxAction(ActionEvent e, TableView table, CheckBox checkBox) {
        if(checkBox.isSelected()) {
            System.out.println("checked");
        }
        if(!checkBox.isSelected()) {
            System.out.println("unchecked");
        }
    }

    // -- START APPLICATION --
    @Override
    public void start(Stage primaryStage) {
        ComponentCustomization init = new ComponentCustomization();
        init.setShapes();
        init.setImages();
        init.setPanes();
        init.setButtons();
        init.setLabels();
        init.setCheckboxes();
        init.setTextfields();
        init.setComboboxes();
        init.setTableColumns();
        init.setTables();
        init.setPrimaryStage(primaryStage);

        //Buttons
        //-- importButton --
        Button importButton = init.getImportButton();
        //-- generateFileButton --
        Button generateFileButton = init.getGenerateFileButton();
        //-- dateFromButton --
        Button dateFromButton = init.getDateFromButton();
        //--dateToButton --
        Button dateToButton = init.getDateToButton();
        //-- nextMonthButton --
        Button nextMonthButton = init.getNextMonthButton();
        //-- prevMonthButton --
        Button prevMonthButton = init.getPrevMonthButton();
        //-- errorOkButton --
        Button errorOkButton = init.getErrorOkButton();
        //-- saveButton --
        Button saveButton = init.getSaveButton();
        //-- bottomMenuButtonMaxNews --
        Button bottomMenuButtonMaxNews = init.getBottomMenuButtonMaxNews();
        //-- bottomMenuButtonWeekly --
        Button bottomMenuButtonWeekly = init.getBottomMenuButtonWeekly();
        //-- bottomMenuButtonDaily --
        Button bottomMenuButtonDaily = init.getBottomMenuButtonDaily();
        //-- closeButton --
        Button closeButton = init.getCloseButton();
        //-- maximizeButton --
        Button maximizeButton = init.getMaximizeButton();
        //-- minimizeButton --
        Button minimizeButton = init.getMinimizeButton();

        //-- Labels --
        Label curFileLabel = init.getCurFileLabel();
        Label newFileLabel = init.getNewFileLabel();
        Label prevWeekLabel = init.getPrevWeekLabel();
        Label curWeekLabel = init.getCurWeekLabel();
        Label loginLabel = init.getLoginLabel();
        Label windowLabel = init.getWindowLabel();
        //maybe
        Label dateFromLabel = init.getDateFromLabel();
        Label dateToLabel = init.getDateToLabel();

        // -- Images --
        ImageView view = init.getView();

        VBox image = new VBox();
        image.setPrefSize(20,20);
        image.setPadding(new Insets(4,0,0,4));
        image.getChildren().add(view);
        image.setId("image-view");

        //-- Checkboxes --
        CheckBox topFiveCheckbox = init.getTopFiveCheckbox();
        CheckBox topFiveMultipleCheckbox = init.getTopFiveMultipleCheckbox();

        //-- Text fields --
        TextField importField = init.getImportField();
        TextField userField = init.getUserField();
        TextField dateFromField = init.getDateFromField();
        TextField dateToField = init.getDateToField();
        //-- Password fields --
        PasswordField passField = init.getPassField();

        //-- Comboboxes --
        ComboBox<String> curWeekMenu = init.getCurWeekMenu();
        ComboBox<String> prevWeekMenu = init.getPrevWeekMenu();
        ComboBox<String> sortByMenu = init.getSortByMenu();
        ComboBox<String> sortByOrderMenu = init.getSortByOrderMenu();

        //-- Tables --
        TableView<String> curFileTable = init.getCurFileTable();
        TableView<String> newFileTable = init.getNewFileTable();

        //-- Table columns --
        TableColumn restColumn = init.getRestColumn();
        TableColumn prevWeekColumn = init.getPrevWeekColumn();
        TableColumn curWeekColumn = init.getCurWeekColumn();
        TableColumn changeColumn = init.getChangeColumn();
        TableColumn percentageColumn = init.getPercentageColumn();

        //-- Menu bar --
        MenuBar menubar = init.getMenubar();

        Menu fileMenu = init.getFileMenu();
        MenuItem fileItem1 = init.getFileItem1();
        MenuItem fileItem2 = init.getFileItem2();
        fileMenu.getItems().addAll(fileItem1, fileItem2);

        Menu editMenu = init.getEditMenu();
        MenuItem editItem1 = init.getEditItem1();
        editMenu.getItems().addAll(editItem1);

        Menu helpMenu = init.getHelpMenu();
        MenuItem helpItem1 = init.getHelpItem1();
        MenuItem helpItem2 = init.getHelpItem2();
        MenuItem helpItem3 = init.getHelpItem3();
        helpMenu.getItems().addAll(helpItem1, helpItem2, helpItem3);

        menubar.getMenus().addAll(fileMenu, editMenu, helpMenu);



        //Panes
        //root
        GridPane root = new GridPane();
        ColumnConstraints mainColumn = new ColumnConstraints(620);
        mainColumn.setMaxWidth(950);
        mainColumn.setMinWidth(620);
        mainColumn.setHgrow(Priority.ALWAYS);

        RowConstraints row0 = new RowConstraints(500);
        row0.setMinHeight(400);
        row0.setPrefHeight(400);
        //row0.setPercentHeight(50);
        row0.setMaxHeight(800);
        row0.setVgrow(Priority.SOMETIMES);

        root.getColumnConstraints().add(mainColumn);
        root.getColumnConstraints().add(new ColumnConstraints(10));
        root.getColumnConstraints().add(mainColumn);
        root.getRowConstraints().add(0, row0);
        root.setVgap(10);
        root.setMinWidth(1270);
        root.setId("root");


        EventHandler<MouseEvent> screenDrag = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dragWindowEvent(mouseEvent, primaryStage);
            }
        };



        //add to panes -- LEFT SIDE
        //Import pane Hbox
        HBox importPane = new HBox();
        importPane.setPrefSize(640, 50);
        importPane.setMinHeight(50);
        importPane.setMaxHeight(50);
        importPane.setAlignment(Pos.CENTER);
        importPane.setSpacing(10);
        importPane.setId("table-footer");
        importPane.getChildren().add(importField);
        importPane.getChildren().add(importButton);


        //Week selection pane Hbox
        HBox weekSelectPane = new HBox();
        weekSelectPane.setPrefSize(640, 50);
        weekSelectPane.setAlignment(Pos.CENTER);
        weekSelectPane.setSpacing(10);
        weekSelectPane.setBorder(init.getBottomLine());
        weekSelectPane.setId("sub-menu");
        weekSelectPane.getChildren().addAll(prevWeekLabel, prevWeekMenu, curWeekLabel, curWeekMenu);

        //Vbox as gap
        VBox gap = new VBox();
        gap.setPrefSize(640, 30);
        gap.setId("gap");

        //Date selection Vbox & generateButton pane Hbox
        HBox dateFrom = new HBox();
        dateFrom.setPrefSize(200,70);
        dateFrom.getChildren().addAll(dateFromField, dateFromButton);

        HBox dateTo = new HBox();
        dateTo.setPrefSize(200,70);
        dateTo.getChildren().addAll(dateToField, dateToButton);

        VBox dateSelection = new VBox();
        dateSelection.setPrefSize(220, 70);
        dateSelection.setMaxHeight(100);
        dateSelection.setShape(init.getDateMenuShape());
        dateSelection.setId("date-box");
        dateSelection.setSpacing(10);
        dateSelection.setPadding(new Insets(12));
        dateSelection.setAlignment(Pos.CENTER);
        dateSelection.getChildren().addAll(dateFrom, dateTo);

        //loginFields Pane
        HBox loginFieldsPane = new HBox();
        loginFieldsPane.setSpacing(10);
        loginFieldsPane.setPadding(new Insets(0, 20, 10, 20));
        loginFieldsPane.getChildren().addAll(userField, passField);

        //login Vbox
        VBox loginPane = new VBox();
        loginPane.setMaxHeight(100);
        loginPane.setSpacing(2);
        loginPane.setPadding(new Insets(12));
        loginPane.setId("login-box");
        loginPane.setAlignment(Pos.CENTER_LEFT);
        loginPane.getChildren().addAll(loginLabel, loginFieldsPane);

        //bottom of LeftBottomPane
        HBox leftBottom = new HBox();
        leftBottom.setPrefSize(640, 150);
        leftBottom.setAlignment(Pos.CENTER_LEFT);
        leftBottom.setSpacing(20);
        leftBottom.setPadding(init.getLeftGap());
        leftBottom.setId("sub-menu");
        leftBottom.getChildren().addAll(dateSelection, loginPane);

        // generateButton VBOX
        VBox generateButtonPane = new VBox();
        generateButtonPane.getChildren().add(generateFileButton);
        generateButtonPane.setPadding(new Insets(0, 30, 0, 0));
        generateButtonPane.setAlignment(Pos.TOP_RIGHT);


        //left table Vbox
        VBox leftTable = new VBox();
        leftTable.setBorder(init.getBlackSharp());
        leftTable.setId("table-header");
        leftTable.getChildren().addAll(curFileLabel, curFileTable, importPane);
        VBox.setVgrow(curFileTable, Priority.ALWAYS);
        VBox.setVgrow(importPane, Priority.NEVER);

        //left bottom Vbox
        VBox leftBottomPane = new VBox();
        leftBottomPane.setPrefSize(640, 300);
        leftBottomPane.setId("bottom-pane");
        leftBottomPane.setAlignment(Pos.TOP_RIGHT);
        leftBottomPane.getChildren().addAll(weekSelectPane, gap, leftBottom, generateButtonPane);


        //add to panes -- RIGHT SIDE
        //Sort pane Hbox
        HBox sortPane = new HBox();
        sortPane.setPrefSize(640, 50);
        sortPane.setMinHeight(50);
        sortPane.setAlignment(Pos.CENTER_LEFT);
        sortPane.setPadding(init.getLeftGap());
        sortPane.setSpacing(10);
        sortPane.setId("table-footer");
        sortPane.getChildren().addAll(sortByMenu, sortByOrderMenu, topFiveCheckbox, topFiveMultipleCheckbox);

        //right table Vbox
        VBox rightTable = new VBox();
        rightTable.setBorder(init.getBlackSharp());
        rightTable.setId("table-header");
        rightTable.getChildren().addAll(newFileLabel, newFileTable, sortPane);
        VBox.setVgrow(newFileTable, Priority.ALWAYS);
        VBox.setVgrow(sortPane, Priority.NEVER);

        // -- VBOX AS GAP --
        HBox rightGap = new HBox();
        rightGap.setPrefSize(640, 30);
        rightGap.setId("gap");

        HBox rightGap2 = new HBox();
        rightGap2.setPrefSize(640, 150);
        rightGap2.setId("gap");

        //save pane
        HBox savePane = new HBox();
        savePane.setPrefSize(640, 50);
        savePane.setAlignment(Pos.CENTER_RIGHT);
        savePane.setSpacing(10);
        savePane.setId("sub-menu");
        savePane.getChildren().add(saveButton);

        //right bottom pane
        VBox rightBottomPane = new VBox();
        rightBottomPane.setPrefSize(640, 300);
        rightBottomPane.setId("bottom-pane");
        rightBottomPane.getChildren().addAll(rightGap, rightGap2, savePane);





        //add to root
        //left column
        root.add(leftTable, 0, 0);
        root.add(leftBottomPane, 0, 1);

        //right column
        root.add(rightTable, 2, 0);
        root.add(rightBottomPane, 2, 1);


        //-- BOTTOM MENU --
        HBox bottomMenu = new HBox();
        bottomMenu.setPrefSize(1280, 50);
        bottomMenu.setId("bottom-bar-menu");
        bottomMenu.getChildren().addAll(bottomMenuButtonMaxNews, bottomMenuButtonWeekly, bottomMenuButtonDaily);

        // HBOX TOPGAP
        HBox topGap = new HBox();
        topGap.setPrefWidth(1000);
        topGap.setMaxWidth(1690);
        topGap.autosize();
        topGap.setAlignment(Pos.CENTER_LEFT);
        topGap.addEventFilter(MouseEvent.MOUSE_PRESSED, screenDrag);
        topGap.addEventFilter(MouseEvent.MOUSE_DRAGGED, screenDrag);
        topGap.getChildren().add(windowLabel);
        HBox.setHgrow(topGap, Priority.ALWAYS);

        //Menubar Hbox
        HBox top = new HBox();
        top.setPrefSize(1280, 30);
        top.setMaxWidth(1920);
        top.setAlignment(Pos.TOP_LEFT);
        top.setId("top-menu");
        top.getChildren().addAll(image, menubar, topGap, minimizeButton, maximizeButton, closeButton);
        HBox.setHgrow(top, Priority.ALWAYS);


        //main
        BorderPane mainStage = new BorderPane();
        mainStage.setMinSize(1280, 830);
        mainStage.setPrefSize(1280, 830);
        mainStage.setMaxSize(1920, 1080);
        mainStage.setTop(top);
        mainStage.setCenter(root);
        mainStage.setBottom(bottomMenu);
        mainStage.setId("main-stage");





        // -- SET SCENE --
        Scene scene = new Scene(mainStage, 1280, 830);
        scene.getStylesheets().add("CSSfile.css");

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.getScene().setFill(null);
        primaryStage.getScene().getRoot().setId("stage");
        primaryStage.setMaxHeight(1080);
        primaryStage.setMaxWidth(1920);
        primaryStage.setMinHeight(830);
        primaryStage.setMinWidth(1280);
        ResizeHelper.addResizeListener(primaryStage);
        primaryStage.show();

        System.out.println("root width = " + root.getWidth());
        System.out.println("top width = " + top.getWidth());
        System.out.println("bottom width = " + bottomMenu.getWidth());
        System.out.println("stage width = " + primaryStage.getWidth());
    }

    public static void main(String[] args) {
        launch();
    }
}