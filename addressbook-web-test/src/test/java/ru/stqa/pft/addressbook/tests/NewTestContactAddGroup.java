package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

public class NewTestContactAddGroup extends TestBase {
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
    public void testNewTestContactAddGroup() {
        /*через UI, надо через БД, id контакта после создания 2147483647, на UI такого нет, поэтому тест не проходит
        app.goTo().groupPage();
        GroupData group = app.сontact().createGroup(new GroupData().withName("test1").withHeader("Test2").withFooter("Test3"));
        app.goTo().contactPage();
        ContactData contact = app.сontact().createContact((new ContactData().withFirstname("Ostap").withLastname("Bender")
                .withPhoto(new File("./src/test/resources/contact.jpg/"))
                .withAddress("221B Baker Street").withHomePhone("789").withMobilePhone("78963145")
                .withWorkPhone("213549873").withEmail("testTest@mail.ru").withEmail2("widh@njv.oo")
                .withEmail3("klnsv@sni.oo")));
        app.goTo().homePage();
        app.сontact().addGroup(contact, group);
        app.goTo().addedGroupPage(group.getId());
        ContactData after = app.db().contacts().stream().filter(item -> item.getFirstname().equals(contact.getFirstname())).findFirst().get();
        assertThat(after.getGroups(), equalTo(new Groups()));*/

        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        int contact = testContact();
        System.out.println(contact + " id контакта без группы");
        app.сontact().addGroup(contact, group);
        app.goTo().addedGroupPage(group.getId());
    }

    public int testContact() {
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() != 0) {
            }
            if (contact.getGroups().size() == 0) {
                //System.out.println("КОНТАКТ 2 " + contact + " ВТОРОГО УСЛОВИЯ, НЕТ ГРУППЫ!!!");
                return contact.getId();
            /*} else {
                app.goTo().contactPage();
                app.сontact().createContact((new ContactData().withFirstname("Ostap").withLastname("Bender")
                        .withPhoto(new File("./src/test/resources/contact.jpg/"))
                        .withAddress("221B Baker Street").withHomePhone("789").withMobilePhone("78963145")
                        .withWorkPhone("213549873").withEmail("testTest@mail.ru").withEmail2("widh@njv.oo")
                        .withEmail3("klnsv@sni.oo")));
                System.out.println("КОНТАКТ 3 " + contact + " ТРЕТЬЕГО УСЛОВИЯ");
                return contact.getId();
            }*/
            }
        }return 0;
    }
}
