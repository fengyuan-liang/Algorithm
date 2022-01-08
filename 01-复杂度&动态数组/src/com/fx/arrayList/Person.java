package com.fx.arrayList;

/**
 * @author: 梁峰源
 * @date: 2021/12/29 21:00
 * Description:
 */
public class Person {
    private String userName;
    private Integer age;
    private String sex;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("该对象被销毁了："+this.getClass());
    }

    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    public Person(String userName, Integer age, String sex) {
        this.userName = userName;
        this.age = age;
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
