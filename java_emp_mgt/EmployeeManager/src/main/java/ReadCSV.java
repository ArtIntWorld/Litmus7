import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {

    public List<String[]> readCSV(String filename) {
        String line;
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br =  new BufferedReader(new FileReader(filename))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] celldata = line.split(",");
                
                data.add(celldata);
            }
        } catch (IOException e) {
            System.err.println("File Error: " + e.getMessage());
        }
        return data;
    }
}
