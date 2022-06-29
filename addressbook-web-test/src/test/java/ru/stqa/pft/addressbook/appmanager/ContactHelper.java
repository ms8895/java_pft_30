package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {
    private ApplicationManager app;

    public ContactHelper(WebDriver wd, ApplicationManager app) {
        super(wd);
        this.app = app;
    }

    public void returnContactHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactFormIsGroup(ContactData contactData) {
        fillContactForm(contactData);
        Assert.assertFalse(isElementPresent(By.name("new_group")));

    }

    public void initContactCreation() {
        click(By.name("firstname"));
    }

    public void selectToGroupById(int id) {
        wd.findElement(By.xpath("//select[@name='to_group']/option[@value='" + id + "']")).click();
        //$x("//select[@name='to_group']/option[@value='188']") //Проверка в консоли
    }

    public void selectToGroupByIdWithContact(int id) {
        wd.findElement(By.xpath("//select[@name='group']/option[@value='" + id + "']")).click();
        //$x("//select[@name='to_group']/option[@value='188']") //Проверка в консоли
    }

    public void selectContactForGroupById(int id) {
        wd.findElement(By.xpath("//*[@id='" + id + "']")).click();
    }

    ////*[@id="260"]
    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
        /*<a href="edit.php?id=133"><img src="icons/pencil.png" title="Edit" alt="Edit"></a>*/
    }

    public void initLastContactModification() {// Выбор последнего контакта для редактирования
        List<WebElement> elements = wd.findElements(By.cssSelector("[name='entry']"));
        List<WebElement> cells = elements.get(elements.size() - 1).findElements(By.tagName("td"));
        cells.get(7).click();
    }

    public void submitContactModification() {
        click(By.xpath("//input[22]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void AcceptAlertContact() {
        wd.switchTo().alert().accept();
    }

    public void createContactIfNotExist(ContactData contactData) {
        if (app.db().contacts().size() == 0) {
            app.group().createGroupIfNotExist(new GroupData().withName(contactData.getGroups().iterator().next().getName())
                    .withHeader("Test2").withFooter("Test3"));
            app.goTo().contactPage();
            app.сontact().create(contactData);
        }
    }

    public GroupData createGroup(GroupData groupData) {
        Set<GroupData> result = app.db().groups();
        for (GroupData group : result) {
            if (group.getName().equals(groupData.getName())) {
                return group;
            }
        }
        app.group().create(groupData);
        result = app.db().groups();
        for (GroupData group : result) {
            if (group.getName().equals(groupData.getName())) {
                return group;
            }
        }
        throw new IllegalStateException();
    }

    public ContactData createUniqContact(ContactData contactData) {
        Set<ContactData> result = app.db().contacts();
        for (ContactData contact : result) {
            if (contact.getFirstname().equals(contactData.getFirstname())
                    && contact.getLastname().equals(contactData.getLastname())
                    && contact.getGroups().size() > 0
                    && contact.getGroups().size() == contactData.getGroups().size()
                    && contact.getGroups().iterator().next().getName().equals(contactData.getGroups().iterator().next()
                    .getName())) {
                return contact;
            }
        }
        app.сontact().create(contactData);
        result = app.db().contacts();
        for (ContactData contact : result) {
            if (contact.getFirstname().equals(contactData.getFirstname())
                    && contact.getLastname().equals(contactData.getLastname())
                    && contact.getGroups().size() > 0
                    && contact.getGroups().size() == contactData.getGroups().size()
                    && contact.getGroups().iterator().next().getName().equals(contactData.getGroups().iterator().next()
                    .getName())) {
                return contact;
            }
        }
        throw new IllegalStateException();
    }


    //Создание контакта и группы
    public void createContactForGroup(ContactData contactData, GroupData groupData) {
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.сontact().createContact(contactData);
        }
        if (app.db().groups().size() == 0) {
            app.group().createGroupIfNotExist(groupData);
        }
    }

    //Создание контакта для добавления в группу
    public ContactData createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        contactCache = null;
        returnContactHomePage();
        return contact;
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(contact);
        submitContactCreation();
        contactCache = null;
        returnContactHomePage();
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("phone2"), contactData.getPhone2());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        attach(By.name("photo"), contactData.getPhoto());
    }

    public void selectGroup(ContactData contactData) {
        List<WebElement> elements = wd.findElement(By.name("new_group")).findElements(By.tagName("option"));
        for (WebElement element : elements) {
            String group = element.getText();
            if (group.equals(contactData.getGroups().iterator().next().getName())) {
                element.click();
                return;
            }
        }
    }

    //Для добавления в группу
    public void addGroup(ContactData contact, GroupData group) {
        app.goTo().homePage();
        selectToGroupById(group.getId());
        selectContactForGroupById(contact.getId());
        contactAddGroup();
    }

    public void addGroup(int contact, GroupData group) {
        app.goTo().homePage();
        selectToGroupById(group.getId());
        selectContactForGroupById(contact);
        contactAddGroup();
    }

    public void addGroup(int contact, int group) {
        app.goTo().homePage();
        selectToGroupById(group);
        selectContactForGroupById(contact);
        contactAddGroup();
    }

    public ContactData testContact() {
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() != 0) {
            }
            if (contact.getGroups().size() == 0) {
                return contact;
            }
        }
        return null;
    }

    public void contactAddGroup() {
        click(By.name("add"));
    }

    public void contactRemoveFromGroup() {
        click(By.name("remove"));
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
        fillContactFormIsGroup(contact);
        submitContactModification();
        contactCache = null;
        returnContactHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
        AcceptAlertContact();
        returnContactHomePage();
    }

    public void createGroupAndContact(GroupData group, ContactData contact) {
        app.group().createGroupIfNotExist(group);
        app.goTo().contactPage();
        app.сontact().create(contact);
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int isThereGroup() {
        return wd.findElements(By.name("new_group")).size();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("[name='entry']"));

        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails));
        }
        return new Contacts(contactCache);
    }

    /* Разрезает строку с номерами телефона и эл.почты на три части, если в поле 3 строки*/
    public Contacts allSplit() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("[name='entry']"));

        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String[] phones = cells.get(5).getText().split("\n");
            String[] emails = cells.get(4).getText().split("\n");
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address).withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2])
                    .withEmail(emails[0]).withEmail2(emails[1]).withEmail3(emails[2]));
        }
        return new Contacts(contactCache);
    }

    public ContactData InfoFromEditForm(ContactData contact) {
        newInitContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withPhone2(phone2)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    // Выбор контакта Лекция 5.9. Способы построения сложных локаторов
    // Метод для выбора кнопки редактировать контакт
    public void newInitContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
        /*WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();*/
/*Выбор кнопки редактировать контакта, сложные локаторы
        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
        wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();*/
    }
}
