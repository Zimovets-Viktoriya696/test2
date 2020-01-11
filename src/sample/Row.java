package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс строки матрицы
 */
public class Row {

    // размер строки
    public static final int ROW_SIZE = 10;
    private List<Float> row;

    @Override
    public String toString() {
        return "\nRow{" +
                "row=" + row +
                '}';
    }

    /**
     * Конструктор
     */
    Row() {
        this.row = generateRow();
    }

    /**
     * Метод генерирования случайной строки матрицы
     */
    private ArrayList<Float> generateRow() {

        Random random = new Random();

        ArrayList<Float> newRow = new ArrayList<>();
        for(int i = 0; i < ROW_SIZE; i++) {
            newRow.add((float) (random.nextInt(20) + 40));
        }

        return newRow;
    }

    public void clear() {
        this.row = new ArrayList<>();
        for(int i = 0; i < ROW_SIZE; i++) {
            this.row.add(i, 0.0f);
        }
    }

    public Float get(int index) {
        return row.get(index);
    }

    public List<Float> getAll() {
        return row;
    }

    public void set(int index, float value) {
        row.set(index, value);
    }

    public int size() {
        return row.size();
    }
}