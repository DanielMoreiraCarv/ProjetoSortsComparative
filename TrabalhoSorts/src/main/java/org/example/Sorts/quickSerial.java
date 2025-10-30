package org.example.Sorts;

public class quickSerial {

    public static Long quickSeria(int[] arr,int low,int high){
        Long startTimeNano1 = System.nanoTime();
        if(low < high){
            int pi = partition(arr,low,high);
            quickSeria(arr, low, pi-1);
            quickSeria(arr, pi+1, high);
        }
        Long endTimeNano = System.nanoTime();
        return endTimeNano-startTimeNano1;
    }

    private static int partition(int[] arr,int low,int high){
        int pivot = arr[high];
        int i = (low-1);
        for(int j = low;j<high;j++){
            if(arr[j]<= pivot){
                i++;
                int temp = arr[i];
                arr[i]= arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return  i+1;
    }
}
