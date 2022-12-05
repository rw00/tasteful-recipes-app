package com.rw.test.tastefulapp.common.validation;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {
    public final String UUID_REGEX = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";

    // copied from StackOverflow with slight modification to reject leading and trailing spaces
    public final String NAME_REGEX = "^[\\u00c0-\\u01ffa-zA-Z]([ \\u00c0-\\u01ffa-zA-Z'\\-])*[\\u00c0-\\u01ffa-zA-Z]$";
    public final String UNIT_REGEX = "^[a-zA-Z][a-zA-Z]+$";

}
