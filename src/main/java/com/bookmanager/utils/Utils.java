package com.bookmanager.utils;

import javafx.scene.control.Alert;

public final class Utils {
    public static Alert CreateAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }
}
