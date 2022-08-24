package com.chen.algorithm.day22;

import java.util.HashMap;
import java.util.Map;

/**
 * 节点堆
 * 针对 Dijkstra 算法完成的堆结构
 *
 * @author chenzihan
 * @date 2022/08/23
 */
public class NodeHeap {

    /**
     * 堆结构的容器
     */
    private Node[] nodes;

    /**
     * 反向索引表：key -> 节点；value -> 数组中的位置
     */
    private Map<Node, Integer> indexMap;

    /**
     * 距离地图
     */
    private Map<Node, Integer> distanceMap;

    private int size;

    public NodeHeap(int size) {
        nodes = new Node[size];
        this.size = 0;
        indexMap = new HashMap<>();
        distanceMap = new HashMap<>();
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 弹出堆顶元素
     * 将堆顶元素和最后一个元素交换，然后删除掉原来的对顶元素，对新的对顶元素进行 heapify
     *
     * @return {@link Record}
     */
    public Record pop() {
        if (!isEmpty()) {
            Node ans = nodes[0];
            int distance = distanceMap.get(ans);
            swap(0, size - 1);
            distanceMap.remove(ans);
            indexMap.put(ans, -1);
            nodes[--size] = null;
            heapify(0, size);
            return new Record(ans, distance);
        }
        return null;
    }

    public void addOrUpdateOrIgnore(Node node, int distance) {
        // update or ignore
        if (indexMap.containsKey(node) && indexMap.get(node) != -1) {
            distance = Math.min(distanceMap.get(node), distance);
            distanceMap.put(node, distance);
            heapInsert(indexMap.get(node));
        }
        // add
        if (!indexMap.containsKey(node)) {
            indexMap.put(node, size);
            distanceMap.put(node, distance);
            nodes[size] = node;
            heapInsert(size++);
        }
    }

    /**
     * 元素在堆中上升
     *
     * @param index 指数
     */
    private void heapInsert(int index) {
        // 我比父节点小时，交换
        while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 元素在堆中下沉
     *
     * @param index 指数
     * @param size  大小
     */
    private void heapify(int index, int size) {
        int left = index * 2 + 1;
        // 我的左子节点比我小时，进行讨论交换
        while (left < size) {
            int min = left;
            // 当存在右节点，并且右节点的distance小于左节点时
            if (left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])) {
                min = left + 1;
            }
            if (distanceMap.get(nodes[index]) <= distanceMap.get(nodes[min])) {
                break;
            }
            swap(index, min);
            index = min;
            left = 2 * index + 1;
        }
    }

    private void swap(int index1, int index2) {
        Node node1 = nodes[index1];
        Node node2 = nodes[index2];
        indexMap.put(node1, index2);
        indexMap.put(node2, index1);
        nodes[index1] = node2;
        nodes[index2] = node1;
    }
}
