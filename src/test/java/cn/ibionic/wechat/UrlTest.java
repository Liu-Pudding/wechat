package cn.ibionic.wechat;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author ：Yuho Liu
 * @description：
 * @date ：2022/1/10 12:55 AM
 */
@SpringBootTest
public class UrlTest {

    private static final List<String> operationSecondLabel = Arrays.asList("TO B", "TO C", "海外用户", "TO G");

    public void test(){
        List<String> list = Arrays.asList("TO B", "标签");
        Collection<String> intersection = CollectionUtils.intersection(operationSecondLabel, list);
        System.out.println();
    }
}
