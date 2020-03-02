package us.betahouse.haetae.user.utils;

import org.junit.Test;

public class EncryptUtilTest {

    @Test
    public void encryptPassword() {
        System.out.println(EncryptUtil.encryptPassword("123456", "0587e6fc-c810-4e0a-885a-d3ef464b59ff"));
    }
}