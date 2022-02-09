package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Ostap", "Bender", "221B Baker Street"
                ,null, "testTest@mail.ru", "Test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnContactHomePage();
    }

}
