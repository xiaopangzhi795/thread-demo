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
}
