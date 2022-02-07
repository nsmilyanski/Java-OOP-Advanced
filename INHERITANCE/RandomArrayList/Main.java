package INHERITANCE.RandomArrayList;

public class Main {
    public static void main(String[] args) {

        RandomArrayList randomArrayList = new RandomArrayList();

        for (int i = 1; i <= 20 ; i++) {
            randomArrayList.add(i);
        }

        System.out.println(randomArrayList.getRandomElement());
    }
}
