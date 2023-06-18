package com.alibaba.graphar.utils;

import org.junit.Test;
import org.junit.Assert;
import com.alibaba.fastffi.stdcxx.StdString;

public class InfoVersionTest {
    @Test
    public void test() {
        InfoVersion infoVersion = InfoVersion.create(1);
        Assert.assertEquals(1, infoVersion.version());
        // Assert.assertEquals("gar/v1", infoVersion.toStdString().toJavaString());
        Assert.assertTrue(infoVersion.checkType(StdString.create("string")));
        Assert.assertEquals(0, infoVersion.userDefineTypes().size());
    }
}
