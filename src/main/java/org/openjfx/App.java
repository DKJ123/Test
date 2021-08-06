package org.openjfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Preloader;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import Excel.*;


/**
 * JavaFX App
 */
public class App extends Application {
    // -- WINDOW DRAG & RESIZE VARIABLES --
    private static double xOffset = 0;
    private static double yOffset = 0;
    private String dateToString;
    private String dateFromString;



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
    public void importButtonAction(ActionEvent e, Stage stage, TableView table, TextField field) { //curFileTable, importField också
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource file");
        File initDir = new File("C:/Users/de3948/OneDrive - MAX Burgers AB/Desktop/Statistik/");
        fileChooser.setInitialDirectory(initDir);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel files", "*.xlsx"));
        table.getItems().removeAll(table.getItems());

        try {
            File selectedFile = fileChooser.showOpenDialog(stage);
            String path = selectedFile.getAbsolutePath();
            field.setText(path);
            ReadFile read = new ReadFile();
            String allValues = read.file(path);
            StringBuilder sb = new StringBuilder();
            String[] split = allValues.split(";");
            String headers = split[0];
            for(int i = 0; i < split.length; i++) {
                if(i == 0) {
                    continue;
                } else {
                    sb.append(split[i]).append(";");
                }
            }

            String[] header = headers.split(",");
            int i = 0;
            for(String headerValue : header) {
                TableColumn<CurrentFileValues, String> column = (TableColumn<CurrentFileValues, String>) table.getColumns().get(i);
                column.setText(headerValue);
                table.getColumns().remove(i);
                table.getColumns().add(i, column);
                if(i == 4) {
                    break;
                }
                i++;
            }

            allValues = sb.toString();

            AddToTable add = new AddToTable();
            add.addToTable(table, allValues);
        }catch(NullPointerException ex) {
            System.out.println("no file selected");

        }
    }
    public void dateFromButtonAction(ActionEvent e, Stage stage, TextField field) {
        Node node = stage.getScene().getFocusOwner();
        Point2D point = node.localToScreen(0,0);
        NewWindow dateWindow = new NewWindow();
        dateWindow.initDateWindow(point, field);
    }
    public void dateToButtonAction(ActionEvent e, Stage stage, TextField field) {
        Node node = stage.getScene().getFocusOwner();
        Point2D point = node.localToScreen(0,0);
        NewWindow dateWindow = new NewWindow();
        dateWindow.initDateWindow(point, field);
    }
    public void generateFileButtonAction(ActionEvent e, TableView table) {
        System.out.println("this is generate button 1!");
    }
    public void generateFileButton2Action(ActionEvent e, TableView table) {
        table.getItems().removeAll(table.getItems());
    }
    public void manualModeButtonAction(ActionEvent e, VBox leftBottomPane, HBox leftBottom, HBox manualLeftBottom,
                                       VBox generateButtonPane, Button generateButton1, Button generateButton2) {
        try {
            int index = leftBottomPane.getChildren().indexOf(leftBottom);
            leftBottomPane.getChildren().remove(leftBottom);
            leftBottomPane.getChildren().add(index, manualLeftBottom);

            int index1 = generateButtonPane.getChildren().indexOf(generateButton1);
            generateButtonPane.getChildren().remove(generateButton1);
            generateButtonPane.getChildren().add(index1, generateButton2);
        }catch (IllegalArgumentException ex) {

        }
    }
    public void automatedModeButtonAction(ActionEvent e, VBox leftBottomPane, HBox leftBottom, HBox manualLeftBottom,
                                          VBox generateButtonPane, Button generateButton1, Button generateButton2) {
        try {
            int index = leftBottomPane.getChildren().indexOf(manualLeftBottom);
            leftBottomPane.getChildren().remove(manualLeftBottom);
            leftBottomPane.getChildren().add(index, leftBottom);

            int index1 = generateButtonPane.getChildren().indexOf(generateButton2);
            generateButtonPane.getChildren().remove(generateButton2);
            generateButtonPane.getChildren().add(index1, generateButton1);
        }catch(IllegalArgumentException ex) {

        }
    }

    public void copyAndPasteButtonAction(ActionEvent e, Stage stage, TableView table, TextField field) {
        Node node = stage.getScene().getFocusOwner();
        Point2D point = node.localToScreen(0,0);
        NewWindow textAreaWindow = new NewWindow();
        textAreaWindow.initTextAreaWindow(point, table, field);

    }
    public void fromExcelButtonAction(ActionEvent e, Stage stage, TableView table, TextField field) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Generate from");
        File initDir = new File("C:/Users/de3948/OneDrive - MAX Burgers AB/Desktop/Statistik/");
        fileChooser.setInitialDirectory(initDir);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel files", "*.xlsx", "*.xls"));
        File newFile = fileChooser.showOpenDialog(stage);
        String path = newFile.getAbsolutePath();
        StringBuilder sb = new StringBuilder();
        HashMap<String, Integer> newValuesHashMap = new HashMap<>();
        HashMap<String, Integer> oldValuesHashMap = new HashMap<>();

