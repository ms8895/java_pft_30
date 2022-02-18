package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().createGroupAndContact();
    }
}
