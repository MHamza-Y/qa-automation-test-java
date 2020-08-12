package utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    /**
     * Takes an string containing array and converts it into list of strings
     * @param input String containing array
     * @return List of strings parsed
     */
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
