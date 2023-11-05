package com.fx.behaviorPattern.observer.b;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Role role = new Role();
        role.setName("角色");
        role.setHp(100);

        Panel panel = new Panel();
        role.addObserver(panel);

        BallPanel ballPanel = new BallPanel();
        role.addObserver(ballPanel);

        HeadPanel headPanel = new HeadPanel();
        role.addObserver(headPanel);

        Monster monster = new Monster();
        monster.attack(role);
        monster.attack(role);
        monster.attack(role);
    }
}

@Data
class Role {
    private String name;
    /** 血量 **/
    private int hp;
    /** 魔法值（蓝） **/
    private int mp;
    // 所有观察者
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer obj) {
        observers.add(obj);
    }

    public void removeObserver(Observer obj) {
        observers.remove(obj);
    }

    public void notifyObservers(int hp) {
        observers.forEach(obj -> obj.notifyObserver(hp));
    }

    public void setHp(int hp) {
        // 更新血条的时候，一定要通知三个地方：1. 血条、球、面板
        this.hp = hp;
        System.out.println("血条更新为：" + hp);

        System.out.println("球形更新为：" + hp);
        System.out.println("面板更新为：" + hp);
    }
}

class Monster {
    public void attack(Role r) {
        r.setHp(r.getHp() - 10);
        r.notifyObservers(r.getHp() - 10);
    }
}

/**
 * 观察者们需要一个方法来接收数据
 */

interface Observer{
    void notifyObserver(int hp);
}

class Panel implements Observer{
    @Override
    public void notifyObserver(int hp) {
        System.out.printf("Panel 接收到消息%d\n", hp);
    }
}

class BallPanel implements Observer{
    @Override
    public void notifyObserver(int hp) {
        System.out.printf("BallPanel 接收到消息%d\n", hp);
    }
}

class HeadPanel implements Observer{
    @Override
    public void notifyObserver(int hp) {
        System.out.printf("HeadPanel 接收到消息%d\n", hp);
    }
}
