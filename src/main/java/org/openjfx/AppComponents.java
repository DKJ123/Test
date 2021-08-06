package org.openjfx;

import Excel.CurrentFileValues;
import Excel.NewFileValues;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.ColorPickerSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Stack;

public class AppComponents extends App{

    //-- Stages --
    Stage primaryStage;

    // -- ModePanes --
    HBox manualLeftBottom;
    HBox leftBottom;
    VBox leftBottomPane;
    VBox generateButtonPane;

    // -- Buttons
    Button importButton;
    Button generateFileButton;
    Button generateFileButton2;
    Button dateFromButton;
    Button dateToButton;
    Button nextMonthButton;
    Button prevMonthButton;
    ToggleButton manualModeButton;
    ToggleButton automatedModeButton;
    Button copyAndPasteButton;
    Button fromExcelButton;
    Button errorOkButton;
    Button saveButton;
    ToggleButton bottomHomeButton;
    ToggleButton bottomMenuButtonMaxNews;
    ToggleButton bottomMenuButtonWeekly;
    ToggleButton bottomMenuButtonDaily;

    Button closeButton;
    Button maximizeButton;
    Button minimizeButton;

    // -- StackPane for topButtons --
    StackPane cross;
    StackPane subMenuButtonPane;
    StackPane box;

    // -- Labels --
    Label curFileLabel;
    Label newFileLabel;
    Label prevWeekLabel;
    Label curWeekLabel;
    Label loginLabel;
    Label dateFromLabel;
    Label dateToLabel;
    Label excelSettingsLabel;
    Label excelHeaderColorpickerLabel;
    Label excelCellColorpickerLabel;

    Label windowLabel;

    // -- ColorPicker --
    ColorPicker headerColorPicker;
    ColorPicker cellColorPicker;

    //-- Images --
    Image logo;
    ImageView view;


    // -- Text fields --
    TextField importField;
    TextField userField;
    TextField dateFromField;
    TextField dateToField;
    // -- Password fields --
    PasswordField passField;

    // -- Checkboxes --
    CheckBox topFiveCheckbox;
    CheckBox topFiveMultipleCheckbox;
    CheckBox restaurantsZeroCheckbox;
    CheckBox onlyImportantColumnsCheckbox;
    CheckBox excelVerticalGridLinesCheckbox;
    CheckBox excelHorizontalGridLinesCheckbox;

    // -- Comboboxes --
    ComboBox<String> prevWeekMenu;
    ComboBox<String> curWeekMenu;
    ComboBox<String> sortByMenu;
    ComboBox<String> sortByOrderMenu;

    // -- Tables --
    TableView curFileTable;
    TableView newFileTable;

    // -- Table columns --
    TableColumn<CurrentFileValues, String> restColumn;
    TableColumn<CurrentFileValues, Integer> prevWeekColumn;
    TableColumn<CurrentFileValues, Integer> curWeekColumn;
    TableColumn<CurrentFileValues, Integer> changeColumn;
    TableColumn<CurrentFileValues, Float> percentageColumn;
    TableColumn<NewFileValues, String> restColumnNew;
    TableColumn<NewFileValues, String> prevWeekColumnNew;
    TableColumn<NewFileValues, String> curWeekColumnNew;
    TableColumn<NewFileValues, String> changeColumnNew;
    TableColumn<NewFileValues, String> percentageColumnNew;

    // -- Menu bar --
    MenuBar menubar;
    // -- Menu --
    Menu fileMenu;
    Menu editMenu;
    Menu helpMenu;
    // -- Menu items --
    MenuItem fileItem1;
    MenuItem fileItem2;
    MenuItem editItem1;
    MenuItem helpItem1;
    MenuItem helpItem2;
    MenuItem helpItem3;

    // -- shapes --
    Rectangle buttonShape = new Rectangle();
    Rectangle rectangle = new Rectangle();
    Rectangle dateMenuShape = new Rectangle();

    Path p;

    Line line1;
    Line line2;
    Line minimizeLine;
    Line maximizeLeftLine;
    Line maximizeBottomLine;
    Line maximizeRightLine;
    Line maximizeTopLine;

    // -- Insets --
    Insets leftGap;

    // -- Backgrounds --
    Background mainBackground;
    Background subMenuBackground;
    Background tableHeaderBackground;
    Background lessWhiteBackground;
    Background white;

    // -- Fonts --
    Font tableHeaderFont;
    Font arialSize14;
    Font arialBold;
    Font loginFont;

    // -- Borders --
    Border black;
    Border blackSharp;
    Border bottomLine;
    Border bottomTopLine;
    Border dateButtonBorder;
    Border dateFieldBorder;
    Border topBorder;


    public AppComponents(){
        setColorpickers();
        setButtons();
        setLabels();
        setCheckboxes();
        setTextfields();
        setMenuBar();
        setImages();
        setMenu();
        setMenuItems();
        setShapes();
        setFonts();
        setBackgrounds();
        setBorders();
        setInsets();
        setPanes();
    }

    // -- STAGE SETTERS AND GETTERS --
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // -- LEFTBOTTOMPANE SETTER AND GETTER --
    public void setLeftBottomPane(VBox vBox) {
        leftBottomPane = vBox;
    }
    public VBox getLeftBottomPane() {
        return leftBottomPane;
    }

