package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ComponentCustomization extends App{

    //-- Stages --
    Stage primaryStage;

    // -- Buttons
    Button importButton;
    Button generateFileButton;
    Button dateFromButton;
    Button dateToButton;
    Button nextMonthButton;
    Button prevMonthButton;
    Button errorOkButton;
    Button saveButton;
    Button bottomMenuButtonMaxNews;
    Button bottomMenuButtonWeekly;
    Button bottomMenuButtonDaily;

    Button closeButton;
    Button maximizeButton;
    Button minimizeButton;

    // -- StackPane for topButtons --
    StackPane cross;
    StackPane box;

    // -- Labels --
    Label curFileLabel;
    Label newFileLabel;
    Label prevWeekLabel;
    Label curWeekLabel;
    Label loginLabel;
    Label dateFromLabel;
    Label dateToLabel;
    Label windowLabel;

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

    // -- Comboboxes --
    ComboBox<String> prevWeekMenu;
    ComboBox<String> curWeekMenu;
    ComboBox<String> sortByMenu;
    ComboBox<String> sortByOrderMenu;

    // -- Tables --
    TableView<String> curFileTable;
    TableView<String> newFileTable;

    // -- Table columns --
    TableColumn restColumn;
    TableColumn prevWeekColumn;
    TableColumn curWeekColumn;
    TableColumn changeColumn;
    TableColumn percentageColumn;

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


    public ComponentCustomization(){
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
                importButtonAction(actionEvent, primaryStage);
            }
        });

        // -- dateFromButton --
        dateFromButton = new Button("From");
        dateFromButton.setPrefSize(55,30);
        dateFromButton.setShape(rectangle);
        dateFromButton.setBorder(dateButtonBorder);
        dateFromButton.setCursor(Cursor.HAND);

        // -- dateToButton --
        dateToButton = new Button("To");
        dateToButton.setPrefSize(55,30);
        dateToButton.setShape(rectangle);
        dateToButton.setBorder(dateButtonBorder);
        dateToButton.setCursor(Cursor.HAND);

        // -- generateFileButton --
        generateFileButton = new Button("Generate new file");
        generateFileButton.setPrefSize(140,50);
        generateFileButton.setCursor(Cursor.HAND);
        generateFileButton.setId("standard-button");
        generateFileButton.setFont(arialSize14);
        // -- saveButton --
        saveButton = new Button("Save");
        saveButton.setId("standard-button");

        // -- nextMonthButton --
        nextMonthButton = new Button("Next");

        // -- prevMonthButton --
        prevMonthButton = new Button("Prev");

        // -- errorOkButton --
        errorOkButton = new Button("Ok");
        errorOkButton.setId("standard-button");


        // -- bottomMenuButtonMaxNews --
        bottomMenuButtonMaxNews = new Button("Maxnews");
        bottomMenuButtonMaxNews.setBackground(getWhite());
        bottomMenuButtonMaxNews.setFont(getArialSize14());
        bottomMenuButtonMaxNews.setPrefSize(100, 50);
        bottomMenuButtonMaxNews.setCursor(Cursor.HAND);
        bottomMenuButtonMaxNews.setId("bottom-button-lowered");
        //action = display maxnews editor

        // -- bottomMenuButtonWeekly --
        bottomMenuButtonWeekly = new Button("Weekly report");
        bottomMenuButtonWeekly.setBackground(getWhite());
        bottomMenuButtonWeekly.setFont(getArialSize14());
        bottomMenuButtonWeekly.setPrefSize(120, 50);
        bottomMenuButtonWeekly.setCursor(Cursor.HAND);
        bottomMenuButtonWeekly.setId("bottom-button-lowered");
        //action = display weekly report editor

        // -- bottomMenuButtonDaily --
        bottomMenuButtonDaily = new Button("Daily report");
        bottomMenuButtonDaily.setBackground(getWhite());
        bottomMenuButtonDaily.setFont(getArialSize14());
        bottomMenuButtonDaily.setPrefSize(100, 50);
        bottomMenuButtonDaily.setCursor(Cursor.HAND);
        bottomMenuButtonDaily.setId("bottom-button-lowered");
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
    public Button getNextMonthButton() {
        return nextMonthButton;
    }
    public Button getPrevMonthButton() {
        return prevMonthButton;
    }
    public Button getErrorOkButton() {
        return errorOkButton;
    }
    public Button getSaveButton() {
        return saveButton;
    }
    public Button getBottomMenuButtonMaxNews() {
        return bottomMenuButtonMaxNews;
    }
    public Button getBottomMenuButtonWeekly() {
        return bottomMenuButtonWeekly;
    }
    public Button getBottomMenuButtonDaily() {
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
        cross = new StackPane();
        cross.setPrefSize(30,30);
        cross.getChildren().addAll(getLine1(), getLine2());

        box = new StackPane();
        box.setPrefSize(30,30);
        box.getChildren().add(getRectangle());
    }

    public StackPane getCross() {
        return cross;
    }
    public StackPane getBox() {
        return box;
    }

    // -- LABELS SETTERS AND GETTERS --
    public void setLabels() {
        curFileLabel = new Label("CURRENT FILE");
        curFileLabel.setId("table-header-label");

        newFileLabel = new Label("PREVIEW OF NEW FILE");
        newFileLabel.setId("table-header-label");

        prevWeekLabel = new Label("Select new previous week");
        prevWeekLabel.setId("standard-label");
        curWeekLabel = new Label("Select new current week");
        curWeekLabel.setId("standard-label");
        loginLabel = new Label("Log in to Max ÄHS:");
        loginLabel.setId("standard-label");
        dateFromLabel = new Label();
        dateToLabel = new Label();
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
        topFiveCheckbox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                topFiveCheckboxAction(actionEvent, newFileTable, topFiveCheckbox, topFiveMultipleCheckbox);
            }
        });

        topFiveMultipleCheckbox = new CheckBox("Allow multiple");
        topFiveMultipleCheckbox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                topFiveMultipleCheckboxAction(actionEvent, newFileTable, topFiveMultipleCheckbox);
            }
        });
        topFiveMultipleCheckbox.setVisible(false);
    }

    public CheckBox getTopFiveCheckbox() {
        return topFiveCheckbox;
    }
    public CheckBox getTopFiveMultipleCheckbox() {
        return topFiveMultipleCheckbox;
    }


    //-- TEXTFIELD SETTERS AND GETTERS --
    public void setTextfields() {
        importField = new TextField();
        importField.setPrefSize(400, 30);
        importField.setBorder(getBlackSharp());

        userField = new TextField("Username");
        userField.setPrefSize(100, 30);
        userField.setBorder(getBlackSharp());
        userField.setFont(getLoginFont());

        dateFromField = new TextField();
        dateFromField.setPrefSize(160, 30);
        dateFromField.setBorder(getDateFieldBorder());

        dateToField = new TextField();
        dateToField.setPrefSize(160,30);
        dateToField.setBorder(getDateFieldBorder());

        passField = new PasswordField();
        passField.setPrefSize(100,30);
        passField.setBorder(getBlackSharp());
        passField.setText("Password");
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

        curWeekMenu = new ComboBox<>(getWeeks());
        curWeekMenu.setValue("Week");
        curWeekMenu.setVisibleRowCount(8);

        sortByMenu = new ComboBox<>(getSortBy());
        sortByMenu.setValue("Sort by");
        sortByMenu.setVisibleRowCount(8);

        sortByOrderMenu = new ComboBox<>(getSortOrder());
        sortByOrderMenu.setValue("Order by");
        sortByOrderMenu.setVisibleRowCount(4);
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

        curFileTable = new TableView<>();
        curFileTable.getColumns().addAll(restColumn, prevWeekColumn, curWeekColumn, changeColumn, percentageColumn);
        curFileTable.setId("main-table");
        curFileTable.setEditable(false);
        curFileTable.setMaxHeight(1000);
        curFileTable.getItems().add("row");

        newFileTable = new TableView<>();
        newFileTable.getColumns().addAll(restColumn, prevWeekColumn, curWeekColumn, changeColumn, percentageColumn);
        newFileTable.setId("main-table");
        newFileTable.setEditable(false);
        newFileTable.setMaxHeight(1000);
        newFileTable.getItems().add("row");
    }

    public TableView<String> getCurFileTable() {
        return curFileTable;
    }
    public TableView<String> getNewFileTable() {
        return newFileTable;
    }

    //-- TABLECOLUMNS SETTERS AND GETTERS --
    public void setTableColumns() {
        restColumn = new TableColumn("Restauranger");
        restColumn.setPrefWidth(300);
        restColumn.setId("column");
        //restColumn.setCellValueFactory(new PropertyValueFactory<>("Restaurang"));

        prevWeekColumn = new TableColumn("V.25");
        prevWeekColumn.setPrefWidth(50);
        prevWeekColumn.setId("column");
        //prevWeekColumn.setCellValueFactory(new PropertyValueFactory<>("V.25"));

        curWeekColumn = new TableColumn("V.26");
        curWeekColumn.setPrefWidth(50);
        curWeekColumn.setId("column");
        //curWeekColumn.setCellValueFactory(new PropertyValueFactory<>("V.26"));

        changeColumn = new TableColumn("Förändring");
        changeColumn.setPrefWidth(110);
        changeColumn.setId("column");
        //changeColumn.setCellValueFactory(new PropertyValueFactory<>("Förändring"));

        percentageColumn = new TableColumn("Procent");
        percentageColumn.setPrefWidth(105);
        percentageColumn.setId("column");
        //percentageColumn.setCellValueFactory(new PropertyValueFactory<>("Procent"));
    }

    public TableColumn getRestColumn() {
        return restColumn;
    }
    public TableColumn getPrevWeekColumn() {
        return prevWeekColumn;
    }
    public TableColumn getCurWeekColumn() {
        return curWeekColumn;
    }
    public TableColumn getChangeColumn() {
        return changeColumn;
    }
    public TableColumn getPercentageColumn() {
        return percentageColumn;
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
        fileMenu.setMnemonicParsing(true);
        editMenu = new Menu("_Edit");
        editMenu.setMnemonicParsing(true);
        helpMenu = new Menu("_Help");
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
        dateMenuShape.setHeight(120);
        dateMenuShape.setWidth(200);
        dateMenuShape.setArcWidth(5);
        dateMenuShape.setArcHeight(5);

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

}
