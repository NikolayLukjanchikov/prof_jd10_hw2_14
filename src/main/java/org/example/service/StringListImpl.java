package org.example.service;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private final int SIZE = 12;
    private final int RESIZE_VALUE = 2;
    private int elementsCounter = 0;
    private String[] stringList;

    public StringListImpl() {
        this.stringList = new String[SIZE];
    }

    @Override
    public String add(String item) {
        if (elementsCounter == stringList.length - 1) {
            resize(stringList.length * RESIZE_VALUE);
        }
        stringList[elementsCounter] = item;
        String addedElement = stringList[elementsCounter];
        elementsCounter++;
        return addedElement;
    }

    @Override
    public String add(int index, String item) {
        if ((index >= elementsCounter) || (index >= stringList.length - 1)) {
            throw new RuntimeException();
        }
        if (elementsCounter == stringList.length - 1) {
            resize(stringList.length * RESIZE_VALUE);
        }
        for (int i = elementsCounter; i >= index; i--) {
            stringList[i + 1] = stringList[i];
        }
        stringList[index] = item;
        elementsCounter++;
        return stringList[index];
    }

    @Override
    public String set(int index, String item) {
        if ((index >= elementsCounter - 1) || (index >= stringList.length - 1)) {
            throw new RuntimeException();
        }
        stringList[index] = item;
        return stringList[index];
    }

    @Override
    public String remove(String item) {
        boolean isItemInList = true;
        for (int i = 0; i < elementsCounter; i++) {
            if (stringList[i].equals(item)) {
                remove(i);
                isItemInList = false;
            }
        }
        if (isItemInList) {
            throw new RuntimeException();
        }
        return item;
    }

    @Override
    public String remove(int index) {
        if ((index >= elementsCounter) || (index >= stringList.length - 1)) {
            throw new RuntimeException();
        }
        String deletedString = stringList[index];
        for (int i = index; i < elementsCounter; i++) {
            stringList[i] = stringList[i + 1];
            stringList[elementsCounter - 1] = null;
            elementsCounter--;
        }
        if (stringList.length > SIZE && elementsCounter < stringList.length / RESIZE_VALUE) {
            resize(stringList.length / RESIZE_VALUE);
        }
        return deletedString;
    }

    @Override
    public boolean contains(String item) {
        //stringList.
        return false;
    }

    @Override
    public int indexOf(String item) {
        return 0;
    }

    @Override
    public int lastIndexOf(String item) {
        return 0;
    }

    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public boolean equals(StringList otherList) {
        return false;
    }

    @Override
    public String toString() {
        return "StringListImpl{" +
                "stringList=" + Arrays.toString(stringList) +
                '}';
    }

    @Override
    public int size() {
        return elementsCounter;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public String[] toArray() {
        return new String[0];
    }

    private void resize(int sizeValue) {
        String[] resizedArray = new String[sizeValue];
        System.arraycopy(stringList, 0, resizedArray, 0, elementsCounter);
        stringList = resizedArray;
    }


}
