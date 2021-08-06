package Excel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class AddToTable {

    public void addToTable(TableView table, String values) {
        String[] split = values.split(";");

        ObservableList<CurrentFileValues> data = FXCollections.observableArrayList();

        for(String value : split) {
            CurrentFileValues currentFileValues = setValues(value);
            data.add(currentFileValues);

        }
        table.setItems(data);
    }

    public CurrentFileValues setValues(String values) {
        String[] value = values.split(",");

        int prevWeek = Integer.parseInt(value[1]);
        int curWeek = Integer.parseInt(value[2]);
        int change = prevWeek - curWeek;
        float percent = 0;

        if (change > 0) {
            percent = (change * 100.0f) / prevWeek;
            percent = (float) Math.ceil(percent);
        }
        if (change == 0) {
            percent = 0;
        }
        if (change < 0) {
            percent = (change * 100.0f) / prevWeek;
            percent = (float) Math.ceil(percent);
        }
        if (change < 0 && prevWeek == 0) {
            percent = (change * (-1)) * 100;
            percent = percent * (-1);
        }
        if(change > 0 && curWeek == 0 && prevWeek > 0) {
            percent = ((change * 100.0f) / prevWeek) * change;
        }


        CurrentFileValues currentFileValues = new CurrentFileValues(value[0], prevWeek, curWeek, change, percent);

        return currentFileValues;
    }

    public void addToNewTable(TableView table, String values) {
        String[] split = values.split(";");

        ObservableList<NewFileValues> data = FXCollections.observableArrayList();

        for(String value : split) {
            System.out.println(value);
            NewFileValues newFileValues = setNewValues(value);
            data.add(newFileValues);

        }
        table.setItems(data);
    }

    public NewFileValues setNewValues(String values) {
        String[] value = values.split(",");


        int prevWeek = Integer.parseInt(value[1]);
        int curWeek = Integer.parseInt(value[2]);
        int change = prevWeek - curWeek;
        float percent = 0;

        if (change > 0) {
            percent = (change * 100.0f) / prevWeek;
            percent = (float) Math.ceil(percent);
        }
        if (change == 0) {
            percent = 0;
        }
        if (change < 0) {
            percent = (change * 100.0f) / prevWeek;
            percent = (float) Math.ceil(percent);
        }
        if (change < 0 && prevWeek == 0) {
            percent = (change * (-1)) * 100;
            percent = percent * (-1);
        }
        if(change > 0 && curWeek == 0 && prevWeek > 0) {
            percent = ((change * 100.0f) / prevWeek) * change;
        }


        NewFileValues newFileValues = new NewFileValues(value[0], prevWeek, curWeek, change, percent);

        return newFileValues;
    }



}
