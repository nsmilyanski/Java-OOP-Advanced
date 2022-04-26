package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {
    private Farm farm;
    private Animal lion;
    private Animal dog;
    private Animal cat;

    @Before
    public void setup() {
        lion = new Animal("lion", 100);
        dog = new Animal("dog", 70);
        cat = new Animal("cat", 50);

        farm = new Farm("villians", 25);
    }

    @Test
    public void testAddMethod() {
        farm.add(lion);
        farm.add(dog);
        farm.add(cat);

        int count = farm.getCount();

        Assert.assertEquals(3, count);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMethodToAddSameAnimal() {
        farm.add(lion);
        farm.add(lion);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMethodToAddMoreAnimals() {
        Farm farm1 = new Farm("Toshko", 1);

        farm1.add(lion);
        farm1.add(dog);
    }

    @Test
    public void testRemoveMethod() {
        farm.add(lion);
        farm.add(dog);


        boolean remove = farm.remove(lion.getType());

        Assert.assertEquals(true, remove);

        boolean remove1 = farm.remove(cat.getType());

        Assert.assertEquals(false, remove1);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testToSetCapacityLowerThanZero() {
        Farm farm1 = new Farm("Toshko", -5);
    }

    @Test(expected = NullPointerException.class)
    public void testToSetEmptyName() {
        Farm farm1 = new Farm("  ", 10);
    }

    @Test
    public void testGetName() {
        String name = farm.getName();

        Assert.assertEquals("villians", name);
    }

    @Test
    public void testGetCapacity() {
        int capacity = farm.getCapacity();

        Assert.assertEquals(25, capacity);
    }
}
