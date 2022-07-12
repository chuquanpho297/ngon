package com.example.oopmain.controller;

import com.example.oopmain.HelloApplication;
import com.example.oopmain.constant.*;
import com.example.oopmain.rdf.ILoadHandler;
import com.example.oopmain.rdf.IQueryHandler;
import com.example.oopmain.rdf.ISaveHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

//import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShowController implements Initializable {

    private IQueryHandler queryHandler;
    private ISaveHandler saveHandler;

    private ILoadHandler loadHandler;
    @FXML
    private TextArea textAreaOutputQuery;
    @FXML
    private ChoiceBox<String> choiceBoxFormatsShow;
    @FXML
    private ChoiceBox<String> choiceBoxQueryLinkShow;
    @FXML
    private ChoiceBox<String> choiceBoxTopicsShow;
    @FXML
    private Button buttonChooseFile;
    @FXML
    private Label labelShowInfo;
    @FXML
    private Label labelShowDirFile;
    private static String format;
    private static String queryLink;
    private List<String> lstFile;
    private static String topic;
    private static String fileDir;

    public ShowController(IQueryHandler queryHandler, ISaveHandler saveHandler,ILoadHandler loadHandler) {
        this.queryHandler = queryHandler;
        this.saveHandler = saveHandler;
        this.loadHandler = loadHandler;
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

        lstFile = new ArrayList<>();
        for (String exten : ExtensionConstant.extensionConstant){
            lstFile.add(exten);
        }

        choiceBoxFormatsShow.getItems().addAll(FormatFileConstant.FORMAT_FILES);
        choiceBoxFormatsShow.setOnAction(e->{
            textAreaOutputQuery.setText("");
            labelShowInfo.setText("");
            format = choiceBoxFormatsShow.getValue();
        });

        choiceBoxQueryLinkShow.getItems().addAll(QueryAddsConstant.QUERYADDS);
        choiceBoxQueryLinkShow.setOnAction(e->{
            textAreaOutputQuery.setText("");
            labelShowInfo.setText("");
            labelShowDirFile.setText("");
            fileDir = null;
            queryLink = choiceBoxQueryLinkShow.getValue();
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
        choiceBoxTopicsShow.setOnAction(e->{
            textAreaOutputQuery.setText("");
            topic = choiceBoxTopicsShow.getValue();
            labelShowDirFile.setText("");
            labelShowInfo.setText("");
            fileDir = null;
        });

    }


    public void showFile() {
        labelShowInfo.setText("");
        String data = null;
        if(format!= null){
            if(topic != null && queryLink != null) {
                String query = queryHandler.constructQuery(topic);
                if(fileDir != null) data = queryHandler.QuerySqarqlSelect(query, queryLink, format);
                else data = queryHandler.QuerySqarqlConstruct(query, queryLink, format);
            }
            else if(fileDir != null)
                data = loadHandler.LoadFile(format,fileDir);
            if (data != null) {
                textAreaOutputQuery.setText(data);
                labelShowInfo.setText("Done !!");
            }
            else {
                if(topic == null || queryLink == null) labelShowInfo.setText("Invalid input !!");
                else labelShowInfo.setText("Not found !!");
            }
        }
        else labelShowInfo.setText("Invalid input !!");
    }
    public void chooseFileButton(){
        textAreaOutputQuery.setText("");
        topic = queryLink = null;

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("RDF Files",lstFile));
        File f = fc.showOpenDialog(null);

        if(f!=null) {
            labelShowDirFile.setText(f.getAbsolutePath());
            fileDir = f.getAbsolutePath();
        }
    }
}
