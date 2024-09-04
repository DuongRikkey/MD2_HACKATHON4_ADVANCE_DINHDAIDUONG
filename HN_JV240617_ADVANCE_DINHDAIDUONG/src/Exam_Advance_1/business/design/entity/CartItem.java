package Exam_Advance_1.business.design.entity;

import Exam_Advance_1.business.design.features.implement.ProductFeatureImpl;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class CartItem {
    private int cartItemId;
    private Product product;
    private double price;
    private int quantity;

    public CartItem() {
    }

    public CartItem(int cartItemId, Product product, double price, int quantity) {
        this.cartItemId = cartItemId;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //Input Data Cart
    public void inputData(Scanner sc, List<CartItem> cartItems, List<Product> products, String Product){
        this.cartItemId =inputCartId(cartItems);
        this.product = inputProduct(sc,Product);
        this.quantity = inputCategoryQuantiry(sc,this.product);
        this.price = this.product.getProductPrice() * this.quantity;
    }

    private int inputCategoryQuantiry(Scanner sc, Product product) {
        int qtyProducts;
        do {
            while (true){
                try{
                    System.out.println("Mời bạn nhập số lượng  ");
                    qtyProducts = Integer.parseInt(sc.nextLine());
                    break;
                }
                catch (NumberFormatException e){
                    System.out.println("Nhập sai cú pháp phải là số nguyên");
                }
            }
            if (product.getStock()<qtyProducts){
                System.err.println("Số lượng mua không được nhiều hơn số hàng trong kho");
            }else {
                return qtyProducts;
            }

        }while (true);
    }



    private Product inputProduct(Scanner sc,String Product) {


        Optional<Product> optionalProduct = ProductFeatureImpl.products.stream().
                filter(product1 -> product1.getProductId().equals(Product)).findFirst();
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }

        return null;
    }

    private int inputCartId (List<CartItem> cartItems) {


        return cartItems.stream().mapToInt(CartItem::getCartItemId).max().orElse(0)+1;
    }

    //Display Cart
    public void displayData(){
        Locale localeVN =new Locale("vi","VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);
        System.out.printf("ID : %3d | ProductName: %12s  | Price: %4s VND  | Quantity: %5s \n"
                ,this.cartItemId,this.product.getProductName(),currencyFormatter.format(this.price),this.quantity);
    }

}
