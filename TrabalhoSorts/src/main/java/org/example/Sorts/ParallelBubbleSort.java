package org.example.Sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class ParallelBubbleSort {
//    public static void main(String[] args) throws Exception {
//        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3,2,-1));
//        int numThreads = 4;
//
//        parallelOddEvenSort(list, numThreads);
//
//        System.out.println("Ordenado: " + list);
//    }

    public static void parallelOddEvenSort(List<Integer> list, int numThreads) throws Exception {
        int n = list.size();
        CyclicBarrier barrier = new CyclicBarrier(numThreads);
        SortThread[] threads = new SortThread[numThreads];

        int chunkSize = (int) Math.ceil((double) n / numThreads);

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, n);
            threads[i] = new SortThread(list, start, end, barrier);
            threads[i].start();
        }

        for (SortThread t : threads) {
            t.join();
        }
    }

    static class SortThread extends Thread {
        private final List<Integer> list;
        private final int start, end;
        private final CyclicBarrier barrier;

        SortThread(List<Integer> list, int start, int end, CyclicBarrier barrier) {
            this.list = list;
            this.start = start;
            this.end = end;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            int n = list.size();
            try {
                for (int phase = 0; phase < n; phase++) {
                    // Alterna entre pares (even) e ímpares (odd)
                    int begin = (phase % 2 == 0) ? start : start + 1;

                    for (int i = begin; i < end - 1; i += 2) {
                        synchronized (list) {
                            if (list.get(i) > list.get(i + 1)) {
                                int temp = list.get(i);
                                list.set(i, list.get(i + 1));
                                list.set(i + 1, temp);
                            }
                        }
                    }

                    // 🔧 Cada thread também precisa verificar se o último par
                    // ultrapassa sua faixa e ainda está dentro dos limites
                    if (end < n && (end % 2 == (phase % 2))) {
                        synchronized (list) {
                            if (list.get(end - 1) > list.get(end)) {
                                int temp = list.get(end - 1);
                                list.set(end - 1, list.get(end));
                                list.set(end, temp);
                            }
                        }
                    }

                    barrier.await(); // sincroniza threads
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
