/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dzuba.mortalkombatbversion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/** 
 * Класс Game для инициализирующих действий игры.
 * @autor Мария
*/
public class Game {

    CharacterAction action = new CharacterAction();
    ChangeTexts change = new ChangeTexts();
    Fight fight = new Fight();
    private final ArrayList<Result> results = new ArrayList<>();
    private final Items[] items = new Items[3];
    
    /** 
    * Конструктор - для инициализации хранящихся вещей в запасе у игрока.
    */
    public Game(){        
        items[0]=new Items("Малое зелье лечение",0);
        items[1]=new Items("Большое зелье лечение",0);
        items[2]=new Items("Крест возрождения",0);
    }
        
    /** 
    * Функция, которая получает коллекцию вещей в запасе у игрока.
    */
    public Items[] getItems(){
        return items;
    }
    
    /** 
    * Функция, которая получает случайно выбранного врага.
    */
    public Player NewEnemy(JLabel L1, JLabel L2,
            JLabel L3, JLabel L4, JProgressBar pr2) {
        action.setEnemyes();
        Player enemy = action.ChooseEnemy(L1, L2, L3, L4);
        action.HP(enemy, pr2);
        pr2.setMaximum(enemy.getMaxHealth());
        return enemy;
    }
    
    /** 
    * Функция, которая получает созданного игрока.
    */
    public Human NewHuman(JProgressBar pr1){
        Human human = new Human (0,80,16,1);
        action.HP(human, pr1);
        pr1.setMaximum(human.getMaxHealth());
        return human;
    }

    /** 
    * Функция, которая осуществляет вызов отображения в виде таблицы и записи в excel при условии, что игрок вошёл в топ-10.
    */
    public void EndGameTop(Human human, JTextField text, JTable table) throws IOException {
        results.add(new Result(text.getText(), human.getPoints()));
        results.sort(Comparator.comparing(Result::getPoints).reversed());
        WriteToTable(table);
        WriteToExcel();
    }
    
    /** 
    * Функция, которая осуществляет запись в excel.
    */
    public void WriteToExcel() throws IOException{
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Результаты ТОП 10");
        XSSFRow r = sheet.createRow(0);
        r.createCell(0).setCellValue("№");
        r.createCell(1).setCellValue("Имя");
        r.createCell(2).setCellValue("Количество баллов");
        for (int i=0; i<results.size();i++){
            if (i<10){
                XSSFRow r2 = sheet.createRow(i+1);
                r2.createCell(0).setCellValue(i+1);
                r2.createCell(1).setCellValue(results.get(i).getName());
                r2.createCell(2).setCellValue(results.get(i).getPoints());
            }
        }
        File f = new File(".\\resources\\Results.xlsx");
        book.write(new FileOutputStream(f));
        book.close();
    }
        
    /** 
    * Функция, которая получает хранящиеся результаты игроков.
    */
    public ArrayList<Result> getResults(){
        return this.results;
    }
    
    /** 
    * Функция, которая считывает в поле results информацию о результатах, хранящихся в таблице.
    */
    public void ReadFromExcel() throws IOException{
        XSSFWorkbook book = new XSSFWorkbook(".\\resources\\Results.xlsx");
        XSSFSheet sh = book.getSheetAt(0);
        for (int i=1; i<=sh.getLastRowNum();i++) {
            results.add(new Result(sh.getRow(i).getCell(1).getStringCellValue(),(int)sh.getRow(i).getCell(2).getNumericCellValue()));
        }
    }
        
    /** 
    * Функция, которая осуществляет отображение результатов игроков в виде таблицы.
    */
    public void WriteToTable(JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i=0; i<results.size();i++){
            if (i<10){
                model.setValueAt(results.get(i).getName(), i, 0);
                model.setValueAt(results.get(i).getPoints(), i, 1);
            }
        }
    }
}
