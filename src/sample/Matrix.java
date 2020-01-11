package sample;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

    public static final int SECTION_COUNT = 3;
    private List<Section> sectionList;

    @Override
    public String toString() {
        return "Matrix{" +
                "sectionList=" + sectionList +
                '}';
    }

    /**
     * Конструктор
     */
    public Matrix() {

        // список "матрица-секция"
        sectionList = new ArrayList<>();

        // генерируем столько матриц, сколько секций
        for(int i = 0; i < SECTION_COUNT; i++) {
            sectionList.add(new Section());
        }
    }

    /**
     * Возвращает эталонный вектор матрицы
     */
    public List<Float> getBinaryVector() {

        List<Float> binaryVektor = new ArrayList<>();

        // генерируем столько матриц, сколько секций
        for(int i = 0; i < SECTION_COUNT; i++) {
            List<Float> sectionBinaryVektor = sectionList.get(i).getBinaryVector();
            binaryVektor.addAll(sectionBinaryVektor);
        }

        return binaryVektor;
    }

    public List<Float> getBinaryRow(int index) {
        List<Float> row = new ArrayList<>();

        // генерируем столько матриц, сколько секций
        for(int i = 0; i < SECTION_COUNT; i++) {
            List<Float> sectionRow = sectionList.get(i).getBinaryRow(index).getAll();
            row.addAll(sectionRow);
        }

        return row;
    }

    /**
     * Количество различий между двумя строками
     */
    public static int getRowDiff(List<Float> vektor1, List<Float> vektor2) {
        // длины векторов не совпадают
        if(vektor1.size() != vektor2.size()) return -1;
        int count = 0;

        for(int i = 0; i < vektor1.size(); i++) {
            float var1 = vektor1.get(i);
            float var2 = vektor2.get(i);
            if(var1 != var2) count ++;
        }

        return count;
    }
}
