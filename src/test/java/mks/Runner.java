package mks;


import spring.Autowired;
import spring.Component;
import spring.Qualifier;

import java.util.logging.Logger;

@Component
public class Runner {
    @Autowired
    TestOne testOne;
    @Autowired
    TestTwo testTwo;
    @Autowired
    @Qualifier("testFour")
    TestThree testThree;

    private static final Logger log = Logger.getLogger(Runner.class.getName());

    public void run() {
        log.info("Start runner");
        System.out.println(testOne.getTestTwo());
        System.out.println(testTwo.getTestThree());
        System.out.println(testThree); //TestFour
        log.info("Finnish runner");
    }
}
