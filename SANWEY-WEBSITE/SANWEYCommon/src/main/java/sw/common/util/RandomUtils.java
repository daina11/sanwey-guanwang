package sw.common.util;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

import java.util.UUID;

/**
 * @author wwh
 * @date 2021/4/16
 * @email 644129971@qq.com
 */
public class RandomUtils {

    /**生成随机验证码*/
    public static String random(String baseString, int length) {
        return RandomUtil.randomString(baseString, length);
    }

    /**生成32位UUID*/
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return new StringBuffer().append(uuid.substring(0,8)).append(uuid.substring(9,13)).append(uuid.substring(14,18)).append(uuid.substring(19,23)).append(uuid.substring(24)).toString();
    }
}
