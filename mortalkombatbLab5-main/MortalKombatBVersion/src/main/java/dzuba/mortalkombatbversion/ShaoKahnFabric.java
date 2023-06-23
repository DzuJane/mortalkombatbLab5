/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dzuba.mortalkombatbversion;

/**
 * Класс ShaoKahnFabric для создания объектов типа ShaoKahn.
 * @autor Мария
*/
public class ShaoKahnFabric implements EnemyFabricInterface{
    
    /** 
     * Метод создания объекта класса ShaoKahn с определенными параметрами.
     */
    @Override
    public Player create(int i) {
        Player enemy;
        if(i==0){
            enemy = new ShaoKahn(3, 100, 30, 1);
        }
        else{
           enemy = new ShaoKahn(3, 145, 44, 1); 
        }
        return enemy;
    }
}
