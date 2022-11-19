package mks;

import spring.Autowired;
import spring.Component;

@Component
public class TestTwo {
    @Autowired
    private TestThree testThree;

    public TestThree getTestThree(){
        return testThree;
    }
}
