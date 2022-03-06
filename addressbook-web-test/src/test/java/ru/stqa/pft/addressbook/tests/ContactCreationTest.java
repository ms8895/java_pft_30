package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new ContactData().withFirstname("Ostap 1").withLastname("Bender 1")
                .withAddress("221B Baker Street 1").withPhoto(new File(".\\src\\test\\resources\\bu.jpg"))
                .withGroup(new GroupData().withName("Test 1").getName()),
                new GroupData().withName("Test 1").withHeader("header 1").withFooter("footer 1")});
        list.add(new Object[]{new ContactData().withFirstname("Ostap 2").withLastname("Bender 2")
                .withAddress("221B Baker Street 2").withPhoto(new File(".\\src\\test\\resources\\bu.jpg"))
                .withGroup(new GroupData().withName("Test 2").getName()),
                new GroupData().withName("Test 2").withHeader("header 2").withFooter("footer 2")});
        list.add(new Object[]{new ContactData().withFirstname("Ostap 3").withLastname("Bender 3")
                .withAddress("221B Baker Street 3").withPhoto(new File(".\\src\\test\\resources\\bu.jpg"))
                .withGroup(new GroupData().withName("Test 3").getName()),
                new GroupData().withName("Test 3").withHeader("header 3").withFooter("footer 3")});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact, GroupData group) throws Exception {
        app.goTo().homePage();
        Contacts before = app.сontact().all();
        app.сontact().createGroupAndContact(group, contact);
        assertThat(app.сontact().count(), equalTo(before.size() + 1));
        Contacts after = app.сontact().all();
        assertThat(after, equalTo(before.withAdded(contact
                .withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}

