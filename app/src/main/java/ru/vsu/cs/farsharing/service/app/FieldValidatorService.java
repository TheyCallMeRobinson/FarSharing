package ru.vsu.cs.farsharing.service.app;

import android.widget.EditText;

import java.util.List;

public class FieldValidatorService {
    public static boolean checkFieldMatchesRegex(EditText field, String regex) {
        return field.getText().toString().matches(regex);
    }

    public static boolean checkFieldMatchesRegex(EditText field, String regex, String errorToShow) {
        if (field.getText().toString().matches(regex)) {
            return true;
        } else {
            field.setError(errorToShow);
            return false;
        }
    }

    public static boolean checkFieldsMatch(EditText first, EditText second) {
        return first.getText().toString().equals(second.getText().toString());
    }

    public static boolean checkFieldsMatch(EditText first, EditText second, String error) {
        if (first.getText().toString().equals(second.getText().toString())) {
            return true;
        } else {
            first.setError(error);
            second.setError(error);
            return false;
        }
    }

    public static void showWrongInputFields(List<EditText> wrongInputFields) {
        for(EditText field : wrongInputFields) {
            field.setError("Неверное значение");
        }
    }

    public static boolean checkInputLength(EditText input, int length) {
        return input.getText().toString().length() == length;
    }

    public static boolean checkInputLength(EditText field, int length, String errorToShow) {
        if (field.getText().toString().length() == length) {
            return true;
        } else {
            field.setError(errorToShow);
            return false;
        }
    }
}
