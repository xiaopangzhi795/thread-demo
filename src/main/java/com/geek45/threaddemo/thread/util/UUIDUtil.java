/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.threaddemo.thread.util;

import java.util.UUID;

/**
 * @ClassName: UUIDUtil
 * @Decription:
 * @Author: rubik
 *  rubik create UUIDUtil.java of 2022/1/20 11:23 下午
 */
public class UUIDUtil {
    public static String uuidStr() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 取后16位uid
     * @return
     */
    public static String uuidStrShort() {
        return uuidStr().substring(16, 32);
    }

    /**
     * 873afc03f0c564ea
     * a794932dcca592da
     * @param args
     */
    public static void main(String[] args) {
        System.err.println(uuidStr());
        System.err.println(uuidStrShort());
    }
}
