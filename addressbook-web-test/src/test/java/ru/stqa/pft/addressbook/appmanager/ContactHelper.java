package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void initLastContactModification() {
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
        if (app.сontact().list().size() == 0) {
            app.group().createGroupIfNotExist(new GroupData().withName(contactData.getGroup()).withHeader("Test2").withFooter("Test"));
            app.goTo().contactPage();
            app.сontact().create(contactData);
        }
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(contact);
        submitContactCreation();
        returnContactHomePage();
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

    }

    public void selectGroup(ContactData contactData) {
        List<WebElement> elements = wd.findElement(By.name("new_group")).findElements(By.tagName("option"));
        for (WebElement element : elements) {
            String group = element.getText();
            if (group.equals(contactData.getGroup())) {
                element.click();
                return;
            }
        }
    }

    public void modify(int index, ContactData contact) {
        selectContact(index);
        initLastContactModification();
        fillContactFormIsGroup(contact);
        submitContactModification();
        returnContactHomePage();
    }

    public void delete(int index) {
        selectContact(index);
        deleteSelectedContact();
        AcceptAlertContact();
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

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("[name='entry']"));

        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String mobile = cells.get(5).getText();
            String email = cells.get(4).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address).withMobile(mobile).withEmail(email));
        }
        return contacts;
    }
    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("[name='entry']"));

        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String mobile = cells.get(5).getText();
            String email = cells.get(4).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address).withMobile(mobile).withEmail(email));
        }
        return contacts;
    }
}
