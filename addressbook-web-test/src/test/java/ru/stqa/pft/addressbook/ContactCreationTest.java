package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        gotoContactPage();
        initContactCreation();
        fillContactForm(new ContactData("Ostap", "Bender", "+79998882211", "testTest@mail.ru"));
        submitContactCreation();
        returnHomePage();
    }

}
