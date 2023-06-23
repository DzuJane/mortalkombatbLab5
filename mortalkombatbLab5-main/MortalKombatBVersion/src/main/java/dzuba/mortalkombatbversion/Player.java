/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dzuba.mortalkombatbversion;

/** 
 * Класс Player для манипуляций с параметрами существа (игрока и врага).
 * @autor Мария
*/
public class Player {
    
    int level;
    private int health;
    private int maxhealth;
    private int damage;
    private int attack;
    private int count_attack_enemy;
    private int count_defense_enemy;
    /** 
    * Конструктор с инициализацией по переданным уровню, здоровью, урону и атаке.
    */ 
    public Player(int level, int health, int damage, int attack){
        this.level=level;
        this.health=health;
        this.damage=damage;
        this.attack=attack;
        this.maxhealth=health;
        this.count_attack_enemy = 0;
        this.count_defense_enemy = 0;
    }
    /** 
    * Метод для добавления количества атак противника.
    */ 
    public void addCount_attack_enemy() {
        this.count_attack_enemy++;
    }
    /** 
    * Метод для добавления количества защит противника.
    */ 
    public void addCount_defense_enemy() {
        this.count_defense_enemy++;
    }

    /** 
    * Getters & Setters для параметров существа (уровень, текущее здоровье, максималльно возможное на данный момент здоровье, значение урона, значение атаки, подсчитанное количество атак и защит противника).
    */
    public int getCount_attack_enemy() {
        return count_attack_enemy;
    }

    public void setCount_attack_enemy(int count_attack_enemy) {
        this.count_attack_enemy = count_attack_enemy;
    }

    public int getCount_defense_enemy() {
        return count_defense_enemy;
    }

    public void setCount_defense_enemy(int count_defense_enemy) {
        this.count_defense_enemy = count_defense_enemy;
    }
    
    public void setLevel(){
        this.level++;
    }
    public void setHealth(int h){
        this.health+=h;
    }
    public void setNewHealth(int h){
        this.health=h;
    }
    public void setDamage(int d){
        this.damage+=d;
    }
    public void setAttack(int a){
        this.attack=a;
    }
    public void setMaxHealth(int h){
        this.maxhealth+=h;
    }
    
    public int getLevel(){
        return this.level;
    }
    public int getHealth(){
        return this.health;
    }
    public int getDamage(){
        return this.damage;
    }
    public int getAttack(){
        return this.attack;
    }
    public int getMaxHealth(){
        return this.maxhealth;
    }
    
    public String getName(){
        return "";
    }
    
}
