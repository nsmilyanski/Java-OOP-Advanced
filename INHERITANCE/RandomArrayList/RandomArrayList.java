package INHERITANCE.RandomArrayList;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList<E> extends ArrayList  {

    public E getRandomElement(){
        int index = super.size();
        Random random = new Random();
        int randomIndex = random.nextInt(index);
        return (E) remove(randomIndex);
    }
}
