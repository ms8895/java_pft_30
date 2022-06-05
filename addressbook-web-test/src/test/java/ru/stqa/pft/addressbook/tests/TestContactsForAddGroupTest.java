// Класс для 16 дз, для добавления в группу
/*
Выбрать контакт >
Проверить есть у него группа >
Если есть ничего проверить другой контакт>
Если нет группы, выбрать группу и добавить в группу

 */
package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

public class TestContactsForAddGroupTest extends TestBase {
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
    public void TestContactsForAddGroup() {
        System.out.println(checkContacts());
    }

    public Contacts checkContacts() {
        Contacts contacts = app.db().contacts();
        return contacts;
    }
}
