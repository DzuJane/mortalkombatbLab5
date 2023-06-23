/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dzuba.mortalkombatbversion;

/** 
 * Класс Items для работы с вещами, которые хранятся у игрока в запасе.
 * @autor Мария
*/
public class Items {
            
    /** Поле имя вещи */  
    private String name;        
    /** Поле количество вещи */  
    private int count;
            
    /** 
     * Конструктор, принимающий имя вещи и её количество.
     */  
    public Items(String n, int c){
        this.name=n;
        this.count=c;
    }
            
    /** 
     * Функция, которая устанавливает значение имени вещи. 
     */  
    public void setName(String s){
        this.name=s;
    }        
    
    /** 
     * Функция, которая добавляет к имеющемуся количеству переданное значение. 
     */  
    public void setCount(int c){
        this.count+=c;
    }
            
    /** 
     * Функция получения имени вещи. 
     */  
    public String getName(){
        return this.name;
    }        
    /** 
     * Функция получения количества вещи. 
     */  
    public int getCount(){
        return this.count;
    }
}
