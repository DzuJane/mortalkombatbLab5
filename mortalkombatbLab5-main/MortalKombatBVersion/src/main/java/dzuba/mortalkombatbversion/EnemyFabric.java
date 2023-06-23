/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dzuba.mortalkombatbversion;

/**
* Класс EnemyFabric для создания объектов типа Player.
* @autor Мария
*/
public class EnemyFabric {

    /** 
     * Метод создания объекта класса Player в зависимости от  переданных значений
     * @param i - код типа врага
     * @param j - код вызова фабрики
     */
    public Player create(int i, int j) {
        EnemyFabricInterface fabric = null;

        switch (i) {
            case 0 -> fabric = new BarakaFabric();
            case 1 -> fabric = new SubZeroFabric();
            case 2 -> fabric = new LiuKangFabric();
            case 3 -> fabric = new SonyaBladeFabric();
            case 4 -> fabric = new ShaoKahnFabric();
        }
        Player enemy = fabric.create(j);
        return enemy;
    }
}
