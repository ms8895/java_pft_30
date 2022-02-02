package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoContactPage();
        app.initContactCreation();
        app.fillContactForm(new ContactData("Ostap", "Bender", "+79998882211", "testTest@mail.ru"));
        app.submitContactCreation();
        app.returnContactHomePage();
    }

}
