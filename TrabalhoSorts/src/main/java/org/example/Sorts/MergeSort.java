package org.example.Sorts;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class MergeSort {

    @Getter
    @Setter
    private List<Integer> lstOriginal;

    @Getter
    @Setter
    private List<Integer> lstOrdenada;

    @Getter
    @Setter
    private Long timeNano;

    public MergeSort(List<Integer> lstOriginal){
        this.lstOriginal = lstOriginal;
        this.lstOrdenada = new ArrayList<>();
    }
    public MergeSort(){
        this.lstOriginal = new ArrayList<>();
        this.lstOrdenada = new ArrayList<>();
    }

    public void mergeSortSerial() {
        Long nanoIni = System.nanoTime();
        if (lstOriginal != null && !lstOriginal.isEmpty()) {
            lstOrdenada = mergeSortSerial(lstOriginal);
        }
        Long nanoFim = System.nanoTime();
        this.timeNano = nanoFim - nanoIni;
    }

    private List<Integer> mergeSortSerial(List<Integer> list) {
        if (list.size() <= 1) {
            return list;
        }

        int middle = list.size() / 2;
        List<Integer> left = list.subList(0, middle);
        List<Integer> right = list.subList(middle, list.size());


        List<Integer> sortedLeft = mergeSortSerial(new ArrayList<>(left));
        List<Integer> sortedRight = mergeSortSerial(new ArrayList<>(right));


        return merge(sortedLeft, sortedRight);
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }


        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }

        return merged;
    }

}