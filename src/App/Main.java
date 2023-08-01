package App;

import java.util.*;
import Model.RegModel;
import Appliers.Methods;
import Middleware.AbsCrud;
import Appliers.Crud;

public class Main {
	static String admin="Admin";
	static String pass="Pass";
	public static AbsCrud op;
	public static String uname;
	static Scanner s=new Scanner(System.in);
	public static void Register(RegModel r) {
		Methods ser=new Methods();
		ser.create(r);
		
		
	}
	public static void Login() {
		String logname;
		int lognum;
		boolean l=true;
		while(l) {
			System.out.println("You are Now Logging In");
			System.out.println("Enter Your Name: ");
			logname=s.nextLine();
			System.out.println("Enter Your Mobile Number: ");
			lognum=s.nextInt();
			s.nextLine();
			System.out.println("Enter Your Password: ");
			String logpass=s.nextLine();
			Methods sr=new Methods();
			boolean val=sr.check(logname,lognum,logpass);
			
			if(val) {
				l=false;
				uname=logname;
			}
			else {
				System.out.println("Enter Your details Properly / Register : R - Register / L-Login");
				String g=s.nextLine();
				if(g.equals("R")) {
					System.out.println("Please Restart your Application for registering");
					break;
				}else {
					continue;
				}
			}
		}
		op=new Crud();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("X*******  Welcome To Car-zy Cars *******X");
		System.out.println("X* CATALOUGE *X");
		op.display();
		System.out.println();
		System.out.println();
		boolean n=true;
		while(n) {
				System.out.println("Enter your Choice [Pick from Id column]");
				int i=s.nextInt();
				System.out.println("For How many hours: ");
				int hr=s.nextInt();
				String carName = op.getCarName(i);
				if (carName != null) {
					// Mark the car as 'n.a' in the CarDetail table and store the order in the Orders table.
					op.markCarAsNAAndStoreOrder(i, uname, carName, hr);
					n=false;
	//            System.out.println("Car with ID " + i + " is marked as 'n.a' and the order is stored in the Orders table.");
				} else {
					System.out.println("Car with ID " + i + " not found.");
					continue;
				}
		}
		System.out.println("Your Rental is successful");
		op.display();
	}
	public static void main(String[]args) {
		RegModel reg=new RegModel();
		
		System.out.println();
        System.out.println("X*******  Welcome To Car-zy Cars *******X");
        System.out.println();
        String role;
        while(true) {
    	    System.out.println("Please Enter Who You are :  (C - Customer / A - office Admin)");
    	    role=s.nextLine();
    	    if(role.equals("C")||role.equals("A"))
    	  	    break;
    	    else {
    		    System.out.println("Please Enter Your Choice Properly using Capiptals");
    		    System.out.println();
    	    }
        }
        
        if(role.equals("C")) {
        	String up;
        	while(true) {
        		System.out.println("Please Enter Who You are :  (L - Login / R - Register if You're New)");
        	    up=s.nextLine();
        	    if(up.equals("L")|| up.equals("R"))
        	  	    break;
        	    else {
        		    System.out.println("Please Enter Your Choice Properly using Capiptals");
        		    System.out.println();
        	    }
        	}
        	if(up.equals("R")) {
        		System.out.print("Please Enter Your Username: ");
        		reg.setName(s.nextLine());
        		System.out.println("Please Enter Your Mobile Number: ");
        		reg.setMobile(s.nextInt());
        		s.nextLine();
        		System.out.println("Please Enter Your Password: ");
        		reg.setPassword(s.nextLine());
        		Register(reg);
        		System.out.println("Thank You for Registering.... Please Login");
        		Login();
        	}else {
        		Login();
        	}
        }else {
        	boolean pool=true;
        	while(pool) {
	        		System.out.println("Enter Your Admin Username: ");
	        		String aname=s.nextLine();
	        		System.out.println("Enter Your Password: ");
	        		String apass=s.nextLine();
	        		
	        		if(aname.equals(admin) && apass.equals(pass)) {
	        			System.out.println("You've Entered ADMIN mode ....");
	        			pool=false;
	        		}else {
	        			System.out.println("You've entered Wrong Details ....");
	        			continue;
	        		}
        	}
        	AbsCrud sa=new Crud();
        	sa.display();
        	System.out.println();
        	boolean gt=true;
        	while(gt) {
        		
        		System.out.println("Please Enter Your Choice: D - Delete , U - Update Existing , AD -Adding the Details,E-Exit");
        		String cs=s.nextLine();
        		switch(cs) {
        		case("D"):
        			System.out.println("Enter ID to be deleted : ");
        		int t= s.nextInt();
        		update(t);
        		break;
        		case("U"):
        			System.out.println("Enter the id you need update: ");
        		int y=s.nextInt();
        		s.nextLine();
        		System.out.println("Enter the column name to be Updated: ");
        		String a=s.nextLine();
        		System.out.println("Enter the new Value to be Updated: ");
        		String c=s.nextLine();
        		update(y,a,c);
        		break;
        		case("AD"):
        			System.out.println("Enter How Many Car Details You need to Add: ");
	        		int n=s.nextInt();
	        		s.nextLine();
	        		for(int i=0;i<n;i++) {
	        			System.out.println("Enter the new Car name: ");
	        			String ab=s.nextLine();
	        			System.out.println("Enter the new Car Type: ");
	        			String bc=s.nextLine();
	        			System.out.println("Enter the new Price: ");
	        			String cd=s.nextLine();
	        			System.out.println("Enter the new Availability: ");
	        			String de=s.nextLine();
	        			sa.add(ab,bc,cd,de);
	        		}
	        		break;
        		case("E"):
        			gt=false;
        			break;
        		
        		}
        	}
        }
        
      
	}
	private static void update(int y,String columnname,String newValue) {
		// TODO Auto-generated method stub
		AbsCrud sa=new Crud();
		sa.Update(y,columnname,newValue);
		System.out.println("X**** Your Updated Column ****X");
		sa.display();
	}
	private static void update(int t) {
		// TODO Auto-generated method stub
		AbsCrud sa=new Crud();
		sa.delete(t);
		System.out.println("X**** Your Updated Column ****X");
		sa.display();
	}

}
