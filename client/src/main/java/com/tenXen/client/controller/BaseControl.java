package com.tenXen.client.controller;

import com.tenXen.client.util.LayoutLoader;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseControl {

    protected final Logger Log = LoggerFactory.getLogger(getClass());

    protected Point point = new Point();

    protected abstract Stage getStage();

    protected abstract Parent getRoot();

    protected abstract ImageView getMinImage();

    protected abstract ImageView getCloseImage();

//    protected abstract void before();

//    protected abstract void handle();

    public void init() {
        Scene scene = new Scene(getRoot());
        scene.setFill(Color.TRANSPARENT);
        getStage().setScene(scene);
        getStage().getIcons().add(new javafx.scene.image.Image(LayoutLoader.STAG_IMAGE));
        getStage().initStyle(StageStyle.TRANSPARENT);
        getRoot().setOnMousePressed(event -> {
            MouseEvent e = event;

            point.setX(getStage().getX() - e.getScreenX());
            point.setY(getStage().getY() - e.getScreenY());
        });

        getRoot().setOnMousePressed(event -> {
            MouseEvent e = event;
            point.setX(getStage().getX() - e.getScreenX());
            point.setY(getStage().getY() - e.getScreenY());
        });
        getRoot().setOnMouseReleased(event -> {
            if (getStage().getY() < 0) {
                getStage().setY(0);
            }
        });
        getRoot().setOnMouseDragged(event -> {
            MouseEvent e = event;
            if (getStage().isFullScreen()) {
                return;
            }

            final double x = (e.getScreenX() + point.getX());
            final double y = (e.getScreenY() + point.getY());

            Platform.runLater(() -> {
                getStage().setX(x);
                getStage().setY(y);
                if (getStage().getY() < 0) {
                    getStage().setY(0);
                }

            });
        });

        ImageView minImage = getMinImage();

        minImage.setOnMouseClicked(event -> onMin());

        minImage.setOnMouseEntered(event -> {
//                minImage.setImage(ResourceContainer.getMin_1());
        });

        minImage.setOnMouseExited(event -> {
//                minImage.setImage(ResourceContainer.getMin());
        });

        ImageView closeImage = getCloseImage();

        closeImage.setOnMouseClicked(event -> onClose());

        closeImage.setOnMouseEntered(event -> {
//                closeImage.setImage(ResourceContainer.getClose_1());
        });

        closeImage.setOnMouseExited(event -> {
//                closeImage.setImage(ResourceContainer.getClose());
        });
    }

    protected void show() {
        Platform.runLater(() -> {
            if (getStage() != null) {
                getStage().show();
                getStage().toFront();
            } else {
                System.exit(0);
            }
        });
    }

    protected void onMin() {
        Platform.runLater(() -> getStage().setIconified(true));
    }

    protected abstract void onClose();

}
