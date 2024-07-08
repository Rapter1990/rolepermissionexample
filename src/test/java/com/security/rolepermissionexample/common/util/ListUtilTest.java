package com.security.rolepermissionexample.common.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListUtilTest {

    @Test
    void utilityClass_ShouldNotBeInstantiated() {
        assertThrows(InvocationTargetException.class, () -> {
            // Attempt to use reflection to create an instance of the utility class
            java.lang.reflect.Constructor<ListUtil> constructor = ListUtil.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }

    @Test
    void testToWithValidList() {

        // Arrange
        List<String> originalList = Arrays.asList("one", "two", "three");

        // Act
        List<String> result = ListUtil.to(originalList, String.class);

        // Assert
        assertNotNull(result);
        assertEquals(originalList.size(), result.size());
        assertEquals(originalList, result);

    }

    @Test
    void testToWithEmptyList() {

        // Arrange
        List<String> originalList = Arrays.asList();

        // Act
        List<String> result = ListUtil.to(originalList, String.class);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());

    }

    @Test
    void testToWithNullList() {

        // Arrange
        List<String> originalList = null;

        // Act
        List<String> result = ListUtil.to(originalList, String.class);

        // Assert
        assertNull(result);

    }

    @Test
    void testToWithDifferentClassType() {

        // Arrange
        List<Integer> originalList = Arrays.asList(1, 2, 3);

        // Act
        List<Integer> result = ListUtil.to(originalList, Integer.class);

        // Assert
        assertNotNull(result);
        assertEquals(originalList.size(), result.size());
        assertEquals(originalList, result);

    }

}