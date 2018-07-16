package com.github.websocketserverutils.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AppController extends AppControllerBase implements Initializable {

    @Value("${websocket.endpoint}")
    private String _websocketEndpoint;

    @Value("${websocket.handler}")
    private String _stompHandler;

    private String _streamUrl;
    private StringProperty _content;

    @Autowired
    private SimpMessagingTemplate _template;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _websocketField.setText("ws://localhost:8080" + _websocketEndpoint);
        _websocketField.setEditable(false);

        _content = new SimpleStringProperty();

        _streamUrl = _stompHandler;
        _streamUrlLabel.setText(_stompHandler + "/");
        _streamUrlField.textProperty().addListener((observable, oldValue, newValue) -> {
            String value;
            if (_streamUrlField.getText().isEmpty()) {
                value = _stompHandler;
            } else {
                value = String.format("%s/%s", _stompHandler, _streamUrlField.getText());
            }
            _streamUrl = value.toLowerCase();
        });
        _content.bind(_contentField.textProperty());
    }

    @Override
    public void onNotify() {
        System.out.println(String.format("Sending on %s at %s :\n%s\n---------------------------\n",
                                         _websocketField.getText(),
                                         _streamUrl,
                                         _content.get()));

        _template.convertAndSend(_streamUrl, _content.get());
    }

}
