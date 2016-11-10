package com.tenXen.client.common;

import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

/**
 * Created by wt on 2016/11/10.
 */
public class ChatTabBox {

    private Tab tab;
    private VBox vBox;

    public ChatTabBox(Tab tab, VBox vBox) {
        this.tab = tab;
        this.vBox = vBox;
    }

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }
}
