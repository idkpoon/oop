package pets;

class Pet implements PetBehaviour{
	private String name;
	private String type;
	private int hunger;
	private int energy;
	private boolean isDead;
	
	public Pet(String name, String type) {
		this.setName(name);
		this.setType(type);
		this.setHunger(0);
		this.setEnergy(100);
		this.isDead = false;
	}
	
	

	@Override
	public void eat() {
		setEnergy(Math.max(getEnergy() + 10, 0));
		setHunger(Math.min(100, getHunger() - 5));
	}

	@Override
	public void play() {
		setEnergy(Math.max(0, getEnergy() - 5));
		setHunger(Math.min(100, getHunger() + 5));
	}

	@Override
	public void sleep() {
		setEnergy(100);
		setHunger(Math.min(100, getHunger() + 5));
	}

	@Override
	public String status() {
        return getName() + " | Hunger: " + getHunger() + " | Energy: " + getEnergy();
	}

	public int getHunger() {
		return hunger;
	}

	public void setHunger(int hunger) {
		this.hunger = Math.max(0, Math.min(100, hunger));
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = Math.max(0, Math.min(100, energy));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public boolean checkKitchen() {
		return false;
		
	}
	
	public boolean hasEnergy() {
		if (energy > 0) {
			return true;
		}
		return false;
	}
	
	public void setDead(boolean dead) {
		if (dead) isDead = true;
		else isDead = false;
	}
	
	public boolean getDead() {
		return isDead;
	}



	@Override
	public void getsEaten() {
			setHunger(Math.min(100, getHunger() + 20));
			setEnergy(Math.max(getEnergy() - 20, 0));

		
		
	}
	
}
