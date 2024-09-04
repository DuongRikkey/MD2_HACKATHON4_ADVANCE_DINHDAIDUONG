package Exam_Advance_1.business.design.features;

import Exam_Advance_1.business.design.entity.Product;

public interface IProductFeatures extends IGenericService<Product,String> {
    void sortProductByPrice();
    void searchProductByName(String nameProduct);
}
