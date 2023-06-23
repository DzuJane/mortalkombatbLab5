/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dzuba.mortalkombatbversion;

//ADD IMAGE!!!

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;


/** 
 * Класс Fight для имитации боя игрока и врага.
 * @autor Мария, Ксения
*/
public class Fight {
    /** Поле для вызова манипуляций с текстом */
    private ChangeTexts change = new ChangeTexts();
    /** Поле для хранения выбранной стратегии */
    private int kind_attack[] = {0};
    /** Поле, хранящее опыт необходимый для перехода на новый уровень */
    private int experiences[] = {40, 90, 180, 260, 410};
    /** Поле хранящее фабрику, для создания врагов*/
    private EnemyFabric fabric = new EnemyFabric();
    int i = 1;
    private int k = -1;
    private int stun = 0;
    private double v = 0.0;
    private boolean bossEnemy = false;

    /*public int tryCallRegenerate(Player p){
        v = Math.random();
        //System.out.println(p1 instanceof ShaoKahn | p2 instanceof ShaoKahn);
        if (p instanceof ShaoKahn & v < 0.1){
            //p.setAttack(2);
            return 2;
        } else {
            return p.getAttack();
        }
        
    }*/
    
    /** 
     * Функция, которая вызывает "бой" в зависимости от контекста, полученного при выборе игроком и его врагом действий.
     */
    public void Move(Player p1, Player p2, JLabel l, JLabel l2) {
        if (stun == 1) {
            p1.setAttack(-1);
        }
        switch (Integer.toString(p1.getAttack()) + Integer.toString(p2.getAttack())) {
            case "10":
                v = Math.random();
                if (p1 instanceof ShaoKahn & v < 0.15) {
                    p2.setHealth(-(int) (p1.getDamage() * 0.5));
                    l2.setText("Your block is broken");

                } else {
                    p1.setHealth(-(int) (p2.getDamage() * 0.5));
                    l2.setText(p2.getName() + " counterattacked");
                }
                break;
            case "11":
                p2.setHealth(-p1.getDamage());
                l2.setText(p1.getName() + " attacked");
                break;
            case "00":
                v = Math.random();
                if (v <= 0.5) {
                    stun = 1;
                }
                l2.setText("Both defended themselves");
                break;
            case "01":
                l2.setText(p1.getName() + " didn't attacked");
                break;
            case "-10":
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                l2.setText(p2.getName() + " didn't attacked");
                break;
            case "-11":
                p1.setHealth(-p2.getDamage());
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                l2.setText(p2.getName() + " attacked");
                break;
        }
    }
    
