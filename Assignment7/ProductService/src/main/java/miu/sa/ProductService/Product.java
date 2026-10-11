package miu.sa.ProductService;

public class Product {

    private int productNumber;
    private String name;
    private int numberOnStock;

    public Product() {
    }

    public Product(int productNumber, String name, int numberOnStock) {
        this.productNumber = productNumber;
        this.name = name;
        this.numberOnStock = numberOnStock;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOnStock() {
        return numberOnStock;
    }

    public void setNumberOnStock(int numberOnStock) {
        this.numberOnStock = numberOnStock;
    }
}