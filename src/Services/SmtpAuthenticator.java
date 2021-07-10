/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author acer
 */
public class SmtpAuthenticator extends Authenticator {

    public SmtpAuthenticator() {

        super();
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "chernisabrine32@gmail.com";
        String password = "rootR!??";
        if ((username != null) && (username.length() > 0) && (password != null)
                && (password.length() > 0)) {

            return new PasswordAuthentication(username, password);
        }

        return null;
    }
}
