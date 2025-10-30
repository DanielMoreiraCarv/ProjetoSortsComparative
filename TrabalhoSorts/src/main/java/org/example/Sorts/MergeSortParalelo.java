package org.example.Sorts;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MergeSortParalelo
{
    @Getter @Setter
    private List<Integer> lstOriginal;

    @Getter @Setter
    private List<Integer> lstOrdenada;

    @Getter @Setter
    private Long timeNano;

    @Getter @Setter
    private int numThreads; // número de threads configurável

    public MergeSortParalelo(List<Integer> lstOriginal, int numThreads) {
        this.lstOriginal = lstOriginal;
        this.lstOrdenada = new ArrayList<>();
        this.numThreads = numThreads > 0 ? numThreads : Runtime.getRuntime().availableProcessors();
    }

    public MergeSortParalelo() {
        this.lstOriginal = new ArrayList<>();
        this.lstOrdenada = new ArrayList<>();
        this.numThreads = Runtime.getRuntime().availableProcessors();
    }

    public void mergeSortParalelo() {
        Long nanoIni = System.nanoTime();

        if (lstOriginal != null && !lstOriginal.isEmpty()) {
            ForkJoinPool pool = new ForkJoinPool(numThreads); // usa o número definido
            lstOrdenada = pool.invoke(new MergeSortTask(lstOriginal));
            pool.shutdown(); // encerra o pool
        }

        Long nanoFim = System.nanoTime();
        this.timeNano = nanoFim - nanoIni;
    }

    // ---------- Classe interna do MergeSort ----------
    private static class MergeSortTask extends RecursiveTask<List<Integer>> {
        private final List<Integer> list;
        private static final int LIMITE = 500;

        MergeSortTask(List<Integer> list) {
            this.list = list;
        }

        @Override
        protected List<Integer> compute() {
            if (list.size() <= 1) {
                return list;
            }

            if (list.size() <= LIMITE) {
                return mergeSortDireto(list);
            }

            int middle = list.size() / 2;
            List<Integer> left = new ArrayList<>(list.subList(0, middle));
            List<Integer> right = new ArrayList<>(list.subList(middle, list.size()));

            MergeSortTask leftTask = new MergeSortTask(left);
            MergeSortTask rightTask = new MergeSortTask(right);

            leftTask.fork(); // executa esquerda em paralelo
            List<Integer> sortedRight = rightTask.compute();
            List<Integer> sortedLeft = leftTask.join();

            return merge(sortedLeft, sortedRight);
        }

        private static List<Integer> mergeSortDireto(List<Integer> list) {
            if (list.size() <= 1) return list;
            int middle = list.size() / 2;
            List<Integer> left = new ArrayList<>(list.subList(0, middle));
            List<Integer> right = new ArrayList<>(list.subList(middle, list.size()));
            return merge(mergeSortDireto(left), mergeSortDireto(right));
        }

        private static List<Integer> merge(List<Integer> left, List<Integer> right) {
            List<Integer> merged = new ArrayList<>();
            int i = 0, j = 0;

            while (i < left.size() && j < right.size()) {
                if (left.get(i) <= right.get(j)) {
                    merged.add(left.get(i++));
                } else {
                    merged.add(right.get(j++));
                }
            }

            while (i < left.size()) merged.add(left.get(i++));
            while (j < right.size()) merged.add(right.get(j++));
            return merged;
        }
    }
}
