package mks;

import spring.Component;

@Component
public class TestFour extends TestThree {
    private int four = 4;
    void printFour(){
        System.out.println("print Four - " + four);
    }
}
