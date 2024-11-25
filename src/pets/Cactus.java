package pets;

public class Cactus extends Pet{


	
	public Cactus(String name, String type) {
		super(name, type);
	}
	
    @Override
    public void play() {
        setHunger(getHunger() + 8);
        setEnergy(getEnergy() - 8);
    }
    
	@Override
	public void eat() {
		int rand1 = (int)(Math.random() * 20);  
		int rand2 = (int)(Math.random() * 20);  

		setEnergy(getEnergy() + rand1);
		setHunger(getHunger() + rand2);
	}

    

}
