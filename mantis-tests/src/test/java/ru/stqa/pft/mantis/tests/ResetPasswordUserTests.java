package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordUserTests extends TestBase {
    @BeforeMethod
    public void startMailSever() {
        app.mail().start();
    }

    @Test
    public void testResetPasswordUser() throws MessagingException, IOException {
        app.registration().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        Users users = app.db().users();
        System.out.println("111" + users + "111");
        UserData selectedUser = users.iterator().next();
        String username = selectedUser.getUserName();
        String email = selectedUser.getEmail();
        app.registration().resetPasswordUser(username);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        String newPasswordForUser = "root";
        app.registration().finish(confirmationLink, newPasswordForUser);
        assertTrue(app.newSession().login(username, newPasswordForUser));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMail() {
        app.mail().stop();
    }
}