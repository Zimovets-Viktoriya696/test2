package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс представления матрицы
 */
public class Section {

    // размер строки
    public static final int ROW_COUNT = 10;
    public static final int VECTOR_DIFF_CONST = 5;
    // секция матрицы
    private List<Row> section;
    // бинарная матрица
    private List<Row> binaryMatrix;
    // средний вектор, бинарный, верхний и нижний
    private List<Float> meanVector;
    private List<Float> binaryVector;
    private List<Float> highMeanVector;
    private List<Float> lowMeanVector;

    @Override
    public String toString() {
        return "Section{" +
                "section=" + section +
                '}';
    }

    public List<Float> getBinaryVector() {
        return binaryVector;
    }

    public Row getBinaryRow(int index) {
        return binaryMatrix.get(index);
    }

    /**
     * Конструктор
     */
    public Section() {

        // генерем матрицу
        section = generateSection();

        // считаем вектор средних значений
        meanVector = createMeanVektor(section);

        // из вектора средних получаем 2 вектора
        highMeanVector = new ArrayList<>(meanVector);
        lowMeanVector = new ArrayList<>(meanVector);

        // изменяем значения векторов
        modifyVektor(VECTOR_DIFF_CONST, highMeanVector);
        modifyVektor(-VECTOR_DIFF_CONST, lowMeanVector);

       /* System.out.println(highMeanVector);
        System.out.println(lowMeanVector);
        System.out.println(section);*/

        // создаем бинарную матрицу
        binaryMatrix = createBinarySection(highMeanVector, lowMeanVector, section);



        // считаем эталонный вектор
        binaryVector = createMeanVektor(binaryMatrix);
        for (int i = 0; i < binaryVector.size(); i++) {
            if(binaryVector.get(i) > 0.5) {
                binaryVector.set(i, 1f);
            } else {
                binaryVector.set(i, 0f);
            }
        }

    }

    /**
     * Метод генерирования случайной матрицы
     */
    private static ArrayList<Row> generateSection() {

        // генерируем строки и заполняем матрицу
        ArrayList<Row> newMatrix = new ArrayList<>();
        for(int i = 0; i < ROW_COUNT; i++) {
            newMatrix.add(new Row());
        }

        return newMatrix;
    }

    /**
     * Считаем вектор средних значений
     */
    private ArrayList<Float> createMeanVektor(List<Row> section) {
        ArrayList<Float> vektor = new ArrayList<>();

        for (int i = 0; i < ROW_COUNT; i++) {
            Row row = section.get(i);
            float columnSum = 0;
            for (int j = 0; j < Row.ROW_SIZE; j++) {
                columnSum += row.get(j);
            }
            vektor.add(columnSum / ROW_COUNT);
        }

        return vektor;
    }

    /**
     * Модифицируем вектор на значение modifyValue
     */
    private void modifyVektor(int modifyValue, List<Float> vektor) {
        for(int i = 0; i < vektor.size(); i++) {
            vektor.set(i, vektor.get(i) + modifyValue);
        }
    }

    /**
     * Генерем бинарную матрицу
     */
    public List<Row> createBinarySection(List<Float> highVektor, List<Float> lowVektor, List<Row> section) {
        List<Row> binaryMatrix = new ArrayList<>();


        for (Row row : section) {
            Row binaryRow = new Row();
            binaryRow.clear();
            for (int i = 0; i < Section.ROW_COUNT; i++) {
                for (int j = 0; j < Row.ROW_SIZE; j++) {
                    if (row.get(j) >= lowVektor.get(i) && row.get(j) <= highVektor.get(i)) {
                        binaryRow.set(i, 1.0f);
                    } else {
                        binaryRow.set(i, 0.0f);
                    }
                }
            }
            binaryMatrix.add(binaryRow);

        }

        return binaryMatrix;
    }
}