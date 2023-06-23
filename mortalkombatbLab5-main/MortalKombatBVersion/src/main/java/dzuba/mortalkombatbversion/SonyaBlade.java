/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dzuba.mortalkombatbversion;


/** 
* Класс SonyaBlade для манипуляций с параметрами врага.
* @autor Мария
*/
public class SonyaBlade extends Player{
    
    /** 
    * Конструктор с инициализацией по переданным уровню, здоровью, урону и атаке.
    */ 
    public SonyaBlade (int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
    }
    
    /** 
    * Переопределенный метод для получения имени врага.
    */
    @Override
    public String getName(){
        return "Sonya Blade";
    }
}
