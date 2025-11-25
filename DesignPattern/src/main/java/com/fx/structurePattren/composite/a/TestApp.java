package com.fx.structurePattren.composite.a;

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
 * <p> 1. 因为现在的菜单只能添加菜单项，不能再添加其他菜单
 * <p> 2. 不能出现嵌套的菜单形式
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
        menu.add(new MenuItem("蚂蚁上树", "永州血鸭"));
        Menu menu2 = new Menu("辣椒炒肉", "湖南人爱吃辣椒炒肉");
        /*
         * =====>>>>  cannot compile <=========
         * 因为现在的菜单只能添加菜单项，不能再添加其他菜单
         */
        // menu.add(menu2);
        menu.print("\t |--");
    }

    /**
     * 菜单组件，用于抽取出菜单和菜单项的共性
     */
    @Data
    static class MenuComponent {
        private String name;
        private String description;

        public MenuComponent() {}

        public MenuComponent(String menuName, String description) {
            setName(menuName);
            setDescription(description);
        }
    }

    @Data
    static class Menu extends MenuComponent {
        private List<MenuItem> menuItemList = new ArrayList<>();
        public Menu(String menuName, String description) {
            super(menuName, description);
        }
        public void add(MenuItem menuItem) {
            menuItemList.add(menuItem);
        }
        public void print(String prefix) {
            System.out.println("menu " + getName() + "--" + getDescription());
            for (MenuItem menuItem : menuItemList) {
                menuItem.print(prefix);
            }
        }
    }

    @Data
    static class MenuItem extends MenuComponent{
        public MenuItem(String menuName, String description) {
            super(menuName, description);
        }
        public void print(String prefix) {
            System.out.println(prefix + getName() + "--" + getDescription());
        }
    }
}
