package org.planejamente.planejamente.strategy;

import org.planejamente.planejamente.entities.Usuario;

import java.util.List;

public class SelectionSortStrategy<T extends Usuario> implements ISortStrategy<T> {

    @Override
    public void sort(List<T> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j).getNome().compareToIgnoreCase(arr.get(minIndex).getNome()) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = arr.get(i);
                arr.set(i, arr.get(minIndex));
                arr.set(minIndex, temp);
            }
        }
    }

    @Override
    public void sortDescending(List<T> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j).getNome().compareToIgnoreCase(arr.get(maxIndex).getNome()) > 0) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                T temp = arr.get(i);
                arr.set(i, arr.get(maxIndex));
                arr.set(maxIndex, temp);
            }
        }
    }
}