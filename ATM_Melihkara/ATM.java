public class ATM {

    FileManager fileManager;

    int[] banknote = new int[30];
    int[] count = new int[100];
    int size = 0;

    ATM(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    void readCashFile() {
        size = fileManager.readCashFile(banknote, count);
        sortDescending();
    }

    void writeCashFile() {
        fileManager.writeCashFile(banknote, count, size);
    }

    void sortDescending() {
        //Buuble sort ile büyükten küçüğe sıralıyoruz
        //2 for loopunun sebebi hem banknot değerlerini hem sayılarını sıralamak ki karışmasınlar
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (banknote[j] < banknote[j + 1]) {
                    int tmp = banknote[j];
                    banknote[j] = banknote[j + 1];
                    banknote[j + 1] = tmp;

                    tmp = count[j];
                    count[j] = count[j + 1];
                    count[j + 1] = tmp;
                }
            }
        }
    }

    int BanknoteExist(int value) {
        for (int i = 0; i < size; i++) {
            if (banknote[i] == value) return i;
        }
        return -1;
    }

    int totalMoney() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += banknote[i] * count[i];
        }
        return sum;
    }

    void Deposit(int value, int pieces) {
        if (value <= 0 || pieces <= 0) {
            System.out.println("Invalid deposit values");
            return;
        }

        int banknoteIndex = BanknoteExist(value);
        if (banknoteIndex != -1) {
            count[banknoteIndex] += pieces;
        } else {
            banknote[size] = value;
            count[size] = pieces;
            size++;
        }

        sortDescending();
        writeCashFile();
        System.out.println("Deposit completed");
    }

    boolean Withdraw(int amount) {
        if (amount <= 0)
            return false;
        if (amount > totalMoney())
            return false;

        int[] used = new int[size];

        boolean ok = solve(0, amount, used);
        if (!ok) return false;

        for (int i = 0; i < size; i++) {
            count[i] -= used[i];
        }

        writeCashFile();
        return true;
    }

    //i took help from GPT for this part, i couldn't implement the banknote withdraw algorithm
    boolean solve(int i, int remaining, int[] used) {
        if (remaining == 0) return true;
        if (i == size) return false;

        int value = banknote[i];
        int limit = Math.min(count[i], remaining / value);

        for (int take = limit; take >= 0; take--) {
            used[i] = take;
            int newRemaining = remaining - take * value;

            if (solve(i + 1, newRemaining, used)) return true;
        }

        used[i] = 0;
        return false;
    }
}