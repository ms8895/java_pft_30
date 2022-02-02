package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Ostap", "Bender", "+79998882211", "testTest@mail.ru"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnContactHomePage();
    }

}
