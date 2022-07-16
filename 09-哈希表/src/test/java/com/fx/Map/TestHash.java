package com.fx.Map;

import org.junit.Test;

/**
 * <p>
 *
 * </p>
 *
 * @since: 2022/7/13 17:59
 * @author: 梁峰源
 */
public class TestHash {
    @Test
    public void test01(){
        String str = "jack";
        int len = str.length();
        int hashCode = 0;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
//          hashCode = hashCode * 31 + c;
            hashCode = (hashCode << 5) - hashCode + c;
        }
        System.out.println(hashCode);
        System.out.println(str.hashCode());
    }

    @Test
    public void test02() {
        Integer integer = 18;
        Float _float = 18.0f;
        Double _double = 18.0;
        Long _long = 18L;
        String str = "rose";
        System.out.println(integer.hashCode()); // 18
        System.out.println(_float.hashCode()); // 1099956224
        System.out.println(_double.hashCode()); // 1077018624
        System.out.println(_long.hashCode()); // 18
        System.out.println(str.hashCode()); // 3506511
    }
}
