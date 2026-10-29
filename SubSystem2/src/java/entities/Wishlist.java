/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aco
 */
@Entity
@Table(name = "WISHLIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wishlist.findAll", query = "SELECT w FROM Wishlist w"),
    @NamedQuery(name = "Wishlist.findByIdWish", query = "SELECT w FROM Wishlist w WHERE w.idWish = :idWish"),
    @NamedQuery(name = "Wishlist.findByIdUser", query = "SELECT w FROM Wishlist w WHERE w.idUser = :idUser"),
    @NamedQuery(name = "Wishlist.findByTime", query = "SELECT w FROM Wishlist w WHERE w.time = :time"),
    @NamedQuery(name = "Wishlist.findByIdUserAndIdItem",query = "SELECT w FROM Wishlist w WHERE w.idUser = :idUser AND w.idItem =:idItem")})
public class Wishlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdWish")
    private Integer idWish;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdUser")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "IdItem", referencedColumnName = "IdItem")
    @ManyToOne(optional = false)
    private Item idItem;

    public Wishlist() {
    }

    public Wishlist(Integer idWish) {
        this.idWish = idWish;
    }

    public Wishlist(Integer idWish, int idUser, Date time) {
        this.idWish = idWish;
        this.idUser = idUser;
        this.time = time;
    }

    public Integer getIdWish() {
        return idWish;
    }

    public void setIdWish(Integer idWish) {
        this.idWish = idWish;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Item getIdItem() {
        return idItem;
    }

    public void setIdItem(Item idItem) {
        this.idItem = idItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWish != null ? idWish.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wishlist)) {
            return false;
        }
        Wishlist other = (Wishlist) object;
        if ((this.idWish == null && other.idWish != null) || (this.idWish != null && !this.idWish.equals(other.idWish))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Wishlist[ idWish=" + idWish + " ]";
    }
    
}
