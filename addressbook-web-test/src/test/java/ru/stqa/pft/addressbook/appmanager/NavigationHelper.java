package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void contactPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
                && isElementPresent(By.name("submit"))) {
            return;
        }
        click(By.linkText("add new"));
    }

    public void homePage() {
        if (isElementPresent(By.name("MainForm"))) {
            return;
        }
        click(By.linkText("home"));
    }
    
    //переход в группу, в которую добавили контакт
    //#content > div > i > a //*/div/i/a
   // wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    // "a[href='./?group=" + id + "']"
    public void addedGroupPage(int id) {
        if (isElementPresent(By.tagName("i"))
                && wd.findElement(By.tagName("i")).getText().equals("Go to")) {
            return;
        }
        click(By.cssSelector("a[href='./?group=" + id + "']"));
    }
}
