package pets;

public class Reindeer extends Pet{


	public Reindeer(String name, String type) {
		super(name, type);
	}
	
    @Override
    public void play() {
		int rand1 = (int)(Math.random() * 20);  

        setHunger(getHunger() + rand1);
        setEnergy(getEnergy() - 8);
    }
    
	@Override
	public void eat() {
		int rand2 = (int)(Math.random() * 20);  

		setEnergy(getEnergy() + 20);
		setHunger(getHunger() + rand2);
	}
	

	public void sing() {
		int rand2 = (int)(Math.random() * 20);  

		setEnergy(getEnergy() + 30);
		setHunger(getHunger() - 2);
	}

    

}
