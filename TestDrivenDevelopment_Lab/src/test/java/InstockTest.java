import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InstockTest {
    private ProductStock inStock;

    @Before
    public void setup() {
        inStock = new Instock();
    }




    @Test
   public void testAddMethodIfWorks() {
        Instock instock = new Instock();
        Product product1 = new Product("bread", 1.90, 2);
        Product product2 = new Product("water", 1.20, 3);
        Product product3 = new Product("wine", 3.20, 4);


        instock.add(product1);
        instock.add(product2);

        Assert.assertTrue(instock.contains(product1));
        Assert.assertTrue(instock.contains(product2));

        Assert.assertFalse(instock.contains(product3));

    }

    @Test
    public void testCountMethod() {
        fillStock();

        Assert.assertEquals(3, inStock.getCount());

    }

    @Test
    public void testMethodIfFindOnIndex() {
        fillStock();

        Product product = inStock.find(2);
        Assert.assertEquals(product.getLabel(), "wine");

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIfMethodThrowException() {
        fillStock();
        inStock.find(3);
    }

    @Test
    public void testChangeQuantityMethod() {
        fillStock();

        inStock.changeQuantity("bread", 5);

        Product product = inStock.find(0);

        Assert.assertEquals(5, product.getQuantity());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeMethodIfThrowException() {
        fillStock();

        inStock.changeQuantity("apple", 10);

    }

    @Test
    public void testFindByLabelMethod() {
        fillStock();

        Product water = inStock.findByLabel("water");

        Assert.assertEquals("water", water.getLabel());
    }


    @Test(expected = IllegalArgumentException.class)
    public void findByLabelMethodThrowsException() {
        fillStock();

        inStock.findByLabel("rakia");
    }

    @Test
    public void testFindFirstByAlphabeticalOrder() {

        Product product1 = new Product("cola", 1.90, 2);
        Product product2 = new Product("apple", 1.20, 3);
        Product product3 = new Product("bread", 3.20, 4);

        inStock.add(product1);
        inStock.add(product2);
        inStock.add(product3);

        List<String> expectedLabels = List.of("apple", "bread", "cola");


        Iterable<Product> firstByAlphabeticalOrder = inStock.findFirstByAlphabeticalOrder(3);

        int index = 0;

        for (Product p: firstByAlphabeticalOrder) {
            Product product = p;
            Assert.assertEquals(expectedLabels.get(index), p.getLabel());
            index++;
        }

        Assert.assertEquals(3, index);

    }

    @Test
    public void testIfFindFirstByAlphabeticalOrderMethodReturnEmptyCollection() {
        fillStock();
        Iterable<Product> interiable = inStock.findFirstByAlphabeticalOrder(4);

        Assert.assertEquals(new ArrayList<>(), interiable);
    }


    @Test
    public void testFindAllInPriceRange() {

        fillStock();

        Iterable<Product> allInRange = inStock.findAllInRange(1.20, 3.20);

        List<Double> expectedPrices = List.of(3.20, 1.90);


        int index = 0;
        for (Product p: allInRange) {
            Double price = p.getPrice();
            Double expected = expectedPrices.get(index);
            Assert.assertEquals(expected, price);
            index++;
        }

        Assert.assertEquals(expectedPrices.size(), index);
    }

    @Test
    public void testFindAllInPriceRangeReturnEmptyCollection() {
        fillStock();

        Iterable<Product> allInRange = inStock.findAllInRange(10, 20);

        Assert.assertEquals(new ArrayList<>(), allInRange);
    }

    @Test
    public void testFindAllByPrice() {

        fillStock();

        Iterable<Product> allByPrice = inStock.findAllByPrice(1.20);

        for (Product p: allByPrice) {
            Assert.assertEquals("water", p.getLabel());
        }

    }

    @Test
    public void testFindAllByPriceIfReturnEmptyCollection() {
        fillStock();

        Iterable<Product> allByPrice = inStock.findAllByPrice(5.50);

        Assert.assertEquals(new ArrayList<>(), allByPrice);
    }

    @Test
    public void findFirstMostExpensiveProducts() {
        fillStock();

        Iterable<Product> firstMostExpensiveProducts = inStock.findFirstMostExpensiveProducts(2);

        List<String> expectedProducts = List.of("wine", "bread");

        int index = 0;
        for (Product p: firstMostExpensiveProducts) {
            Assert.assertEquals(expectedProducts.get(index), p.getLabel());
            index++;
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void findFirstMostExpensiveProductsIfThrowException() {
        fillStock();
        inStock.findFirstMostExpensiveProducts(5);
    }

    @Test
    public void findAllByQuantity() {
        fillStock();

        Iterable<Product> allByQuantity = inStock.findAllByQuantity(3);

        int index = 0;
        for (Product p: allByQuantity) {
            Assert.assertEquals("water", p.getLabel());
            index++;
        }
    }

    @Test
    public void findAllByQuantityIfThrowsEmptyCollection() {
        fillStock();

        Iterable<Product> allByQuantity = inStock.findAllByQuantity(5);

        Assert.assertEquals(new ArrayList<>(), allByQuantity);
    }

    @Test
    public void testInterator() {

        fillStock();

        Iterator<Product> iterator = inStock.iterator();

        List<String> expectedProducts = List.of("bread", "water", "wine");

        int index = 0;
        while (iterator.hasNext()){
            Product next = iterator.next();
            Assert.assertEquals(expectedProducts.get(index), next.getLabel());
            index++;

        }
    }

    private void fillStock() {
        Product product1 = new Product("bread", 1.90, 2);
        Product product2 = new Product("water", 1.20, 3);
        Product product3 = new Product("wine", 3.20, 4);
        inStock.add(product1);
        inStock.add(product2);
        inStock.add(product3);
    }




}