import java.io.*;

public class FileManager {

    int readCashFile(int[] banknote, int[] count) {
        int size = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("cash.txt"));
            String line;

            //Satırları okuyup boş satır varsa geçiyoruz
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.equals("")) continue;

                String[] parts = line.split("\\s+");
                banknote[size] = Integer.parseInt(parts[0]);
                count[size] = Integer.parseInt(parts[1]);
                size++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println("cash.txt could not be read");
        }
        return size;
    }

    void writeCashFile(int[] banknote, int[] count, int size) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("cash.txt"));
            for (int i = 0; i < size; i++) {
                bw.write(banknote[i] + " " + count[i]);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("cash.txt could not be written");
        }
    }
}