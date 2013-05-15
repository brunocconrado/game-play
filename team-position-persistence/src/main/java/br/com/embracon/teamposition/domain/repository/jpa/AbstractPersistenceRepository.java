package br.com.embracon.teamposition.domain.repository.jpa;

import java.util.Collection;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import br.com.embracon.j4e.domain.DomainModel;
import br.com.embracon.j4e.domain.Entity;
import br.com.embracon.j4e.domain.repository.ListCriteria;
import br.com.embracon.j4e.domain.repository.PersistenceType;
import br.com.embracon.j4e.domain.repository.Repository;
import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.domain.storage.Query;
import br.com.embracon.teamposition.domain.repository.DAOService;

@Local
@SuppressWarnings("restriction")
public abstract class AbstractPersistenceRepository <E extends Entity> implements Repository<E>, DAOService<E, Long> {


	
	private static final long serialVersionUID = 169409239685338232L;

	private String defaultIdName;

	private Class<E> clazz;

	@PersistenceContext(unitName = "team-positon")
	protected EntityManager entityManager;

	protected AbstractPersistenceRepository(Class<E> clazz) {
		this.clazz = clazz;
		this.defaultIdName = "id";
	}

	protected CriteriaBuilder getCriteriaBuilder() {
		return this.entityManager.getCriteriaBuilder();
	}
	
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	protected synchronized E executeCommand(E obj, PersistenceType type) {

		if (type == null) {
			throw new IllegalAccessError("The PersistenceType can not null");
		}

		if (obj == null) {
			throw new IllegalAccessError("The Persistence Object can not null");
		}		

		try {		

			switch (type) {
			case INCLUDE:
				try {
					entityManager.persist(obj);		
				} catch (Exception e) {
					entityManager.merge(obj);
				}	

				break;
			case UPDATE:
				entityManager.merge(entityManager.getReference(obj.getClass(), obj)); 		
				break;
			case DELETE:
				entityManager.remove(obj);
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


	public Collection<E> findAll() {	
		 return entityManager.createQuery(
				 getCriteriaBuilder().createQuery(clazz)).getResultList();
	}

	public E findByIdentity(Long id) {
		try {
			CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
			CriteriaQuery<E> criteriaQuery =  criteriaBuilder.createQuery(clazz);
			Root<E> root =  criteriaQuery.from(clazz);

			criteriaQuery.where(criteriaBuilder.equal(
					root.get(defaultIdName), 
					criteriaBuilder.parameter(Long.class, defaultIdName)));

			TypedQuery<E> q = entityManager.createQuery(criteriaQuery);
			q.setParameter(defaultIdName, id);

			return q.getSingleResult();
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
