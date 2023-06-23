/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dzuba.mortalkombatbversion;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

/** 
 * Класс CharacterAction для манипуляций с поведением игрока и врага.
 * @autor Мария
 * 
*/
public class CharacterAction {
               
    /** Поле, хранящее информацию об опыте необходимом для перехода на следующий уровень*/  
    private final int experience_for_next_level[] = {40, 90, 180, 260, 410, 1000};
           
    /** Поле, хранящее информацию о стратегиях поведения врагов*/  
    private final int kind_fight[][] = {{1, 0}, {1, 1, 0}, {0, 1, 0}, {1, 1, 1, 1}};
           
    /** Поле, хранящее коллекцию возможных врагов*/  
    private Player enemyes[] = new Player[6];
           
    /** Поле, хранящее фабрику {@link EnemyFabric} по созданию существ*/  
    private EnemyFabric fabric = new EnemyFabric();
           
    /** Поле, хранящее врага*/  
    private Player enemyy = null;

    //private static boolean chooseHealth = true;
    
   /* public static void setChooseHealth(boolean ch){
        chooseHealth = ch;
    }*/
    /** 
     * Конструктор - создает набор врагов
     */
    public void setEnemyes() {
        enemyes[0] = fabric.create(0, 0);
        enemyes[1] = fabric.create(1, 0);
        enemyes[2] = fabric.create(2, 0);
        enemyes[3] = fabric.create(3, 0);
        enemyes[4] = fabric.create(4, 0);
        enemyes[5] = fabric.create(4, 0);
    }

    /** 
     * Функция, которая позволяет получить набор сгенерированных врагов
     */
    public Player[] getEnemyes() {
        return this.enemyes;
    }

    /** 
     * Функция, которая получает выбранного врага, отображает изображение врага и его имя  
     */
    public Player ChooseEnemy(JLabel label, JLabel label2, JLabel text, JLabel label3) {
        int i = (int) (Math.random() * 4);
        ImageIcon icon1 = null;
        switch (i) {
            case 0 -> {
                enemyy = enemyes[0];
                icon1 = new ImageIcon(".\\resources\\Baraka.png");
                label2.setText("Baraka (танк)");
            }
            case 1 -> {
                enemyy = enemyes[1];
                icon1 = new ImageIcon(".\\resources\\Sub-Zero.png");
                label2.setText("Sub-Zero (маг)");
            }
            case 2 -> {
                enemyy = enemyes[2];
                icon1 = new ImageIcon(".\\resources\\Liu Kang.png");
                label2.setText("Liu Kang (боец)");
            }
            case 3 -> {
                enemyy = enemyes[3];
                icon1 = new ImageIcon(".\\resources\\Sonya Blade.png");
                label2.setText("Sonya Blade (солдат)");
            }
        }
        label.setIcon(icon1);
        text.setText(Integer.toString(enemyy.getDamage()));
        label3.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
        return enemyy;
    }

    /** 
     * Функция, которая получает выбранного босса, отображает изображение босса и его имя  
     */
    public Player ChooseBoss(JLabel label, JLabel label2, JLabel text, JLabel label3, int i) {
        ImageIcon icon1 = new ImageIcon(".\\resources\\Shao Kahn.png");
        label2.setText("Shao Kahn (босс)");
        switch (i) {
            case 0 -> enemyy = enemyes[4];
            case 1 -> enemyy = enemyes[4];
            case 2 -> enemyy = enemyes[4];
            case 3 -> enemyy = enemyes[5];
            case 4 -> enemyy = enemyes[5];
            case 5 -> enemyy = enemyes[5];
        }
        label.setIcon(icon1);
        text.setText(Integer.toString(enemyy.getDamage()));
        label3.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
        return enemyy;
    }

    /** 
     * Функция, которая при условии наличия свойства регенерации генерирует с вероятностью 5% номер действия, когда регенерация будет осуществлена
     */
    private int getRandomNumb(boolean regenerate, int[] concrete_kind_fight){
        double v = Math.random();
        if (v < 0.005 && regenerate){
            return (int) (Math.random()* (concrete_kind_fight.length-1));
        }
        return -1;
    }
    
