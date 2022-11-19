package mks;

import spring.Autowired;
import spring.Component;
import spring.PostConstructor;

@Component
public class TestOne {
    @Autowired
    private TestTwo testTwo;

    public TestTwo getTestTwo() {
        return testTwo;
    }

    @PostConstructor
    public void print(){
        System.out.println("PostConstructor");
    }
}
