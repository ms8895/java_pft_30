package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        app.сontact().createContactForGroup(new ContactData().withFirstname("Ostap").withLastname("Bender")
                        .withPhoto(new File("./src/test/resources/contact.jpg/"))
                        .withAddress("221B Baker Street").withHomePhone("789").withMobilePhone("78963145")
                        .withWorkPhone("213549873").withEmail("testTest@mail.ru").withEmail2("widh@njv.oo")
                        .withEmail3("klnsv@sni.oo"), new GroupData().withName("Test 2"));
    }

    @Test(enabled = true)
    public void testContactAddGroup() {
        Contacts beforeContact = app.db().contacts();
        Groups beforeGroup = app.db().groups();
        ContactData contact = beforeContact.iterator().next();
        GroupData group = beforeGroup.iterator().next();
        app.сontact().addGroup(contact, group);
        app.goTo().addedGroupPage(group.getId());
        ContactData after = app.db().contacts().stream().filter(item -> item.getFirstname().equals(contact.getFirstname())).findFirst().get();
        assertThat(after.getGroups(), equalTo(new Groups().withAdded(group)));
    }
}

