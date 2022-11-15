package org.example.service;

import org.example.exception.CapacityOversizeException;
import org.example.exception.ElementNotFoundException;
import org.example.exception.NullValueException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList{

    private final int SIZE = 12;
    private final int RESIZE_VALUE = 2;
    private int elementsCounter = 0;
    private Integer[] intList;

    public IntegerListImpl() {
        this.intList = new Integer[SIZE];
    }

    @Override
    public Integer add(Integer item) {
        validateNull(item);
        if (elementsCounter == intList.length - 1) {
            resize(intList.length * RESIZE_VALUE);
        }
        intList[elementsCounter] = item;
        Integer addedElement = intList[elementsCounter];
        elementsCounter++;
        return addedElement;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateNull(item);
        validateCapacity(index);
        if (elementsCounter == intList.length - 1) {
            resize(intList.length * RESIZE_VALUE);
        }
        for (int i = elementsCounter; i >= index; i--) {
            intList[i + 1] = intList[i];
        }
        intList[index] = item;
        elementsCounter++;
        return intList[index];
    }




    @Override
    public Integer set(int index, Integer item) {
        validateNull(item);
        validateCapacity(index);
        intList[index] = item;
        return intList[index];
    }

    @Override
    public Integer remove(Integer item) {
        validateNull(item);
        boolean isItemInList = true;
        for (int i = 0; i < elementsCounter; i++) {
            if (intList[i].equals(item)) {
                remove(i);
                i--;
                isItemInList = false;
            }
        }
        if (intList.length > SIZE && elementsCounter < intList.length / RESIZE_VALUE) {
            resize(intList.length / RESIZE_VALUE);
        }
        if (isItemInList) {
            throw new ElementNotFoundException("Элемент отсутствует в списке");
        }
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateCapacity(index);
        Integer deletedString = intList[index];
        for (int i = index; i < elementsCounter; i++)
            intList[i] = intList[i + 1];
        elementsCounter--;
        intList[elementsCounter] = null;
        if (intList.length > SIZE && elementsCounter < intList.length / RESIZE_VALUE) {
            resize(intList.length / RESIZE_VALUE);
        }
        return deletedString;
    }

    @Override
    public boolean contains(Integer item) {
        validateNull(item);
        boolean isItemInList = false;
        for (int i = 0; i < elementsCounter; i++) {
            if (intList[i].equals(item)) {
                isItemInList = true;
                break;
            }
        }
        return isItemInList;
    }

    @Override
    public int indexOf(Integer item) {
        validateNull(item);
        int index = -1;
        for (int i = 0; i < elementsCounter; i++) {
            if (intList[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Integer item) {
        validateNull(item);
        int index = -1;
        for (int i = elementsCounter - 1; i >= 0; i--) {
            if (intList[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public Integer get(int index) {
        validateCapacity(index);
        return intList[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        validateNull(otherList);
        return Arrays.equals((Arrays.stream(intList).toArray()), otherList.toArray());
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
        Arrays.fill(intList, null);
        elementsCounter = 0;
        if (intList.length > SIZE) {
            resize(intList.length / RESIZE_VALUE);
        }
    }

    @Override
    public Integer[] toArray() {
        Integer[] newArray = new Integer[elementsCounter];
        System.arraycopy(intList, 0, newArray, 0, elementsCounter);
        return newArray;
    }

    private void resize(int sizeValue) {
        if (sizeValue < elementsCounter) {
            throw new CapacityOversizeException();
        } else {
            Integer[] resizedArray = new Integer[sizeValue];
            System.arraycopy(intList, 0, resizedArray, 0, elementsCounter);
            intList = resizedArray;
        }
    }

//    private Integer[] resizeToCurrentCapacity(Integer[] strings) {
//        return Arrays.copyOf(intList, elementsCounter);
//    }
//
//    private Integer[] resizeToCurrentCapacity(IntegerList strings) {
//        return Arrays.copyOf(intList, elementsCounter);
//    }
    private static void validateNull(Object item) {
        if (item == null) {
            throw new NullValueException("Укажите не null");
        }
    }
    private void validateCapacity(int index) {
        if ((index >= elementsCounter) || (index >= intList.length - 1)) {
            throw new CapacityOversizeException("Индекс " + index + " превышает размер хранилища");
        }
    }

}
