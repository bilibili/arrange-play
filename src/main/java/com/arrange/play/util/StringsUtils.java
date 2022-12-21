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

    public static String idCardToUpCase(String idCard) {
        idCard = StringUtils.trimToEmpty(idCard);
        if (StringUtils.isBlank(idCard)) {
            return idCard;
        } else {
            char[] chars = idCard.toCharArray();
            int lastIndex = chars.length - 1;
            char lastChar = chars[lastIndex];
            if (lastChar == 'x') {
                chars[lastIndex] = (char)(chars[lastIndex] - 32);
                return String.valueOf(chars);
            } else {
                return idCard;
            }
        }
    }


}
