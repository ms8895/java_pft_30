package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groupPage();
        app.сontact().createGroup(new GroupData().withName("test1").withHeader("Test2").withFooter("Test3"));
        app.goTo().contactPage();
        app.сontact().createContact((new ContactData().withFirstname("Ostap").withLastname("Bender")
                .withPhoto(new File("./src/test/resources/contact.jpg/"))
                .withAddress("221B Baker Street").withHomePhone("789").withMobilePhone("78963145")
                .withWorkPhone("213549873").withEmail("testTest@mail.ru").withEmail2("widh@njv.oo")
                .withEmail3("klnsv@sni.oo")));
    }

    @Test(enabled = true)
    public void testContactAddGroup() {
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        ContactData contact = app.сontact().testContact();
        System.out.println(contact + " id контакта без группы");
        app.сontact().addGroup(contact, group);
        app.goTo().addedGroupPage(group.getId());
        ContactData after = app.db().contacts().stream().filter(item -> item.getFirstname().equals(contact.getFirstname())).findFirst().get();
        assertThat(after.getGroups(), equalTo(new Groups().withAdded(group)));
    }
}

