package com.example.notesfordanil;

import com.example.notesfordanil.dao.Note;
import com.example.notesfordanil.dao.NoteDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloController {
  private NoteDAO noteDAO = new NoteDAO();
  private List<Note> notes ;
  @FXML
  private TextArea textArea ;
  @FXML
  private Label nc ;
  @FXML
  private VBox noteVBox ;
  @FXML
  private Label tema ;

  private int counterup;
    private int counter;
    private Map<String , Button> buttonMap = new HashMap<>();

    @FXML
   public void newNote (){
        HBox hBox = new HBox();
        TextField textField = new TextField();
        Button button = new Button("Save");
        hBox.getChildren().addAll(textField , button);
        noteVBox.getChildren().add(hBox);
        button.setOnAction(new EventHandler<ActionEvent>() {
            String name ;
            @Override
            public void handle(ActionEvent actionEvent) {
                name = textField.getText();
                noteVBox.getChildren().remove(hBox);
                noteDAO.newNote(name);
                Button bt2 = new Button(name);
                buttonMap.put(bt2.getText(), bt2) ;
                bt2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        textArea.setText(noteDAO.getText(bt2.getText()));
                        tema.setText(bt2.getText());
                    }
                });
                noteVBox.getChildren().add(bt2);
            }
        });
   }
   public void delete (){
    textArea.setText("");
    noteDAO.save(tema.getText() , textArea.getText());
   }
   @FXML
   public void save (){
    noteDAO.save(tema.getText(), textArea.getText());
   }
   public  void update (){
        if(counterup==0) {
            notes = noteDAO.getAll();
            for (Note note : notes) {
                Button button  = new Button(note.getNotetopic());
                buttonMap.put(button.getText(),button);
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        textArea.setText(noteDAO.getText(button.getText()));
                        tema.setText(note.getNotetopic());
                    }
                });
                noteVBox.getChildren().add(button);

            }
            counterup++;
        }
   }
   public void deletenote (){
        noteVBox.getChildren().remove(buttonMap.get(tema.getText()));
        noteDAO.deleteNote(tema.getText());
        tema.setText("");
        textArea.setText("");

   }


}