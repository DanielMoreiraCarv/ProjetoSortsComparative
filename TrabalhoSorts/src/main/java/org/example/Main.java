package org.example;

import org.example.Sorts.*;
import org.example.ConverterArrayToList.*;
import org.example.ToCsv.ToCsv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Main {
    public static void main(String[] args) throws Exception {
        Random randomGenerator = new Random();
        List<Integer> lst = new ArrayList<>();
        int size = 800;
        for(int i = 0; i<size ; i++){
            lst.add(randomGenerator.nextInt(200));
        }
        //Merges
        MergeSort mergeSort = new MergeSort(lst);
        mergeSort.mergeSortSerial();
        MergeSortParalelo mergeSortParalelo = new MergeSortParalelo(lst,4);
        MergeSortParalelo mergeSortParalelo2 = new MergeSortParalelo(lst,3);
        MergeSortParalelo mergeSortParalelo3 = new MergeSortParalelo(lst,2);
        MergeSortParalelo mergeSortParalelo4 = new MergeSortParalelo(lst,5);
        MergeSortParalelo mergeSortParalelo5 = new MergeSortParalelo(lst,6);
        mergeSortParalelo.mergeSortParalelo();
        mergeSortParalelo2.mergeSortParalelo();
        mergeSortParalelo3.mergeSortParalelo();
        mergeSortParalelo4.mergeSortParalelo();
        mergeSortParalelo5.mergeSortParalelo();
        System.out.println("Medições Merge: (4 Threads)");
        System.out.println("Serial: "+ mergeSort.getTimeNano());
        System.out.println("Paralelo: "+ mergeSortParalelo.getTimeNano());
        System.out.println((float)mergeSortParalelo.getTimeNano()/ mergeSort.getTimeNano());
        SortsComparative comparativeMerge = new SortsComparative(lst,mergeSort.getLstOrdenada(),"Merge Sort(4 Threads)", mergeSort.getTimeNano(), mergeSortParalelo.getTimeNano());
        SortsComparative comparativeMerge2 = new SortsComparative(lst,mergeSort.getLstOrdenada(),"Merge Sort(3 Threads)", mergeSort.getTimeNano(), mergeSortParalelo2.getTimeNano());
        SortsComparative comparativeMerge3 = new SortsComparative(lst,mergeSort.getLstOrdenada(),"Merge Sort(2 Threads)", mergeSort.getTimeNano(), mergeSortParalelo3.getTimeNano());
        SortsComparative comparativeMerge4 = new SortsComparative(lst,mergeSort.getLstOrdenada(),"Merge Sort(5 Threads)", mergeSort.getTimeNano(), mergeSortParalelo4.getTimeNano());
        SortsComparative comparativeMerge5 = new SortsComparative(lst,mergeSort.getLstOrdenada(),"Merge Sort(6 Threads)", mergeSort.getTimeNano(), mergeSortParalelo5.getTimeNano());


        //Selection
        SelectionSort select = new SelectionSort(lst);
        select.SelectioSortSerial();
        SelectionSortParalelo selectP = new SelectionSortParalelo(lst,4);
        SelectionSortParalelo selectP2 = new SelectionSortParalelo(lst,3);
        SelectionSortParalelo selectP3 = new SelectionSortParalelo(lst,2);
        SelectionSortParalelo selectP4 = new SelectionSortParalelo(lst,5);
        SelectionSortParalelo selectP5 = new SelectionSortParalelo(lst,6);
        selectP.selectionSortParalelo();
        selectP2.selectionSortParalelo();
        selectP3.selectionSortParalelo();
        selectP4.selectionSortParalelo();
        selectP5.selectionSortParalelo();
        System.out.println("Medições Selection: ");
        System.out.println("Serial: "+select.getNanoTime());
        System.out.println("Paralelo: "+selectP.getNanoTime());
        System.out.println((float)selectP.getNanoTime()/select.getNanoTime());
        SortsComparative comparativeSelection = new SortsComparative(lst,select.getLstOrdenada(),"Selection Sort(4 Threads)", select.getNanoTime(), selectP.getNanoTime());
        SortsComparative comparativeSelection2 = new SortsComparative(lst,select.getLstOrdenada(),"Selection Sort(3 Threads)", select.getNanoTime(), selectP2.getNanoTime());
        SortsComparative comparativeSelection3 = new SortsComparative(lst,select.getLstOrdenada(),"Selection Sort(2 Threads)", select.getNanoTime(), selectP3.getNanoTime());
        SortsComparative comparativeSelection4 = new SortsComparative(lst,select.getLstOrdenada(),"Selection Sort(5 Threads)", select.getNanoTime(), selectP4.getNanoTime());
        SortsComparative comparativeSelection5 = new SortsComparative(lst,select.getLstOrdenada(),"Selection Sort(6 Threads)", select.getNanoTime(), selectP5.getNanoTime());

        int[] arr = ConverterArrayToList.convertListToArray(lst);
        List<Integer> lst2 = new ArrayList<>();
        List<Integer> lst22 = new ArrayList<>();
        List<Integer> lst23 = new ArrayList<>();
        List<Integer> lst24 = new ArrayList<>();
        List<Integer> lst25 = new ArrayList<>();
        List<Integer> lst3 = new ArrayList<>();
        List<Integer> lst32 = new ArrayList<>();
        List<Integer> lst33 = new ArrayList<>();
        List<Integer> lst34 = new ArrayList<>();
        List<Integer> lst35 = new ArrayList<>();
        for(int i = 0; i<lst.size(); i++){
            lst2.add(lst.get(i));
            lst22.add(lst.get(i));
            lst23.add(lst.get(i));
            lst24.add(lst.get(i));
            lst25.add(lst.get(i));
            lst3.add(lst.get(i));
            lst32.add(lst.get(i));
            lst33.add(lst.get(i));
            lst34.add(lst.get(i));
            lst35.add(lst.get(i));
        }
        //Quick
        Long nanoTimeSerialQuick = quickSerial.quickSeria(arr,0,arr.length-1);
        Long nanoTimeParallelQuick = ParallelQuickSort.parallelQuickSort(lst2,4);
        Long nanoTimeParallelQuick2 = ParallelQuickSort.parallelQuickSort(lst22,3);
        Long nanoTimeParallelQuick3 = ParallelQuickSort.parallelQuickSort(lst23,2);
        Long nanoTimeParallelQuick4 = ParallelQuickSort.parallelQuickSort(lst24,5);
        Long nanoTimeParallelQuick5 = ParallelQuickSort.parallelQuickSort(lst25,6);
        System.out.println("Medições Quick: ");
        System.out.println("Serial: "+nanoTimeSerialQuick);
        System.out.println("Paralelo: "+nanoTimeParallelQuick);
        System.out.println((float)nanoTimeParallelQuick/nanoTimeSerialQuick);
        SortsComparative comparativeQuick = new SortsComparative(lst,lst2,"Quick Sort(4 Threads)",nanoTimeSerialQuick,nanoTimeParallelQuick);
        SortsComparative comparativeQuick2 = new SortsComparative(lst,lst22,"Quick Sort(3 Threads)",nanoTimeSerialQuick,nanoTimeParallelQuick2);
        SortsComparative comparativeQuick3 = new SortsComparative(lst,lst23,"Quick Sort(2 Threads)",nanoTimeSerialQuick,nanoTimeParallelQuick3);
        SortsComparative comparativeQuick4 = new SortsComparative(lst,lst24,"Quick Sort(5 Threads)",nanoTimeSerialQuick,nanoTimeParallelQuick4);
        SortsComparative comparativeQuick5 = new SortsComparative(lst,lst25,"Quick Sort(6 Threads)",nanoTimeSerialQuick,nanoTimeParallelQuick5);

        //Bubble
        int[] arr2 = ConverterArrayToList.convertListToArray(lst);
        Long nanoTimeSerialBubble = bubbleSerial.bubbleSerial(arr2);
        System.out.println("Medições Bubble: ");
        System.out.println("Serial: "+nanoTimeSerialBubble);
        Long nanoTimeStart = System.nanoTime();
        ParallelBubbleSort.parallelOddEvenSort(lst3,4);
        Long nanoTimeEnd = System.nanoTime();
        Long nanoTimeStart2 = System.nanoTime();
        ParallelBubbleSort.parallelOddEvenSort(lst32,3);
        Long nanoTimeEnd2 = System.nanoTime();
        Long nanoTimeStart3 = System.nanoTime();
        ParallelBubbleSort.parallelOddEvenSort(lst33,2);
        Long nanoTimeEnd3 = System.nanoTime();
        Long nanoTimeStart4 = System.nanoTime();
        ParallelBubbleSort.parallelOddEvenSort(lst34,5);
        Long nanoTimeEnd4 = System.nanoTime();
        Long nanoTimeStart5 = System.nanoTime();
        ParallelBubbleSort.parallelOddEvenSort(lst35,6);
        Long nanoTimeEnd5 = System.nanoTime();

        Long nanoTime = nanoTimeEnd-nanoTimeStart;
        Long nanoTime2 = nanoTimeEnd2-nanoTimeStart2;
        Long nanoTime3 = nanoTimeEnd3-nanoTimeStart3;
        Long nanoTime4 = nanoTimeEnd4-nanoTimeStart4;
        Long nanoTime5 = nanoTimeEnd5-nanoTimeStart5;
        System.out.println("Paralelo: "+nanoTime);
        System.out.println((float)nanoTime/nanoTimeSerialBubble);
        SortsComparative comparativeBublle = new SortsComparative(lst,lst3,"Bubble Sort(4 Threads)",nanoTimeSerialBubble,nanoTime);
        SortsComparative comparativeBublle2 = new SortsComparative(lst,lst32,"Bubble Sort(3 Threads)",nanoTimeSerialBubble,nanoTime2);
        SortsComparative comparativeBublle3 = new SortsComparative(lst,lst33,"Bubble Sort(2 Threads)",nanoTimeSerialBubble,nanoTime3);
        SortsComparative comparativeBublle4 = new SortsComparative(lst,lst34,"Bubble Sort(5 Threads)",nanoTimeSerialBubble,nanoTime4);
        SortsComparative comparativeBublle5 = new SortsComparative(lst,lst35,"Bubble Sort(6 Threads)",nanoTimeSerialBubble,nanoTime5);

        //Criação do CSV
        List<SortsComparative> lstComparative = new ArrayList<>();
        lstComparative.add(comparativeMerge);
        lstComparative.add(comparativeMerge2);
        lstComparative.add(comparativeMerge3);
        lstComparative.add(comparativeMerge4);
        lstComparative.add(comparativeMerge5);
        lstComparative.add(comparativeSelection);
        lstComparative.add(comparativeSelection2);
        lstComparative.add(comparativeSelection3);
        lstComparative.add(comparativeSelection4);
        lstComparative.add(comparativeSelection5);
        lstComparative.add(comparativeQuick);
        lstComparative.add(comparativeQuick2);
        lstComparative.add(comparativeQuick3);
        lstComparative.add(comparativeQuick4);
        lstComparative.add(comparativeQuick5);
        lstComparative.add(comparativeBublle);
        lstComparative.add(comparativeBublle2);
        lstComparative.add(comparativeBublle3);
        lstComparative.add(comparativeBublle4);
        lstComparative.add(comparativeBublle5);

        ToCsv toCsv = new ToCsv();

        toCsv.setLstSorts(lstComparative);

        toCsv.exportToCSV();
    }
}