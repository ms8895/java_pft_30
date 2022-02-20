package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    //private ApplicationManager app;        //Изменен на createNewGroup()

    public ContactHelper(WebDriver wd/*, ApplicationManager app*/) {        //Изменен на createNewGroup()

        super(wd);
        //this.app = app;       //Изменен на createNewGroup()
    }

    public void returnContactHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactFormIsGroup(ContactData contactData, boolean creation) {
        fillContactForm(contactData);

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
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

    public void submitContactModification() {
        click(By.xpath("//input[22]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void AcceptAlertContact() {
        wd.switchTo().alert().accept();
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
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

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    //Изменен на createNewGroup()

    /*public void createGroupAndContact() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isGroupPresent("Test1")) {
            app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test"));
        }
        app.getNavigationHelper().gotoContactPage();
        ContactData newContact = new ContactData("Ostap", "Bender",
                "221B Baker Street", null, "testTest@mail.ru", "Test1");
        app.getContactHelper().createContact(newContact);
    }*/

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstname = element.getText();
            //String firstname = element.findElement(By.tagName("input")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData(id, firstname, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
