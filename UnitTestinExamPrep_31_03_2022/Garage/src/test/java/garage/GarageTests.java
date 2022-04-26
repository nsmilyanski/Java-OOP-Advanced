package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    private Garage garage;
    private Car mercedes;
    private Car audi;
    private Car bmw;

    @Before
    public void setup() {
        this.garage = new Garage();
        mercedes = new Car("Mercedes", 250, 100000.50);
        audi = new Car("Audi", 220, 90000.50);
        bmw = new Car("BMW", 270, 110000.50);
    }


    @Test
    public void testAddMethod() {
        fillGarage();


        Assert.assertEquals(3, garage.getCount());
        Assert.assertEquals("Mercedes", garage.getCars().get(0).getBrand());
        Assert.assertEquals("Audi", garage.getCars().get(1).getBrand());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAllMethodIfThrowsException() {
        garage.addCar(null);
    }

    @Test
    public void getMostExpensiveCar() {
        fillGarage();

        Car theMostExpensiveCar = garage.getTheMostExpensiveCar();

        Assert.assertEquals("BMW", theMostExpensiveCar.getBrand());

    }



    @Test
    public void findCarByBrand() {
        fillGarage();
        List<Car> audi = garage.findAllCarsByBrand("Audi");

        Assert.assertEquals("Audi", audi.get(0).getBrand());

    }
    @Test
    public void getCarsWithSpeedAbove() {
        fillGarage();
        List<Car> allCarsWithMaxSpeedAbove = garage.findAllCarsWithMaxSpeedAbove(250);

        Assert.assertEquals("BMW", allCarsWithMaxSpeedAbove.get(0).getBrand());
        Assert.assertEquals(1 , allCarsWithMaxSpeedAbove.size());
    }

    @Test
    public void testGetMethod() {
        fillGarage();

        List<Car> cars = garage.getCars();

        Assert.assertEquals(3, cars.size());
    }

    private void fillGarage() {
        garage.addCar(mercedes);
        garage.addCar(audi);
        garage.addCar(bmw);
    }

}