package com.alibaba.graphar.utils;

import org.junit.Test;

public class InfoVersionTest {
    @Test
    public void testInfoVersion() {
        InfoVersion infoVersion = InfoVersion.creat(1);
        System.out.println(infoVersion.version());
    }
}
