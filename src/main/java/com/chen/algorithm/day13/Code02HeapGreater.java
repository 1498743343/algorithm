package com.chen.algorithm.day13;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * code02 加强堆
 *
 * 系统提供的堆无法做到的事情：
 * 1）已经入堆的元素，如果参与排序的指标方法变化，系统提供的堆无法做到时间复杂度O(logN)调整！都是O(N)的调整！
 * 2）系统提供的堆只能弹出堆顶，做不到自由删除任何一个堆中的元素，或者说，无法在时间复杂度O(logN)内完成！一定会高于O(logN)
 * 根本原因：无反向索引表
 *
 *
 * @author chenzihan
 * @date 2022/06/23
 */
public class Code02HeapGreater<T> {
    private final ArrayList<T> heap;
    private final Map<T,Integer> indexMap;
    private int heapSize;
    private final Comparator<? super T> comparator;

    public Code02HeapGreater(Comparator<? super T> comparator) {
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

    /**
     * 弹出堆顶元素
     * 1. 获取堆顶元素 result
     * 2. 将堆顶元素与堆中最后一个元素交换
     * 3. heapSize--
     * 4. 在反向索引表中删除 result
     * 5. 调整新的堆顶元素的位置，重新形成堆结构
     *
     * @return {@link T}
     */
    public T pop() {
        T result = heap.get(0);
        swap(0, --heapSize);
        indexMap.remove(result);
        heap.remove(heapSize);
        heapify(0);
        return result;
    }

    /**
     * 删除指定元素
     * 1. 将指定元素找到，判断是不是堆中的最后一个元素
     * 2.2 如果不是最后一个元素：和堆中的最后一个元素交换
     * 3. 在堆和反向索引表中删除指定元素
     * 4. 将交换后的元素调整到适当位置重新形成堆结构
     * 2.1 如果是最后一个元素：直接在 heap 和 indexMap 中删除即可
     *
     * @param element 元素
     */
    public void remove(T element) {
        Integer removeIndex = indexMap.get(element);
        T replaceElement = heap.get(--heapSize);
        heap.remove(heapSize);
        indexMap.remove(element);
        // 上面 4 行代码执行后，如果 element 是堆中的最后一个元素，那么已经完成了 remove 操作
        // 如果不相等，用 replaceElement 替换 element，然后对 replaceElement 执行 revise 操作即可完成 remove
        if (!element.equals(replaceElement)) {
            heap.set(removeIndex, replaceElement);
            indexMap.put(replaceElement, removeIndex);
            revise(replaceElement);
        }
    }

    /**
     * 当指定元素调整位置后，将指定元素移动到合适的位置重新形成堆结构
     *
     * @param element 元素
     */
    public void revise(T element) {
        // 虽然调用了 heapInsert 和 heapify，但是只会执行一个方法，因为这个元素只可能向上走或者向下走
        heapInsert(indexMap.get(element));
        heapify(indexMap.get(element));
    }

    private void swap(int i, int j) {
        T t1 = heap.get(i);
        T t2 = heap.get(j);
        heap.set(i, t2);
        heap.set(j, t1);
        indexMap.put(t1, j);
        indexMap.put(t2, i);
    }

    /**
     * 往上走到合适的位置形成对结构
     *
     * @param index 位置
     */
    private void heapInsert(int index) {
        // 当前元素排序比父元素更靠前时，交换
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 往下走到合适的位置形成对结构
     *
     * @param index 位置
     */
    private void heapify(int index) {

    }
}
