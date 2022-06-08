package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveGroupTest extends TestBase {

    @Test(enabled = true)
    public void testRemovingContactFromGroup() {
        app.goTo().groupPage();
        GroupData group = app.сontact().createGroup(new GroupData().withName("test1").withHeader("Test2").withFooter("Test3"));
        app.goTo().contactPage();
        ContactData contact = app.сontact().createUniqContact(new ContactData().withFirstname("Ostap").withLastname("Bender")
                .withPhoto(new File("./src/test/resources/contact.jpg/"))
                .withAddress("221B Baker Street").inGroup(new GroupData().withName("test1")));
        app.goTo().homePage();
        app.сontact().selectToGroupByIdWithContact(group.getId());
        app.сontact().selectContactForGroupById(contact.getId());
        app.сontact().contactRemoveFromGroup();
        ContactData after = app.db().contacts().stream().filter(item -> item.getFirstname().equals(contact.getFirstname())).findFirst().get();
        assertThat(after.getGroups(), equalTo(new Groups()));
    }
}
