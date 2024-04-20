//package org.planejamente.planejamente.strategy;
//
//import java.util.List;
//
//public class SelectionSortStrategy<T extends Usuario> implements ISortStrategy<T> {
//
//    @Override
//    public void sort(List<T> lista) {
//        int n = lista.size();
//        for (int i = 0; i < n - 1; i++) {
//            int minIndex = i;
//            for (int j = i + 1; j < n; j++) {
//                if (lista.get(j).getNome().compareToIgnoreCase(lista.get(minIndex).getNome()) < 0) {
//                    minIndex = j;
//                }
//            }
//            if (minIndex != i) {
//                T temp = lista.get(i);
//                lista.set(i, lista.get(minIndex));
//                lista.set(minIndex, temp);
//            }
//        }
//    }
//
//    @Override
//    public void sortDecrescente(List<T> lista) {
//        int n = lista.size();
//        for (int i = 0; i < n - 1; i++) {
//            int maxIndex = i;
//            for (int j = i + 1; j < n; j++) {
//                if (lista.get(j).getNome().compareToIgnoreCase(lista.get(maxIndex).getNome()) > 0) {
//                    maxIndex = j;
//                }
//            }
//            if (maxIndex != i) {
//                T temp = lista.get(i);
//                lista.set(i, lista.get(maxIndex));
//                lista.set(maxIndex, temp);
//            }
//        }
//    }
//}