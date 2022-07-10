package com.example.oopmain.controller;

import com.example.oopmain.HelloApplication;
import com.example.oopmain.constant.NameOutputDataConstant;
import com.example.oopmain.rdf.RDFHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowController implements Initializable {

    @FXML
    private TextArea textAreaOutputQuery;

    @FXML
    private ChoiceBox<String> choiceBoxTopics;

    private static String topic;


    public void ChangeQueryButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Query.fxml"));
        Parent root = loader.load();

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxTopics.getItems().addAll(NameOutputDataConstant.namesAndTopics.keySet());
        choiceBoxTopics.setOnAction(this::getTopic);
    }

    private void getTopic(ActionEvent event) {
         topic = choiceBoxTopics.getValue();
    }

    public void showFile(){
        textAreaOutputQuery.setText(RDFHandler.LoadFile(topic));
    }

//    public void
}
