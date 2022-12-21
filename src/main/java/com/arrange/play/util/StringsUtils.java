package com.arrange.play.util;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

public class StringsUtils {

    public static boolean isEmptyOrNull(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return true;
        }

        return "".equals(str.trim());
    }

    public static boolean isNotEmptyOrNull(String str) {
        return !isEmptyOrNull(str);
    }

}
