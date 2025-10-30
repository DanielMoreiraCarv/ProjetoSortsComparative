package org.example.ToCsv;

import lombok.Getter;
import lombok.Setter;
import org.example.Sorts.SortsComparative;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToCsv {
    @Getter
    @Setter
    private List<SortsComparative> lstSorts;

    public void addListagem(SortsComparative comparative) {
        this.lstSorts.add(comparative);
    }

    public ToCsv() {
        lstSorts = new ArrayList<>();
    }

    public void exportToCSV() {
        // Cria a pasta "export" dentro do projeto
        File exportDir = new File("export");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        // Define o caminho completo do arquivo CSV
        String filePath = exportDir.getPath() + "/comparativoSorts.csv";

        // Agora sim abre o FileWriter
        try (FileWriter writer = new FileWriter(filePath)) {

            // Cabeçalho do CSV
            writer.append("Algoritmo,Tempo Serial (ns),Tempo Paralelo (ns),Comparativo (Serial/Paralelo),Tamanho da Lista\n");

            // Dados de cada algoritmo
            for (SortsComparative sc : lstSorts) {
                writer.append(sc.getNomeAlgoritimo()).append(",");
                writer.append(sc.getTimeSetialNano().toString()).append(",");
                writer.append(sc.getTimeParaleloNano().toString()).append(",");
                writer.append(String.valueOf((float) sc.getTimeParaleloNano() / sc.getTimeSetialNano())).append(",");
                writer.append(String.valueOf(sc.getLstOriginal().size())).append("\n");
            }

            System.out.println("✅ CSV exportado com sucesso para: " + filePath);

        } catch (IOException e) {
            System.err.println("❌ Erro ao exportar CSV: " + e.getMessage());
        }
    }
}
