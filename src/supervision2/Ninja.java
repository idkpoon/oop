package supervision1;

interface Ninja {
 void attack();
 void run();
 void defend();
}

class Employee {
	 private String name;
	 private String job;
	
	 public Employee(String name, String job) {
	     this.name = name;
	     this.job = job;
	 }
	 
	
	 // Getters and Setters
	 public String getName() {
	     return name;
	 }
	
	 public String getJob() {
	     return job;
	 }
	}


	
class NinjaEmployee extends Employee implements Ninja {
 private Ninja ninjaBehavior;  

 public NinjaEmployee(String name, String position) {
     super(name, position);
 }

 @Override
 public void attack() {
     ninjaBehavior.attack();  
 }

 @Override
 public void run() {
     ninjaBehavior.run();  
 }
 
 @Override
 public void defend() {
	 ninjaBehavior.defend();
 }
}

