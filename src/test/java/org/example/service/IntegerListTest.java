package org.example.service;

import org.example.exception.CapacityOversizeException;
import org.example.exception.ElementNotFoundException;
import org.example.exception.NullValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.constant.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class IntegerListTest {

    private final IntegerListImpl out = new IntegerListImpl();
    private int addedTestIntCount = 0;
    private int testListIntCounter = 0;

    @BeforeEach
    void createTestList() {
        int generatorCounter = 5;
        for (int i = 0; i < generatorCounter; i++) {
            out.add(INT_1);
            testListIntCounter++;
            out.add(INT_2);
            testListIntCounter++;
            out.add(INT_3);
            testListIntCounter++;
            out.add(INT_4);
            testListIntCounter++;
            addedTestIntCount++;
        }
    }

    @Test
    void shouldAddAndReturnAddedIntegerOrExceptionNullElement() {
        int elementsCapacity = out.size();
        assertEquals(INT_1, out.add(INT_1));
        assertEquals((elementsCapacity + 1), out.size());
        assertEquals(INT_2, out.add(INT_2));
        assertEquals((elementsCapacity + 2), out.size());
        assertThrows(NullValueException.class, () -> out.add(null));
    }

    @Test
    void shouldAddAndReturnAddedIntegerByIndexOrExceptionNullElement() {
        int elementsCapacity = out.size();
        assertEquals(INT_1, out.add(INDEX_2, INT_1));
        assertEquals((elementsCapacity + 1), out.size());
        assertEquals(INT_2, out.add(INDEX_2, INT_2));
        assertEquals((elementsCapacity + 2), out.size());
        assertThrows(NullValueException.class, () -> out.add(INDEX_2, null));
        assertThrows(CapacityOversizeException.class, () -> out.add(OUT_OF_INDEX, INT_3));
    }

    @Test
    void shouldSetAndReturnSetIntegerByIndexOrExceptionNullElement() {
        int elementsCapacity = out.size();
        assertEquals(INT_1, out.set(INDEX_2, INT_1));
        assertEquals((elementsCapacity), out.size());
        assertEquals(INT_2, out.set(INDEX_2, INT_2));
        assertEquals((elementsCapacity), out.size());
        assertThrows(NullValueException.class, () -> out.set(INDEX_2, null));
        assertThrows(CapacityOversizeException.class, () -> out.set(OUT_OF_INDEX, INT_3));
    }

    @Test
    void shouldDeleteAndReturnDeletedIntegerOrExceptionElementNotFound() {
        int elementsCapacity = out.size();
        assertEquals(INT_3, out.remove(INT_3));
        assertEquals((elementsCapacity - addedTestIntCount), out.size());
        elementsCapacity = out.size();
        assertEquals(INT_4, out.remove(INT_4));
        assertEquals((elementsCapacity - addedTestIntCount), out.size());
        assertThrows(ElementNotFoundException.class, () -> out.remove(INT_NOTINLIST));
    }

    @Test
    void shouldDeleteAndReturnDeletedIntegerByIndexOrExceptionElementNotFound() {
        int elementsCapacity = out.size();
        assertEquals(INT_1, out.remove(INDEX_0));
        assertEquals((elementsCapacity - 1), out.size());
        elementsCapacity = out.size();
        assertEquals(INT_3, out.remove(INDEX_1));
        assertEquals((elementsCapacity - 1), out.size());
        assertThrows(ElementNotFoundException.class, () -> out.remove(INT_NOTINLIST));
    }

    @Test
    void shouldReturnTrueIfIntegerInListOrExceptionNullValueIfSendNull() {
        assertTrue(out.contains(INT_4));
        assertFalse(out.contains(INT_NOTINLIST));
        assertThrows(NullValueException.class, () -> out.contains(null));
    }

    @Test
    void shouldReturnIndexOfFirstCurrentIntegerInListOrSpecSymbol() {
        assertEquals(INDEX_0, out.indexOf(INT_1));
        assertEquals(INDEX_NOTINLIST, out.indexOf(INT_NOTINLIST));
        assertThrows(NullValueException.class, () -> out.indexOf(null));
    }

    @Test
    void shouldReturnLastIndexOfCurrentIntegerInListOrSpecSymbol() {
        int elementsCapacity = out.size();
        assertEquals(elementsCapacity - 1, out.lastIndexOf(INT_4));
        assertEquals(INDEX_NOTINLIST, out.lastIndexOf(INT_NOTINLIST));
        assertThrows(NullValueException.class, () -> out.lastIndexOf(null));
    }

    @Test
    void shouldReturnIntegerByIndexOrExceptionCapacityOverSize() {
        assertEquals(INT_1, out.get(INDEX_0));
        assertThrows(CapacityOversizeException.class, () -> out.get(OUT_OF_INDEX));
    }

    @Test
    void shouldReturnTrueIfListsIsEqualsOrExceptionNullValueIfListIsNull() {
        IntegerListImpl testList = new IntegerListImpl();
        for (int i = 0; i < 5; i++) {
            testList.add(INT_1);
            testList.add(INT_2);
            testList.add(INT_3);
            testList.add(INT_4);
        }
        assertTrue(out.equals(testList));
        assertThrows(NullValueException.class, () -> out.equals(null));
    }

    @Test
    void shouldReturnSizeOfList() {
        assertEquals(testListIntCounter, out.size());
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
        Integer[] testArr = new Integer[testListIntCounter];
        for (int i = 0; i < testListIntCounter; ) {
            testArr[i] = INT_1;
            testArr[i + 1] = INT_2;
            testArr[i + 2] = INT_3;
            testArr[i + 3] = INT_4;
            i = i + 4;
        }
        assertArrayEquals(testArr, out.toArray());
    }
}