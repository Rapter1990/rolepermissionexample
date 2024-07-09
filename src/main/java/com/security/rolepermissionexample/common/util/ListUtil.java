package com.security.rolepermissionexample.common.util;

import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * Utility class named {@link ListUtil} for converting objects to lists.
 */
@UtilityClass
public class ListUtil {

    /**
     * Converts an object to a list of a specified class type.
     *
     * @param object The object to convert.
     * @param clazz  The target class type.
     * @param <C>    The type of elements in the list.
     * @return List of elements of type C.
     */
    public <C> List<C> to(Object object, Class<C> clazz) {
        return (List<C>) object;
    }

}
