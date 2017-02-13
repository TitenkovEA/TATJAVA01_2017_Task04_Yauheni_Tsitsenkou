package com.epam.catalog.controller.command;

import java.util.Map;

public interface Command {
    String CATEGORY = "category";
    String TITLE = "title";
    String AUTHOR = "author";

    String execute(Map<String, String> request);
}
