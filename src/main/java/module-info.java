module org.openjfx {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires poi;
    requires poi.ooxml;
    requires java.desktop;
    requires poi.ooxml.schemas;
    requires java.mail;
    requires quartz;
    requires commons.email;
    requires org.jsoup;
    opens org.openjfx to javafx.fxml;
    opens Excel to javafx.base;
    exports org.openjfx;
}