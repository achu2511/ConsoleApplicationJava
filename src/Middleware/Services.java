package Middleware;

import Model.RegModel;

public interface Services {
	void create(RegModel a);
	
	boolean check(String name,int num,String pass);
}
