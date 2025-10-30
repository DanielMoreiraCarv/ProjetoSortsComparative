package org.example.Sorts;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class SelectionSort {
    @Getter
    @Setter
    private List<Integer> lstOriginal;

    @Getter
    @Setter
    private List<Integer> lstOrdenada;

    @Getter
    @Setter
    private Long nanoTime;

    public SelectionSort(List<Integer> lstOriginal){
        this.lstOriginal = lstOriginal;
        this.lstOrdenada = new ArrayList<>();
    }

    public SelectionSort(){
        this.lstOriginal = new ArrayList<>();
        this.lstOrdenada = new ArrayList<>();
    }

    public void SelectioSortSerial(){
        Long nanoIni = System.nanoTime();
        if (lstOriginal != null && !lstOriginal.isEmpty()) {
            lstOrdenada = selectionSortSerial(lstOriginal);
        }
        Long nanoFim = System.nanoTime();
        this.nanoTime = nanoFim - nanoIni;
    }

    private List<Integer> selectionSortSerial(List<Integer> lst) {
        if (lst.isEmpty()) {
            return new ArrayList<>();
        }

        int minValue = lst.get(0);
        int indexMin = 0;
        for (int i = 1; i < lst.size(); i++) {
            if (lst.get(i) < minValue) {
                minValue = lst.get(i);
                indexMin = i;
            }
        }

        List<Integer> lstOrdenadaSelect = new ArrayList<>();
        lstOrdenadaSelect.add(minValue);

        List<Integer> restante = new ArrayList<>(lst);
        restante.remove(indexMin);

        lstOrdenadaSelect.addAll(selectionSortSerial(restante));

        return lstOrdenadaSelect;
    }



}
