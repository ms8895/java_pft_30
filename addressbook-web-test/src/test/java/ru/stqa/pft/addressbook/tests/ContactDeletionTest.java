package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        app.сontact().createContactIfNotExist(new ContactData().withFirstname("Ostap").withLastname("Bender")
                .withPhoto(new File("./src/test/resources/contact.jpg/"))
                .withAddress("221B Baker Street").withHomePhone("789").withMobilePhone("78963145").withWorkPhone("213549873")
               .withEmail("testTest@mail.ru").withEmail2("widh@njv.oo").withEmail3("klnsv@sni.oo")
                .inGroup(new GroupData().withName("Test 1")));
    }

    @Test(enabled = true)
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.сontact().delete(deletedContact);
        app.goTo().homePage();
        assertThat(app.сontact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListInUi();
    }
}