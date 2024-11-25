package pets;

public class Capybara extends Pet{

	public Capybara(String name, String type) {
		super(name, type);
	}
	
    @Override
    public void play() {
        setHunger(getHunger() + 8);
        setEnergy(getEnergy() - 8);
    }

    

}
