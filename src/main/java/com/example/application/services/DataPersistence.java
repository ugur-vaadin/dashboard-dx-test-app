package com.example.application.services;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import org.json.JSONArray;

import java.util.List;
import java.util.Objects;

public class DataPersistence {

    @ClientCallable
    public static void storeItemsInDB(JSONArray items) {
        Objects.requireNonNull(items);
    }

    public static void storeItemsInDB(List<Component> items) {
        Objects.requireNonNull(items);
    }

    @ClientCallable
    public static void updateItemInDB(int itemId, String title, Integer colspan, Integer rowspan) {
        // NO-OP
    }
}
