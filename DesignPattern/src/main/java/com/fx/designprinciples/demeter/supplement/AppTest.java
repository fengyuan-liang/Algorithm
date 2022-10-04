package com.fx.designprinciples.demeter.supplement;


/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/10/3 13:22
 */
public class AppTest {
    public void func() {
        AppBean appBean = BeanFactory.getAppBean();
        // 朋友的朋友就不是朋友了
        this.getStr(appBean);
    }
    /* 将朋友的细节转换为自己属性的方法 */
    public String getStr(AppBean appBean){
        return appBean.getStr();
    }
}

class BeanFactory {
    public static AppBean getAppBean() {
        return new AppBean();
    }
}

class AppBean {
    public String getStr() {
        return "";
    }
}