    // -- LEFTBOTTOM SETTER AND GETTER --
    public void setLeftBottom(HBox hbox) {
        leftBottom = hbox;
    }
    public HBox getLeftBottom() {
        return leftBottom;
    }
    // -- MANUAL LEFTBOTTOM SETTER AND GETTER --
    public void setManualLeftBottom(HBox hbox) {
        manualLeftBottom = hbox;
    }
    public HBox getManualLeftBottom() {
        return manualLeftBottom;
    }

    // -- GENERATEBUTTONPANE SETTER AND GETTER --
    public void setGenerateButtonPane(VBox vbox) {
        generateButtonPane = vbox;
    }
    public VBox getGenerateButtonPane() {
        return generateButtonPane;
    }


    // -- BUTTONS SETTERS AND GETTERS --
    public void setButtons() {
        // -- importButton --
        importButton = new Button("Import");
        importButton.setPrefSize(100, 30);
        importButton.setCursor(Cursor.HAND);
        importButton.setId("standard-button");
        importButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                importButtonAction(actionEvent, getPrimaryStage(), getCurFileTable(), getImportField());
            }
        });

        // -- dateFromButton --
        dateFromButton = new Button("From");
        dateFromButton.setPrefSize(55,30);
        dateFromButton.setShape(rectangle);
        dateFromButton.setBorder(dateButtonBorder);
        dateFromButton.setCursor(Cursor.HAND);
        dateFromButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dateFromButtonAction(actionEvent, getPrimaryStage(), getDateFromField());
            }
        });

        // -- dateToButton --
        dateToButton = new Button("To");
        dateToButton.setPrefSize(55,30);
        dateToButton.setShape(rectangle);
        dateToButton.setBorder(dateButtonBorder);
        dateToButton.setCursor(Cursor.HAND);
        dateToButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dateToButtonAction(actionEvent, getPrimaryStage(), getDateToField());
            }
        });

        // -- generateFileButton --
        generateFileButton = new Button("Generate new file");
        generateFileButton.setPrefSize(140,50);
        generateFileButton.setCursor(Cursor.HAND);
        generateFileButton.setId("standard-button");
        generateFileButton.setFont(arialSize14);
        generateFileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                generateFileButtonAction(actionEvent, getNewFileTable());
            }
        });

        // -- generateFileButton2 --
        generateFileButton2 = new Button("Insert new data");
        generateFileButton2.setPrefSize(140,50);
        generateFileButton2.setCursor(Cursor.HAND);
        generateFileButton2.setId("standard-button");
        generateFileButton2.setFont(arialSize14);
        generateFileButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                generateFileButton2Action(actionEvent, getNewFileTable());
            }
        });

        // -- manualModeButton --
        manualModeButton = new ToggleButton("Manual mode");
        manualModeButton.setPrefSize(100, 20);
        manualModeButton.setId("sub-menu-button");
        manualModeButton.setSelected(true);
        manualModeButton.setShape(getCubicCurve());
        manualModeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                manualModeButton.setSelected(true);
                automatedModeButton.setSelected(false);
                manualModeButtonAction(actionEvent, getLeftBottomPane(), getLeftBottom(), getManualLeftBottom(),
                        getGenerateButtonPane(), getGenerateFileButton(), getGenerateFileButton2());
            }
        });

        // -- automatedModeButton --
        automatedModeButton = new ToggleButton("Automated mode");
        automatedModeButton.setPrefSize(120, 20);
        automatedModeButton.setId("sub-menu-button");
        automatedModeButton.setShape(getCubicCurve());
        automatedModeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                manualModeButton.setSelected(false);
                automatedModeButton.setSelected(true);
                automatedModeButtonAction(actionEvent, getLeftBottomPane(), getLeftBottom(), getManualLeftBottom(),
                        getGenerateButtonPane(), getGenerateFileButton(), getGenerateFileButton2());
            }
        });

        // -- copyAndPasteButton --
        copyAndPasteButton = new Button("Copy and paste");
        copyAndPasteButton.setPrefSize(140, 30);
        copyAndPasteButton.setId("standard-button");
        copyAndPasteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                copyAndPasteButtonAction(actionEvent, getPrimaryStage(), getNewFileTable(), getImportField());
            }
        });

        // -- fromExcelButton --
        fromExcelButton = new Button("From Excel-file");
        fromExcelButton.setPrefSize(140, 30);
        fromExcelButton.setId("standard-button");
        fromExcelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fromExcelButtonAction(actionEvent, getPrimaryStage(), getNewFileTable(), getImportField());
            }
        });

        // -- saveButton --
        saveButton = new Button("Save");
        saveButton.setPrefSize(100, 40);
        saveButton.setId("standard-button");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                saveButtonAction(actionEvent, getNewFileTable(), getPrimaryStage());
            }
        });

        // -- nextMonthButton --
        nextMonthButton = new Button("Next");
        nextMonthButton.setId("date-button");

        // -- prevMonthButton --
        prevMonthButton = new Button("Prev");
        prevMonthButton.setId("date-button");

        // -- errorOkButton --
        errorOkButton = new Button("Ok");
        errorOkButton.setId("standard-button");


        // -- bottomHomeButton --
        bottomHomeButton = new ToggleButton("Home");


        // -- bottomMenuButtonMaxNews --
        bottomMenuButtonMaxNews = new ToggleButton("Maxnews");
        bottomMenuButtonMaxNews.setPrefSize(100, 50);
        bottomMenuButtonMaxNews.setId("bottom-button-lowered");
        bottomMenuButtonMaxNews.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenuButtonMaxNews.setSelected(true);
                bottomMenuButtonDaily.setSelected(false);
                bottomMenuButtonWeekly.setSelected(false);
            }
        });
        //action = display maxnews editor

        // -- bottomMenuButtonWeekly --
        bottomMenuButtonWeekly = new ToggleButton("Weekly report");
        bottomMenuButtonWeekly.setPrefSize(120, 50);
        bottomMenuButtonWeekly.setId("bottom-button-lowered");
        bottomMenuButtonWeekly.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenuButtonMaxNews.setSelected(false);
                bottomMenuButtonDaily.setSelected(false);
                bottomMenuButtonWeekly.setSelected(true);
            }
        });
        //action = display weekly report editor

        // -- bottomMenuButtonDaily --
        bottomMenuButtonDaily = new ToggleButton("Daily report");
        bottomMenuButtonDaily.setPrefSize(100, 50);
        bottomMenuButtonDaily.setId("bottom-button-lowered");
        bottomMenuButtonDaily.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomMenuButtonMaxNews.setSelected(false);
                bottomMenuButtonDaily.setSelected(true);
                bottomMenuButtonWeekly.setSelected(false);
            }
        });
        //Action = display daily report editor


        EventHandler<ActionEvent> windowOptions = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                windowOptionActions(actionEvent, getPrimaryStage());
            }
        };
        // -- closeButton --
        closeButton = new Button();
        closeButton.setPrefSize(40,40);
        //closeButton.setShape(getButtonShape());
        closeButton.setGraphic(getCross());
        closeButton.setId("close-button");
        closeButton.addEventHandler(ActionEvent.ACTION, windowOptions);


        // -- maximizeButton --
        maximizeButton = new Button();
        maximizeButton.setPrefSize(40, 40);
        //maximizeButton.setShape(getButtonShape());
        maximizeButton.setGraphic(getBox());
        maximizeButton.setId("max-button");
        maximizeButton.addEventHandler(ActionEvent.ACTION, windowOptions);

        // -- minimizeButton --
        minimizeButton = new Button();
        minimizeButton.setPrefSize(40, 40);
        //minimizeButton.setShape(getButtonShape());
        minimizeButton.setGraphic(getMinimizeLine());
        minimizeButton.setId("min-button");
        minimizeButton.addEventHandler(ActionEvent.ACTION, windowOptions);
    }

    public Button getImportButton() {
        return importButton;
    }
    public Button getDateFromButton() {
        return dateFromButton;
    }
    public Button getDateToButton() {
        return dateToButton;
    }
    public Button getGenerateFileButton() {
        return generateFileButton;
    }
    public Button getGenerateFileButton2() {
        return generateFileButton2;
    }
    public Button getNextMonthButton() {
        return nextMonthButton;
    }
    public Button getPrevMonthButton() {
        return prevMonthButton;
    }
    public ToggleButton getManualModeButton() {
        return manualModeButton;
    }
    public ToggleButton getAutomatedModeButton() {
        return automatedModeButton;
    }
    public Button getCopyAndPasteButton() {
        return copyAndPasteButton;
    }
    public Button getFromExcelButton() {
        return fromExcelButton;
    }
    public Button getErrorOkButton() {
        return errorOkButton;
    }
    public Button getSaveButton() {
        return saveButton;
    }
    public ToggleButton getBottomMenuButtonMaxNews() {
        return bottomMenuButtonMaxNews;
    }
    public ToggleButton getBottomMenuButtonWeekly() {
        return bottomMenuButtonWeekly;
    }
    public ToggleButton getBottomMenuButtonDaily() {
        return bottomMenuButtonDaily;
    }
    public Button getCloseButton() {
        return closeButton;
    }
    public Button getMaximizeButton() {
        return maximizeButton;
    }
    public Button getMinimizeButton() {
        return minimizeButton;
    }


    // -- STACKPANES SETTERS AND GETTERS --
    public void setPanes() {
        StackPane subMenuButtonPane = new StackPane();
        subMenuButtonPane.setPrefSize(120, 25);
        subMenuButtonPane.getChildren().addAll(getCubicCurve(), getDateMenuShape());

        cross = new StackPane();
        cross.setPrefSize(30,30);
        cross.getChildren().addAll(getLine1(), getLine2());

        box = new StackPane();
        box.setPrefSize(30,30);
        box.getChildren().add(getRectangle());

        this.subMenuButtonPane = subMenuButtonPane;
    }

    public StackPane getCross() {
        return cross;
    }
    public StackPane getBox() {
        return box;
    }
    public StackPane getSubMenuButtonPane() {
        return subMenuButtonPane;
    }

    // -- LABELS SETTERS AND GETTERS --
    public void setLabels() {
        curFileLabel = new Label("CURRENT FILE");
        curFileLabel.setId("table-header-label");

        newFileLabel = new Label("PREVIEW OF NEW FILE");
        newFileLabel.setId("table-header-label");

        prevWeekLabel = new Label("Select new previous week:");
        prevWeekLabel.setId("standard-label");
        curWeekLabel = new Label("Select new current week:");
        curWeekLabel.setId("standard-label");
        loginLabel = new Label("Log in to Max ÄHS:");
        loginLabel.setId("standard-label");
        dateFromLabel = new Label();
        dateToLabel = new Label();
        excelSettingsLabel = new Label("EXCEL SETTINGS");
        excelSettingsLabel.setId("excel-label");
        excelHeaderColorpickerLabel = new Label("Select column header color:");
        excelHeaderColorpickerLabel.setPadding(new Insets(0, 25, 0, 0));
        excelCellColorpickerLabel = new Label("Select cell color:");
        excelCellColorpickerLabel.setPadding(new Insets(0, 87, 0, 0));

        windowLabel = new Label("Statistic helper - Maxnews");
        windowLabel.setId("window-label");
    }

    public Label getCurFileLabel() {
        return curFileLabel;
    }
    public Label getNewFileLabel() {
        return newFileLabel;
    }
    public Label getPrevWeekLabel() {
        return prevWeekLabel;
    }
    public Label getCurWeekLabel() {
        return curWeekLabel;
    }
    public Label getLoginLabel() {
        return loginLabel;
    }
    public Label getDateFromLabel() {
        return dateFromLabel;
    }
    public Label getDateToLabel() {
        return dateToLabel;
    }
    public Label getWindowLabel() {
        return windowLabel;
    }
    public Label getExcelSettingsLabel() {
        return excelSettingsLabel;
    }
    public Label getExcelHeaderColorpickerLabel() {
        return excelHeaderColorpickerLabel;
    }
    public Label getExcelCellColorpickerLabel() {
        return excelCellColorpickerLabel;
    }

    // -- IMAGES SETTERS AND GETTERS --
    public void setImages() {
        try {
        Image logo = new Image(new FileInputStream("src/resources/maxlogoSmall.png"));
        this.logo = logo;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView view = new ImageView(getLogo());
        view.setFitWidth(25);
        view.setFitHeight(20);
        view.setId("logo");
        view.setSmooth(true);

        this.view = view;
    }

    public ImageView getView() {
        return view;
    }
    public Image getLogo() {
        return logo;
    }

    //-- CHECKBOXES SETTERS AND GETTERS --

    public void setCheckboxes() {
        topFiveCheckbox = new CheckBox("Only include top 5");
        topFiveCheckbox.setId("checkbox");
        topFiveCheckbox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                topFiveCheckboxAction(actionEvent, newFileTable, topFiveCheckbox, topFiveMultipleCheckbox);
            }
        });

        topFiveMultipleCheckbox = new CheckBox("Allow multiple");
        topFiveMultipleCheckbox.setId("checkbox");
        topFiveMultipleCheckbox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                topFiveMultipleCheckboxAction(actionEvent, newFileTable, topFiveMultipleCheckbox);
            }
        });
        topFiveMultipleCheckbox.setVisible(false);

        restaurantsZeroCheckbox = new CheckBox("Include restaurants with 0 complaints");
        restaurantsZeroCheckbox.setSelected(true);
        restaurantsZeroCheckbox.setId("checkbox");

        onlyImportantColumnsCheckbox = new CheckBox("Only include important columns");
        onlyImportantColumnsCheckbox.setId("checkbox");

        excelHorizontalGridLinesCheckbox = new CheckBox("Horizontal grid lines");
        excelHorizontalGridLinesCheckbox.setSelected(true);
        excelHorizontalGridLinesCheckbox.setId("checkbox");

        excelVerticalGridLinesCheckbox = new CheckBox("Vertical grid lines");
        excelVerticalGridLinesCheckbox.setSelected(true);
        excelVerticalGridLinesCheckbox.setId("checkbox");
        //action
    }

    public CheckBox getTopFiveCheckbox() {
        return topFiveCheckbox;
    }
    public CheckBox getTopFiveMultipleCheckbox() {
        return topFiveMultipleCheckbox;
    }
    public CheckBox getRestaurantsZeroCheckbox() {
        return restaurantsZeroCheckbox;
    }
    public CheckBox getOnlyImportantColumnsCheckbox() {
        return onlyImportantColumnsCheckbox;
    }
    public CheckBox getExcelVerticalGridLinesCheckbox() {
        return excelVerticalGridLinesCheckbox;
    }
    public CheckBox getExcelHorizontalGridLinesCheckbox() {
        return excelHorizontalGridLinesCheckbox;
    }


    //-- COLOR PICKER SETTER AND GETTERS --
    public void setColorpickers() {
        headerColorPicker = new ColorPicker();
        headerColorPicker.setPrefSize(50, 25);
        headerColorPicker.setId("color-picker");

        cellColorPicker = new ColorPicker();
        cellColorPicker.setPrefSize(50, 25);
        cellColorPicker.setId("color-picker");
    }

    public ColorPicker getHeaderColorPicker() {
        return headerColorPicker;
    }
    public ColorPicker getCellColorPicker() {
        return cellColorPicker;
    }


    //-- TEXTFIELD SETTERS AND GETTERS --
    public void setTextfields() {
        importField = new TextField();
        importField.setPrefSize(400, 30);
        importField.setBorder(getBlackSharp());
        importField.setId("import-field");

        userField = new TextField();
        userField.setPrefSize(100, 30);
        userField.setBorder(getBlackSharp());
        userField.setFont(getLoginFont());
        userField.setPromptText("Username");
        userField.setId("user-field");

        dateFromField = new TextField();
        dateFromField.setPrefSize(160, 30);
        dateFromField.setBorder(getDateFieldBorder());
        dateFromField.setId("date-field");
        dateFromField.setText(getLastMonDate());
        dateFromField.setEditable(false);

        dateToField = new TextField();
        dateToField.setPrefSize(160,30);
        dateToField.setBorder(getDateFieldBorder());
        dateToField.setId("date-field");
        dateToField.setText(getLastSunDate());
        dateToField.setEditable(false);

        passField = new PasswordField();
        passField.setPrefSize(100,30);
        passField.setBorder(getBlackSharp());
        passField.setPromptText("Password");
        passField.setId("pass-field");
    }

    public TextField getImportField() {
        return importField;
    }
    public TextField getUserField() {
        return userField;
    }
    public TextField getDateFromField() {
        return dateFromField;
    }
    public TextField getDateToField() {
        return dateToField;
    }
    public PasswordField getPassField() {
        return passField;
    }



    //-- COMBOBOXES SETTERS AND GETTERS --
    public void setComboboxes() {
        prevWeekMenu = new ComboBox<>(getWeeks());
        prevWeekMenu.setValue("Week");
        prevWeekMenu.setVisibleRowCount(8);
        prevWeekMenu.setId("combobox");

        curWeekMenu = new ComboBox<>(getWeeks());
        curWeekMenu.setValue("Week");
        curWeekMenu.setVisibleRowCount(8);
        curWeekMenu.setId("combobox");

        sortByMenu = new ComboBox<>(getSortBy());
        sortByMenu.setValue("Sort by");
        sortByMenu.setVisibleRowCount(8);
        sortByMenu.setId("combobox");

        sortByOrderMenu = new ComboBox<>(getSortOrder());
        sortByOrderMenu.setValue("Order by");
        sortByOrderMenu.setVisibleRowCount(4);
        sortByOrderMenu.setId("combobox");
    }

    public ComboBox<String> getPrevWeekMenu() {
        return prevWeekMenu;
    }
    public ComboBox<String> getCurWeekMenu() {
        return curWeekMenu;
    }
    public ComboBox<String> getSortByMenu() {
        return sortByMenu;
    }
    public ComboBox<String> getSortByOrderMenu() {
        return sortByOrderMenu;
    }




    //-- TABLES SETTERS AND GETTERS --
    public void setTables() {

        TableView curFileTable = new TableView();
        curFileTable.getColumns().addAll(getRestColumn(), getPrevWeekColumn(), getCurWeekColumn(), getChangeColumn(), getPercentageColumn());
        curFileTable.setId("main-table");
        curFileTable.setEditable(false);
        curFileTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        curFileTable.setMaxHeight(1000);

        this.curFileTable = curFileTable;
    }
    public void setNewFileTable() {
        TableView newFileTable = new TableView();
        newFileTable.getColumns().addAll(getRestColumnNew(), getPrevWeekColumnNew(), getCurWeekColumnNew(), getChangeColumnNew(), getPercentageColumnNew());
        newFileTable.setId("main-table");
        newFileTable.setEditable(false);
        newFileTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        newFileTable.setMaxHeight(1000);

        this.newFileTable = newFileTable;
    }


    public TableView getCurFileTable() {
        return curFileTable;
    }
    public TableView getNewFileTable() {
        return newFileTable;
    }

    //-- TABLECOLUMNS SETTERS AND GETTERS --
    public void setTableColumns() {
        TableColumn<CurrentFileValues, String> restColumn = new TableColumn("Restauranger");
        restColumn.setMinWidth(300);
        restColumn.setPrefWidth(300);
        restColumn.setMaxWidth(500);
        restColumn.setId("column");
        restColumn.setCellValueFactory(new PropertyValueFactory<>("Restaurant"));

        TableColumn<CurrentFileValues, Integer> prevWeekColumn = new TableColumn("V.");
        prevWeekColumn.setMinWidth(50);
        prevWeekColumn.setPrefWidth(50);
        prevWeekColumn.setId("column");
        prevWeekColumn.setCellValueFactory(new PropertyValueFactory<>("PrevWeek"));

        TableColumn<CurrentFileValues, Integer> curWeekColumn = new TableColumn("V.");
        curWeekColumn.setMinWidth(50);
        curWeekColumn.setPrefWidth(50);
        curWeekColumn.setId("column");
        curWeekColumn.setCellValueFactory(new PropertyValueFactory<>("CurWeek"));

        TableColumn<CurrentFileValues, Integer> changeColumn = new TableColumn("Förändring");
        changeColumn.setMinWidth(110);
        changeColumn.setPrefWidth(110);
        changeColumn.setId("column");
        changeColumn.setCellValueFactory(new PropertyValueFactory<>("Change"));

        TableColumn<CurrentFileValues, Float> percentageColumn = new TableColumn("Procent");
        percentageColumn.setMinWidth(105);
        percentageColumn.setPrefWidth(105);
        percentageColumn.setId("column");
        percentageColumn.setCellValueFactory(new PropertyValueFactory<>("Percent"));

        this.restColumn = restColumn;
        this.prevWeekColumn = prevWeekColumn;
        this.curWeekColumn = curWeekColumn;
        this.changeColumn = changeColumn;
        this.percentageColumn = percentageColumn;
    }
    public void setNewTableColumns() {
        TableColumn<NewFileValues, String> restColumnNew = new TableColumn("Restauranger");
        restColumnNew.setMinWidth(300);
        restColumnNew.setPrefWidth(300);
        restColumnNew.setMaxWidth(500);
        restColumnNew.setId("column");
        restColumnNew.setCellValueFactory(new PropertyValueFactory<>("RestaurantNew"));

        TableColumn<NewFileValues, String> prevWeekColumnNew = new TableColumn("V.");
        prevWeekColumnNew.setMinWidth(50);
        prevWeekColumnNew.setPrefWidth(50);
        prevWeekColumnNew.setId("column");
        prevWeekColumnNew.setCellValueFactory(new PropertyValueFactory<>("PrevWeekNew"));

        TableColumn<NewFileValues, String> curWeekColumnNew = new TableColumn("V.");
        curWeekColumnNew.setMinWidth(50);
        curWeekColumnNew.setPrefWidth(50);
        curWeekColumnNew.setId("column");
        curWeekColumnNew.setCellValueFactory(new PropertyValueFactory<>("CurWeekNew"));

        TableColumn<NewFileValues, String> changeColumnNew = new TableColumn("Förändring");
        changeColumnNew.setMinWidth(110);
        changeColumnNew.setPrefWidth(110);
        changeColumnNew.setId("column");
        changeColumnNew.setCellValueFactory(new PropertyValueFactory<>("ChangeNew"));

        TableColumn<NewFileValues, String> percentageColumnNew = new TableColumn("Procent");
        percentageColumnNew.setMinWidth(105);
        percentageColumnNew.setPrefWidth(105);
        percentageColumnNew.setId("column");
        percentageColumnNew.setCellValueFactory(new PropertyValueFactory<>("PercentNew"));

        this.restColumnNew = restColumnNew;
        this.prevWeekColumnNew = prevWeekColumnNew;
        this.curWeekColumnNew = curWeekColumnNew;
        this.changeColumnNew = changeColumnNew;
        this.percentageColumnNew = percentageColumnNew;
    }

    public TableColumn<CurrentFileValues, String> getRestColumn() {
        return restColumn;
    }
    public TableColumn<CurrentFileValues, Integer> getPrevWeekColumn() {
        return prevWeekColumn;
    }
    public TableColumn<CurrentFileValues, Integer> getCurWeekColumn() {
        return curWeekColumn;
    }
    public TableColumn<CurrentFileValues, Integer> getChangeColumn() {
        return changeColumn;
    }
    public TableColumn<CurrentFileValues, Float> getPercentageColumn() {
        return percentageColumn;
    }

    public TableColumn<NewFileValues, String> getRestColumnNew() {
        return restColumnNew;
    }
    public TableColumn<NewFileValues, String> getPrevWeekColumnNew() {
        return prevWeekColumnNew;
    }
    public TableColumn<NewFileValues, String> getCurWeekColumnNew() {
        return curWeekColumnNew;
    }
    public TableColumn<NewFileValues, String> getChangeColumnNew() {
        return changeColumnNew;
    }
    public TableColumn<NewFileValues, String> getPercentageColumnNew() {
        return percentageColumnNew;
    }

    //-- MENUBAR SETTERS AND GETTERS
    //-- MENUBAR --
    public void setMenuBar() {
        menubar = new MenuBar();
        menubar.setMinSize(150, 20);
    }
    //-- MENU --
    public void setMenu() {
        fileMenu = new Menu("_File");
        fileMenu.setId("menu");
        fileMenu.setMnemonicParsing(true);
        editMenu = new Menu("_Edit");
        editMenu.setId("menu");
        editMenu.setMnemonicParsing(true);
        helpMenu = new Menu("_Help");
        helpMenu.setId("menu");
        helpMenu.setMnemonicParsing(true);
    }
    //-- MENUITEMS --
    public void setMenuItems() {
        fileItem1 = new MenuItem("Settings");
        fileItem2 = new MenuItem("Exit");
        editItem1 = new MenuItem("Restart application");
        helpItem1 = new MenuItem("About");
        helpItem2 = new MenuItem("Contact");
        helpItem3 = new MenuItem("Support");
    }

    public MenuBar getMenubar() {
        return menubar;
    }
    public Menu getFileMenu() {
        return fileMenu;
    }
    public Menu getHelpMenu() {
        return helpMenu;
    }
    public Menu getEditMenu() {
        return editMenu;
    }
    public MenuItem getFileItem1() {
        return fileItem1;
    }
    public MenuItem getFileItem2() {
        return fileItem2;
    }
    public MenuItem getEditItem1() {
        return editItem1;
    }
    public MenuItem getHelpItem1() {
        return helpItem1;
    }
    public MenuItem getHelpItem2() {
        return helpItem2;
    }
    public MenuItem getHelpItem3() {
        return helpItem3;
    }

    //-- SHAPES SETTERS AND GETTERS --
    public void setShapes() {
        Rectangle buttonShape = new Rectangle();
        buttonShape.setWidth(30);
        buttonShape.setHeight(25);

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(10);
        rectangle.setHeight(10);
        rectangle.setId("rectangle");
        rectangle.setStroke(Color.BLACK);

        Rectangle dateMenuShape = new Rectangle();
        dateMenuShape.setHeight(25);
        dateMenuShape.setWidth(100);
        dateMenuShape.setArcWidth(5);
        dateMenuShape.setArcHeight(5);

        Path p = new Path();

        MoveTo moveTo = new MoveTo();
        moveTo.setX(0);
        moveTo.setY(0);

        ArcTo arcTo1 = new ArcTo();
        arcTo1.setX(5);
        arcTo1.setY(5);
        arcTo1.setLargeArcFlag(false);
        arcTo1.setSweepFlag(true);
        arcTo1.setRadiusX(5);
        arcTo1.setRadiusY(5);

        LineTo lineTo1 = new LineTo(5, 20);

        ArcTo arcTo2 = new ArcTo();
        arcTo2.setX(10);
        arcTo2.setY(25);
        arcTo2.setRadiusX(5);
        arcTo2.setRadiusY(5);

        LineTo lineTo2 = new LineTo(110, 25);

        ArcTo arcTo3 = new ArcTo();
        arcTo3.setX(115);
        arcTo3.setY(20);
        arcTo3.setRadiusX(5);
        arcTo3.setRadiusY(5);

        LineTo lineTo3 = new LineTo(115, 5);

        ArcTo arcTo4 = new ArcTo();
        arcTo4.setX(120);
        arcTo4.setY(0);
        arcTo4.setLargeArcFlag(false);
        arcTo4.setSweepFlag(true);
        arcTo4.setRadiusX(5);
        arcTo4.setRadiusY(5);

        LineTo lineTo4 = new LineTo(0, 0);

        p.getElements().add(moveTo);
        p.getElements().addAll(arcTo1, lineTo1, arcTo2, lineTo2, arcTo3, lineTo3, arcTo4);




        Line line1 = new Line();
        line1.setStartX(0);
        line1.setStartY(0);
        line1.setEndX(10);
        line1.setEndY(10);

        Line line2 = new Line();
        line2.setStartX(10);
        line2.setStartY(0);
        line2.setEndX(0);
        line2.setEndY(10);

        Line minimizeLine = new Line();
        minimizeLine.setStartX(0);
        minimizeLine.setStartY(5);
        minimizeLine.setEndX(10);
        minimizeLine.setEndY(5);

        Line leftLine = new Line();
        leftLine.setStartX(0);
        leftLine.setStartY(0);
        leftLine.setEndX(0);
        leftLine.setEndY(10);

        Line bottomLine = new Line();
        bottomLine.setStartX(0);
        bottomLine.setStartY(10);
        bottomLine.setEndX(10);
        bottomLine.setEndY(10);

        Line rightLine = new Line();
        rightLine.setStartX(10);
        rightLine.setStartY(10);
        rightLine.setEndX(10);
        rightLine.setEndY(0);

        Line topLine = new Line();
        topLine.setStartX(0);
        topLine.setStartY(0);
        topLine.setEndX(10);
        topLine.setEndY(0);

        this.buttonShape = buttonShape;
        this.rectangle = rectangle;
        this.dateMenuShape = dateMenuShape;
        this.p = p;
        this.line1 = line1;
        this.line2 = line2;
        this.minimizeLine = minimizeLine;
        this.maximizeBottomLine = bottomLine;
        this.maximizeLeftLine = leftLine;
        this.maximizeRightLine = rightLine;
        this.maximizeTopLine = topLine;
    }

    public Rectangle getButtonShape() {
        return buttonShape;
    }
    public Rectangle getRectangle() {
        return rectangle;
    }
    public Rectangle getDateMenuShape() {
        return dateMenuShape;
    }
    public Path getCubicCurve() {
        return p;
    }
    public Line getLine1() {
        return line1;
    }
    public Line getLine2() {
        return line2;
    }
    public Line getMinimizeLine() {
        return minimizeLine;
    }
    public Line getMaximizeLeftLine() {
        return maximizeLeftLine;
    }
    public Line getMaximizeBottomLine() {
        return maximizeBottomLine;
    }
    public Line getMaximizeRightLine() {
        return maximizeRightLine;
    }
    public Line getMaximizeTopLine() {
        return maximizeTopLine;
    }

    //-- INSETS SETTERS AND GETTERS --
    public Insets getLeftGap() {
        return leftGap;
    }

    public void setInsets() {
        Insets leftGap = new Insets(Insets.EMPTY.getBottom(), Insets.EMPTY.getTop(), Insets.EMPTY.getRight(), 15);
        this.leftGap = leftGap;
    }



    //-- BACKGROUNDS SETTERS AND GETTERS --
    public Background getMainBackground() {
        return mainBackground;
    }
    public Background getSubMenuBackground() {
        return subMenuBackground;
    }
    public Background getTableHeaderBackground() {
        return tableHeaderBackground;
    }
    public Background getLessWhiteBackground() {
        return lessWhiteBackground;
    }
    public Background getWhite() {
        return white;
    }

    public void setBackgrounds() {
        Background mainBackground = new Background(new BackgroundFill(Color.rgb(220,210,210), CornerRadii.EMPTY, Insets.EMPTY));
        Background tableHeaderBackground = new Background(new BackgroundFill(Color.rgb(160, 160, 160), new CornerRadii(5), Insets.EMPTY));
        Background subMenuBackground = new Background(new BackgroundFill(Color.rgb(160,160,160), new CornerRadii(5), Insets.EMPTY));
        Background lessWhiteBackground= new Background(new BackgroundFill(Color.rgb(235,235,235), CornerRadii.EMPTY, Insets.EMPTY));
        Background white = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));

        this.mainBackground = mainBackground;
        this.tableHeaderBackground = tableHeaderBackground;
        this.subMenuBackground = subMenuBackground;
        this.lessWhiteBackground = lessWhiteBackground;
        this.white = white;
    }



    //-- FONTS SETTERS AND GETTERS --
    public void setFonts() {
        Font tableHeaderFont = Font.font("Arial", FontWeight.BOLD, 20);
        Font arialSize14 = new Font("Arial", 14);
        Font loginFont = Font.font("Arial", FontPosture.ITALIC, 11);
        Font arialBold = Font.font("Arial", FontWeight.BOLD, 11);
        this.tableHeaderFont = tableHeaderFont;
        this.arialSize14 = arialSize14;
        this.loginFont = loginFont;
        this.arialBold = arialBold;
    }

    public Font getTableHeaderFont() {
        return tableHeaderFont;
    }
    public Font getArialSize14() {
        return arialSize14;
    }
    public Font getArialBold() {
        return arialBold;
    }
    public Font getLoginFont() {
        return loginFont;
    }



    //-- BORDERS SETTERS AND GETTERS --
    public void setBorders() {
        final Border black = new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(2)));
        final Border blackSharp = new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1)));
        final Border bottomLine = new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,2,0)));
        final Border bottomTopLine = new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2,0,2,0)));
        final Border dateButtonBorder = new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1,1,1,0)));
        final Border dateFieldBorder = new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1,0,1,1)));


        this.black = black;
        this.blackSharp = blackSharp;
        this.bottomLine = bottomLine;
        this.bottomTopLine = bottomTopLine;
        this.dateButtonBorder = dateButtonBorder;
        this.dateFieldBorder = dateFieldBorder;
    }

    public Border getBlack() {
        return black;
    }
    public Border getBlackSharp() {
        return blackSharp;
    }
    public Border getBottomLine() {
        return bottomLine;
    }
    public Border getBottomTopLine() {
        return bottomTopLine;
    }
    public Border getDateButtonBorder() {
        return dateButtonBorder;
    }
    public Border getDateFieldBorder() {
        return dateFieldBorder;
    }



    // -- Methods --
    public ObservableList<String> getWeeks() {
        ObservableList<String> weeks = FXCollections.observableArrayList(
                "Week",
                "V.1",
                "V.2",
                "V.3",
                "V.4",
                "V.5",
                "V.6",
                "V.7",
                "V.8",
                "V.9",
                "V.10",
                "V.11",
                "V.12",
                "V.13",
                "V.14",
                "V.15",
                "V.16",
                "V.17",
                "V.18",
                "V.19",
                "V.20",
                "V.21",
                "V.22",
                "V.23",
                "V.24",
                "V.25",
                "V.26",
                "V.27",
                "V.28",
                "V.29",
                "V.30",
                "V.31",
                "V.32",
                "V.33",
                "V.34",
                "V.35",
                "V.36",
                "V.37",
                "V.38",
                "V.39",
                "V.40",
                "V.41",
                "V.42",
                "V.43",
                "V.44",
                "V.45",
                "V.46",
                "V.47",
                "V.48",
                "V.49",
                "V.50",
                "V.51",
                "V.52"
        );
        return weeks;
    }
    public ObservableList<String> getSortBy() {
        ObservableList<String> sortBy = FXCollections.observableArrayList(
                "Sort by",
                "Restaurang",
                "Föregående vecka",
                "Nuvarande vecka",
                "Förändring",
                "Procent"
        );
        return sortBy;
    }
    public ObservableList<String> getSortOrder() {
        ObservableList<String> sortOrder = FXCollections.observableArrayList(
                "Order by",
                "Störst först",
                "Minst först"
        );
        return sortOrder;
    }

    public String getLastMonDate() {
        LocalDate date = LocalDate.now();
        LocalDate lastWeekDate = date.minusWeeks(1);
        LocalDate lastMondayDate = lastWeekDate.with(DayOfWeek.MONDAY);
        return lastMondayDate.toString();
    }

    public String getLastSunDate() {
        LocalDate date = LocalDate.now();
        LocalDate lastWeekDate = date.minusWeeks(1);
        LocalDate lastSundayDate = lastWeekDate.with(DayOfWeek.SUNDAY);
        return lastSundayDate.toString();
    }



}
