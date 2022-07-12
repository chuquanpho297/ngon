package com.example.oopmain.controller;

import com.example.oopmain.HelloApplication;
import com.example.oopmain.constant.FormatFileConstant;
import com.example.oopmain.constant.QueryAddsConstant;
import com.example.oopmain.rdf.IQueryHandler;
import com.example.oopmain.rdf.ISaveHandler;
import com.example.oopmain.rdf.impl.LoadHandler;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
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
    @FXML
    private Label labelShowDirectory;

    private static String formatFile;
    private static String queryAdd;

    private static String fileDir;

    private DirectoryChooser directoryChooser;
    public QueryController(IQueryHandler queryHandler, ISaveHandler saveHandler) {
        this.queryHandler = queryHandler;
        this.saveHandler = saveHandler;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxQuery.getItems().addAll(QueryAddsConstant.QUERYADDS);
        choiceBoxQuery.setOnAction(e->{
            queryAdd = choiceBoxQuery.getValue();
            labelQuery.setText("");
            textAreaQuery.setText("");
        });

        choiceBoxFormatFile.getItems().addAll(FormatFileConstant.FORMAT_FILES);
        choiceBoxFormatFile.setOnAction(e->{
            formatFile = choiceBoxFormatFile.getValue();
            labelQuery.setText("");
            textAreaQuery.setText("");
        });

        directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directories");
        directoryChooser.setInitialDirectory(null);
    }
    public void SubmitQuery(ActionEvent actionEvent){
        labelQuery.setText("");
        String query = textAreaQuery.getText();
        String fileName = textFieldFileName.getText();
        if(queryAdd != null && formatFile != null && fileDir != null) {
            String data = null;
            try {
                data = queryHandler.QuerySqarqlSelect(query, queryAdd, formatFile);
            } catch (Exception e) {
                labelQuery.setText(data);
            }
            String res = saveHandler.SaveFileToDir(data, formatFile, fileName, fileDir);
            labelQuery.setText(res);
        }
        else{
            labelQuery.setText("Can't execute the query");
        }
    }
    public void ChangeShowButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Show.fxml"));
        ShowController showController = new ShowController(new QueryHandler(),new SaveHandler(),new LoadHandler());
        loader.setController(showController);
        Parent root = loader.load();
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void chooseDirectoryButton(){
        File dir = directoryChooser.showDialog(null);
        if(dir != null){
            labelShowDirectory.setText(dir.getAbsolutePath());
            fileDir = dir.getAbsolutePath();
        }
        else labelShowDirectory.setText(null);
    }
}
