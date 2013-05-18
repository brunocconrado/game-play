package br.com.gp.inventory.domain.repository.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import br.com.embracon.j4e.domain.DomainModel;
import br.com.embracon.j4e.domain.Entity;
import br.com.embracon.j4e.domain.repository.ListCriteria;
import br.com.embracon.j4e.domain.repository.PersistenceType;
import br.com.embracon.j4e.domain.repository.Repository;
import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.domain.storage.Query;
import br.com.gp.inventory.domain.repository.DAOService;

@SuppressWarnings({"unchecked","restriction", "hiding"})
class AbstractHibernateRepostirory<E extends Entity> implements Repository<E>, DAOService<E, Long> {

	
	private static final long serialVersionUID = -9059667392213334746L;

	private String defaultIdName;

	private Class<E> clazz;

	@Autowired
	private SessionFactory sessionFactory;
	
	protected AbstractHibernateRepostirory(Class<E> clazz) {
		this(clazz, "id");
	}
	
	protected AbstractHibernateRepostirory(Class<E> clazz, String idName) {
		this.clazz = clazz;
		this.defaultIdName = idName;
	}

	protected Criteria createCriteria() {
		return createCriteria(clazz.getSimpleName());
	}
	
	protected Criteria createCriteria(String alias) {
		return getSession().createCriteria(clazz, alias);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	protected synchronized <E extends Entity> E executeCommand(E obj, PersistenceType type) {

		if (type == null) {
			throw new IllegalAccessError("The PersistenceType can not null");
		}

		if (obj == null) {
			throw new IllegalAccessError("The Persistence Object can not null");
		}		

		try {		

			Session session = getSession();

			switch (type) {
				case INCLUDE:
					try {
						session.save(obj);		
					} catch (HibernateException e) {
						session.merge(obj);
					}	
	
					break;
				case UPDATE:
					try {
						session.saveOrUpdate(obj); 		
					} catch (NonUniqueObjectException e) {
						session.merge(obj);
					} catch (HibernateException e) {
						session.merge(obj);
					}
	
					break;
				case DELETE:
					session.delete(obj);
					break;
				default:
					throw new IllegalAccessError("Unkwnow type for: "+ type);
			}

			return obj;
		} catch (Exception e) {	
			throw new RepositoryException(e);
		} 
	}	

	public Collection<E> save(Collection<E> instances) {
		for(E instance : instances) {
			this.save(instance);
		}		
		return instances;
	}


	public E save(E instance) {
		PersistenceType type = null;
		if(instance.getId() == null) {
			type = PersistenceType.INCLUDE;
		} else {
			type = PersistenceType.UPDATE;
		}

		return this.executeCommand(instance, type);
	}

	public void delete(Collection<E> instances) {
		for(E instance : instances) {
			this.delete(instance);
		}				
	}

	public void delete(E instance) {
		this.executeCommand(instance, PersistenceType.DELETE);		
	}	

	@Transactional(propagation = Propagation.REQUIRED)
	public Collection<E> findAll() {	
		return createCriteria().list();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public E findByIdentity(Long id) {
		return (E) createCriteria()
					.add(Restrictions.eq(defaultIdName, id)).uniqueResult();
	}

	
	public E findBy(Criteria criteria) {
		try {
			return (E) criteria.uniqueResult();
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
		
	}
	
	public List<E> listBy(Criteria criteria) {
		try {
			return (List<E>) criteria.list();
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
		
	}
	
	public E findBy(Criterion restrictions) {
		try {
			return (E) createCriteria().add(restrictions).uniqueResult();
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
		
	}
	
	public List<E> listBy(Criterion criterion) {
		try {
			return (List<E>) createCriteria().add(criterion).list();
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
		
	}
	
	public E findBy(org.hibernate.Query query) {
		try {
			return (E) query.uniqueResult();
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
		
	}
	
	public List<E> listBy(org.hibernate.Query query) {
		try {
			return (List<E>) query.list();
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
		
	}
	
	public void setDomainModel(DomainModel domainModel){}

	public synchronized Query<E> findBySearch(Object search) {
		throw new NotImplementedException();		
	}

	public synchronized Query<E> findBySearch(E search) {
		throw new NotImplementedException();		
	}	

	public synchronized ListCriteria<E> findBy(Object search) {
		throw new NotImplementedException();		
	}

	public synchronized ListCriteria<E> findBy(E search) {
		throw new NotImplementedException();		
	}


}
