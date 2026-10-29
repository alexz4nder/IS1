/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aco
 */
@Entity
@Table(name = "KORISNIK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k"),
    @NamedQuery(name = "Korisnik.findByIdUser", query = "SELECT k FROM Korisnik k WHERE k.idUser = :idUser"),
    @NamedQuery(name = "Korisnik.findByUsername", query = "SELECT k FROM Korisnik k WHERE k.username = :username"),
    @NamedQuery(name = "Korisnik.findByPassword", query = "SELECT k FROM Korisnik k WHERE k.password = :password"),
    @NamedQuery(name = "Korisnik.findByName", query = "SELECT k FROM Korisnik k WHERE k.name = :name"),
    @NamedQuery(name = "Korisnik.findBySurname", query = "SELECT k FROM Korisnik k WHERE k.surname = :surname"),
    @NamedQuery(name = "Korisnik.findByAdress", query = "SELECT k FROM Korisnik k WHERE k.adress = :adress"),
    @NamedQuery(name = "Korisnik.findByMoney", query = "SELECT k FROM Korisnik k WHERE k.money = :money"),
    @NamedQuery(name = "Korisnik.findByIsAdmin", query = "SELECT k FROM Korisnik k WHERE k.isAdmin = :isAdmin")})
    @NamedQuery(name = "Korisnik.userLogin", query = "SELECT k FROM Korisnik k WHERE k.username = :username AND k.password = :password")
public class Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdUser")
    private Integer idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Surname")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Adress")
    private String adress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Money")
    private float money;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsAdmin")
    private boolean isAdmin;
    @JoinColumn(name = "IdCity", referencedColumnName = "IdCity")
    @ManyToOne(optional = false)
    private City idCity;

    public Korisnik() {
    }

    public Korisnik(Integer idUser) {
        this.idUser = idUser;
    }

    public Korisnik(Integer idUser, String username, String password, String name, String surname, String adress, float money, boolean isAdmin) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.money = money;
        this.isAdmin = isAdmin;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public City getIdCity() {
        return idCity;
    }

    public void setIdCity(City idCity) {
        this.idCity = idCity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "subsystem1.Korisnik[ idUser=" + idUser + " ]";
    }
    
}
