package org.example.service;

import org.example.exception.CapacityOversizeException;
import org.example.exception.ElementNotFoundException;
import org.example.exception.NullValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.constant.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class StringListTest {

    private final StringListImpl out = new StringListImpl();
    private int addedTestStringCount = 0;
    private int testListStringsCounter = 0;

    @BeforeEach
    void createTestList() {
        int generatorCounter = 5;
        for (int i = 0; i < generatorCounter; i++) {
            out.add(STR_1);
            testListStringsCounter++;
            out.add(STR_2);
            testListStringsCounter++;
            out.add(STR_3);
            testListStringsCounter++;
            out.add(STR_4);
            testListStringsCounter++;
            addedTestStringCount++;
        }
    }

    @Test
    void shouldAddAndReturnAddedStringOrExceptionNullElement() {
        int elementsCapacity = out.size();
        assertEquals(STR_1, out.add(STR_1));
        assertEquals((elementsCapacity + 1), out.size());
        assertEquals(STR_2, out.add(STR_2));
        assertEquals((elementsCapacity + 2), out.size());
        assertThrows(NullValueException.class, () -> out.add(null));
    }

    @Test
    void shouldAddAndReturnAddedStringByIndexOrExceptionNullElement() {
        int elementsCapacity = out.size();
        assertEquals(STR_1, out.add(INDEX_2, STR_1));
        assertEquals((elementsCapacity + 1), out.size());
        assertEquals(STR_2, out.add(INDEX_2, STR_2));
        assertEquals((elementsCapacity + 2), out.size());
        assertThrows(NullValueException.class, () -> out.add(INDEX_2, null));
        assertThrows(CapacityOversizeException.class, () -> out.add(OUT_OF_INDEX, STR_3));
    }

    @Test
    void shouldSetAndReturnSetStringByIndexOrExceptionNullElement() {
        int elementsCapacity = out.size();
        assertEquals(STR_1, out.set(INDEX_2, STR_1));
        assertEquals((elementsCapacity), out.size());
        assertEquals(STR_2, out.set(INDEX_2, STR_2));
        assertEquals((elementsCapacity), out.size());
        assertThrows(NullValueException.class, () -> out.set(INDEX_2, null));
        assertThrows(CapacityOversizeException.class, () -> out.set(OUT_OF_INDEX, STR_3));
    }

    @Test
    void shouldDeleteAndReturnDeletedStringOrExceptionElementNotFound() {
        int elementsCapacity = out.size();
        assertEquals(STR_3, out.remove(STR_3));
        assertEquals((elementsCapacity - addedTestStringCount), out.size());
        elementsCapacity = out.size();
        assertEquals(STR_4, out.remove(STR_4));
        assertEquals((elementsCapacity - addedTestStringCount), out.size());
        assertThrows(ElementNotFoundException.class, () -> out.remove(STR_NOTINLIST));
    }

    @Test
    void shouldDeleteAndReturnDeletedStringByIndexOrExceptionElementNotFound() {
        int elementsCapacity = out.size();
        assertEquals(STR_1, out.remove(INDEX_0));
        assertEquals((elementsCapacity - 1), out.size());
        elementsCapacity = out.size();
        assertEquals(STR_3, out.remove(INDEX_1));
        assertEquals((elementsCapacity - 1), out.size());
        assertThrows(ElementNotFoundException.class, () -> out.remove(STR_NOTINLIST));
    }

    @Test
    void shouldReturnTrueIfStringInListOrExceptionNullValueIfSendNull() {
        assertTrue(out.contains(STR_4));
        assertFalse(out.contains(STR_NOTINLIST));
        assertThrows(NullValueException.class, () -> out.contains(null));
    }

    @Test
    void shouldReturnIndexOfFirstCurrentStringInListOrSpecSymbol() {
        assertEquals(INDEX_0, out.indexOf(STR_1));
        assertEquals(INDEX_NOTINLIST, out.indexOf(STR_NOTINLIST));
        assertThrows(NullValueException.class, () -> out.indexOf(null));
    }

    @Test
    void shouldReturnLastIndexOfCurrentStringInListOrSpecSymbol() {
        int elementsCapacity = out.size();
        assertEquals(elementsCapacity - 1, out.lastIndexOf(STR_4));
        assertEquals(INDEX_NOTINLIST, out.lastIndexOf(STR_NOTINLIST));
        assertThrows(NullValueException.class, () -> out.lastIndexOf(null));
    }

    @Test
    void shouldReturnStringByIndexOrExceptionCapacityOverSize() {
        assertEquals(STR_1, out.get(INDEX_0));
        assertThrows(CapacityOversizeException.class, () -> out.get(OUT_OF_INDEX));
    }

    @Test
    void shouldReturnTrueIfListsIsEqualsOrExceptionNullValueIfListIsNull() {
        StringListImpl testList = new StringListImpl();
        for (int i = 0; i < 5; i++) {
            testList.add(STR_1);
            testList.add(STR_2);
            testList.add(STR_3);
            testList.add(STR_4);
        }
        assertTrue(out.equals(testList));
        assertThrows(NullValueException.class, () -> out.equals(null));
    }

    @Test
    void shouldReturnSizeOfList() {
        assertEquals(testListStringsCounter, out.size());
    }

    @Test
    void shouldReturnTrueIfListIsEmpty() {
        assertFalse(out.isEmpty());
        out.clear();
        assertTrue(out.isEmpty());
    }

    @Test
    void shouldClearList() {
        out.clear();
        assertTrue(out.isEmpty());
        assertEquals(INDEX_0, out.size());
    }

    @Test
    void toArray() {
        String[] testArr = new String[testListStringsCounter];
        for (int i = 0; i < testListStringsCounter; ) {
            testArr[i] = STR_1;
            testArr[i + 1] = STR_2;
            testArr[i + 2] = STR_3;
            testArr[i + 3] = STR_4;
            i = i + 4;
        }
        assertArrayEquals(testArr, out.toArray());
    }
}