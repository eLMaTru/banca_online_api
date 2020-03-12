package com.bancaonline.api.util;

public interface ValidationPattern {

    /**
     * Valid patterns
     */
    String UUID_PATTERN = "^[0-9a-fA-F]{8}[0-9a-fA-F]{4}[0-9a-fA-F]{4}[0-9a-fA-F]{4}[0-9a-fA-F]{12}$";
    String ONLY_NUMBER_PATTERN = "\\d+";
    String ONLY_LETTERS_PATTERN = "[A-Za-z]*";
}