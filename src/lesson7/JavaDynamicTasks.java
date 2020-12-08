package lesson7;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */

    // скорость O(m*n)
    // память O(m*n)

    public static String longestCommonSubSequence(String first, String second) {
        int m = first.length();
        int n = second.length();
        int[][] res = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    res[i][j] = res[i - 1][j - 1] + 1;
                }
                else {
                    res[i][j] = Math.max(res[i - 1][j], res[i][j - 1]);
                }
            }
        }
        StringBuilder output = new StringBuilder();
        while (m > 0 && n > 0) {
            if (first.charAt(m - 1) == second.charAt(n - 1)) {
                output.append(first.charAt(m - 1));
                m--;
                n--;
            }
            else if (res[m][n - 1] == res[m][n]) {
                n--;
            }
            else {
                m--;
            }
        }
        return output.reverse().toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */

    // скорость O(list.size * list.size)
    // время O(list.size)

    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        if (list.isEmpty() || list.size() == 1) return list;
        int[] seqLength = new int[list.size()];
        int[] auxArray = new int[list.size()];
        ArrayList<Integer> outputList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            seqLength[i] = 1;
            auxArray[i] = -1;
            for (int j = 0; j < i; j++) {
                if (list.get(i) > list.get(j)) {
                    if (1 + seqLength[j] > seqLength[i]) {
                        seqLength[i] = 1 + seqLength[j];
                        auxArray[i] = j;
                    }
                }
            }
        }
        int maxLength = 0;
        int position = 0;
        for (int i = 0; i < list.size(); i++) {
            if (seqLength[i] > maxLength) {
                maxLength = seqLength[i];
                position = i;
            }
        }
        while (position != -1) {
            outputList.add(0, list.get(position));
            position = auxArray[position];
        }
        return outputList;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
