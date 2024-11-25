package pets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class PetGUI extends JPanel implements ActionListener {

    static JTextField nameTextField;
    static JLabel labelName;
    static JComboBox<String> petDropdown;
    ArrayList<Pet> allPets = new ArrayList<>();
    private JList<Pet> petList;  
    static JTabbedPane tabbedPane;
    JComponent panel1;
    JComponent panel2;
    private Timer feedingTimer;  
    private Timer refreshTimer;
    private HashMap<Pet, JLabel> petLabels = new HashMap<>();
    private HashMap<Pet, JLabel> petImages = new HashMap<>();

    public PetGUI() {
        super(new GridLayout(1, 1));


        tabbedPane = new JTabbedPane();
        ImageIcon icon1 = createImageIcon("img/pets.png");
        ImageIcon icon2 = createImageIcon("img/pet-food.png");

        panel1 = makeFirstPanel("Add Pet");
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        tabbedPane.addTab("Add Pet", icon1, panel1, "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        panel2 = makeSecondPanel("My Pets");
        tabbedPane.addTab("My Pets", icon2, panel2, "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		int rand1 = ((int)(Math.random() * 20))*1000;  

		
        feedingTimer = new Timer(rand1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Pet pet : allPets) {
                    pet.eat(); 
                    JLabel petStatus = petLabels.get(pet);  
                	petStatus.setText(pet.getName() + " - Hunger Level: " + pet.getHunger() + "\n - Energy Level: " + pet.getEnergy());
                	if (petStatus != null) {
                    	petStatus.setText(pet.getName() + " - Hunger Level: " + pet.getHunger() + "\n - Energy Level: " + pet.getEnergy());
                    }
                	
                
                	
                }

            }
        });  
        
        refreshTimer = new Timer(2, new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                for (Pet pet : allPets) {
                	
                	JLabel petStatus = petLabels.get(pet);
                	if (pet.getDead() != true) {
                		if (pet.getHunger() == 100 && pet.getEnergy() >= 50) {
                    		// Cannibalism
                    		if (allPets.size() > 1) {
                    			int randPetIndex = (int)(Math.random() * allPets.size()); 
                    			int currPetIndex = allPets.indexOf(pet);
                    			while (randPetIndex == currPetIndex) {
                    				randPetIndex = (int)(Math.random() * allPets.size()); 
                    			}
                    			
                    			JOptionPane.showMessageDialog(null, pet.getName() + " has resorted to cannibalism and is now eating " + allPets.get(randPetIndex).getName());
                    			Pet eatenPet = allPets.get(randPetIndex);
                    			eatenPet.getsEaten();
                    			pet.setHunger(40);
                    			pet.setEnergy(40);
                            	petStatus.setText(pet.getName() + " - Hunger Level: " + pet.getHunger() + "\n - Energy Level: " + pet.getEnergy());

                    		}
                    		else {
                    			pet.setDead(true);
                    			JOptionPane.showMessageDialog(null, pet.getName() + " has died from starvation.");
                    			petImages.get(pet).setIcon(resizeImage("img/grave.png", 100, 100));
                    			pet.setHunger(100);
                    			pet.setHunger(0);
                            	petStatus.setText(pet.getName() + " - Hunger Level: " + pet.getHunger() + "\n - Energy Level: " + pet.getEnergy());

                                
                    		}

                    	}
                    	else if (pet.getHunger() == 100 && pet.getEnergy() < 50) {
                    		JOptionPane.showMessageDialog(null, pet.getName() + " has died from starvation.");
                			pet.setDead(true);
                            petImages.get(pet).setIcon(resizeImage("img/grave.png", 100, 100));
                            pet.setHunger(100);
                			pet.setHunger(0);
                        	petStatus.setText(pet.getName() + " - Hunger Level: " + pet.getHunger() + "\n - Energy Level: " + pet.getEnergy());


                    	}
                    	
                    	if (pet.getEnergy() < 50 || pet.getHunger() > 50) {
                            petImages.get(pet).setIcon(resizeImage("img/" + pet.getType().toLowerCase() + "-angry1.png", 100, 100));
                    	}
                    	if (pet.getEnergy() < 30 || pet.getHunger() > 70) {
                            petImages.get(pet).setIcon(resizeImage("img/" + pet.getType().toLowerCase() + "-angry2.png", 100, 100));
                    	}
//                    	if (pet.getEnergy() >= 50 || pet.getHunger() <= 50) {
//                            petImages.get(pet).setIcon(resizeImage("img/" + pet.getType().toLowerCase() + "-happy.png", 100, 100));
//
//                    	}
                	}
                	else {
                        pet.setHunger(100);
            			pet.setHunger(0);
                        petImages.get(pet).setIcon(resizeImage("img/grave.png", 100, 100));
                        
                	}
                }
        		
        		
        	}
        });
        
        refreshTimer.start();
        feedingTimer.start();
        
    }

    protected JComponent makeFirstPanel(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set vertical layout

        JLabel dogImageLabel = new JLabel(resizeImage("img/pets.png", 100, 100));
        dogImageLabel.setPreferredSize(new Dimension(100, 100)); 
        dogImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the image
        panel.add(dogImageLabel);

        String[] petOptions = {"Select a Pet", "Capybara", "Cactus", "Reindeer", "Toast"};
        petDropdown = new JComboBox<>(petOptions);
        petDropdown.setMaximumSize(new Dimension(Integer.MAX_VALUE, petDropdown.getPreferredSize().height)); // Make it stretch
        panel.add(petDropdown);

        petDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPet = (String) petDropdown.getSelectedItem();
                dogImageLabel.setIcon(resizeImage("img/" + selectedPet.toLowerCase() + "-happy.png", 100, 100));
                	
                ImageIcon petIcon = createImageIcon("img/pets.png"); 
            }
        });
   
        JLabel l1 = new JLabel("Enter Name");
        l1.setAlignmentX(Component.CENTER_ALIGNMENT); 
        panel.add(l1);

        nameTextField = new JTextField(10); 
        nameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, nameTextField.getPreferredSize().height)); // Make it stretch
        panel.add(nameTextField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  
        JButton btnAddPet = new JButton("Add Pet");
        JButton btnName = new JButton("Submit");
        btnName.addActionListener(this);  

        buttonPanel.add(btnName);  
        buttonPanel.add(btnAddPet);  
        panel.add(buttonPanel);  

        labelName = new JLabel("Nothing entered");
        labelName.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the feedback label
        panel.add(labelName);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        btnAddPet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	String selectedPet = (String) petDropdown.getSelectedItem();
                String name = nameTextField.getText();
            	Pet newPet = null;

                	switch (selectedPet.toLowerCase()) {
                		case "capybara": 
                			newPet = new Capybara(name, selectedPet.toLowerCase());
                			break;
                		case "cactus":
                			newPet = new Cactus(name, selectedPet.toLowerCase());
                			break;
                		case "reindeer":
                			newPet = new Reindeer(name, selectedPet.toLowerCase());
                			break;
                		case "toast":
                			newPet = new Toast(name, selectedPet.toLowerCase());
                			break;
                		
                	}
                
//                System.out.println(newPet);
                allPets.add(newPet);
                nameTextField.setText("");
                labelName.setText("Nothing entered");
                petDropdown.setSelectedIndex(0);
                panel2 = makeSecondPanel("My Pets");
                ImageIcon icon2 = createImageIcon("img/cooking.png");
                tabbedPane.remove(1);
                tabbedPane.addTab("My Pets", icon2, panel2, "Does twice as much nothing");
                tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

                add(tabbedPane);
                tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
                
                dogImageLabel.setIcon(resizeImage("img/" + "pets.png", 100, 100));
                

            }
            
        });

        return panel;
    }

    protected JComponent makeSecondPanel(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  
        for (Pet pet : allPets) {
            JPanel petPanel = new JPanel();
            petPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  

            JLabel petImageLabel = new JLabel(resizeImage("img/" + pet.getType().toLowerCase() + "-happy.png", 100, 100));
            petPanel.add(petImageLabel);

            JLabel petInfoLabel = new JLabel(pet.getName() + " - Hunger Level: " + pet.getHunger() + "\n - Energy Level: " + pet.getEnergy());
            petLabels.put(pet, petInfoLabel);
            petImages.put(pet,  petImageLabel);

            JButton feedButton = new JButton("Feed");
            JButton playButton = new JButton("Play");
            JButton sleepButton = new JButton("Sleep");
            
            if (pet.getType().toLowerCase() == "reindeer" ) {
                JButton singButton = new JButton("Sing");
                singButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	Reindeer r = (Reindeer) pet;
                    	r.sing();
                    	petInfoLabel.setText(pet.getName() + " - Hunger Level: " + pet.getHunger() + "\n - Energy Level: " + pet.getEnergy());
                    	petImages.get(pet).setIcon(resizeImage("img/" + pet.getType().toLowerCase() + "-eat.png", 100, 100));

                    }
                });


            }
    
            feedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	pet.eat();
                	petInfoLabel.setText(pet.getName() + " - Hunger Level: " + pet.getHunger() + "\n - Energy Level: " + pet.getEnergy());
                	petImages.get(pet).setIcon(resizeImage("img/" + pet.getType().toLowerCase() + "-eat.png", 100, 100));

                }
            });

            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	pet.play();
                	petInfoLabel.setText(pet.getName() + " - Hunger Level: " + pet.getHunger() + "\n - Energy Level: " + pet.getEnergy());
                	petImages.get(pet).setIcon(resizeImage("img/" + pet.getType().toLowerCase() + "-happy.png", 100, 100));

                }
            });

            sleepButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	pet.sleep();
                	petInfoLabel.setText(pet.getName() + " - Hunger Level: " + pet.getHunger() + "\n - Energy Level: " + pet.getEnergy());
                	petImages.get(pet).setIcon(resizeImage("img/" + pet.getType().toLowerCase() + "-sleep.png", 100, 100));
                	
                }
            });
            
            
            petPanel.add(feedButton);
            petPanel.add(playButton);
            petPanel.add(sleepButton);
            petPanel.add(petInfoLabel);
            
            

            panel.add(petPanel);
        }
        
        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Submit")) {
            String selectedPet = (String) petDropdown.getSelectedItem();
            labelName.setText("Name: " + nameTextField.getText() + " (" + selectedPet + ")");
            
        }
        else if (s.equals("Add Pet")) {
        	
        }
    }
    

    protected ImageIcon resizeImage(String path, int width, int height) {
        ImageIcon originalIcon = createImageIcon(path);
        if (originalIcon != null) {
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }
        return null; 
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = PetGUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Pets Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PetGUI(), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            createAndShowGUI();
        });
    }
}