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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aco
 */
@Entity
@Table(name = "SUBORDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suborder.findAll", query = "SELECT s FROM Suborder s"),
    @NamedQuery(name = "Suborder.findByIdSubOrder", query = "SELECT s FROM Suborder s WHERE s.idSubOrder = :idSubOrder"),
    @NamedQuery(name = "Suborder.findByIdItem", query = "SELECT s FROM Suborder s WHERE s.idItem = :idItem"),
    @NamedQuery(name = "Suborder.findByUnitPrice", query = "SELECT s FROM Suborder s WHERE s.unitPrice = :unitPrice"),
    @NamedQuery(name = "Suborder.findByAmount", query = "SELECT s FROM Suborder s WHERE s.amount = :amount")})
public class Suborder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdSubOrder")
    private Integer idSubOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdItem")
    private int idItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unitPrice")
    private float unitPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;
    @JoinColumn(name = "IdOrder", referencedColumnName = "IdOrder")
    @ManyToOne(optional = false)
    private Order idOrder;

    public Suborder() {
    }

    public Suborder(Integer idSubOrder) {
        this.idSubOrder = idSubOrder;
    }

    public Suborder(Integer idSubOrder, int idItem, int unitPrice, int amount) {
        this.idSubOrder = idSubOrder;
        this.idItem = idItem;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    public Integer getIdSubOrder() {
        return idSubOrder;
    }

    public void setIdSubOrder(Integer idSubOrder) {
        this.idSubOrder = idSubOrder;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Order getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Order idOrder) {
        this.idOrder = idOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubOrder != null ? idSubOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suborder)) {
            return false;
        }
        Suborder other = (Suborder) object;
        if ((this.idSubOrder == null && other.idSubOrder != null) || (this.idSubOrder != null && !this.idSubOrder.equals(other.idSubOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Suborder[ idSubOrder=" + idSubOrder + " ]";
    }
    
}
