package de.ibb.web.basic.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PersonBeanTest {
    @Test
    void joinsPresentNames() {
        PersonBean bean = new PersonBean();
        bean.setFirstName("Ada");
        bean.setLastName("Lovelace");
        assertEquals("Ada Lovelace", bean.getDisplayName());
        bean.setLastName(" ");
        assertEquals("Ada", bean.getDisplayName());
        bean.setFirstName(null);
        bean.setLastName("Lovelace");
        assertEquals("Lovelace", bean.getDisplayName());
    }
}
