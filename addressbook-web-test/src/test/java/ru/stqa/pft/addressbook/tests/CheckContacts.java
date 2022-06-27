package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

public class CheckContacts extends TestBase {
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
    public void testCheckContacts() {
        /*System.out.println("1111 " + app.db().contacts().size() + "2222");
        Contacts contacts = app.db().contacts();
        if (app.db().contacts().iterator().next().getGroups().size() == 0) {
            //app.db().contacts().iterator().next().getId();
            int contact = app.db().contacts().iterator().next().getId();
            System.out.println("айди " + contact + " контакта");
            int group = app.db().groups().iterator().next().getId();
            app.сontact().addGroup(contact, group);
            *//*app.goTo().addedGroupPage(group.getId());*//*
            //System.out.println("Контакт без группы");
        } else {
            System.out.println("HELLO");
        }

        */
        // System.out.println("1111 " + app.db().contacts().iterator().next().getGroups().size() + "4444");
        /*public void selectGroup(ContactData contactData) {
            List<WebElement> elements = wd.findElement(By.name("new_group")).findElements(By.tagName("option"));
            for (WebElement element : elements) {
                String group = element.getText();
                if (group.equals(contactData.getGroups().iterator().next().getName())) {
                    element.click();
                    return;
                }
            }
        }*/
        //System.out.println("КОНТАКТ" + testGroup() + "КОНТАКТ");
        //System.out.println("КОНТАКТ ID" + testContact() + " ID КОНТАКТ");
        //System.out.println("КОНТАКТ ID " + app.db().contacts().iterator() + " ID КОНТАКТ");
        //System.out.println("КОНТАКТ ID " + app.db().contacts().iterator().next().getGroups().equals(true) + " ID КОНТАКТ");
        ContactData beforeContact = contactForGroup();
        Groups beforeGroup = app.db().groups();
        int contactId = beforeContact.getId();
        int group = beforeGroup.iterator().next().getId();
        app.сontact().addGroup(contactId, group);
        app.goTo().addedGroupPage(group);
    }
////остановился тут
    public ContactData contactForGroup() {
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            //System.out.println("ИЗ ЦИКЛА "+ contact.getGroups().isEmpty() +"КОНТАКТ");
            if (!contact.getGroups().isEmpty()) {
                //return contact;
            } /*else {
                app.goTo().contactPage();
                app.сontact().createContact((new ContactData().withFirstname("Ostap").withLastname("Bender")
                        .withPhoto(new File("./src/test/resources/contact.jpg/"))
                        .withAddress("221B Baker Street").withHomePhone("789").withMobilePhone("78963145")
                        .withWorkPhone("213549873").withEmail("testTest@mail.ru").withEmail2("widh@njv.oo")
                        .withEmail3("klnsv@sni.oo")));
                if (contact.getGroups().isEmpty()) {
                    return contact;
                }
            }*/
        }
        app.goTo().contactPage();
        app.сontact().createContact((new ContactData().withFirstname("Ostap").withLastname("Bender")
                .withPhoto(new File("./src/test/resources/contact.jpg/"))
                .withAddress("221B Baker Street").withHomePhone("789").withMobilePhone("78963145")
                .withWorkPhone("213549873").withEmail("testTest@mail.ru").withEmail2("widh@njv.oo")
                .withEmail3("klnsv@sni.oo")));
        System.out.println("ВОТ ЭТО ВОТ ШТО " + new ContactData() + " ШТО ЭТО");

        return new ContactData();//.getGroups().isEmpty();
    }

    public int testContact() {
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() != 0) {
            }
            if (contact.getGroups().size() == 0) {
                System.out.println("КОНТАКТ 2" + contact + " ВТОРОГО УСЛОВИЯ");
                return contact.getId();
            } else {
                app.goTo().contactPage();
                app.сontact().createContact((new ContactData().withFirstname("Ostap").withLastname("Bender")
                        .withPhoto(new File("./src/test/resources/contact.jpg/"))
                        .withAddress("221B Baker Street").withHomePhone("789").withMobilePhone("78963145")
                        .withWorkPhone("213549873").withEmail("testTest@mail.ru").withEmail2("widh@njv.oo")
                        .withEmail3("klnsv@sni.oo")));
                System.out.println("КОНТАКТ 3" + contact + " ТРЕТЬЕГО УСЛОВИЯ");
                return contact.getId();
            }
        }
        return app.db().contacts().iterator().next().getId();
    }///Отсюда

    /*public GroupData selectedGroup(ContactData contact) {
        Groups all = app.db().groups();
        Collection<GroupData> freeGroups = new HashSet<GroupData>(all);
        freeGroups.removeAll(contact.getGroups());
        return freeGroups.iterator().next();
    }

    public ContactData selectedContact() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() < groups.size()) {
                return contact;
            }
        }
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test1"));
        app.goTo().homePage();
        return contacts.iterator().next();
    }*/

   /* public GroupData selectedGroup(ContactData contact) {
        Groups all = app.db().groups();
        Collection<GroupData> freeGroups = new HashSet(all);
        freeGroups.removeAll(contact.getGroups());
        return (GroupData)freeGroups.iterator().next();
    }

    public ContactData selectedContact() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        Iterator var3 = contacts.iterator();

        ContactData contact;
        do {
            if (!var3.hasNext()) {
                app.goTo().groupPage();
                app.group().create((new GroupData()).withName("TestName1").withHeader("TestHeader1").withFooter("TestFooter1"));
                return (ContactData)contacts.iterator().next();
            }

            contact = (ContactData)var3.next();
        } while(contact.getGroups().size() >= groups.size());

        return contact;
    }*/


}

