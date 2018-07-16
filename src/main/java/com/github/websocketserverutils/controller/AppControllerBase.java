package com.github.websocketserverutils.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public abstract class AppControllerBase {

    @FXML
    protected TextField _websocketField;

    @FXML
    protected Label _streamUrlLabel;

    @FXML
    protected TextField _streamUrlField;

    @FXML
    protected TextArea _contentField;

    @FXML
    protected Button _notifyButton;

    @FXML
    protected abstract void onNotify();

}
