package org.example.Sorts;


public class bubbleSerial {
    public static Long bubbleSerial(int[] arr){
        Long startTimeNano = System.nanoTime();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j<n-i-1;j++){
                if(arr[j]> arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        Long endTimeNano = System.nanoTime();
        return endTimeNano-startTimeNano;
    }
}
