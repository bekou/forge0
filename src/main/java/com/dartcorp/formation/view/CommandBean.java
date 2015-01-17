package com.dartcorp.formation.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dartcorp.formation.model.Command;

/**
 * Backing bean for Command entities.
 * <p/>
 * This class provides CRUD functionality for all Command entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class CommandBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Command entities
    */

   private Long id;

   public Long getId()
   {
      return this.id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   private Command command;

   public Command getCommand()
   {
      return this.command;
   }

   public void setCommand(Command command)
   {
      this.command = command;
   }

   @Inject
   private Conversation conversation;

   @PersistenceContext(unitName = "pharma-persistence-unit", type = PersistenceContextType.EXTENDED)
   private EntityManager entityManager;

   public String create()
   {

      this.conversation.begin();
      this.conversation.setTimeout(1800000L);
      return "create?faces-redirect=true";
   }

   public void retrieve()
   {

      if (FacesContext.getCurrentInstance().isPostback())
      {
         return;
      }

      if (this.conversation.isTransient())
      {
         this.conversation.begin();
         this.conversation.setTimeout(1800000L);
      }

      if (this.id == null)
      {
         this.command = this.example;
      }
      else
      {
         this.command = findById(getId());
      }
   }

   public Command findById(Long id)
   {

      return this.entityManager.find(Command.class, id);
   }

   /*
    * Support updating and deleting Command entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.command);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.command);
            return "view?faces-redirect=true&id=" + this.command.getId();
         }
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   public String delete()
   {
      this.conversation.end();

      try
      {
         Command deletableEntity = findById(getId());

         this.entityManager.remove(deletableEntity);
         this.entityManager.flush();
         return "search?faces-redirect=true";
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   /*
    * Support searching Command entities with pagination
    */

   private int page;
   private long count;
   private List<Command> pageItems;

   private Command example = new Command();

   public int getPage()
   {
      return this.page;
   }

   public void setPage(int page)
   {
      this.page = page;
   }

   public int getPageSize()
   {
      return 10;
   }

   public Command getExample()
   {
      return this.example;
   }

   public void setExample(Command example)
   {
      this.example = example;
   }

   public String search()
   {
      this.page = 0;
      return null;
   }

   public void paginate()
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

      // Populate this.count

      CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
      Root<Command> root = countCriteria.from(Command.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Command> criteria = builder.createQuery(Command.class);
      root = criteria.from(Command.class);
      TypedQuery<Command> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Command> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      long prixTotal = this.example.getPrixTotal();
      if (prixTotal != 0)
      {
         predicatesList.add(builder.equal(root.get("prixTotal"), prixTotal));
      }
      long qte = this.example.getQte();
      if (qte != 0)
      {
         predicatesList.add(builder.equal(root.get("qte"), qte));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Command> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Command entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Command> getAll()
   {

      CriteriaQuery<Command> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(Command.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(Command.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final CommandBean ejbProxy = this.sessionContext.getBusinessObject(CommandBean.class);

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context,
               UIComponent component, String value)
         {

            return ejbProxy.findById(Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context,
               UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((Command) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Command add = new Command();

   public Command getAdd()
   {
      return this.add;
   }

   public Command getAdded()
   {
      Command added = this.add;
      this.add = new Command();
      return added;
   }
}
