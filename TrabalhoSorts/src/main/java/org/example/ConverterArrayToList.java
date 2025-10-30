package org.example;

import java.util.ArrayList;
import java.util.List;

public class ConverterArrayToList {
    public static List<Integer> converterArrayToList(int[] array){
        List<Integer> lstAuxiliar = new ArrayList<>();
        for(int i = 0; i<array.length ; i++){
            lstAuxiliar.add(array[i]);
        }
        return lstAuxiliar;
    }

    public static int[] convertListToArray(List<Integer> lst) {
        int[] array = new int[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            array[i] = lst.get(i);
        }
        return array;
    }

}
