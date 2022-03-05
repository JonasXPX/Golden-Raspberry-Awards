package me.jonas.goldenraspberryawards.utils;

import java.util.Arrays;
import java.util.Objects;

public class BooleanConverter {

    private static final String[] trueValues = {"yes", "y", "true", "t"};

    private BooleanConverter() {
        // util class
    }

    public static boolean convert(String textBoolean) {
        if (Objects.isNull(textBoolean)) return false;
        return Arrays.stream(trueValues).anyMatch(textBoolean::equalsIgnoreCase);
    }
}
