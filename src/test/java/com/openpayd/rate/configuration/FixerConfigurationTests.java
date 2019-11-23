package com.openpayd.rate.configuration;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FixerConfigurationTests.TestConfiguration.class})
public class FixerConfigurationTests {

    @Autowired
    FixerConfiguration fixerConfiguration;

    @Test
    public void Should_Check_FixerConfiguration() {
        Assert.assertEquals(fixerConfiguration.getUrl(), "http://data.fixer.io/api/");
        Assert.assertEquals(fixerConfiguration.getApiKey(), "96bfcb20f940d0169074d3ca80a749ec");
    }

    @EnableConfigurationProperties(FixerConfiguration.class)
    public static class TestConfiguration {
    }

}
