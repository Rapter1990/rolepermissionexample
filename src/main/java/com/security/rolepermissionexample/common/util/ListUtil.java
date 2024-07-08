package com.security.rolepermissionexample.common.util;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ListUtil {

    public <C> List<C> to(Object object, Class<C> clazz) {
        return (List<C>) object;
    }

}
