package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader readerContact = new BufferedReader(new FileReader(new File("src\\test\\resources\\contacts.csv")));
        String lineContact = readerContact.readLine();
        while (lineContact != null) {
            String[] splitContact = lineContact.split(";");
            list.add(new Object[]{new ContactData().withFirstname(splitContact[0]).withLastname(splitContact[1])
                    .withAddress(splitContact[2]).withGroup(splitContact[3]).withPhoto(new File(splitContact[4]))});
            lineContact = readerContact.readLine();
        }
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) throws Exception {
        app.goTo().homePage();
        Contacts before = app.сontact().all();
        GroupData group = new GroupData().withName("Test1");
        app.сontact().createGroupAndContact(group, contact);
        assertThat(app.сontact().count(), equalTo(before.size() + 1));
        Contacts after = app.сontact().all();
        assertThat(after, equalTo(before.withAdded(contact
                .withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}

