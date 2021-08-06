package org.openjfx;

import Excel.AddToTable;
import Excel.ReadFile;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.StringReader;
import java.nio.charset.Charset;
import java.text.CharacterIterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewWindow {
    TextField dateFromField;

    private static double xOffset = 0;
    private static double yOffset = 0;

    public void monthButtonActionPerformed(ActionEvent e, Label label, Button prevYearButton, Button curYearButton, Button nextYearButton, TableView table) {
        List<String> monthNames = monthNameList();
        List<Integer> monthNumbers = monthNumList();
        String newMonthName;
        Button button = (Button) e.getSource();
        String curMonth = label.getText();
        int index = monthNames.indexOf(curMonth);
        int curMonthNum = monthNumbers.get(index);

        LocalDate date;

        String prevText = prevYearButton.getText();
        String curText = curYearButton.getText();
        String nextText = nextYearButton.getText();
        int prev = Integer.parseInt(prevText);
        int cur = Integer.parseInt(curText);
        int next = Integer.parseInt(nextText);

        if(button.getText().equals("Prev")) {
            curMonthNum--;
            try {
                newMonthName = monthNames.get(index - 1);
                label.setText(newMonthName);
                date = LocalDate.of(cur, curMonthNum, 1);
                new newWindowComponents().calendarDays(date, table);
            }catch(IndexOutOfBoundsException ex) {
                String december = monthNames.get(11);
                index = monthNames.indexOf(december);
                newMonthName = monthNames.get(index);
                curMonthNum = monthNumbers.get(index);
                label.setText(newMonthName);
                prev--;
                cur--;
                next--;
                date = LocalDate.of(cur, curMonthNum, 1);
                new newWindowComponents().calendarDays(date, table);
            }
        } else if (button.getText().equals("Next")) {
            curMonthNum++;
            try {
                newMonthName = monthNames.get(index + 1);
                label.setText(newMonthName);
                date = LocalDate.of(cur, curMonthNum, 1);
                new newWindowComponents().calendarDays(date, table);
            }catch(IndexOutOfBoundsException exp) {
                String january = monthNames.get(0);
                index = monthNames.indexOf(january);
                newMonthName = monthNames.get(index);
                curMonthNum = monthNumbers.get(index);
                label.setText(newMonthName);
                prev++;
                cur++;
                next++;
                date = LocalDate.of(cur, curMonthNum, 1);
                new newWindowComponents().calendarDays(date, table);
            }
        }
        String newPrevText = String.valueOf(prev);
        String newCurText = String.valueOf(cur);
        String newNextText = String.valueOf(next);

        prevYearButton.setText(newPrevText);
        curYearButton.setText(newCurText);
        nextYearButton.setText(newNextText);
    }
    public void yearButtonActionPerformed(ActionEvent e, Label label, Button prevYearButton, Button curYearButton, Button nextYearButton, TableView table) {
        LocalDate date;
        List<String> monthNameList = monthNameList();
        List<Integer> monthNumList = monthNumList();
        String curMonth = label.getText();

        int index = monthNameList.indexOf(curMonth);
        int curMonthNum = monthNumList.get(index);

        String prevText = prevYearButton.getText();
        String curText = curYearButton.getText();
        String nextText = nextYearButton.getText();

        int prev = Integer.parseInt(prevText);
        int cur = Integer.parseInt(curText);
        int next = Integer.parseInt(nextText);

        if(e.getSource().equals(prevYearButton)) {
            prev--;
            cur--;
            next--;
            date = LocalDate.of(cur, curMonthNum, 1);
            new newWindowComponents().calendarDays(date, table);
        }
        if(e.getSource().equals(nextYearButton)) {
            prev++;
            cur++;
            next++;
            date = LocalDate.of(cur, curMonthNum, 1);
            new newWindowComponents().calendarDays(date, table);
        }

        String newPrevText = String.valueOf(prev);
        String newCurText = String.valueOf(cur);
        String newNextText = String.valueOf(next);

        prevYearButton.setText(newPrevText);
        curYearButton.setText(newCurText);
        nextYearButton.setText(newNextText);
    }
    public String calendarMouseClicked(MouseEvent e, TableView table, Label month, Button year, Stage stage) {

        try {
            TableColumn selectedColumn = table.getFocusModel().getFocusedCell().getTableColumn();
            String dayString = (String) selectedColumn.getCellData(table.getSelectionModel().getSelectedItem());
            String dateMonthString = month.getText();
            String dateYearString = year.getText();

            List<String> monthNames = monthNameList();
            List<Integer> monthNumbers = monthNumList();

            int dateYear = Integer.parseInt(dateYearString);
            int dateMonth = monthNumbers.get(monthNames.indexOf(dateMonthString));
            int dateDay = Integer.parseInt(dayString);


            LocalDate date = LocalDate.of(dateYear, dateMonth, dateDay);
            date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

            stage.close();
            return date.toString();
        }catch(NumberFormatException ex) {
            return null;
        }
    }

    // -- TEXTAREA WINDOW DRAG --
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

    // -- TEXTAREA WINDOW BUTTONS --
    public void windowButtonActions(ActionEvent e, Stage stage) {
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
    public void doneButtonAction(ActionEvent e, TextArea textArea, TableView table, Stage stage, TextField field) {

        StringBuilder sb = new StringBuilder();
        List<String> restaurantList = restaurantList();
        List<String> sortedRestaurantList = restaurantList.stream().sorted().collect(Collectors.toList());
        List<String> fetchedList = new ArrayList<>();
        String values = textArea.getText();
        String[] split = values.split("\n");
        int counter = 0;
        HashMap<String, Integer> hashMap = new HashMap();
        HashMap<String, Integer> newValues = new HashMap();

        for (String val : split) {
            fetchedList.add(val);
        }

        for (String rest : sortedRestaurantList) {
            int occurrences = Collections.frequency(fetchedList, rest);
            hashMap.put(rest, occurrences);
        }

        ReadFile file = new ReadFile();
        String path = field.getText();
        String curWeekValues = file.getCurWeekValues(path);

        String[] curSplit = curWeekValues.split(";");

        for(String curVal : curSplit) {
            String[] curSplit2 = curVal.split(",");

            String rest = curSplit2[0];
            String valueString = curSplit2[2];
            int value = Integer.parseInt(valueString);

            newValues.put(rest, value);
        }

        StringBuilder sb1 = new StringBuilder();
        for (String rest : newValues.keySet()) {
            sb1.append(rest).append(",").append(newValues.get(rest)).append(",").append(hashMap.get(rest)
            ).append(",").append("temp").append(",").append("temp").append(";");

        }
        String allValues = sb1.toString();




        AddToTable add = new AddToTable();
        add.addToNewTable(table, allValues);



        stage.close();
    }

    // -- TEXTAREA TEXT INPUT --
    public void textAreaInput(TextArea textArea) {
        List<String> restaurantList = restaurantList();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();

        try {
            String text = textArea.getText();
            String newText = "";

            for(String rest : restaurantList) {

                String replacement = "¤" + rest + "¤";
                text = text.replaceAll(rest, replacement);
            }

            String[] split = text.split("¤");
            for(String rest : restaurantList) {
                for(String value : split) {
                    if(rest.equals(value)) {
                        sb1.append(value).append("\n");
                    }
                }
            }


        }catch (StackOverflowError e) {
            e.printStackTrace();
        }
        textArea.setText(sb1.toString());
    }


    public NewWindow() {

    }

    public void initDateWindow(Point2D point, TextField field) {
        newWindowComponents init = new newWindowComponents();
        init.setTable(field);


        // -- PANES --
        HBox dateHeader = new HBox();
        dateHeader.setPrefSize(240, 30);
        dateHeader.setMaxWidth(240);
        dateHeader.setAlignment(Pos.CENTER);
        dateHeader.setId("date-header");
        dateHeader.getChildren().addAll(init.getPrevMonthButton(), init.getMonthLabel(), init.getNextMonthButton());

        HBox dateBody = new HBox();
        dateBody.setPrefSize(240, 170);
        dateBody.setMaxWidth(240);
        dateBody.setAlignment(Pos.CENTER);
        dateBody.setId("date-body");
        dateBody.getChildren().add(init.getCalendar());

        HBox dateFooter = new HBox();
        dateFooter.setPrefSize(240, 40);
        dateFooter.setMaxWidth(240);
        dateFooter.setAlignment(Pos.CENTER);
        dateFooter.setId("date-footer");
        dateFooter.getChildren().addAll(init.getPrevYearButton(), init.getCurYearButton(), init.getNextYearButton());




        GridPane main = new GridPane();
        ColumnConstraints mainColumn = new ColumnConstraints(230);
        mainColumn.setPrefWidth(270);

        RowConstraints row0 = new RowConstraints(30);
        row0.setPrefHeight(30);
        RowConstraints row1 = new RowConstraints(180);
        row1.setPrefHeight(180);
        RowConstraints row2 = new RowConstraints(40);
        row2.setPrefHeight(40);

        main.setAlignment(Pos.CENTER);
        main.setId("main-window");
        main.getRowConstraints().addAll(row0, row1, row2);

        main.add(dateHeader, 0, 0);
        main.add(dateBody, 0, 1);
        main.add(dateFooter, 0 , 2);

        Scene dateWindow = new Scene(main);
        dateWindow.getStylesheets().add("newWindowStyle.css");

        Stage stage = new Stage();
        stage.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if(oldVal) {
                    stage.close();
                }
            }
        });
        stage.setX(point.getX() - 250);
        stage.setY(point.getY() - 250);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);





        stage.setScene(dateWindow);
        stage.getScene().setFill(null);
        stage.getScene().getRoot().setId("window");
        stage.show();
        init.setStage(stage);
    }

    public void initTextAreaWindow(Point2D point, TableView table, TextField field) {
        newWindowComponents init = new newWindowComponents();
        init.setNewFileTable(table);
        init.setImportField(field);

        EventHandler<MouseEvent> screenDrag = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dragWindowEvent(mouseEvent, init.getStage());
            }
        };

        HBox topWindowMenu = new HBox();
        topWindowMenu.setPrefSize(550, 30);
        topWindowMenu.setId("top-window-menu");
        topWindowMenu.setAlignment(Pos.CENTER_RIGHT);
        topWindowMenu.getChildren().addAll(init.getMinimizeButton(), init.getMaximizeButton(), init.getCloseButton());
        topWindowMenu.addEventFilter(MouseEvent.MOUSE_PRESSED, screenDrag);
        topWindowMenu.addEventFilter(MouseEvent.MOUSE_DRAGGED, screenDrag);
        HBox.setHgrow(topWindowMenu, Priority.ALWAYS);

        HBox doneButtonPane = new HBox();
        doneButtonPane.setPrefSize(550, 30);
        doneButtonPane.setMaxHeight(30);
        doneButtonPane.setId("done-button-pane");
        doneButtonPane.setAlignment(Pos.BOTTOM_CENTER);
        doneButtonPane.getChildren().addAll(init.getDoneButton());
        HBox.setHgrow(doneButtonPane, Priority.ALWAYS);

        VBox main = new VBox();
        main.setId("main-window");
        main.getChildren().addAll(topWindowMenu, init.getTextArea(), doneButtonPane);
        VBox.setVgrow(main, Priority.ALWAYS);

        Scene scene = new Scene(main);
        scene.getStylesheets().add("textAreaWindowStyle.css");
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setY(point.getY() - 300);
        stage.setX(point.getX());
        stage.setScene(scene);
        ResizeHelper.addResizeListener(stage);
        init.setStage(stage);
        stage.show();
    }


    public List<String> monthNameList() {
        List<String> months = new ArrayList<>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

        return months;
    }
    public List<Integer> monthNumList() {
        List<Integer> months = new ArrayList<>();
        months.add(1);
        months.add(2);
        months.add(3);
        months.add(4);
        months.add(5);
        months.add(6);
        months.add(7);
        months.add(8);
        months.add(9);
        months.add(10);
        months.add(11);
        months.add(12);

        return months;
    }
    public List<String> restaurantList() {
        List<String> list = new ArrayList<>();

        list.add("Alingsås");
        list.add("Boden");
        list.add("Bollnäs");
        list.add("Borlänge 1 - Åkrerondellen");
        list.add("Borlänge 2 - Kupolen");
        list.add("Borås 1 - Arena");
        list.add("Borås 2 - Brämhult");
        list.add("Enköping");
        list.add("Eskilstuna 1 - Tuna Park");
        list.add("Eskilstuna 2 - Västerleden");
        list.add("Event 1");
        list.add("Falkenberg");
        list.add("Falun");
        list.add("Gävle 1 - Norr");
        list.add("Gävle 2 - Hemsta");
        list.add("Gävle 3 - Hemlingby vid E4");
        list.add("Göteborg 1 - Partille");
        list.add("Göteborg 2 - Ullevi");
        list.add("Göteborg 3 - Torpavallen");
        list.add("Göteborg 4 - Bäckebol");
        list.add("Göteborg 5 - Mölndal Galleria");
        list.add("Göteborg 6 - Sisjön");
        list.add("Göteborg 7 - Lindholmen");
        list.add("Göteborg 8 - Nordstan");
        list.add("Halmstad 1 - Eurostop");
        list.add("Halmstad 2 - Flygstaden");
        list.add("Haparanda");
        list.add("Helsingborg 1 - Väla");
        list.add("Helsingborg 2 - Hyllinge");
        list.add("Helsingborg 3 - Södra City");
        list.add("Hudiksvall");
        list.add("Härnösand");
        list.add("Jönköping 1 - Jordbrovägen");
        list.add("Jönköping 2 - Herkulesvägen");
        list.add("Jönköping 3 - Hästhovsvägen");
        list.add("Kalmar 1 - Gamla Polishuset");
        list.add("Kalmar 2 - Guldfågeln Arena");
        list.add("Karlskoga");
        list.add("Karlskrona");
        list.add("Karlstad 1 - Våxnäs");
        list.add("Karlstad 2 - Bergvik");
        list.add("Kristianstad");
        list.add("Kungsbacka");
        list.add("Köping");
        list.add("Landskrona");
        list.add("Linköping 1 - Kallerstad");
        list.add("Linköping 2 - Tornby");
        list.add("Ljungby");
        list.add("Luleå 1 - Midgårds");
        list.add("Luleå 2 - City");
        list.add("Luleå 3 - Robertsvik");
        list.add("Luleå 4 - Storheden");
        list.add("Lund 1 - Kung Oscars väg");
        list.add("Lund 2 - Nova");
        list.add("Löddeköpinge");
        list.add("Malmö 1 - City Stortorget");
        list.add("Malmö 2 - Ystadvägen");
        list.add("Malmö 3 - Emporia");
        list.add("Malmö 4 - Lockarp");
        list.add("Malmö 5 - Svågertorp");
        list.add("Mjölby");
        list.add("Mora");
        list.add("Märsta");
        list.add("Norrköping 1 - Resecentrum");
        list.add("Norrköping 2 - Ingelstarondellen");
        list.add("Norrtälje");
        list.add("Nyköping 1 - Gumsbacken");
        list.add("Nyköping 2 - Påljungshage");
        list.add("Piteå");
        list.add("Ronneby");
        list.add("Skellefteå 1 - Xet");
        list.add("Skellefteå 2 - City");
        list.add("Skellefteå 3 - Solbacken");
        list.add("Skövde");
        list.add("Sthlm 1 - Barkarby");
        list.add("Sthlm 10 - Länna");
        list.add("Sthlm 11 - Värmdö");
        list.add("Sthlm 12 - Bredden");
        list.add("Sthlm 13 - Slagsta");
        list.add("Sthlm 14 - Torsplan");
        list.add("Sthlm 15 - Roslags-Näsby");
        list.add("Sthlm 16 - Lidingö");
        list.add("Sthlm 17 - Mall of Scandinavia");
        list.add("Sthlm 18 - Häggvik");
        list.add("Sthlm 19 - Rotebro");
        list.add("Sthlm 2 - Solna");
        list.add("Sthlm 20 - Hötorget");
        list.add("Sthlm 21 - Medborgarplatsen");
        list.add("Sthlm 22 - Täby Centrum");
        list.add("Sthlm 23 - Fridhemsplan");
        list.add("Sthlm 24 - Tumba");
        list.add("Sthlm 25 - Hallunda");
        list.add("Sthlm 26 - Åkersberga");
        list.add("Sthlm 27 - Sköndal");
        list.add("Sthlm 3 - Vårby");
        list.add("Sthlm 4 - City Hamngatan");
        list.add("Sthlm 5 - Hammarby");
        list.add("Sthlm 6 - City Vasagatan");
        list.add("Sthlm 7 - Kista");
        list.add("Sthlm 8 - Bromma");
        list.add("Sthlm 9 - Arninge");
        list.add("Strängnäs");
        list.add("Strömstad 1 - Nordby");
        list.add("Strömstad 2 - E6");
        list.add("Sundsvall 1 - E4");
        list.add("Sundsvall 2 - Birsta");
        list.add("Södertälje");
        list.add("Trollhättan 1 - City");
        list.add("Trollhättan 2 - Överby");
        list.add("Uddevalla");
        list.add("Umeå 1 - E4");
        list.add("Umeå 2 - City");
        list.add("Umeå 3 - Söderslätt");
        list.add("Uppsala 1 - Kvarngärdet");
        list.add("Uppsala 2 - Boländerna");
        list.add("Uppsala 3 - City");
        list.add("Uppsala 4 - Brillinge");
        list.add("Uppsala 5 - Gnista");
        list.add("Varberg");
        list.add("Vetlanda");
        list.add("Visby");
        list.add("Värnamo");
        list.add("Västervik");
        list.add("Västerås 1 - Hälla");
        list.add("Västerås 2 - Erikslund");
        list.add("Västerås 3 - Folkparksmotet");
        list.add("Växjö 1 - Handelsplats I11");
        list.add("Växjö 2 - Teleborgsvägen");
        list.add("Ystad - Dragongatan");
        list.add("Åre");
        list.add("Örebro 1 - Eurostop");
        list.add("Örebro 2 - Marieberg");
        list.add("Örebro 3 - Behrn Arena");
        list.add("Örebro 4 - Lillån");
        list.add("Örnsköldsvik");
        list.add("Östersund");

        return list;
    }



}
