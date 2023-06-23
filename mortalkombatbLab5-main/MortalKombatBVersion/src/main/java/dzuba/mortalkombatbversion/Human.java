/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dzuba.mortalkombatbversion;

/**
 * Класс Human для манипуляций с параметрами игрока.
 * @autor Мария
*/
public class Human extends Player{

    private int points;
    private int experience;
    private int win;
    private int nextexperience;
    private int countLocations;
    private int tempLocations;
    private int countEnemies;
    private int tempEnemies;
    
    /** 
    * Getters & Setters для параметров игрока (очки, опыт, победы, опыт для следующего уровня, номер текущей локации, количество локаций, номер текущего врага в локации, количество врагов в локации).
    */
    
    public int getCountEnemies() {
        return countEnemies;
    }

    public void setCountEnemies(int countEnemies) {        
        this.countEnemies = countEnemies + (int)(Math.random()*2 + 1);
    }

    public int getTempEnemies() {
        return tempEnemies;
    }

    public void setTempEnemies(int tempEnemies) {
        this.tempEnemies = tempEnemies;
    }

    public void addTempEnemies() {
        this.tempEnemies++;
    }
    public void addTempLocations() {
        this.tempLocations++;
    }
    public int getCountLocations() {
        return countLocations;
    }

    public void setCountLocations(int countLocations) {
        this.countLocations = countLocations;
    }

    public int getTempLocations() {
        return tempLocations;
    }

    public void setTempLocations(int tempLocations) {
        this.tempLocations = tempLocations;
    }    

    
    public Human(int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
        this.points=0;
        this.experience=0;
        this.nextexperience=40;
        this.win=0;
        this.tempLocations = 1;
        this.countLocations = 1;
        this.tempEnemies = 0;
        this.setCountEnemies(0);
    }
    
    public void NewHealthHuman(boolean chooseHealth) {
        this.setLevel();
        int hp = 0;
        int damage = 0;
        switch (this.getLevel()) {
            case 1 -> {
                hp = 25;
                damage = 3;
            }
            case 2 -> {
                hp = 30;
                damage = 3;
            }
            case 3 -> {
                hp = 30;
                damage = 4;
            }
            case 4 -> {
                hp = 40;
                damage = 6;
            }
        }
        System.out.println(chooseHealth);
        if (chooseHealth) {
            this.setMaxHealth(hp);
        } else {
            this.setDamage(damage);
        }
    }
    

    public int getPoints(){
        return this.points;
    }
    public int getExperience(){
        return this.experience;
    }
    public int getNextExperience(){
        return this.nextexperience;
    }
    public int getWin(){
        return this.win;
    }

    public void setPoints(int p){
        this.points+=p;
    }
    public void setExperience(int e){
        this.experience+=e;
    }
    public void setNextExperience(int e){
        this.nextexperience=e;
    }
    public void setWin(){
        this.win++;
    }
    
    @Override
    public String getName(){
        return "You";
    }

    
}
