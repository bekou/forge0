package com.dartcorp.formation.model;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

import java.lang.Override;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.dartcorp.formation.model.Produit;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.ManyToMany;

import com.dartcorp.formation.model.Client;

import javax.persistence.OneToMany;

@Entity
@XmlRootElement
public class Command implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private long prixTotal;

   @Column
   private long qte;

   @Column
   @Temporal(TemporalType.DATE)
   private Date dateJour;

   @ManyToMany(fetch=FetchType.EAGER)
   private Set<Produit> produit = new HashSet<Produit>();

   @OneToMany(fetch=FetchType.EAGER)
   private Set<Client> client = new HashSet<Client>();

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Command))
      {
         return false;
      }
      Command other = (Command) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public long getPrixTotal()
   {
      return prixTotal;
   }

   public void setPrixTotal(long prixTotal)
   {
      this.prixTotal = prixTotal;
   }

   public long getQte()
   {
      return qte;
   }

   public void setQte(long qte)
   {
      this.qte = qte;
   }

   public Date getDateJour()
   {
      return dateJour;
   }

   public void setDateJour(Date dateJour)
   {
      this.dateJour = dateJour;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      result += "prixTotal: " + prixTotal;
      result += ", qte: " + qte;
      return result;
   }

   public Set<Produit> getProduit()
   {
      return this.produit;
   }

   public void setProduit(final Set<Produit> produit)
   {
      this.produit = produit;
   }

   public Set<Client> getClient()
   {
      return this.client;
   }

   public void setClient(final Set<Client> client)
   {
      this.client = client;
   }
}