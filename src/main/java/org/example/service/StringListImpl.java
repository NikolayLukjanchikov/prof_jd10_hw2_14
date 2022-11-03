package org.example.service;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private final int SIZE = 12;
    private final int RESIZE_VALUE = 2;
    private int elementsCounter = 0;
    private String[] stringList ;

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
        boolean isItemInList = false;
        for (int i = 0; i < elementsCounter; i++) {
            if (stringList[i].equals(item)) {
                isItemInList = true;
                break;
            }
        }
        return isItemInList;
    }

    @Override
    public int indexOf(String item) {
        int index = -1;
        for (int i = 0; i < elementsCounter; i++) {
            if (stringList[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(String item) {
        int index = -1;
        for (int i = elementsCounter - 1; i >= 0; i--) {
            if (stringList[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public String get(int index) {
        if ((index >= elementsCounter) || (index >= stringList.length - 1)) {
            throw new RuntimeException();
        }
        return stringList[index];
    }

    @Override
    public boolean equals(StringList otherList) {
//        String[] currentList = new String[elementsCounter];
//        System.arraycopy(stringList, 0, currentList, 0, elementsCounter);
        String str1 = (resizeToCurrentCapacity(stringList)).toString();
        String str2 = (resizeToCurrentCapacity(otherList)).toString();
        return str1.equals(str2);
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
        return (elementsCounter > 0);
    }

    @Override
    public void clear() {
        for (int i = 0; i < elementsCounter; i++) {
            stringList[i] = null;
        }
    }

    @Override
    public String[] toArray() {
        String[] newArray = new String[elementsCounter];
        System.arraycopy(stringList, 0, newArray, 0, elementsCounter);
        return newArray;
    }

    private void resize(int sizeValue) {
        if (sizeValue < elementsCounter) {
            throw new RuntimeException();
        } else {
            String[] resizedArray = new String[sizeValue];
            System.arraycopy(stringList, 0, resizedArray, 0, elementsCounter);
            stringList = resizedArray;
        }
    }
    private String[] resizeToCurrentCapacity(StringList strings) {
            String[] resizedArray = new String[elementsCounter];
            System.arraycopy(stringList, 0, resizedArray, 0, elementsCounter);
            return resizedArray;
        }
}
