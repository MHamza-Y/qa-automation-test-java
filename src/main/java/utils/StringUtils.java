package utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static List<String> parseStringToList(String input) {
        return Arrays.asList(
                input
                        .replaceAll("\\[", "")
                        .replaceAll("\\]", "")
                        .replaceAll("\"", "")
                        .split(",")
        );
    }
}
