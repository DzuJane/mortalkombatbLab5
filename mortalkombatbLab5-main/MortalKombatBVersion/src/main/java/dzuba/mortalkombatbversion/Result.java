/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dzuba.mortalkombatbversion;


/** 
 * Класс Result для работы с результатами побед в игре.
 * @autor Мария
*/
public class Result {
    
    private String name;
    private int points;
    /** 
     * Конструктор, с инициализацией полей в зависимости от полученных имени и очков
     */
    public Result(String n, int p){
        this.name=n;
        this.points=p;
    }
    
    /** 
     * Функция для установления имени в результате
     */
    public void setName(String s){
        this.name=s;
    }
    /** 
     * Функция для установления очков в результате
     */
    public void setPoints(int p){
        this.points=p;
    }
    /** 
     * Функция для получения имени игрока
     */
    public String getName(){
        return this.name;
    }
    /** 
     * Функция для получения очков игрока
     */
    public int getPoints(){
        return this.points;
    }
    
}
