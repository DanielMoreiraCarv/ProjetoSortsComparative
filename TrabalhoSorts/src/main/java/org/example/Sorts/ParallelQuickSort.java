package org.example.Sorts;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort
{
    static class QuickSortTask extends RecursiveAction {
        private final List<Integer> list;
        private final int left, right;
        private static final int LIMITE = 500; // tamanho mínimo para paralelizar

        QuickSortTask(List<Integer> list, int left, int right) {
            this.list = list;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (left < right) {
                // evita overhead com partições muito pequenas
                if (right - left <= LIMITE) {
                    quickSortSequencial(list, left, right);
                    return;
                }

                int pivotIndex = partition(list, left, right);

                QuickSortTask leftTask = new QuickSortTask(list, left, pivotIndex - 1);
                QuickSortTask rightTask = new QuickSortTask(list, pivotIndex + 1, right);

                invokeAll(leftTask, rightTask);
            }
        }

        private static int partition(List<Integer> list, int left, int right) {
            int pivot = list.get(right);
            int i = left - 1;

            for (int j = left; j < right; j++) {
                if (list.get(j) <= pivot) {
                    i++;
                    Collections.swap(list, i, j);
                }
            }

            Collections.swap(list, i + 1, right);
            return i + 1;
        }

        private static void quickSortSequencial(List<Integer> list, int left, int right) {
            if (left < right) {
                int pivotIndex = partition(list, left, right);
                quickSortSequencial(list, left, pivotIndex - 1);
                quickSortSequencial(list, pivotIndex + 1, right);
            }
        }
    }

    public static Long parallelQuickSort(List<Integer> list, int numThreads) {
        Long timeStart = System.nanoTime();

        int threads = (numThreads > 0) ? numThreads : Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(threads);

        pool.invoke(new QuickSortTask(list, 0, list.size() - 1));
        pool.shutdown();

        Long timeEnd = System.nanoTime();
        return timeEnd - timeStart;
    }

//    public static void main(String[] args) {
//        List<Integer> list = List.of(9, 4, 6, 2, 7, 3, 8, 1, 5);
//        List<Integer> mutableList = new ArrayList<>(list);
//        Long tempo = parallelQuickSort(mutableList, 4);
//        System.out.println("Ordenado: " + mutableList);
//        System.out.println("Tempo (ns): " + tempo);
//    }
}
