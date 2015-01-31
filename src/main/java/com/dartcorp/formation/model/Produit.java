package com.dartcorp.formation.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Produit implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String name;

   @Column
   private long prix;

   @Column
   private long qte;

   @Column
   private String reference;

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
      if (!(obj instanceof Produit))
      {
         return false;
      }
      Produit other = (Produit) obj;
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

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public long getPrix()
   {
      return prix;
   }

   public void setPrix(long prix)
   {
      this.prix = prix;
   }

   public long getQte()
   {
      return qte;
   }

   public void setQte(long qte)
   {
      this.qte = qte;
   }

   public String getReference()
   {
      return reference;
   }

   public void setReference(String reference)
   {
      this.reference = reference;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (name != null && !name.trim().isEmpty())
         result += "name: " + name;
      result += ", prix: " + prix;
      result += ", qte: " + qte;
      if (reference != null && !reference.trim().isEmpty())
         result += ", reference: " + reference;
      return result;
   }
}