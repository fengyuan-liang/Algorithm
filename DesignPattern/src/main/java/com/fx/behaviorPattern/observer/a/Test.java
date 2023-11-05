package com.fx.behaviorPattern.observer.a;


import lombok.Data;

public class Test {
    public static void main(String[] args) {
        Role role = new Role();
        role.setName("角色");
        role.setHp(100);
        Monster monster = new Monster();
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
    }
}
