package org.planejamente.planejamente.strategy;

import java.util.List;

public interface ISortStrategy<T> {
    void sort(List<T> lista);

    void sortDecrescente(List<T> lista);
}