package pets;

public class Toast extends Pet{


	
	public Toast(String name, String type) {
		super(name, type);
	}
	
    @Override
    public void play() {
        setHunger(getHunger() + 8);
        setEnergy(getEnergy() - 8);
    }
    
	@Override
	public void sleep() {
		setEnergy(100);
		int rand1 = (int)(Math.random() * 10);  

        setHunger(getHunger() + 10);
	}
    

}
