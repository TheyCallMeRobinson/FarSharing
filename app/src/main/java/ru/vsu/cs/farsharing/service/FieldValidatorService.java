package ru.vsu.cs.farsharing.service;

import android.graphics.Color;
import android.widget.EditText;

import java.util.List;

public class FieldValidatorService {
    public static boolean checkFieldMatchesRegex(EditText field, String regex) {
        return field.getText().toString().matches(regex);
    }

    public static boolean checkFieldsMatch(EditText first, EditText second) {
        return first.getText().toString().equals(second.getText().toString());
    }

    public static void showWrongInputFields(List<EditText> wrongInputFields) {
        for(EditText field : wrongInputFields) {
            field.setError("Неверное значение");
        }
    }
}
