package com.example.oopmain.controller;

import com.example.oopmain.HelloApplication;
import com.example.oopmain.rdf.RDFHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QueryController implements Initializable {
    @FXML
    private TextArea textAreaQuery;
    @FXML
    private ChoiceBox<String> checkBoxQuery;
    @FXML
    private ChoiceBox<String> checkBoxFormatFile;

    @FXML
    private TextField textFieldFileName;

    @FXML
    private Label labelQuery;
    private final String[] formatFiles = {"Turtle","N-Triples","RDF/XML","JSON"};
    private final String[] queryAdds = {"Wikidata","Dbpedia"};

    private static String queryAdd;

    private static String formatFile;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkBoxQuery.getItems().addAll(queryAdds);
        checkBoxFormatFile.getItems().addAll(formatFiles);
        checkBoxQuery.setOnAction(this::getText1);
        checkBoxFormatFile.setOnAction(this::getText2);
    }
    public void getText1(ActionEvent event){
        queryAdd = checkBoxQuery.getValue();

    }
    public void getText2(ActionEvent event){
        formatFile = checkBoxFormatFile.getValue();
    }
    public void SubmitQuery(ActionEvent actionEvent){
        String query = textAreaQuery.getText();
        String fileName = textFieldFileName.getText();
        if(queryAdd != null && formatFile != null) {
            String res = RDFHandler.QueryAndSave(query, fileName, queryAdd, formatFile);
            labelQuery.setText(res);
        }
        else{
            labelQuery.setText("Can't execute the query");
        }
    }
    public void ChangeShowButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Show.fxml"));
        Parent root = loader.load();
        ShowController showController = loader.getController();
        showController.setTextArea(textAreaQuery.getText());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
