package org.example.Sorts;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SelectionSortParalelo
{
    @Getter @Setter
    private List<Integer> lstOriginal;

    @Getter @Setter
    private List<Integer> lstOrdenada;

    @Getter @Setter
    private Long nanoTime;

    @Getter @Setter
    private int numThreads; // número de threads configurável

    public SelectionSortParalelo(List<Integer> lstOriginal, int numThreads)
    {
        this.lstOriginal = lstOriginal;
        this.lstOrdenada = new ArrayList<>();
        this.numThreads = numThreads > 0 ? numThreads : Runtime.getRuntime().availableProcessors();
    }

    public SelectionSortParalelo()
    {
        this.lstOriginal = new ArrayList<>();
        this.lstOrdenada = new ArrayList<>();
        this.numThreads = Runtime.getRuntime().availableProcessors();
    }

    public void selectionSortParalelo()
    {
        Long nanoIni = System.nanoTime();

        if (lstOriginal != null && !lstOriginal.isEmpty())
        {
            ForkJoinPool pool = new ForkJoinPool(numThreads); // cria pool com threads definidas
            lstOrdenada = selectionSortParallel(lstOriginal, pool);
            pool.shutdown(); // encerra o pool após o uso
        }

        Long nanoFim = System.nanoTime();
        this.nanoTime = nanoFim - nanoIni;
    }

    private List<Integer> selectionSortParallel(List<Integer> lst, ForkJoinPool pool)
    {
        List<Integer> ordenada = new ArrayList<>();
        List<Integer> copia = new ArrayList<>(lst);

        while (!copia.isEmpty())
        {
            MinResult minResult = pool.invoke(new FindMinTask(copia, 0, copia.size()));
            ordenada.add(minResult.value);
            copia.remove(minResult.index);
        }

        return ordenada;
    }

    private static class MinResult
    {
        int value;
        int index;

        MinResult(int value, int index)
        {
            this.value = value;
            this.index = index;
        }
    }

    private static class FindMinTask extends RecursiveTask<MinResult>
    {
        private final List<Integer> list;
        private final int start;
        private final int end;
        private static final int LIMITE = 500;

        FindMinTask(List<Integer> list, int start, int end)
        {
            this.list = list;
            this.start = start;
            this.end = end;
        }

        @Override
        protected MinResult compute()
        {
            int tamanho = end - start;

            if (tamanho <= LIMITE)
            {
                int minValue = list.get(start);
                int minIndex = start;
                for (int i = start + 1; i < end; i++)
                {
                    if (list.get(i) < minValue)
                    {
                        minValue = list.get(i);
                        minIndex = i;
                    }
                }
                return new MinResult(minValue, minIndex);
            }

            int meio = (start + end) / 2;

            FindMinTask leftTask = new FindMinTask(list, start, meio);
            FindMinTask rightTask = new FindMinTask(list, meio, end);

            leftTask.fork();
            MinResult rightResult = rightTask.compute();
            MinResult leftResult = leftTask.join();

            return (leftResult.value <= rightResult.value) ? leftResult : rightResult;
        }
    }
}
