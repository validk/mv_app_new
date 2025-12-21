package com.example.hello.controller;
import com.example.hello.db.DbUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    // Inject DbUtil
    @Autowired
    private DbUtil dbUtil;

    @GetMapping("/dbtime")
    public String dbTime() {
        logger.info("[{}] DBTime endpoint invoked", POD_NAME);
        String dbTime = dbUtil.getDbTime();
        return "Database current time from - " + POD_NAME + " : " + dbTime;
    }

    // Kubernetes pod or standalone server
    private static final String POD_NAME = System.getenv().getOrDefault("HOSTNAME", "standalone-server");
    private static final String DOMAIN_UID = System.getenv().getOrDefault("DOMAIN_UID", "prod-tele-domain");
    private static final String DOMAIN_NAME = System.getenv().getOrDefault("DOMAIN_NAME", "domain1");
    private static final String CLUSTER_NAME = System.getenv().getOrDefault("CLUSTER_NAME", "cluster-1");

    @GetMapping("/hello")
    public String hello() {
        logger.info("[{}] Hello endpoint invoked", POD_NAME);
        logger.debug("[{}] Debug trace: controller execution successful", POD_NAME);
        return "Hello from - " + POD_NAME;
    }

    @GetMapping("/status")
    public String status() {
        logger.info("[{}] Status endpoint invoked", POD_NAME);
        return "Status OK from - " + POD_NAME;
    }

    @GetMapping("/info")
    public String info() {
        logger.info("[{}] Info endpoint invoked", POD_NAME);

        // Fetch DB time
        // String dbTime = DbUtil.getDbTime();

        StringBuilder sb = new StringBuilder();
        sb.append("Hello World! This is version 'v1' of the sample JSP web-app.<br>")
        .append("Welcome to WebLogic Server '").append(POD_NAME).append("'!<br><br>")
        .append("  domain UID  = '").append(DOMAIN_UID).append("'<br>")
        .append("  domain name = '").append(DOMAIN_NAME).append("'<br><br>")
        .append("Found 1 local cluster runtime:<br>")
        .append("  Cluster '").append(CLUSTER_NAME).append("'<br><br>")
        .append("Found min threads constraint runtime named 'SampleMinThreads' with configured count: 1<br><br>")
        .append("Found max threads constraint runtime named 'SampleMaxThreads' with configured count: 10<br><br>")
        .append("Found 0 local data sources: <br><br>");
        return sb.toString();
    }

}
