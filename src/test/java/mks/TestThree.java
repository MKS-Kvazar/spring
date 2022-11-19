package mks;

import spring.Autowired;
import spring.Component;

@Component
public class TestThree {
    @Autowired
    private TestOne testOne;

    public TestOne getTestOne(){
        return testOne;
    }
}
