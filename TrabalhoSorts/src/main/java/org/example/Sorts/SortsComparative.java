package org.example.Sorts;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SortsComparative
{

    @Getter
    @Setter
    private List<Integer> lstOriginal;

    @Getter
    @Setter
    private List<Integer> lstOrdenada;

    @Getter
    @Setter
    private String nomeAlgoritimo;

    @Getter
    @Setter
    private Long timeSetialNano;

    @Getter
    @Setter
    private Long timeParaleloNano;

    @Getter
    @Setter
    private Double timeComparativeNano;


    public SortsComparative(List<Integer> lstOriginal, List<Integer> lstOrdenada, String nomeAlgoritimo,
                            Long timeSetialNano, Long timeParaleloNano)
    {
        this.lstOriginal = lstOriginal;
        this.lstOrdenada = lstOrdenada;
        this.nomeAlgoritimo = nomeAlgoritimo;
        this.timeSetialNano = timeSetialNano;
        this.timeParaleloNano = timeParaleloNano;
        this.timeComparativeNano = (double) (timeSetialNano / timeParaleloNano);
    }
}
