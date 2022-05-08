module com.example.notesfordanil {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.jdbc;
    requires org.postgresql.jdbc;


    opens com.example.notesfordanil to javafx.fxml;
    exports com.example.notesfordanil;
}