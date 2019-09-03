package tech.zxuuu.dao;

import java.util.List;

import tech.zxuuu.entity.Product;

public interface IProductMapper {

	public List<Product> listProductByType(String type);

	public List<Product> searchProduct(String product);

	public Boolean insertNewProcut(Product product);
	
}
