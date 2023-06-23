/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dzuba.mortalkombatbversion;


/**
 * Класс BarakaFabric для создания объектов типа Baraka.
 * @autor Мария
*/
public class BarakaFabric implements EnemyFabricInterface {

    /** 
     * Метод создания объекта класса Baraka с определенными параметрами.
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new Baraka(1, 100, 12, 1);
        return enemy;
    }
}
