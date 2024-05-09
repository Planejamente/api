package org.planejamente.planejamente.oredenacao;


import org.planejamente.planejamente.entity.usuario.Psicologo;

import java.util.List;

public class QuickSort<T extends Psicologo> {
    public static <T extends Psicologo> void ordenarQuickSort(List<T> psicologos) {
        ordenarQuickSortRecursivo(psicologos, 0, psicologos.size() - 1);
    }

    private static <T extends Psicologo> void ordenarQuickSortRecursivo(List<T> psicologos, int inicio, int fim) {
        if (inicio < fim) {
            int indiceParticao = particionar(psicologos, inicio, fim);
            ordenarQuickSortRecursivo(psicologos, inicio, indiceParticao - 1);
            ordenarQuickSortRecursivo(psicologos, indiceParticao + 1, fim);
        }
    }

    private static <T extends Psicologo> int particionar(List<T> psicologos, int inicio, int fim) {
        T pivo = psicologos.get(fim);
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (psicologos.get(j).getNome().compareTo(pivo.getNome()) < 0) {
                i++;
                trocar(psicologos, i, j);
            }
        }

        trocar(psicologos, i + 1, fim);
        return i + 1;
    }

    private static <T extends Psicologo> void trocar(List<T> psicologos, int i, int j) {
        T temporario = psicologos.get(i);
        psicologos.set(i, psicologos.get(j));
        psicologos.set(j, temporario);
    }
}

