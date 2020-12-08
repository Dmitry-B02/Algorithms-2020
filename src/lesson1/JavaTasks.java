package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static lesson1.Sorts.mergeSort;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */

    // скорость O(n)
    // память O(n)

    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        double minT = -2730;
        double maxT = 5000;
        int[] array = new int[(int) (maxT - minT + 1)]; // массив, индексы которого = температура * 10 + 2730 (+1 т.к. включая границы)
        BufferedReader br = new BufferedReader(new FileReader(inputName));
        String str = br.readLine();

        while (str != null) {
            int aux = (int) (Double.parseDouble(str) * 10 + 2730);
            array[aux]++;
            str = br.readLine();
        }

        try (BufferedWriter fw = new BufferedWriter(new FileWriter(outputName))) {
            for (int i = 0; i < array.length; i++) {
                while (array[i] > 0) {
                    String aux = Double.toString(((double) (i - 2730)) / 10);
                    fw.write(aux);
                    fw.write(System.lineSeparator());
                    array[i]--;
                }
            }
        }
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */

    // скорость O(n)
    // память O(n)

    static public void sortSequence(String inputName, String outputName) throws IOException {
        HashMap<Integer, Integer> map = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(inputName));
        String str = br.readLine();
        ArrayList<Integer> list = new ArrayList<>();

        while (str != null) { // создаю мап, в котором подсчитано количество каждой из цифр
            int num = Integer.parseInt(str);
            list.add(num);
            int count = 1;
            if (!map.containsKey(num)) {
                map.put(num, count);
            } else {
                count = map.get(num) + 1;
                map.replace(num, count);
            }
            str = br.readLine();
        }

        int maxCount = 0;
        int maxCountKey = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) { // выбираю элемент, который нужно перенести
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxCountKey = entry.getKey();
            } else if (entry.getValue() == maxCount && entry.getKey() < maxCountKey) {
                maxCountKey = entry.getKey();
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputName))) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != maxCountKey) {
                    bw.write(list.get(i) + System.lineSeparator());
                }
            }
            while (maxCount != 0) {
                bw.write(maxCountKey + System.lineSeparator());
                maxCount--;
            }
        }
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
