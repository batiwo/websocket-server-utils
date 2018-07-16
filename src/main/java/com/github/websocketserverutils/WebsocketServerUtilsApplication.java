package com.github.websocketserverutils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebsocketServerUtilsApplication extends Application {

    private static String[] _args;
    private ConfigurableApplicationContext _context;
    private Parent _root;

    public static void main(String[] args) {
        _args = args;
        launch(WebsocketServerUtilsApplication.class, args);
    }

    @Override
    public void init() throws Exception {
        _context = SpringApplication.run(WebsocketServerUtilsApplication.class, _args);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/app.fxml"));
        fxmlLoader.setControllerFactory(_context::getBean);
        _root = fxmlLoader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Websocket Server Utils");
        Scene scene = new Scene(_root);
        scene.getStylesheets().add("/ui/app.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        SpringApplication.exit(_context);
    }
}
