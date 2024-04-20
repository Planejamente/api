//package org.planejamente.planejamente.strategy;
//
//import java.util.List;
//
//public class BubbleSortStrategy<T extends Usuario> implements ISortStrategy<T> {
//
//    @Override
//    public void sort(List<T> lista) {
//        int n = lista.size();
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = 0; j < n - i - 1; j++) {
//                if (lista.get(j).getNome().compareToIgnoreCase(lista.get(j + 1).getNome()) < 0) {
//                    T temp = lista.get(j);
//                    lista.set(j, lista.get(j + 1));
//                    lista.set(j + 1, temp);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void sortDecrescente(List<T> lista) {
//        int n = lista.size();
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = 0; j < n - i - 1; j++) {
//                if (lista.get(j).getNome().compareToIgnoreCase(lista.get(j + 1).getNome()) > 0) {
//                    T temp = lista.get(j);
//                    lista.set(j, lista.get(j + 1));
//                    lista.set(j + 1, temp);
//                }
//            }
//        }
//    }
//}
