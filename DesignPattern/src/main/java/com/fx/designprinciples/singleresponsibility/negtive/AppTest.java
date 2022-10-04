package com.fx.designprinciples.singleresponsibility.negtive;

import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/10/2 20:19
 */
public class AppTest {

    static class Goods {
        private BigDecimal price;

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getPrice() {
            return this.price.multiply(new BigDecimal("0.8"));
        }
    }

    static class DiscountGoods extends Goods{
        @Override
        public BigDecimal getPrice() {
            return super.getPrice().multiply(new BigDecimal("0.8"));
        }
    }
}
