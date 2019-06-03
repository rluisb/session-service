package com.github.rluisb.session;


import com.github.rluisb.session.cucumber.World;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {
                SessionServiceApplication.class,
                World.class
        })
public class TestConfig {
}
