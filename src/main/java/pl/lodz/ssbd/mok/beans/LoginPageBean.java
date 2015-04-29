/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.ssbd.mok.beans;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Robert Mielczarek <180640@edu.p.lodz.pl>
 */
@Named(value = "loginPageBean")
@RequestScoped
public class LoginPageBean {

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
            
    private String password;
    
    private String IP;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getIP() {
        return IP;
    }
    
    @Inject
    private UzytkownikSession uzytkownikSession;
    
    
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public LoginPageBean() {
    }
    
    public String zaloguj(){
        try {
            getRequest().login(username, password);
            uzytkownikSession.zalogujPoprawneUwierzytelnienie(username, password, IP);
            return "zalogowano";
        } catch (ServletException ex) {
            uzytkownikSession.zalogujNiepoprawneUwierzytenienie(username, password, IP);
            Logger.getLogger(LoginPageBean.class.getName()).log(Level.SEVERE, null, ex);
            return "porazka";
        }
    }
    
     private javax.servlet.http.HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
     }
    
}
