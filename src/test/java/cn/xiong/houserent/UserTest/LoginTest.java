package cn.xiong.houserent.UserTest;

import cn.xiong.houserent.utils.EncryptUtils;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class LoginTest {
    @Test
    public void testLogin() throws NoSuchAlgorithmException {
        EncryptUtils encryptUtils = new EncryptUtils();
        String password = "123456";
        String md5 = encryptUtils.md5(password);
        System.out.println(md5);
    }
}
