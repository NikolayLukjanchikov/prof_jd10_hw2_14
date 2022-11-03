package org.example.service;

import org.example.exception.CapacityOversizeException;
import org.example.exception.ElementNotFoundException;
import org.example.exception.NullValueException;

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
        if (item == null) {
            throw new NullValueException("Укажите не null");
        }
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
        if (item == null) {
            throw new NullValueException("Укажите не null");
        }
        if ((index >= elementsCounter) || (index >= stringList.length - 1)) {
            throw new CapacityOversizeException("Индекс " + index + " превышает размер хранилища");
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
        if (item == null) {
            throw new NullValueException("Укажите не null");
        }
        if ((index >= elementsCounter - 1) || (index >= stringList.length - 1)) {
            throw new CapacityOversizeException("Индекс " + index + " превышает размер хранилища");
        }
        stringList[index] = item;
        return stringList[index];
    }

    @Override
    public String remove(String item) {
        if (item == null) {
            throw new NullValueException("Укажите не null");
        }
        boolean isItemInList = true;
        for (int i = 0; i < elementsCounter; i++) {
            if (stringList[i].equals(item)) {
                remove(i);
                i--;
                isItemInList = false;
            }
        }
        if (stringList.length > SIZE && elementsCounter < stringList.length / RESIZE_VALUE) {
            resize(stringList.length / RESIZE_VALUE);
        }
        if (isItemInList) {
            throw new ElementNotFoundException("Элемент отсутствует в списке");
        }
        return item;
    }

    @Override
    public String remove(int index) {
        if ((index >= elementsCounter) || (index >= stringList.length - 1)) {
            throw new CapacityOversizeException("Индекс " + index + " превышает размер хранилища");
        }
        String deletedString = stringList[index];
        for (int i = index; i < elementsCounter; i++)
            stringList[i] = stringList[i + 1];
        elementsCounter--;
        stringList[elementsCounter] = null;
        if (stringList.length > SIZE && elementsCounter < stringList.length / RESIZE_VALUE) {
            resize(stringList.length / RESIZE_VALUE);
        }
        return deletedString;
    }

    @Override
    public boolean contains(String item) {
        if (item == null) {
            throw new NullValueException("Укажите не null");
        }
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
        if (item == null) {
            throw new NullValueException("Укажите не null");
        }
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
        if (item == null) {
            throw new NullValueException("Укажите не null");
        }
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
            throw new CapacityOversizeException("Индекс " + index + " превышает размер хранилища");
        }
        return stringList[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullValueException("Укажите не null");
        }
        String str1 = Arrays.toString((resizeToCurrentCapacity(stringList)));
        String str2 = Arrays.toString(resizeToCurrentCapacity(otherList));
        return str1.equals(str2);
    }

//    @Override
//    public String toString() {
//        return "StringListImpl{" +
//                "stringList=" + Arrays.toString(stringList) +
//                '}';
//    }

    @Override
    public int size() {
        return elementsCounter;
    }

    @Override
    public boolean isEmpty() {
        return (elementsCounter == 0);
    }

    @Override
    public void clear() {
        Arrays.fill(stringList, null);
        if (stringList.length > SIZE && elementsCounter < stringList.length / RESIZE_VALUE) {
            resize(stringList.length / RESIZE_VALUE);
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

    private String[] resizeToCurrentCapacity(String[] strings) {
        return Arrays.copyOf(stringList, elementsCounter);
    }

    private String[] resizeToCurrentCapacity(StringList strings) {
        return Arrays.copyOf(stringList, elementsCounter);
    }
}