    /** 
     * Функция, которая определяет с заданной вероятностью стратегию поведения врага
     */
    public int[] EnemyBehavior(int k1, int k2, int k3, int k4, double i, boolean regenerate) {
        int arr[] = null;
        int id = -1;
        if (i < k1 * 0.01) {
            arr = kind_fight[0];
            id = getRandomNumb(regenerate, kind_fight[0]);
            if (id >= 0){
                kind_fight[0][id] = 2;
            }
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            arr = kind_fight[1];
            id = getRandomNumb(regenerate, kind_fight[0]);
            if (id >= 0){
                kind_fight[0][id] = 2;
            }
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            arr = kind_fight[2];
            id = getRandomNumb(regenerate, kind_fight[0]);
            if (id >= 0){
                kind_fight[0][id] = 2;
            }
        }
        if (i >= (k1 + k2 + k3) * 0.01 & i < 1) {
            arr = kind_fight[3];
            id = getRandomNumb(regenerate, kind_fight[0]);
            if (id >= 0){
                kind_fight[0][id] = 2;
            }
        }
        return arr;
    }

    /** 
     * Функция, которая вызывает определение стратегии {@link CharacterAction#EnemyBehavior} для различных врагов в зависимости от назначенных вероятностей поведения. 
     */  
    public int[] ChooseBehavior(Player enemy, CharacterAction action) {
        int arr[] = null;
        double i = Math.random();
        if (enemy instanceof Baraka) {
            arr = action.EnemyBehavior(15, 15, 60, 10, i, false);
        }
        if (enemy instanceof SubZero) {
            arr = action.EnemyBehavior(25, 25, 0, 50, i, false);
        }
        if (enemy instanceof LiuKang) {
            arr = action.EnemyBehavior(13, 13, 10, 64, i, false);
        }
        if (enemy instanceof SonyaBlade) {
            arr = action.EnemyBehavior(25, 25, 50, 0, i, false);
        }
        if (enemy instanceof ShaoKahn) {
            arr = action.EnemyBehavior(10, 45, 0, 45, i, true);
        }
        return arr;
    }

    /** 
     * Функция, которая устанавливает значение прогресса у игрока. 
     */  
    public void HP(Player player, JProgressBar progress) {

        if (player.getHealth() >= 0) {
            progress.setValue(player.getHealth());
        } else {
            progress.setValue(0);
        }
    }
        
    /** 
     * Функция, которая изменяет значение полученных очков в ходе боя у игрока . 
     */  
    public void AddPoints(Human human, Player[] enemyes, JDialog transitionDialog,
            JDialog chooseHealthOrDamageDialog,
            JFrame fightFrame, JButton okHealthOrDamageButton,
            JLabel healthOrDamageLabel1,
            JRadioButton healthRadioButton1, JRadioButton damageRadioButton1) {
        switch (human.getLevel()) {
            case 0 -> {
                human.setExperience(20);
                human.setPoints(25 + human.getHealth() / 4);
            }
            case 1 -> {
                human.setExperience(25);
                human.setPoints(30 + human.getHealth() / 4);
            }
            case 2 -> {
                human.setExperience(30);
                human.setPoints(35 + human.getHealth() / 4);
            }
            case 3 -> {
                human.setExperience(40);
                human.setPoints(45 + human.getHealth() / 4);
            }
            case 4 -> {
                human.setExperience(50);
                human.setPoints(55 + human.getHealth() / 4);
            }
        }
        boolean findFlag = false;
        for (int i = 0; i < 5; i++) {
            if (human.getLevel()<=i &&  experience_for_next_level[i] == human.getExperience()) {
                findFlag = true;
            }
        }
        fightFrame.setVisible(false);
        if (findFlag){
                healthOrDamageLabel1.setVisible(true);
                healthRadioButton1.setSelected(false);
                damageRadioButton1.setSelected(false);
                healthRadioButton1.setVisible(true);
                damageRadioButton1.setVisible(true);
        } else {
            healthOrDamageLabel1.setVisible(false);
            healthRadioButton1.setVisible(false);
            damageRadioButton1.setVisible(false);
        }
        //while(!okHealthOrDamageButton.isSelected()){}
        for (int i = 0; i < 5; i++) {
            if (human.getLevel()<=i &&  experience_for_next_level[i] == human.getExperience()) {                
                //chooseHealthOrDamageDialog.setVisible(true);
                //chooseHealthOrDamageDialog.setBounds(100, 100, 580, 450);                
                human.setNextExperience(experience_for_next_level[i + 1]);
                //NewHealthHuman(human);
                for (int j = 0; j < 4; j++) {
                    NewHealthEnemy(enemyes[j], human);
                }
            }
        }
    }
        
