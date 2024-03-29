package domain.db;

import domain.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDbInMemory implements ProductDb
{
	private Map<Integer, Product> records = new HashMap<>();
	
	public ProductDbInMemory () {
		add(new Product("Rose", "Thorny plant", 2.25));
		add(new Product("azazaz", "Thoplant", 3.25));
		add(new Product("dfghg", "Thplant", 2.25));
		add(new Product("erjkqhghj", "Thorny", 2.25));
		add(new Product("ksjfhuerghqherz", "Thoant", 2.25));
	}
	
	@Override
    public Product get(int id){
		if(id < 0){
			throw new DbException("No valid id given");
		}
		return records.get(id);
	}
	
	@Override
    public List<Product> getAll(){
		return new ArrayList<Product>(records.values());	
	}

	@Override
    public void add(Product product){
		if(product == null){
			throw new DbException("No product given");
		}
		int id = records.size() + 1;
		product.setProductId(id);
		if (records.containsKey(product.getProductId())) {
			throw new DbException("Product already exists");
		}

		for (Product p : this.records.values())
		{
			if(p.getName().equals(product.getName()))
			{
				throw new DbException("Product already exists");
			}
		}

		records.put(product.getProductId(), product);
	}
	
	@Override
    public void update(Product product){
		if(product == null){
			throw new DbException("No product given");
		}
		if(!records.containsKey(product.getProductId())){
			throw new DbException("No product found");
		}
		records.put(product.getProductId(), product);
	}
	
	@Override
    public void delete(int id){
		if(id < 0){
			throw new DbException("No valid id given");
		}
		records.remove(id);
	}

	@Override
    public int getNumbeOfProducts() {
		return records.size();
	}
}
