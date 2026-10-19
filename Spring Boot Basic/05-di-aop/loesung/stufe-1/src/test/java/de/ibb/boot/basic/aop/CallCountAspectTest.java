package de.ibb.boot.basic.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CallCountAspectTest {
    @Autowired
    private Greeter greeter;
    @Autowired
    private CallCountAspect aspect;

    @Test
    void countsHello() {
        int before = aspect.getCalls();
        assertEquals("Hallo Ada", greeter.hello("Ada"));
        greeter.hello("Ada");
        assertEquals(before + 2, aspect.getCalls());
    }

}
