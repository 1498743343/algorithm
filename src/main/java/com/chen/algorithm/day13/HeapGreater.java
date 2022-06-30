package com.chen.algorithm.day13;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class HeapGreater<T> {
    private final ArrayList<T> heap;
    private final Map<T, Integer> indexMap;
    private int heapSize;
    private final Comparator<? super T> comparator;

    public HeapGreater(Comparator<? super T> comparator) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T t) {
        return indexMap.containsKey(t);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T element) {
        heap.add(element);
        indexMap.put(element, heapSize);
        heapInsert(heapSize++);
    }


    public T pop() {
        T result = heap.get(0);
        swap(indexMap.get(result), --heapSize);
        heap.remove(heapSize);
        indexMap.remove(result);
        heapify(heapSize);
        return result;
    }


    public void remove(T element) {
        T lastElement = heap.get(--heapSize);
        Integer elementIndex = indexMap.get(element);
        heap.remove(heapSize);
        indexMap.remove(element);
        if (!element.equals(lastElement)) {
            heap.set(elementIndex, lastElement);
            indexMap.put(lastElement, elementIndex);
            revise(lastElement);
        }
    }

    public void revise(T element) {
        heapInsert(indexMap.get(element));
        heapify(indexMap.get(element));
    }

    private void swap(int i, int j) {
        T t1 = heap.get(i);
        T t2 = heap.get(j);
        heap.set(i, t2);
        heap.set(j, t1);
        indexMap.put(t2, i);
        indexMap.put(t1, j);
    }


    private void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        int comparatorIndex = 2 * index + 1;
        while (comparatorIndex < heapSize) {
            if (comparatorIndex + 1 < heapSize && comparator.compare(heap.get(comparatorIndex + 1), heap.get(comparatorIndex)) < 0) {
                comparatorIndex++;
            }
            if (comparator.compare(heap.get(index), heap.get(comparatorIndex)) <= 0) {
                break;
            }
            swap(index, comparatorIndex);
            index = comparatorIndex;
            comparatorIndex = 2 * index + 1;
        }
    }

}
