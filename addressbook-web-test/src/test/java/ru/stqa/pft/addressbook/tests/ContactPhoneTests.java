package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        app.сontact().createContactIfNotExist(new ContactData().withFirstname("Ostap").withLastname("Bender")
                .withAddress("221B Baker Street").withHomePhone("+7896").withMobilePhone("+38569").withWorkPhone("+1654").withEmail("testTest@mail.ru").withGroup("Test1"));
    }
    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.сontact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.сontact().InfoFromEditForm(contact);

    }
}