    /** 
     * Функция, которая осуществляет выбор стратегии игроками, вызов "боя" в зависимости от них и проверку на завершения раунда.
     */
    public void Hit(Player human, Player enemy, int a, JLabel label,
            JLabel label2, JDialog dialog, JLabel label3, CharacterAction action,
            JProgressBar pr1, JProgressBar pr2, JDialog dialog1,
            JDialog dialog2, JFrame frame, ArrayList<Result> results,
            JLabel label4, JLabel label5, JLabel label6, JLabel label7,
            JLabel label8, Items[] items, JRadioButton rb, JDialog chooseHealthOrDamageDialog,
            JFrame fightFrame, JButton okHealthOrDamageButton,
            JLabel healthOrDamageLabel,
            JRadioButton healthRadioButton, JRadioButton damageRadioButton, 
            JLabel countLocationLabel,JLabel countEnemiesLabel,
            JLabel tempLocationLabel,JLabel tempEnemiesLabel) {
        label7.setText("");
        
        countLocationLabel.setText(String.valueOf(((Human)human).getCountLocations()));
        tempLocationLabel.setText(String.valueOf(((Human)human).getTempLocations()));
        countEnemiesLabel.setText(String.valueOf(((Human)human).getCountEnemies()));
        tempEnemiesLabel.setText(String.valueOf(((Human)human).getTempEnemies()));
        
        human.setAttack(a);
        System.out.println("## " + k + " ##");
        if (k < kind_attack.length - 1) {
            k++;
        } else {
            //kind_attack = action.ChooseBehavior(enemy, action);
            kind_attack = action.ChooseBehavior(enemy, action);
            k = 0;
        }
        enemy.setAttack(kind_attack[k]);
        if (i % 2 == 1) {
            Move(human, enemy, label7, label8);
        } else {
            Move(enemy, human, label7, label8);
        }
        i++;
        change.RoundTexts(human, enemy, label, label2, i, label6);
        action.HP(human, pr1);
        action.HP(enemy, pr2);
        if (human.getHealth() <= 0 & items[2].getCount() > 0) {
            human.setNewHealth((int) (human.getMaxHealth() * 0.05));
            items[2].setCount(-1);
            action.HP(human, pr1);
            label2.setText(human.getHealth() + "/" + human.getMaxHealth());
            rb.setText(items[2].getName() + ", " + items[2].getCount() + " шт");
            label7.setText("Вы воскресли");
        }
        System.out.println(human.getHealth() + " | " + enemy.getHealth());
        if (human.getHealth() <= 0 | enemy.getHealth() <= 0) {
            if (human.getHealth() <= 0){
                EndFinalRound(((Human) human), action, results, dialog1, dialog2,
                        frame, label4, label5, chooseHealthOrDamageDialog, fightFrame,
                        okHealthOrDamageButton, healthOrDamageLabel, 
                             healthRadioButton,damageRadioButton);
            } else if (((Human) human).getTempLocations() < ((Human) human).getCountLocations() || 
                    !(enemy instanceof ShaoKahn)){
                EndRound(human, enemy, dialog, label3, action, items, chooseHealthOrDamageDialog, 
                        fightFrame, okHealthOrDamageButton, healthOrDamageLabel, 
                        healthRadioButton,damageRadioButton, 
                        countLocationLabel, countEnemiesLabel,
                        tempLocationLabel, tempEnemiesLabel);
            } else {
                EndFinalRound(((Human) human), action, results, dialog1, dialog2,
                        frame, label4, label5, chooseHealthOrDamageDialog, fightFrame,
                        okHealthOrDamageButton, healthOrDamageLabel, 
                             healthRadioButton,damageRadioButton);
            } 
            /*if (((Human) human).getWin() == 11) {
                EndFinalRound(((Human) human), action, results, dialog1, dialog2,
                        frame, label4, label5, chooseHealthOrDamageDialog, fightFrame,
                        okHealthOrDamageButton, healthOrDamageLabel, 
                             healthRadioButton,damageRadioButton);
            } else {
                EndRound(human, enemy, dialog, label3, action, items, chooseHealthOrDamageDialog, 
                        fightFrame, okHealthOrDamageButton, healthOrDamageLabel, 
                             healthRadioButton,damageRadioButton);
            }*/
        }
    }

    /** 
     * Функция, которая определяет поведение игроков после боя и выводит информацию о его завершении.
     */
    public void EndRound(Player human, Player enemy, JDialog dialog, JLabel label,
            CharacterAction action, Items[] items, JDialog chooseHealthOrDamageDialog,
            JFrame fightFrame, JButton okHealthOrDamageButton,
            JLabel healthOrDamageLabel,
            JRadioButton healthRadioButton, JRadioButton damageRadioButton, 
            JLabel countLocationLabel,JLabel countEnemiesLabel,
            JLabel tempLocationLabel,JLabel tempEnemiesLabel) {
        fightFrame.setVisible(false);
        
        human.setCount_attack_enemy(0);
        human.setCount_defense_enemy(0);
        enemy.setCount_attack_enemy(0);
        enemy.setCount_defense_enemy(0);
        
        if (human.getHealth() > 0) {
            System.out.println("*** " + ((Human) human).getWin());
            
            label.setText("You win");
            ((Human) human).setWin();
            if (enemy instanceof ShaoKahn) {
                ((Human) human).setCountEnemies(human.getLevel());
                ((Human) human).setTempEnemies(0);
                ((Human) human).addTempLocations();
                action.AddItems(38, 23, 8, items);
                action.AddPointsBoss(((Human) human), action.getEnemyes(), 
                        chooseHealthOrDamageDialog, fightFrame,
                        healthOrDamageLabel, healthRadioButton,damageRadioButton);
            } else {
                
                ((Human) human).addTempEnemies();
                action.AddItems(25, 15, 5, items);
                action.AddPoints(((Human) human), action.getEnemyes(), dialog,
                        chooseHealthOrDamageDialog, fightFrame, okHealthOrDamageButton,
                        healthOrDamageLabel, healthRadioButton,damageRadioButton);
            }
        } else {
            if (enemy instanceof ShaoKahn) {
                //((Human) human).setCountEnemies(human.getLevel());
                //((Human) human).setTempEnemies(0);
                //((Human) human).addTempLocations();
                bossEnemy = true;
            } else {
                ((Human) human).addTempEnemies();
            }
            healthOrDamageLabel.setVisible(false);
            healthRadioButton.setVisible(false);
            damageRadioButton.setVisible(false);
            label.setText(enemy.getName() + " win");
        }
        //healthOrDamageLabel1.setVisible(false);
        //healthRadioButton1.setVisible(false);
        //damageRadioButton1.setVisible(false);
        dialog.setVisible(true);
        dialog.setBounds(300, 150, 700, 600);
        i = 1;
        k = -1;
        kind_attack = ResetAttack();
        //((Human) human).setCountEnemies(human.getLevel());
        //((Human) human).setTempEnemies(0);
        //((Human) human).setTempEnemies(0);
        
        countLocationLabel.setText(String.valueOf(((Human)human).getCountLocations()));
        tempLocationLabel.setText(String.valueOf(((Human)human).getTempLocations()));
        countEnemiesLabel.setText(String.valueOf(((Human)human).getCountEnemies()));
        tempEnemiesLabel.setText(String.valueOf(((Human)human).getTempEnemies()));
    }

