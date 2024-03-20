package org.planejamente.planejamente.strategy;

import java.util.List;

public interface ISortStrategy<T> {
    void sort(List<T> arr);

    void sortDescending(List<T> arr);
}
