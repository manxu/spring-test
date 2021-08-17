package com.zc.original_test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 计算工具.
 */
public class CalcUtil {

    /**
     * 比较大小 （null 比任何数字小）.
     * <li>相等为0
     * <li>number1 < number2 ： -1
     * <li>number1 > number2 ： 1
     * @param number1 数字1
     * @param number2 数字2
     * @return 比较结果
     */
    public static int compareTo(Object number1, Object number2) {

        if (number1 == null && number2 == null) {
            return 0;
        }

        if (number1 == null) {
            return -1;
        }

        if (number2 == null) {
            return 1;
        }

        return toBigDecimal(number1).compareTo(toBigDecimal(number2));
    }

    /**
     * 转换成BigDecimal型（默认值为0）.
     *
     * @param number
     *            转换对象
     * @return 转换后值
     */
    public static BigDecimal toBigDecimal(Object number) {

        return toBigDecimal(number, BigDecimal.ZERO);

    }

    /**
     * 转换成BigDecimal型.
     *
     * @param number
     *            转换对象
     * @param defaultValue
     *            默认值
     * @return 转换后值
     */
    public static BigDecimal toBigDecimal(Object number, BigDecimal defaultValue) {

        if (number == null) {
            return defaultValue;
        }

        if (number instanceof String) {
            return new BigDecimal((String) number);
        } else if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        } else if (number instanceof Number) {
            return BigDecimal.valueOf(((Number) number).doubleValue()) ;
        }

        return defaultValue;
    }

    /**
     * 数字相加.
     *
     * @param numbers
     *            相加对象
     * @return 相加后值
     */
    public static BigDecimal addBigDecimal(Object... numbers) {

        if (numbers == null || numbers.length == 0) {
            return BigDecimal.ZERO;
        }
        if (numbers.length == 1) {
            return toBigDecimal(numbers[0]);
        }
        BigDecimal ret = BigDecimal.ZERO;
        for (int i = 0; i < numbers.length; i++) {
            ret = ret.add(toBigDecimal(numbers[i]));
        }
        return ret;
    }

    /**
     * 数字相减.
     *
     * @param subser
     *            减数
     * @param toSub
     *            被减数
     * @return 相除结果
     */
    public static BigDecimal subtractBigDecimal(Object subser, Object toSub) {

        BigDecimal ret = toBigDecimal(subser);
        if (toSub == null) {
            return ret;
        }
        return ret.subtract(toBigDecimal(toSub));
    }

    /**
     * 数字相乘.
     *
     * @param muler
     *            除数
     * @param toMul
     *            被除数
     * @return 相乘结果
     */
    public static BigDecimal multiplyBigDecimal(Object muler, Object toMul) {

        if (muler == null || toMul == null) {
            return BigDecimal.ZERO;
        }
        return toBigDecimal(muler).multiply(toBigDecimal(toMul));
    }

    /**
     * 数字相除.
     *
     * @param divser
     *            除数
     * @param toDivs
     *            被除数
     * @param scale
     *            精度
     * @return 相除结果
     */
    public static BigDecimal divideBigDecimal(Object divser, Object toDivs, int scale) {

        if (divser == null || toDivs == null) {
            return BigDecimal.ZERO;
        }
        return toBigDecimal(divser).divide(toBigDecimal(toDivs), scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 设置经度
     * @param number
     * @param scale
     * @param mode
     * @return
     */
    public static BigDecimal toBigDecimal(Object number, int scale,RoundingMode mode) {
        BigDecimal decimal = toBigDecimal(number);
        if(decimal != null) {
            decimal = decimal.setScale(scale, mode);
        }
        return decimal;
    }
}

