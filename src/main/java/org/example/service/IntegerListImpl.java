package org.example.service;

import org.example.exception.CapacityOversizeException;
import org.example.exception.ElementNotFoundException;
import org.example.exception.NullValueException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private final int SIZE = 12;
    private final double RESIZE_VALUE = 1.5;
    private int elementsCounter = 0;
    private Integer[] intList;

    public IntegerListImpl() {
        this.intList = new Integer[SIZE];
    }

    @Override
    public Integer add(Integer item) {
        validateNull(item);
        if (elementsCounter == intList.length - 1) {
            growOrDecrease((int) (intList.length * RESIZE_VALUE));
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
            growOrDecrease((int) (intList.length * RESIZE_VALUE));
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
            growOrDecrease((int) (intList.length / RESIZE_VALUE));
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
            growOrDecrease((int) (intList.length / RESIZE_VALUE));
        }
        return deletedString;
    }

    @Override
    public boolean contains(Integer item) {
        validateNull(item);
        int[] arrToFind = toIntArray(intList);
        quickSort(arrToFind, 0, intList.length - 1);
        return binarySearch(arrToFind, item);
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
        String str1 = Arrays.toString((resizeToCurrentCapacity(intList)));
        String str2 = Arrays.toString(resizeToCurrentCapacity(otherList));
        return str1.equals(str2);
    }

//    @Override
//    public String toString() {
//        return "StringListImpl{" +
//                "stringList=" + Arrays.toString(intList) +
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
            growOrDecrease((int) (intList.length / RESIZE_VALUE));
        }
    }

    @Override
    public Integer[] toArray() {
        Integer[] newArray = new Integer[elementsCounter];
        System.arraycopy(intList, 0, newArray, 0, elementsCounter);
        return newArray;
    }

    private int[] toIntArray(Integer[] arr) {
        int result[] = Arrays.stream(arr)
                .mapToInt(i -> (i == null ? 0 : i))
                .toArray();
        return result;
    }

    private void growOrDecrease(int sizeValue) {
        if (sizeValue < elementsCounter) {
            throw new CapacityOversizeException();
        } else {
            Integer[] resizedArray = new Integer[sizeValue];
            System.arraycopy(intList, 0, resizedArray, 0, elementsCounter);
            intList = resizedArray;
        }
    }

    private Integer[] resizeToCurrentCapacity(Integer[] strings) {
        return Arrays.copyOf(intList, elementsCounter);
    }

    private Integer[] resizeToCurrentCapacity(IntegerList strings) {
        return Arrays.copyOf(intList, elementsCounter);
    }

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

//    public void sorting() {
//        int[] randomArray1 = generateRandomArray();
//        long start = System.currentTimeMillis();
//        sortBubble(randomArray1);
//        System.out.println(System.currentTimeMillis() - start + " Пузырьком");
//
//        int[] randomArray2 = generateRandomArray();
//        long start2 = System.currentTimeMillis();
//        sortSelection(randomArray2);
//        System.out.println(System.currentTimeMillis() - start2 + " Выборкой");
//
//        int[] randomArray3 = generateRandomArray();
//        long start3 = System.currentTimeMillis();
//        sortInsertion(randomArray3);
//        System.out.println(System.currentTimeMillis() - start3 + " Вставкой");
//
//        int[] randomArray4 = generateRandomArray();
//        long start4 = System.currentTimeMillis();
//        quickSort(randomArray4, 0,randomArray4.length-1);
//        System.out.println(System.currentTimeMillis() - start4 + " Быстрая");
//
//        int[] randomArray5 = generateRandomArray();
//        long start5 = System.currentTimeMillis();
//        mergeSort(randomArray5, 0,randomArray5.length-1);
//        System.out.println(System.currentTimeMillis() - start5 + " Слиянием");
//
//    }

//    private static int[] generateRandomArray() {
//        java.util.Random random = new java.util.Random();
//        int[] arr = new int[100000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = random.nextInt(100_000) + 100_000;
//        }
//        return arr;
//    }

//    private static void swapElements(int[] arr, int indexA, int indexB) {
//        int tmp = arr[indexA];
//        arr[indexA] = arr[indexB];
//        arr[indexB] = tmp;
//    }
//
//    private static void sortBubble(int[] arr) {
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = 0; j < arr.length - 1 - i; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    swapElements(arr, j, j + 1);
//                }
//            }
//        }
//    }
//
//    private static void sortSelection(int[] arr) {
//        for (int i = 0; i < arr.length - 1; i++) {
//            int minElementIndex = i;
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[j] < arr[minElementIndex]) {
//                    minElementIndex = j;
//                }
//            }
//            swapElements(arr, i, minElementIndex);
//        }
//    }
//
//    private static void sortInsertion(int[] arr) {
//        for (int i = 1; i < arr.length; i++) {
//            int temp = arr[i];
//            int j = i;
//            while (j > 0 && arr[j - 1] >= temp) {
//                arr[j] = arr[j - 1];
//                j--;
//            }
//            arr[j] = temp;
//        }
//    }


    private static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;//завершить выполнение, если длина массива равна 0
        if (low >= high)
            return;//завершить выполнение если уже нечего делить
        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = array[middle];
        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }
            while (array[j] > opora) {
                j--;
            }
            if (i <= j) {//меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, j);
        if (high > i)
            quickSort(array, i, high);
    }

//    private static void mergeSort(int[] source, int left, int right) {
//        // Выберем разделитель, т.е. разделим пополам входной массив
//        int delimiter = left + ((right - left) / 2) + 1;
//        // Выполним рекурсивно данную функцию для двух половинок (если сможем разбить(
//        if (delimiter > 0 && right > (left + 1)) {
//            mergeSort(source, left, delimiter - 1);
//            mergeSort(source, delimiter, right);
//        }
//        // Создаём временный массив с нужным размером
//        int[] buffer = new int[right - left + 1];
//        // Начиная от указанной левой границы идём по каждому элементу
//        int cursor = left;
//        for (int i = 0; i < buffer.length; i++) {
//            // Мы используем delimeter чтобы указывать на элемент из правой части
//            // Если delimeter > right, значит в правой части не осталось недобавленных элементов
//            if (delimiter > right || source[cursor] > source[delimiter]) {
//                buffer[i] = source[cursor];
//                cursor++;
//            } else {
//                buffer[i] = source[delimiter];
//                delimiter++;
//            }
//        }
//        System.arraycopy(buffer, 0, source, left, buffer.length);
//    }

    private static boolean binarySearch(int[] arr, int element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (element == arr[mid]) {
                return true;
            }
            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

}
