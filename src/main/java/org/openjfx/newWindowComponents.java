package org.openjfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class newWindowComponents extends NewWindow {
    LocalDate date = LocalDate.now();

    // -- BUTTONS --
    Button nextMonthButton;
    Button prevMonthButton;
    Button curYearButton;
    Button prevYearButton;
    Button nextYearButton;
    Button closeButton;
    Button maximizeButton;
    Button minimizeButton;

    // -- BUTTONS IN TEXTAREAWINDOW --
    Button doneButton;

    // -- EVENTHANDLERS --
    EventHandler<ActionEvent> monthButtonAction;
    EventHandler<ActionEvent> yearButtonAction;

    // -- LABELS --
    Label monthLabel;

    // -- TEXTAREAS --
    TextArea textArea;

    // -- IMPORTFIELD --
    TextField importField;

    // -- TABLECOLUMNS --
    TableColumn<CalendarDays, String> monday;
    TableColumn<CalendarDays, String> tuesday;
    TableColumn<CalendarDays, String> wednesday;
    TableColumn<CalendarDays, String> thursday;
    TableColumn<CalendarDays, String> friday;
    TableColumn<CalendarDays, String> saturday;
    TableColumn<CalendarDays, String> sunday;

    // -- TABLE --
    TableView calendar;

    // -- newFileTable --
    TableView newFileTable;

    // -- STAGE --
    Stage dateStage;

    public newWindowComponents() {
        setEventHandlers();
        setButtons();
        setLabels();
        setTableColumns();
        setTextArea();
    }

    // -- BUTTON SETTERS AND GETTERS --
    public void setButtons() {
        // -- BUTTONS --
        Button nextMonthButton = new Button("Next");
        nextMonthButton.setId("month-button");
        nextMonthButton.addEventFilter(ActionEvent.ACTION, getMonthButtonAction());

        Button prevMonthButton = new Button("Prev");
        prevMonthButton.setId("month-button");
        prevMonthButton.addEventFilter(ActionEvent.ACTION, getMonthButtonAction());

        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int nextYear = year + 1;
        int prevYear = year - 1;

        String curYearString = String.valueOf(year);
        String nextYearString = String.valueOf(nextYear);
        String prevYearString = String.valueOf(prevYear);

        Button curYearButton = new Button(curYearString);
        curYearButton.setId("year-button");
        curYearButton.setDisable(true);

        Button prevYearButton = new Button(prevYearString);
        prevYearButton.setId("not-cur-year-button");
        prevYearButton.addEventFilter(ActionEvent.ACTION, getYearButtonAction());

        Button nextYearButton = new Button(nextYearString);
        nextYearButton.setId("not-cur-year-button");
        nextYearButton.addEventFilter(ActionEvent.ACTION, getYearButtonAction());

        Button doneButton = new Button("Done");
        doneButton.setId("done-button");
        doneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                doneButtonAction(actionEvent, getTextArea(), getNewFileTable(), getStage(), getImportField());
            }
        });

        AppComponents a = new AppComponents();
        EventHandler<ActionEvent> windowOptions = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                windowButtonActions(actionEvent, getStage());
            }
        };
        // -- closeButton --
        Button closeButton = new Button();
        closeButton.setPrefSize(40,40);
        //closeButton.setShape(getButtonShape());
        closeButton.setGraphic(a.getCross());
        closeButton.setId("close-button");
        closeButton.addEventHandler(ActionEvent.ACTION, windowOptions);


        // -- maximizeButton --
        Button maximizeButton = new Button();
        maximizeButton.setPrefSize(40, 40);
        //maximizeButton.setShape(getButtonShape());
        maximizeButton.setGraphic(a.getBox());
        maximizeButton.setId("max-button");
        maximizeButton.addEventHandler(ActionEvent.ACTION, windowOptions);

        // -- minimizeButton --
        Button minimizeButton = new Button();
        minimizeButton.setPrefSize(40, 40);
        minimizeButton.setGraphic(a.getMinimizeLine());
        minimizeButton.setId("min-button");
        minimizeButton.addEventHandler(ActionEvent.ACTION, windowOptions);

        this.nextMonthButton = nextMonthButton;
        this.prevMonthButton = prevMonthButton;
        this.curYearButton = curYearButton;
        this.prevYearButton = prevYearButton;
        this.nextYearButton = nextYearButton;
        this.doneButton = doneButton;
        this.closeButton = closeButton;
        this.maximizeButton = maximizeButton;
        this.minimizeButton = minimizeButton;
    }

    public Button getNextMonthButton() {
        return nextMonthButton;
    }
    public Button getPrevMonthButton() {
        return prevMonthButton;
    }
    public Button getCurYearButton() {
        return curYearButton;
    }
    public Button getPrevYearButton() {
        return prevYearButton;
    }
    public Button getNextYearButton() {
        return nextYearButton;
    }
    public Button getDoneButton() {
        return doneButton;
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

    // -- EVENTHANDLERS SETTERS AND GETTERS --
    public void setEventHandlers() {
        EventHandler<ActionEvent> monthButtonAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                monthButtonActionPerformed(actionEvent, getMonthLabel(), getPrevYearButton(), getCurYearButton(), getNextYearButton(), getCalendar());
            }
        };

        EventHandler<ActionEvent> yearButtonAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                yearButtonActionPerformed(actionEvent, getMonthLabel(), getPrevYearButton(), getCurYearButton(), getNextYearButton(), getCalendar());
            }
        };

        this.monthButtonAction = monthButtonAction;
        this.yearButtonAction = yearButtonAction;
    }

    public EventHandler<ActionEvent> getMonthButtonAction() {
        return monthButtonAction;
    }
    public EventHandler<ActionEvent> getYearButtonAction() {
        return yearButtonAction;
    }

    // -- LABELS SETTERS AND GETTERS --
    public void setLabels() {
        Label monthLabel = new Label();
        monthLabel.setId("month-label");
        String month = date.getMonth().name().toLowerCase();
        String output = month.substring(0,1).toUpperCase() + month.substring(1);
        monthLabel.setText(output);

        this.monthLabel = monthLabel;
    }

    public Label getMonthLabel() {
        return monthLabel;
    }

    // -- TEXTAREAS SETTERS AND GETTERS --
    public void setTextArea() {
        TextArea textArea = new TextArea();
        textArea.setMinHeight(500);
        textArea.setMaxHeight(1100);
        textArea.setId("text-area");
        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                textAreaInput(textArea);
            }
        });

        this.textArea = textArea;
    }
    public TextArea getTextArea() {
        return textArea;
    }

    // -- IMPORTFIELD SETTER AND GETTER --
    public void setImportField(TextField field) {
        this.importField = field;
    }
    public TextField getImportField() {
        return importField;
    }

    // -- TABLECOLUMNS SETTERS AND GETTERS --
    public void setTableColumns() {
        TableColumn<CalendarDays, String> monday = new TableColumn("Mon");
        monday.setCellValueFactory(new PropertyValueFactory<>("Day1"));
        monday.setSortable(false);

        TableColumn<CalendarDays, String> tuesday = new TableColumn("Tue");
        tuesday.setCellValueFactory(new PropertyValueFactory<>("Day2"));
        tuesday.setSortable(false);

        TableColumn<CalendarDays, String> wednesday = new TableColumn("Wed");
        wednesday.setCellValueFactory(new PropertyValueFactory<>("Day3"));
        wednesday.setSortable(false);

        TableColumn<CalendarDays, String> thursday = new TableColumn("Thu");
        thursday.setCellValueFactory(new PropertyValueFactory<>("Day4"));
        thursday.setSortable(false);

        TableColumn<CalendarDays, String> friday = new TableColumn("Fri");
        friday.setCellValueFactory(new PropertyValueFactory<>("Day5"));
        friday.setSortable(false);

        TableColumn<CalendarDays, String> saturday = new TableColumn("Sat");
        saturday.setCellValueFactory(new PropertyValueFactory<>("Day6"));
        saturday.setSortable(false);

        TableColumn<CalendarDays, String> sunday = new TableColumn("Sun");
        sunday.setCellValueFactory(new PropertyValueFactory<>("Day7"));
        sunday.setSortable(false);

        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public TableColumn<CalendarDays, String> getMonday() {
        return monday;
    }
    public TableColumn<CalendarDays, String> getTuesday() {
        return tuesday;
    }
    public TableColumn<CalendarDays, String> getWednesday() {
        return wednesday;
    }
    public TableColumn<CalendarDays, String> getThursday() {
        return thursday;
    }
    public TableColumn<CalendarDays, String> getFriday() {
        return friday;
    }
    public TableColumn<CalendarDays, String> getSaturday() {
        return saturday;
    }
    public TableColumn<CalendarDays, String> getSunday() {
        return sunday;
    }

    // -- TABLES SETTERS AND GETTERS --
    public void setTable(TextField field) {
        TableView calendar = new TableView();
        calendar.setId("calendar");
        calendar.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        calendar.setPrefSize(238, 180);
        calendar.setMaxSize(238, 180);
        calendar.getColumns().addAll(getMonday(), getTuesday(), getWednesday(), getThursday(), getFriday(), getSaturday(), getSunday());
        calendar.setEditable(false);
        calendar.getSelectionModel().setCellSelectionEnabled(true);
        calendar.addEventFilter(ScrollEvent.ANY, Event::consume);
        calendar.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        String[] dateSplit = field.getText().split("-");
        String year = dateSplit[0];
        String month = dateSplit[1];
        String day = dateSplit[2];
        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);
        int dayInt = Integer.parseInt(day);
        if(dayInt < 10) {
            day = day.substring(1);
        }

        getCurYearButton().setText(year);
        List<String> monthNames = monthNameList();
        List<Integer> monthNumbers = monthNumList();
        int index = monthNumbers.indexOf(monthInt);
        month = monthNames.get(index);
        getMonthLabel().setText(month);


        date = LocalDate.of(yearInt, monthInt, dayInt);
        calendarDays(date, calendar);

        for(int i = 0; i <= calendar.getItems().size(); i++) {
            TableColumn column = (TableColumn) calendar.getColumns().get(i);
            for(int j = 0; j < calendar.getItems().size(); j++) {
                try {
                    if (column.getCellData(j).equals(day)) {
                        calendar.getSelectionModel().select(j, column);
                    }
                } catch (NullPointerException ex) {

                }
            }
        }


        calendar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String dateString = calendarMouseClicked(mouseEvent, calendar, getMonthLabel(), getCurYearButton(), getStage());
                field.setText(dateString);
            }
        });


        this.calendar = calendar;
    }

    public TableView getCalendar() {
        return calendar;
    }

    // -- TEXTAREA WINDOW SET AND GET newFileTable --
    public void setNewFileTable(TableView table) {
        this.newFileTable = table;
    }
    public TableView getNewFileTable() {
        return newFileTable;
    }

    // -- TEXTFIELDS SETTERS AND GETTERS --
    public void setStage(Stage stage) {
        this.dateStage = stage;
    }


    public Stage getStage() {
        return dateStage;
    }



    // -- CORRECT DAYS IN CALENDAR --
    public void calendarDays(LocalDate date, TableView table) {
        CalendarDays[] days = new CalendarDays[6];
        LocalDate firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        String day = firstDayOfMonth.getDayOfWeek().name();
        int lengthOfMonth = date.lengthOfMonth();

        CalendarDays firstWeek = new CalendarDays("1", "2", "3", "4", "5", "6", "7");
        CalendarDays secondWeek = new CalendarDays("8", "9", "10", "11", "12", "13", "14");
        CalendarDays thirdWeek = new CalendarDays("15", "16", "17", "18", "19", "20", "21");
        CalendarDays fourthWeek = new CalendarDays("22", "23", "24", "25", "26", "27", "28");
        CalendarDays fifthWeek = new CalendarDays("29", "30", "31", null, null, null, null);
        CalendarDays sixthWeek = new CalendarDays(null, null, null, null, null, null, null);

        if(day.equals("MONDAY")) {
            days[0] = firstWeek;
            days[1] = secondWeek;
            days[2] = thirdWeek;
            days[3] = fourthWeek;
            if(lengthOfMonth == 28) {
                fifthWeek = new CalendarDays(null, null, null, null, null, null, null);
                days[4] = fifthWeek;
            } else if(lengthOfMonth == 29) {
                fifthWeek = new CalendarDays("29", null, null, null, null, null, null);
                days[4] = fifthWeek;
            } else if(lengthOfMonth == 30) {
                fifthWeek = new CalendarDays("29", "30", null, null, null, null, null);
                days[4] = fifthWeek;
            } else {
                days[4] = fifthWeek;
            }
        } else if(day.equals("TUESDAY")) {
            firstWeek = new CalendarDays(null, "1", "2", "3", "4", "5", "6");
            secondWeek = new CalendarDays("7", "8", "9", "10", "11", "12", "13");
            thirdWeek = new CalendarDays("14", "15", "16", "17", "18", "19", "20");
            fourthWeek = new CalendarDays("21", "22", "23", "24", "25", "26", "27");
            fifthWeek = new CalendarDays("28", "29", "30", "31", null, null, null);

            days[0] = firstWeek;
            days[1] = secondWeek;
            days[2] = thirdWeek;
            days[3] = fourthWeek;
            if(lengthOfMonth == 28) {
                fifthWeek = new CalendarDays("28", null, null, null, null, null, null);
                days[4] = fifthWeek;
            } else if(lengthOfMonth == 29) {
                fifthWeek = new CalendarDays("28", "29", null, null, null, null, null);
                days[4] = fifthWeek;
            } else if(lengthOfMonth == 30) {
                fifthWeek = new CalendarDays("28", "29", "30", null, null, null, null);
                days[4] = fifthWeek;
            } else {
                days[4] = fifthWeek;
            }

        } else if(day.equals("WEDNESDAY")) {
            firstWeek = new CalendarDays(null, null, "1", "2", "3", "4", "5");
            secondWeek = new CalendarDays("6", "7", "8", "9", "10", "11", "12");
            thirdWeek = new CalendarDays("13", "14", "15", "16", "17", "18", "19");
            fourthWeek = new CalendarDays("20", "21", "22", "23", "24", "25", "26");
            fifthWeek = new CalendarDays("27", "28", "29", "30", "31", null, null);

            days[0] = firstWeek;
            days[1] = secondWeek;
            days[2] = thirdWeek;
            days[3] = fourthWeek;
            if(lengthOfMonth == 28) {
                fifthWeek = new CalendarDays("27", "28", null, null, null, null, null);
                days[4] = fifthWeek;
            } else if(lengthOfMonth == 29) {
                fifthWeek = new CalendarDays("27", "28", "29", null, null, null, null);
                days[4] = fifthWeek;
            } else if(lengthOfMonth == 30) {
                fifthWeek = new CalendarDays("27", "28", "29", "30", null, null, null);
                days[4] = fifthWeek;
            } else {
                days[4] = fifthWeek;
            }
        } else if(day.equals("THURSDAY")) {
            firstWeek = new CalendarDays(null, null, null, "1", "2", "3", "4");
            secondWeek = new CalendarDays("5", "6", "7", "8", "9", "10", "11");
            thirdWeek = new CalendarDays("12", "13", "14", "15", "16", "17", "18");
            fourthWeek = new CalendarDays("19", "20", "21", "22", "23", "24", "25");
            fifthWeek = new CalendarDays("26", "27", "28", "29", "30", "31", null);

            days[0] = firstWeek;
            days[1] = secondWeek;
            days[2] = thirdWeek;
            days[3] = fourthWeek;
            if(lengthOfMonth == 28) {
                fifthWeek = new CalendarDays("26", "27", "28", null, null, null, null);
                days[4] = fifthWeek;
            } else if(lengthOfMonth == 29) {
                fifthWeek = new CalendarDays("26", "27", "28", "29", null, null, null);
                days[4] = fifthWeek;
            } else if(lengthOfMonth == 30) {
                fifthWeek = new CalendarDays("26", "27", "28", "29", "30", null, null);
                days[4] = fifthWeek;
            } else {
                days[4] = fifthWeek;
            }
        }else if(day.equals("FRIDAY")) {
            firstWeek = new CalendarDays(null, null, null, null, "1", "2", "3");
            secondWeek = new CalendarDays("4", "5", "6", "7", "8", "9", "10");
            thirdWeek = new CalendarDays("11", "12", "13", "14", "15", "16", "17");
            fourthWeek = new CalendarDays("18", "19", "20", "21", "22", "23", "24");
            fifthWeek = new CalendarDays("25", "26", "27", "28", "29", "30", "31");

            days[0] = firstWeek;
            days[1] = secondWeek;
            days[2] = thirdWeek;
            days[3] = fourthWeek;
            if(lengthOfMonth == 28) {
                fifthWeek = new CalendarDays("25", "26", "27", "28", null, null, null);
                days[4] = fifthWeek;
            } else if(lengthOfMonth == 29) {
                fifthWeek = new CalendarDays("25", "26", "27", "28", "29", null, null);
                days[4] = fifthWeek;
            } else if(lengthOfMonth == 30) {
                fifthWeek = new CalendarDays("25", "26", "27", "28", "29", "30", null);
                days[4] = fifthWeek;
            } else {
                days[4] = fifthWeek;
            }
        } else if(day.equals("SATURDAY")) {
            firstWeek = new CalendarDays(null, null, null, null, null, "1", "2");
            secondWeek = new CalendarDays("3", "4", "5", "6", "7", "8", "9");
            thirdWeek = new CalendarDays("10", "11", "12", "13", "14", "15", "16");
            fourthWeek = new CalendarDays("17", "18", "19", "20", "21", "22", "23");
            fifthWeek = new CalendarDays("24", "25", "26", "27", "28", "29", "30");
            sixthWeek = new CalendarDays("31", null, null, null, null, null, null);

            days[0] = firstWeek;
            days[1] = secondWeek;
            days[2] = thirdWeek;
            days[3] = fourthWeek;
            if(lengthOfMonth == 28) {
                fifthWeek = new CalendarDays("24", "25", "26", "27", "28", null, null);
                sixthWeek = new CalendarDays(null, null, null, null, null, null, null);
                days[4] = fifthWeek;
            } else if(lengthOfMonth == 29) {
                fifthWeek = new CalendarDays("24", "25", "26", "27", "28", "29", null);
                sixthWeek = new CalendarDays(null, null, null, null, null, null, null);
                days[4] = fifthWeek;
            } else if(lengthOfMonth == 30) {
                fifthWeek = new CalendarDays("24", "25", "26", "27", "28", "29", "30");
                sixthWeek = new CalendarDays(null, null, null, null, null, null, null);
                days[4] = fifthWeek;
            } else {
                days[4] = fifthWeek;
                days[5] = sixthWeek;
            }
        }else if(day.equals("SUNDAY")) {
            firstWeek = new CalendarDays(null, null, null, null, null, null, "1");
            secondWeek = new CalendarDays("2", "3", "4", "5", "6", "7", "8");
            thirdWeek = new CalendarDays("9", "10", "11", "12", "13", "14", "15");
            fourthWeek = new CalendarDays("16", "17", "18", "19", "20", "21", "22");
            fifthWeek = new CalendarDays("23", "24", "25", "26", "27", "28", "29");
            sixthWeek = new CalendarDays("30", "31", null, null, null, null, null);

            days[0] = firstWeek;
            days[1] = secondWeek;
            days[2] = thirdWeek;
            days[3] = fourthWeek;
            if(lengthOfMonth == 28) {
                fifthWeek = new CalendarDays("23", "24", "25", "26", "27", "28", null);
                sixthWeek = new CalendarDays(null, null, null, null, null, null, null);
                days[4] = fifthWeek;
            } else if(lengthOfMonth == 29) {
                fifthWeek = new CalendarDays("23", "24", "25", "26", "27", "28", "29");
                sixthWeek = new CalendarDays(null, null, null, null, null, null, null);
                days[4] = fifthWeek;
            } else if(lengthOfMonth == 30) {
                fifthWeek = new CalendarDays("23", "24", "25", "26", "27", "28", "29");
                sixthWeek = new CalendarDays("30", null, null, null, null, null, null);
                days[4] = fifthWeek;
                days[5] = sixthWeek;
            } else {
                days[4] = fifthWeek;
                days[5] = sixthWeek;
            }
        } else {
            System.out.println("SOMETHING WENT WRONG");
        }
        table.getItems().clear();
        table.getItems().addAll(firstWeek, secondWeek, thirdWeek, fourthWeek, fifthWeek, sixthWeek);

    } //Dont need the []
}
