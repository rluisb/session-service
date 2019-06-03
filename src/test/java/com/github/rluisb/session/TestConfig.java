package com.github.rluisb.session;


import com.github.rluisb.agenda.cucumber.World;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {
                AgendaServiceApplication.class,
                World.class
        })
public class TestConfig {
}
