package Exam_Advance_1.business.design.features.implement;

import Exam_Advance_1.business.design.entity.Product;
import Exam_Advance_1.business.design.features.IProductFeatures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductFeatureImpl implements IProductFeatures {
    public static List<Product> products=new ArrayList<>();

    @Override
    public void sortProductByPrice() {
        if (products == null || products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống, không thể sắp xếp.");
            return;
        }

        try {
            products.stream().sorted(Comparator.comparingDouble(Product::getProductPrice).reversed()).forEach(Product::displayData);
            System.out.println("Danh sách sản phẩm đã được sắp xếp theo giá.");
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi sắp xếp sản phẩm: " + e.getMessage());
        };
    }

    @Override
    public void searchProductByName(String nameProduct) {
        for (Product product : products) {
            if(product.getProductName().equals(nameProduct)){
                System.out.println("Chi tiết sản phẩm");
                product.displayData();
                return;
            }
        }
    }

    @Override
    public void save(Product product) {
        int indexUpdate=findIndexById(product.getProductId());
        if(indexUpdate==-1){
            products.add(product);

        }
        else {
            products.set(indexUpdate,product);
        }
    }

    @Override
    public void remove(String id) {
        products.remove(findIndexById(id));

    }

    @Override
    public int findIndexById(String id) {
        return products.stream().map(Product::getProductId).toList().indexOf(id);
    }

    @Override
    public List<Product> getAll() {
        return products;
    }
}
