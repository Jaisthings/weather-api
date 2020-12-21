package com.jaisthings.weather;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.spring.web.plugins.Docket;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WeatherApplicationTests {

    @Autowired
    Docket docket;

    @Test
    void contextLoads() {
        assertThat(docket).isNotNull();
    }
}
