package com.example.oopmain;

import com.example.oopmain.controller.QueryController;
import com.example.oopmain.rdf.impl.QueryHandler;
import com.example.oopmain.rdf.impl.SaveHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Query.fxml"));
        QueryController queryController = new QueryController(new QueryHandler(), new SaveHandler());
        fxmlLoader.setController(queryController);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}