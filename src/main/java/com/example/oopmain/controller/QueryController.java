package com.example.oopmain.controller;

import javax.inject.Inject;
import com.example.oopmain.HelloApplication;
import com.example.oopmain.constant.FormatFileConstant;
import com.example.oopmain.constant.QueryAddsConstant;
import com.example.oopmain.rdf.IQueryHandler;
import com.example.oopmain.rdf.ISaveHandler;
import com.example.oopmain.rdf.impl.QueryHandler;
import com.example.oopmain.rdf.impl.SaveHandler;
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

    private IQueryHandler queryHandler;
    private ISaveHandler saveHandler;
    @FXML
    private TextArea textAreaQuery;
    @FXML
    private ChoiceBox<String> choiceBoxQuery;
    @FXML
    private ChoiceBox<String> choiceBoxFormatFile;

    @FXML
    private TextField textFieldFileName;

    @FXML
    private Label labelQuery;
    private static String formatFile;
    private static String queryAdd;

    public QueryController(IQueryHandler queryHandler, ISaveHandler saveHandler) {
        this.queryHandler = queryHandler;
        this.saveHandler = saveHandler;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxQuery.getItems().addAll(QueryAddsConstant.QUERYADDS);
        choiceBoxFormatFile.getItems().addAll(FormatFileConstant.FORMAT_FILES);
        choiceBoxQuery.setOnAction(this::getQuery);
        choiceBoxFormatFile.setOnAction(this::getFormatFile);
    }
    private void getQuery(ActionEvent event){
        queryAdd = choiceBoxQuery.getValue();

    }
    private void getFormatFile(ActionEvent event){
        formatFile = choiceBoxFormatFile.getValue();
    }
    public void SubmitQuery(ActionEvent actionEvent){
        String query = textAreaQuery.getText();
        String fileName = textFieldFileName.getText();
        if(queryAdd != null && formatFile != null) {
                String data = queryHandler.QuerySqarql(query,queryAdd,formatFile);
                String res = saveHandler.SaveFileToDir(data,formatFile,fileName);
                labelQuery.setText(res);
        }
        else{
            labelQuery.setText("Can't execute the query");
        }
    }
    public void ChangeShowButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Show.fxml"));
        ShowController showController = new ShowController(new QueryHandler(),new SaveHandler());
        loader.setController(showController);
        Parent root = loader.load();
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