    /** 
     * Функция, которая изменяет значение полученных очков в ходе боя с боссом у игрока . 
     */  
    public void AddPointsBoss(Human human, Player[] enemyes, JDialog chooseHealthOrDamageDialog,
            JFrame fightFrame, JLabel healthOrDamageLabel1,
            JRadioButton healthRadioButton1, JRadioButton damageRadioButton1) {
        switch (human.getLevel()) {
            case 0 -> {
                human.setExperience(30);
                human.setPoints(45 + human.getHealth() / 2);
            }
            case 1 -> {
                human.setExperience(30);
                human.setPoints(45 + human.getHealth() / 2);
            }
            case 2 -> {
                human.setExperience(30);
                human.setPoints(45 + human.getHealth() / 2);
            }
            case 3 -> {
                human.setExperience(50);
                human.setPoints(65 + human.getHealth() / 2);
            }
            case 4 -> {
                human.setExperience(50);
                human.setPoints(65 + human.getHealth() / 2);
            }
            case 5 -> {
                human.setExperience(50);
                human.setPoints(65 + human.getHealth() / 2);
            }
        }
        fightFrame.setVisible(false);
        //chooseHealthOrDamageDialog.setVisible(true);
        //chooseHealthOrDamageDialog.setBounds(100, 100, 580, 450);
        
        boolean findFlag = false;
        for (int i = 0; i < 5; i++) {
            if (human.getLevel()<=i &&  experience_for_next_level[i] == human.getExperience()) {
                findFlag = true;
            }
        }
        if (findFlag){
                healthOrDamageLabel1.setVisible(true);
                healthRadioButton1.setSelected(false);
                damageRadioButton1.setSelected(false);
                healthRadioButton1.setVisible(true);
                damageRadioButton1.setVisible(true);
        } else {
            healthOrDamageLabel1.setVisible(false);
            healthRadioButton1.setVisible(false);
            damageRadioButton1.setVisible(false);
        }
        for (int i = 0; i < 5; i++) {
            if (human.getLevel()<=i &&  experience_for_next_level[i] <= human.getExperience()) {
                human.setLevel();
                human.setNextExperience(experience_for_next_level[i + 1]);
                //NewHealthHuman(human);
                for (int j = 0; j < 4; j++) {
                    NewHealthEnemy(enemyes[j], human);
                }
            }
        }
    }
        
    /** 
     * Функция, которая изменяет значение полученных вещей в ходе боя у игрока. 
     */  
    public void AddItems(int k1, int k2, int k3, Items[] items) {
        double i = Math.random();
        if (i < k1 * 0.01) {
            items[0].setCount(1);
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            items[1].setCount(1);
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            items[2].setCount(1);
        }
    }
           
    /** 
     * Функция, которая определяет значение здоровья и урона у игрока и его врага. 
     */  
    public void NewHealthEnemy(Player enemy, Human human) {
        int hp = 0;
        int damage = 0;
        switch (human.getLevel()) {
            case 1 -> {
                hp = 32;
                damage = 25;
            }
            case 2 -> {
                hp = 30;
                damage = 20;
            }
            case 3 -> {
                hp = 23;
                damage = 24;
            }
            case 4 -> {
                hp = 25;
                damage = 26;
            }
        }
        enemy.setMaxHealth((int) enemy.getMaxHealth() * hp / 100);
        enemy.setDamage((int) enemy.getDamage() * damage / 100);
        enemy.setLevel();
    }
        
    /** 
     * Функция, которая поволяет изменить значения здоровья у игрока в зависимости от использованной вещи. 
     */  
    public void UseItem(Player human, Items[] items, String name, JDialog dialog, JDialog dialog1) {
        switch (name) {
            case "smallPotionRadioButton":
                if (items[0].getCount() > 0) {
                    human.setHealth((int) (human.getMaxHealth() * 0.25));
                    items[0].setCount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "bigPotionRadioButton":
                if (items[1].getCount() > 0) {
                    human.setHealth((int) (human.getMaxHealth() * 0.5));
                    items[1].setCount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "resurrectionCrossRadioButton":
                dialog.setVisible(true);
                dialog.setBounds(300, 200, 400, 300);
                break;
        }
        
        if(dialog.isVisible()==false){
            dialog1.dispose();
        }
    }
}
