package cn.ibionic.wechat;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：Yuho Liu
 * @description：
 * @date ：2021/11/28 2:23 PM
 */
@SpringBootTest
public class UserTest {


    @Test
     public void test(){
        String a = "a";
        foo(a);
        System.out.println(a);

        List<String> list = new ArrayList<>();
        fo(list);
        System.out.println(list.size());
     }
     void foo(String a) {
         a = "b";
         System.out.println(a);
     }

     void fo(List<String> list) {
        list.add("a");
     }
}
