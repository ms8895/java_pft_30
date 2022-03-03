package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        app.сontact().createContactIfNotExist(new ContactData().withFirstname("Ostap").withLastname("Bender")
                /*.withAddress("221B Baker Street").withHomePhone("1 11").withMobilePhone("-222").withWorkPhone("3(33)")*/
                .withEmail("testTest@mail.ru").withEmail2("dfg@mail.ru").withEmail3("57f@test.com").withGroup("Test1"));
    }

    @Test
    public void testContactEmails() {
        app.goTo().homePage();
        ContactData contact = app.сontact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.сontact().InfoFromEditForm(contact);


        assertThat(contact.getEmail(), equalTo(cleaned(contactInfoFromEditForm.getEmail())));
        assertThat(contact.getEmail2(), equalTo(cleaned(contactInfoFromEditForm.getEmail2())));
        assertThat(contact.getEmail3(), equalTo(cleaned(contactInfoFromEditForm.getEmail3())));
    }
    public String cleaned(String  email){
        return email.replaceAll("\\s","").replaceAll("[-@,.+]","");
    }
}

