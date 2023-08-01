package Middleware;

public abstract class AbsCrud {
	public abstract void display();
	
//	public abstract void Order(int id);
	
	public abstract String getCarName(int carId);
	
	public abstract void markCarAsNAAndStoreOrder(int carId, String customerName, String carName, int hours);

	public abstract void delete(int t);

	public abstract void Update(int y,String columnname,String newValue);

	public abstract void add(String ab, String bc, String cd, String de);
}
