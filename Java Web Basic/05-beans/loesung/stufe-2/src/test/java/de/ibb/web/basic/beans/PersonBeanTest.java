package de.ibb.web.basic.beans;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PersonBeanTest {
    @Test
    void checksEmailAndAge() {
        PersonBean bean = new PersonBean();
        assertFalse(bean.hasValidEmail());
        bean.setEmail("ab.de");
        assertFalse(bean.hasValidEmail());
        bean.setEmail("@b.de");
        assertFalse(bean.hasValidEmail());
        bean.setEmail("a@b.de");
        assertTrue(bean.hasValidEmail());
        bean.setAge(17);
        assertFalse(bean.isAdult());
        bean.setAge(18);
        assertTrue(bean.isAdult());
    }
}
