import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private List<Product> products;

    public Instock() {
        this.products = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public boolean contains(Product product) {
        for (Product p: products) {
            if (p.getLabel().equals(product.getLabel())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        for (Product p : products) {
            if (p.getLabel().equals(product)){
                p.setQuantity(quantity);
                return;
            }
        }

        throw new IllegalArgumentException("Product does not exist!");
    }

    @Override
    public Product find(int index) {
        if (index < 0 || index > products.size()){
            throw new IndexOutOfBoundsException("No element on that index");
        }

        return products.get(index);

    }

    @Override
    public Product findByLabel(String label) {
        for (Product p: products) {
            if (p.getLabel().equals(label)){
                return p;
            }
        }
        throw new IllegalArgumentException("No such product");
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        if (count < 0 || count > products.size()){
            return new ArrayList<>();
        }

        List<Product> inStock = new ArrayList<>();

        List<Product> collect = products.stream().sorted(Comparator.comparing(Product::getLabel))
                .collect(Collectors.toList());

        for (int i = 0; i < count; i++) {
            inStock.add(collect.get(i));
        }
        return inStock;
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        List<Product> productsInRange = new ArrayList<>();
        for (Product p: products) {
            if (p.getPrice() > lo && p.getPrice() <= hi){
                productsInRange.add(p);
            }
        }

        return productsInRange.stream().sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        List<Product> expectedProducts = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() == price){
                expectedProducts.add(p);
            }
        }
        return expectedProducts;
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count <= 0 || count > products.size()){
            throw new IllegalArgumentException("No such products!");
        }
      return   products.stream().sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(count).collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        List<Product> expectedProducts = new ArrayList<>();
        for (Product p : products) {
            if (p.getQuantity()== quantity){
                expectedProducts.add(p);
            }
        }
        return expectedProducts;
    }

    @Override
    public Iterator<Product> iterator() {
      return this.products.iterator();
    }
}
