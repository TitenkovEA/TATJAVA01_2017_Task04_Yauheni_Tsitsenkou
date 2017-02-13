package com.epam.catalog.dao.util.dbresource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Yauheni_Tsitsenkou on 2/13/2017.
 */
public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("db", Locale.ENGLISH);

    private DBResourceManager() {

    }

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
