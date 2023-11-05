package com.fx.behaviorPattern.observer.d;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Role role = new Role();
        role.setName("角色");
        role.setHp(100);

        Panel panel = new Panel(role);
        role.addObserver(panel);

        BallPanel ballPanel = new BallPanel(role);
        role.addObserver(ballPanel);

        HeadPanel headPanel = new HeadPanel(role);
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
    /**
     * 血量
     **/
    private int hp;
    /**
     * 魔法值（蓝）
     **/
    private int mp;
    /**
     * 所有观察者
     **/
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer obj) {
        observers.add(obj);
    }

    public void removeObserver(Observer obj) {
        observers.remove(obj);
    }

    public void notifyObservers() {
        observers.forEach(Observer::notifyObserver);
    }

    public void setHp(int hp) {
        // 更新血条的时候，一定要通知三个地方：1. 血条、球、面板
        this.hp = hp;
        System.out.println("血条更新为：" + hp);

        System.out.println("球形更新为：" + hp);
        System.out.println("面板更新为：" + hp);
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", mp=" + mp +
                '}';
    }
}

class Monster {
    public void attack(Role r) {
        r.setHp(r.getHp() - 10);
        r.notifyObservers();
    }
}

/**
 * 观察者们需要一个方法来接收数据
 */

interface Observer {
    void notifyObserver();
}

/**
 * 观察者模式拥有很多变体
 */
class Panel implements Observer {
    /**
     * 访问者模式其中一种实现就是要持有数据的引用 也就是推拉的形式
     * 推，让数据源推消息给观察者
     * 拉，观察者拉取自己关心的数据
     */
    private final Role r;

    public Panel(Role r) {
        this.r = r;
    }

    @Override
    public void notifyObserver() {
        System.out.printf("Panel 更新血条了值为：%d\n", r.getHp());
    }
}

class BallPanel implements Observer {
    private final Role r;

    public BallPanel(Role r) {
        this.r = r;
    }

    @Override
    public void notifyObserver() {
        System.out.printf("Panel 更新血条了值为：%d\n", r.getHp());
    }
}

class HeadPanel implements Observer {
    private final Role r;

    public HeadPanel(Role r) {
        this.r = r;
    }

    @Override
    public void notifyObserver() {
        System.out.printf("Panel 更新血条了值为：%d\n", r.getHp());
    }
}
