package com.tenXen.client.util;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EventUtil {
	public static void setCommonEvent(final Stage stage, Parent root, final Point point) {
		root.setOnMousePressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				MouseEvent e = (MouseEvent) event;

				point.setX(stage.getX() - e.getScreenX());
				point.setY(stage.getY() - e.getScreenY());
			}
		});

		root.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				MouseEvent e = (MouseEvent) event;
				point.setX(stage.getX() - e.getScreenX());
				point.setY(stage.getY() - e.getScreenY());
			}

		});
		root.setOnMouseReleased(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if (stage.getY() < 0) {
					stage.setY(0);
				}
			}

		});
		root.setOnMouseDragged(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				MouseEvent e = (MouseEvent) event;
				if (stage.isFullScreen()) {
					return;
				}

				final double x = (e.getScreenX() + point.getX());
				final double y = (e.getScreenY() + point.getY());

				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						stage.setX(x);
						stage.setY(y);
						if (stage.getY() < 0) {
							stage.setY(0);
						}

					}
				});
			}

		});
	}
}