    /** 
     * Функция, которая определяет поведение игроков после финального боя и выводит информацию о его завершении.
     */
    public void EndFinalRound(Human human, CharacterAction action,
            ArrayList<Result> results, JDialog dialog1, JDialog dialog2, JFrame frame,
            JLabel label1, JLabel label2, JDialog chooseHealthOrDamageDialog, JFrame fightFrame,
            JButton okHealthOrDamageButton,
            JLabel healthOrDamageLabel,
            JRadioButton healthRadioButton, JRadioButton damageRadioButton) {
        String text = "Победа не на вашей стороне";
        if (human.getHealth() > 0) {
            human.setWin();
            action.AddPoints(human, action.getEnemyes(),dialog1,
                            chooseHealthOrDamageDialog, fightFrame,
                             okHealthOrDamageButton, healthOrDamageLabel, 
                             healthRadioButton,damageRadioButton);
            text = "Победа на вашей стороне";
        } else {
            text = "Вы умерли :(";
        }
        boolean top = false;
        if (results == null) {
            top = true;
        } else {
            int i = 0;
            for (int j = 0; j < results.size(); j++) {
                if (human.getPoints() < results.get(j).getPoints()) {
                    i++;
                }
            }
            if (i < 10) {
                top = true;
            }
        }
        if (top) {
            dialog1.setVisible(true);
            dialog1.setBounds(150, 150, 600, 500);
            label1.setText(text);
        } else {
            dialog2.setVisible(true);
            dialog2.setBounds(150, 150, 470, 360);
            label2.setText(text);
        }
        frame.dispose();
    }
    
    /** 
     * Функция, которая определяет обнуление атаки.
     */
    public int[] ResetAttack() {
        int a[] = {0};
        return a;
    }

    /** 
     * Функция, которая определяет врага, здоровье игрока и врага на начало боя и выводит информацию о выбранном враге.
     */
    public Player NewRound(Player human, JLabel label, JProgressBar pr1,
            JProgressBar pr2, JLabel label2, JLabel text, JLabel label3, CharacterAction action) {

        Player enemy1 = null;
        System.out.println(((Human) human).getWin());

        if (((Human) human).getCountEnemies() > ((Human) human).getTempEnemies() && !bossEnemy){
            //((Human) human).addTempEnemies();
            enemy1 = action.ChooseEnemy(label, label2, text, label3);
        } else {
            //((Human) human).setTempEnemies(0);
            enemy1 = action.ChooseBoss(label, label2, text, label3, human.getLevel());
            if (bossEnemy){
                bossEnemy = false;
            }
        }
        /*if (((Human) human).getWin() == 6 | ((Human) human).getWin() == 11) {
            enemy1 = action.ChooseBoss(label, label2, text, label3, human.getLevel());
        } else {
            enemy1 = action.ChooseEnemy(label, label2, text, label3);
        }*/
        pr1.setMaximum(human.getMaxHealth());
        pr2.setMaximum(enemy1.getMaxHealth());
        human.setNewHealth(human.getMaxHealth());
        enemy1.setNewHealth(enemy1.getMaxHealth());
        action.HP(human, pr1);
        action.HP(enemy1, pr2);
        return enemy1;
    }

}
