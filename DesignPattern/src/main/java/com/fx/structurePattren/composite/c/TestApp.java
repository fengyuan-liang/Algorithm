package com.fx.structurePattren.composite.c;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 但凡有菜单的地方，都可以使用组合设计模式
 * <pre>
 *     菜单和菜单项
 *
 *     菜单
 *     |--湖南菜
 *          |-- 辣椒炒肉
 *                  |-- 普通辣
 *                  |-- 爆辣
 *          |-- 鸭
 *              |-- 永州血鸭
 *              |-- 啤酒鸭
 *     |--江西菜
 *          |-- 小炒黄牛肉
 *          |-- 明基虾
 * </pre>
 *
 * <p>
 * 目前下面代码的问题是
 * <p> 1. 客户需要素菜菜单
 * <p> 2. 客户需要辣菜单
 *
 * <p>
 * 重构代码想要达到的效果如下
 * <p> 1. 菜单可以添加菜单和菜单项
 * <p> 2. 菜单项不能添加菜单项
 *
 * @author Eureka <liangfengyuan1024@gmail.com>
 * @since 2025/11/9 14:35
 */
public class TestApp {
    public static void main(String[] args) {
        Menu menu = new Menu("湖南菜", "湖南人爱吃的菜");
        menu.add(new MenuItem("鸭", "永州血鸭"));
        menu.add(new MenuItem("蚂蚁上树", "粉丝肉末"));

        Menu menu2 = new Menu("辣椒炒肉", "湖南人爱吃辣椒炒肉");
        menu2.add(new MenuItem("普通辣", "普通辣"));
        menu2.add(new MenuItem("爆辣", "爆辣"));

        Menu menu3 = new Menu("江西菜", "江西菜");
        menu3.add(new MenuItem("小炒黄牛肉", "鲜嫩可口"));
        menu3.add(new MenuItem("明基虾", "香辣美味"));

        menu2.add(menu3);
        menu.add(menu2);

        MenuItem mi3 = new MenuItem("剁椒鱼头", "经典湘菜");
        menu.add(mi3);

        menu.print("");
    }

    @Data
    abstract static class MenuComponent {
        private String name;
        private String description;

        public MenuComponent() {
        }

        public MenuComponent(String menuName, String description) {
            setName(menuName);
            setDescription(description);
        }

        protected abstract void print(String prefix);
    }

    @Data
    static class Menu extends MenuComponent {
        private List<MenuComponent> menuItemList = new ArrayList<>();

        public Menu(String menuName, String description) {
            super(menuName, description);
        }

        public void add(MenuComponent menuComponent) {
            menuItemList.add(menuComponent);
        }

        public void print(String prefix) {
            // 使用前缀打印菜单名称
            System.out.println(prefix + "menu " + getName() + "--" + getDescription());

            // 为子项创建新的前缀（增加缩进）
            String newPrefix = prefix + "\t |--";
            // 下面会递归调用子项的print方法
            for (MenuComponent menuComponent : menuItemList) {
                menuComponent.print(newPrefix);
            }
        }
    }

    @Data
    static class MenuItem extends MenuComponent {
        public MenuItem(String menuName, String description) {
            super(menuName, description);
        }

        public void print(String prefix) {
            System.out.println(prefix + getName() + "--" + getDescription());
        }
    }
}