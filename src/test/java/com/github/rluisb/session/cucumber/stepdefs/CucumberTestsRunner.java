package com.github.rluisb.session.cucumber.stepdefs;

import com.github.rluisb.session.SessionServiceApplication;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@ContextConfiguration(loader = SpringBootContextLoader.class, classes = SessionServiceApplication.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.github.rluisb.session.command")
public class CucumberTestsRunner {
}

