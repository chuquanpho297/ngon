package com.example.oopmain.controller;

import com.example.oopmain.HelloApplication;
import com.example.oopmain.constant.*;
import com.example.oopmain.rdf.IQueryHandler;
import com.example.oopmain.rdf.ISaveHandler;
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

//import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowController implements Initializable {

    private IQueryHandler queryHandler;
    private ISaveHandler saveHandler;
    @FXML
    private TextArea textAreaOutputQuery;
    @FXML
    private ChoiceBox<String> choiceBoxFormatsShow;
    @FXML
    private ChoiceBox<String> choiceBoxQueryLinkShow;
    @FXML
    private ChoiceBox<String> choiceBoxTopicsShow;


    private static String format;
    private static String queryLink;

    private static String topic;

    public ShowController(IQueryHandler queryHandler, ISaveHandler saveHandler) {
        this.queryHandler = queryHandler;
        this.saveHandler = saveHandler;
    }

    public void ChangeQueryButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Query.fxml"));
        QueryController queryController = new QueryController(queryHandler, saveHandler);
        loader.setController(queryController);
        Parent root = loader.load();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceBoxFormatsShow.getItems().addAll(FormatFileConstant.FORMAT_FILES);
        choiceBoxFormatsShow.setOnAction(this::getFormat);

        choiceBoxQueryLinkShow.getItems().addAll(QueryAddsConstant.QUERYADDS);
        choiceBoxQueryLinkShow.setOnAction(e->{
            queryLink = choiceBoxQueryLinkShow.getValue();
            System.out.println(queryLink.length());
            if (queryLink == null) choiceBoxTopicsShow.getItems().addAll(new String[]{""});
            else if (queryLink.equals("Dbpedia")){

                choiceBoxTopicsShow.getItems().removeAll(TopicsWikiDataConstant.TOPICS);
                choiceBoxTopicsShow.getItems().addAll(TopicsDbpediaConstant.TOPICS);
            }
            else if (queryLink.equals("Wikidata")) {
                choiceBoxTopicsShow.getItems().removeAll(TopicsDbpediaConstant.TOPICS);
                choiceBoxTopicsShow.getItems().addAll(TopicsWikiDataConstant.TOPICS);
            }
        });
        choiceBoxTopicsShow.setOnAction(this::getTopic);

    }

    private void getFormat(ActionEvent event) {
        format = choiceBoxFormatsShow.getValue();
    }
    private void getTopic(ActionEvent event) {
        topic = choiceBoxTopicsShow.getValue();
    }

    public void showFile() {
        String querySelect = queryHandler.constructQuery(topic);
        String data = queryHandler.QuerySqarql(querySelect, queryLink, format);
        if (data != null) textAreaOutputQuery.setText(data);
        else textAreaOutputQuery.setText("Not found !!");
    }
}
