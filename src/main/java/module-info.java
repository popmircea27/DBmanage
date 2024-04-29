module com.example.disp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires com.oracle.database.jdbc;
    requires java.desktop;

    //opens com.example.disp to javafx.fxml;
   // exports com.example.disp;
    exports com.example.disp.model;
    opens com.example.disp.model to javafx.fxml;
    //exports com.example.disp.Bill;
   // opens com.example.disp.Bill to javafx.fxml;
    exports com.example.disp.ConnectionDB;
    opens com.example.disp.ConnectionDB to javafx.fxml;
    //exports com.example.disp.Controller;
    //opens com.example.disp.Controller to javafx.fxml;
    exports com.example.disp.Interface;
    opens com.example.disp.Interface to javafx.fxml;

}