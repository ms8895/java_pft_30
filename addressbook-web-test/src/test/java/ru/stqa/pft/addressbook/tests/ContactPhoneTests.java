package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        app.сontact().createContactIfNotExist(new ContactData().withFirstname("Ostap").withLastname("Bender")
                .withAddress("221B Baker Street").withHomePhone("1 11").withMobilePhone("-222").withWorkPhone("3(33)").withEmail("testTest@mail.ru").withGroup("Test1"));
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.сontact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.сontact().InfoFromEditForm(contact);

        assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
        assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
        assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
    }
        public String cleaned (String phone){
            return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
