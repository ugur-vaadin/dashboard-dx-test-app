package com.example.application.services;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import org.json.JSONArray;

import java.util.List;

public class DataPersistence {

    @ClientCallable
    public static void storeItemsInDB(JSONArray items) {
        // NO-OP
    }

    public static void storeItemsInDB(List<SerializableDashboardItem> items) {
        // NO-OP
    }

    @ClientCallable
    public static void updateItemInDB(SerializableDashboardItem item) {
        // NO-OP
    }

    public static int getItemId(Component item) {
        return -1;
    }
}