        NewWindow n = new NewWindow();
        List<String> restaurantList = n.restaurantList();

        ReadFile read = new ReadFile();
        List<String> allValuesList = read.getValuesFromFile(path, restaurantList);
        List<String> sortedRestaurantList = restaurantList.stream().sorted().collect(Collectors.toList());

        for (String rest : sortedRestaurantList) {
            int occurrences = Collections.frequency(allValuesList, rest);
            newValuesHashMap.put(rest, occurrences);
        }

        ReadFile file = new ReadFile();
        String path1 = field.getText();
        String curWeekValues = file.getCurWeekValues(path1);

        String[] curSplit = curWeekValues.split(";");

        for(String curVal : curSplit) {
            String[] curSplit2 = curVal.split(",");
            String rest = curSplit2[0];
            String valueString = curSplit2[2];
            int value = Integer.parseInt(valueString);

            oldValuesHashMap.put(rest, value);
        }

        for (String rest : oldValuesHashMap.keySet()) {
            sb.append(rest).append(",").append(oldValuesHashMap.get(rest)).append(",").append(newValuesHashMap.get(rest)
            ).append(",").append("temp").append(",").append("temp").append(";");
        }

        String allValues = sb.toString();
        AddToTable add = new AddToTable();
        add.addToNewTable(table, allValues);
    }
    public void saveButtonAction(ActionEvent e, TableView table, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save file");
        File initDir = new File("C:/Users/de3948/OneDrive - MAX Burgers AB/Desktop/Statistik/");
        fileChooser.setInitialDirectory(initDir);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel files", "*.xlsx"));
        File newFile = fileChooser.showSaveDialog(stage);
        String path = newFile.getAbsolutePath();
        System.out.println(path);
        SaveFile save = new SaveFile();
        save.saveFile(table, path);

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
        AppComponents init = new AppComponents();
        init.setColorpickers();
        init.setShapes();
        init.setMenuBar();
        init.setImages();
        init.setMenu();
        init.setMenuItems();
        init.setPanes();
        init.setButtons();
        init.setLabels();
        init.setCheckboxes();
        init.setTextfields();
        init.setComboboxes();
        init.setTableColumns();
        init.setNewTableColumns();
        init.setTables();
        init.setNewFileTable();



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
        ToggleButton bottomMenuButtonMaxNews = init.getBottomMenuButtonMaxNews();
        //-- bottomMenuButtonWeekly --
        ToggleButton bottomMenuButtonWeekly = init.getBottomMenuButtonWeekly();
        //-- bottomMenuButtonDaily --
        ToggleButton bottomMenuButtonDaily = init.getBottomMenuButtonDaily();
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
        Label excelSettingsLabel = init.getExcelSettingsLabel();
        Label headerColorpickerLabel = init.getExcelHeaderColorpickerLabel();
        Label cellColorpickerLabel = init.getExcelCellColorpickerLabel();
        Label windowLabel = init.getWindowLabel();
        //maybe
        Label dateFromLabel = init.getDateFromLabel();
        Label dateToLabel = init.getDateToLabel();

        // -- ColorPickers --
        ColorPicker excelHeaderColorpicker = init.getHeaderColorPicker();
        ColorPicker excelCellColorpicker = init.getCellColorPicker();

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
        CheckBox restaurantsZeroCheckbox = init.getRestaurantsZeroCheckbox();
        CheckBox onlyImportantColumnsCheckbox = init.getOnlyImportantColumnsCheckbox();
        CheckBox excelVerticalGridLinesCheckbox = init.getExcelVerticalGridLinesCheckbox();
        CheckBox excelHorizontalGridLinesCheckbox = init.getExcelHorizontalGridLinesCheckbox();

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
        gap.setPrefSize(640, 3);
        gap.setId("line-gap");

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
        dateSelection.setId("date-box");
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

        // mode select pane
        HBox selectModePane = new HBox();
        selectModePane.setPrefSize(640, 40);
        selectModePane.getChildren().addAll(init.getManualModeButton(), init.getAutomatedModeButton());
        selectModePane.setAlignment(Pos.TOP_LEFT);
        selectModePane.setId("select-mode-pane");

        //bottom of LeftBottomPane
        HBox leftBottom = new HBox();
        leftBottom.setPrefSize(640, 150);
        leftBottom.setAlignment(Pos.TOP_CENTER);
        leftBottom.setSpacing(20);
        leftBottom.setId("sub-menu");
        leftBottom.getChildren().addAll(dateSelection, loginPane);
        init.setLeftBottom(leftBottom);

        //manual mode leftBottomPane
        HBox manualLeftBottom = new HBox();
        manualLeftBottom.setPrefSize(640, 150);
        manualLeftBottom.setAlignment(Pos.CENTER);
        manualLeftBottom.setId("manual-left-bottom-pane");
        manualLeftBottom.getChildren().addAll(init.getCopyAndPasteButton(), init.getFromExcelButton());
        init.setManualLeftBottom(manualLeftBottom);

        // generateButton VBOX
        VBox generateButtonPane = new VBox();
        generateButtonPane.getChildren().addAll(init.getGenerateFileButton2());
        generateButtonPane.setPadding(new Insets(0, 30, 0, 0));
        generateButtonPane.setAlignment(Pos.TOP_RIGHT);
        init.setGenerateButtonPane(generateButtonPane);

        //left table Vbox
        VBox leftTable = new VBox();
        leftTable.setId("table-header");
        leftTable.getChildren().addAll(curFileLabel, init.getCurFileTable(), importPane);
        VBox.setVgrow(init.getCurFileTable(), Priority.ALWAYS);
        VBox.setVgrow(importPane, Priority.NEVER);

        //left bottom Vbox
        VBox leftBottomPane = new VBox();
        leftBottomPane.setPrefSize(640, 300);
        leftBottomPane.setId("bottom-pane");
        leftBottomPane.setAlignment(Pos.TOP_RIGHT);
        leftBottomPane.getChildren().addAll(weekSelectPane, gap, selectModePane, manualLeftBottom, generateButtonPane);
        init.setLeftBottomPane(leftBottomPane);


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
        rightTable.setId("table-header");
        rightTable.getChildren().addAll(newFileLabel, init.getNewFileTable(), sortPane);
        VBox.setVgrow(init.getNewFileTable(), Priority.ALWAYS);
        VBox.setVgrow(sortPane, Priority.NEVER);

        // -- EXCEL SETTINGS RIGHT PANE --
        HBox excelSettingsHeading = new HBox();
        excelSettingsHeading.setPrefSize(640, 50);
        excelSettingsHeading.setMaxHeight(50);
        excelSettingsHeading.setAlignment(Pos.CENTER);
        excelSettingsHeading.setId("bottom-right-top-pane");
        excelSettingsHeading.getChildren().add(excelSettingsLabel);

        // -- color picker row one --
        HBox colorpickerLabelpane = new HBox();
        colorpickerLabelpane.setPrefSize(200, 30);
        colorpickerLabelpane.setAlignment(Pos.CENTER_RIGHT);
        colorpickerLabelpane.setId("color-picker-row");
        colorpickerLabelpane.getChildren().addAll(headerColorpickerLabel, excelHeaderColorpicker);
        // -- color picker row two --
        HBox colorpickerLabelpane2 = new HBox();
        colorpickerLabelpane2.setPrefSize(200, 30);
        colorpickerLabelpane2.setAlignment(Pos.CENTER_RIGHT);
        colorpickerLabelpane2.setId("color-picker-row");
        colorpickerLabelpane2.getChildren().addAll(cellColorpickerLabel, excelCellColorpicker);

        // -- color picker rows in a vBox --
        VBox colorpickersPane = new VBox();
        colorpickersPane.setPrefSize(250, 250);
        colorpickersPane.setAlignment(Pos.TOP_RIGHT);
        colorpickersPane.setId("color-picker-pane");
        colorpickersPane.getChildren().addAll(colorpickerLabelpane, colorpickerLabelpane2);

        // -- Excel settings checkbox pane --
        VBox excelSettingsCheckboxPane = new VBox();
        excelSettingsCheckboxPane.setPrefSize(250, 250);
        excelSettingsCheckboxPane.setAlignment(Pos.TOP_LEFT);
        excelSettingsCheckboxPane.setId("checkbox-pane");
        excelSettingsCheckboxPane.getChildren().addAll(restaurantsZeroCheckbox, onlyImportantColumnsCheckbox,
                excelHorizontalGridLinesCheckbox, excelVerticalGridLinesCheckbox);

        // -- Right-bottom-middle pane --
        HBox excelSettings = new HBox();
        excelSettings.setPrefSize(490, 110);
        excelSettings.setMaxSize(490, 300);
        excelSettings.setAlignment(Pos.CENTER_LEFT);
        excelSettings.setId("excel-settings-pane");
        excelSettings.getChildren().addAll(excelSettingsCheckboxPane, colorpickersPane);

        // -- right gap --
        VBox rightGap = new VBox();
        rightGap.setPrefSize(640, 40);
        rightGap.setId("gap");

        //save pane
        HBox savePane = new HBox();
        savePane.setPrefSize(640, 100);
        savePane.setAlignment(Pos.BOTTOM_RIGHT);
        savePane.setPadding(new Insets(0, 30, 15, 0));
        savePane.setId("sub-menu");
        savePane.getChildren().add(saveButton);

        //right bottom pane
        VBox rightBottomPane = new VBox();
        rightBottomPane.setPrefSize(640, 300);
        rightBottomPane.setAlignment(Pos.TOP_CENTER);
        rightBottomPane.setId("bottom-pane");
        rightBottomPane.getChildren().addAll(excelSettingsHeading, rightGap, excelSettings, savePane);





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
        scene.getStylesheets().add("AppStyle.css");

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
        init.setPrimaryStage(primaryStage);
    }

    public void setDateFromString(String date) {
        this.dateFromString = date;
    }

    public static void main(String[] args) {
        launch();
    }
}