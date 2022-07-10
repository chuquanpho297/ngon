package com.example.oopmain.controller;

import com.example.oopmain.HelloApplication;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowController {

    @FXML
    private TextArea textAreaOutputQuery;

    public void ChangeQueryButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Query.fxml"));
        Parent root = loader.load();

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));

    }
    public  void setTextArea(String text){
        textAreaOutputQuery.setText(text);
    }

//    public void
}
