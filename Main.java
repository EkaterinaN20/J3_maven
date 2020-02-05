import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    // задание №1.

    public static void swap(Object[] arr, int index1, int index2) {
        Object tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    // задание №2.
    public static <T> ArrayList<T> toArrayList(T[] arr) {
        return new ArrayList<T>(Arrays.asList(arr));
    }


    public static void main(String[] args) {
        // задание №1.
        String [] arrStr = new String [5];
        Integer [] arrInt = new Integer[4];
        arrInt[0] = 1;
        arrInt[1] = 3;
        arrInt[2] = 4;
        arrInt[3] = -7;

        arrStr[0] = "ABC";
        arrStr[1] = "BCD";
        arrStr[2] = "DCE";
        arrStr[3] = "CED";
        arrStr[4] = "Ggdfs";

        System.out.println("Было: " + Arrays.asList(arrInt));
        swap(arrInt, 0, 3);
        System.out.println("Стало: " + Arrays.asList(arrInt));

        System.out.println("Было: " + Arrays.asList(arrStr));
        swap(arrStr, 2, 3);
        System.out.println("Стало: " + Arrays.asList(arrStr));

        // задание №2.
        ArrayList<String> AListStr = toArrayList(arrStr);
        ArrayList<Integer> AListInt = toArrayList(arrInt);

        // задание №3.
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();
        Apple apple4 = new Apple();

        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        Orange orange3 = new Orange();

        Box<Apple> box1 = new Box<Apple>(apple1, apple2, apple3, apple4);
        Box<Orange> box2 = new Box<Orange>(orange1, orange2, orange3);
        System.out.println();
        System.out.println("Вес фруктов в коробке 1 - " + box1.getWeight());
        System.out.println("Вес фруктов в коробке 2 - " + box2.getWeight());
        System.out.println();
        System.out.println("Коробки одинаковы по весу? " + box1.compare(box2));
        Box<Orange> box3 = new Box<Orange>();
        System.out.println("Пересыпаем фрукты из коробки 2 в коробку 3");
        box2.transfer(box3);
        System.out.println("Вес фруктов в коробке 2 - " + box2.getWeight());
        System.out.println("Вес фруктов в коробке 3 - " + box3.getWeight());
    }
    // задание про  лямбду


    }


