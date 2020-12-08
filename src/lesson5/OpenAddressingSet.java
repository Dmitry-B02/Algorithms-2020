package lesson5;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

public class OpenAddressingSet<T> extends AbstractSet<T> {

    private final int bits;

    private final int capacity;

    private final Object[] storage;

    private int size = 0;

    private int startingIndex(Object element) {
        return element.hashCode() & (0x7FFFFFFF >> (31 - bits));
    }

    public OpenAddressingSet(int bits) {
        if (bits < 2 || bits > 31) {
            throw new IllegalArgumentException();
        }
        this.bits = bits;
        capacity = 1 << bits;
        storage = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Проверка, входит ли данный элемент в таблицу
     */
    @Override
    public boolean contains(Object o) {
        int index = startingIndex(o);
        Object current = storage[index];
        while (current != null) {
            if (current.equals(o)) {
                return true;
            }
            index = (index + 1) % capacity;
            current = storage[index];
        }
        return false;
    }

    /**
     * Добавление элемента в таблицу.
     * <p>
     * Не делает ничего и возвращает false, если такой же элемент уже есть в таблице.
     * В противном случае вставляет элемент в таблицу и возвращает true.
     * <p>
     * Бросает исключение (IllegalStateException) в случае переполнения таблицы.
     * Обычно Set не предполагает ограничения на размер и подобных контрактов,
     * но в данном случае это было введено для упрощения кода.
     */
    @Override
    public boolean add(T t) {
        int startingIndex = startingIndex(t);
        int index = startingIndex;
        Object current = storage[index];
        while (current != null && current != deleted) {
            if (current.equals(t)) {
                return false;
            }
            index = (index + 1) % capacity;
            if (index == startingIndex) {
                throw new IllegalStateException("Table is full");
            }
            current = storage[index];
        }
        storage[index] = t;
        size++;
        return true;
    }

    /**
     * Удаление элемента из таблицы
     * <p>
     * Если элемент есть в таблица, функция удаляет его из дерева и возвращает true.
     * В ином случае функция оставляет множество нетронутым и возвращает false.
     * Высота дерева не должна увеличиться в результате удаления.
     * <p>
     * Спецификация: {@link Set#remove(Object)} (Ctrl+Click по remove)
     * <p>
     * Средняя
     */

    // скорость O(1) -- O(n) (исходы: лучший -- худший)
    // память O(1)

    Object deleted = new Object(); // метка

    @Override
    public boolean remove(Object o) {
        int startingIndex = startingIndex(o);
        int index = startingIndex;
        Object current = storage[index];
        while (current != null) {
            if (current.equals(o)) {
                storage[index] = deleted;
                size--;
                return true;
            }
            index = (index + 1) % capacity;
            if (index == startingIndex) return false;
            current = storage[index];
        }
        return false;
    }

    /**
     * Создание итератора для обхода таблицы
     * <p>
     * Не забываем, что итератор должен поддерживать функции next(), hasNext(),
     * и опционально функцию remove()
     * <p>
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     * <p>
     * Средняя (сложная, если поддержан и remove тоже)
     *
     * @return
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new OpenAddressingSetIterator();
    }

    private class OpenAddressingSetIterator implements Iterator<T> {

        private int currentIndex = 0;

        private int auxIndex = -1; // нужен для remove

        private Object currentObj;


        // скорость O(1) -- O(n) (исходы: лучший -- худший)
        // память O(1)
        @Override
        public boolean hasNext() {
            if (size == 0) return false;
            while (currentIndex < capacity && (storage[currentIndex] == null || storage[currentIndex] == deleted))
                currentIndex++;
            return currentIndex < capacity;
        }


        // скорость O(1) -- O(n) (исходы: лучший -- худший)
        // память O(1)
        @Override
        public T next() {
            boolean condition = hasNext();
            if (!condition) throw new IllegalStateException();
            auxIndex = currentIndex;
            currentObj = storage[currentIndex];
            currentIndex++;
            return (T) currentObj;
        }


        // скорость O(1)
        // память O(1)
        @Override
        public void remove() {
            if (auxIndex == -1 || storage[auxIndex] == null) throw new IllegalStateException();
            else storage[auxIndex] = deleted;
            size--;
        }
    }
}
