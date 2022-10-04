package com.fx.creationalpatterns.factorypattern;

// 作者做的抽象产品
interface Food {
    void eat();
}

// 作者做的具体产品
class hamburger implements Food {

    @Override
    public void eat() {
        System.out.println("吃汉堡包...");
    }
}

class hamburger2 implements Food {

    @Override
    public void eat() {
        System.out.println("这是作者修改后的汉堡包...");
    }
}

class FoodFactory {
    public enum FoodNumberEnum {
        HAMBURGER(1001, "汉堡包"),
        HAMBURGER2(1002, "修改后的汉堡包"),
        ;
        private final Integer foodNumber;
        private final String describe;

        FoodNumberEnum(Integer foodNumber, String describe) {
            this.foodNumber = foodNumber;
            this.describe = describe;
        }

        public Integer getFoodNumber() {
            return foodNumber;
        }

        public String getDescribe() {
            return describe;
        }

        public static FoodNumberEnum getByType(int type) {
            for (FoodNumberEnum constants : values()) {
                if (constants.getFoodNumber() == type) {
                    return constants;
                }
            }
            return null;
        }
    }

    public static Food getFood(FoodNumberEnum foodNumberEnum) {
        Food food = null;
        switch (FoodNumberEnum.getByType(foodNumberEnum.getFoodNumber())) {
            case HAMBURGER:
                food = new hamburger();
                break;
            case HAMBURGER2:
                food = new hamburger();
                break;
            default:
                break;
        }
        return food;
    }
}

// 用户的业务逻辑
public class AppTest {
    public static void main(String[] args) {
        Food food = FoodFactory.getFood(FoodFactory.FoodNumberEnum.HAMBURGER);
        food.eat();
    }
}
