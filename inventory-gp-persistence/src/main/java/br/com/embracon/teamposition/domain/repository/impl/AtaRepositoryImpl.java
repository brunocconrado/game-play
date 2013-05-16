package br.com.embracon.teamposition.domain.repository.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.util.ConvertUtils;
import br.com.embracon.teamposition.domain.entity.Ata;
import br.com.embracon.teamposition.domain.entity.AtaExcecao;
import br.com.embracon.teamposition.domain.repository.AtaRepository;
import br.com.embracon.teamposition.domain.search.AtaSearch;

@Repository("ataRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class AtaRepositoryImpl  extends AbstractHibernateRepostirory<Ata> implements AtaRepository {

	private static final long serialVersionUID = -8653068498128143741L;

	public AtaRepositoryImpl() {
		super(Ata.class);
	}

	public List<Ata> searchBy(AtaSearch search) {

		Criteria criteria = createCriteria().add(
				Restrictions.like("name", search.getName(), MatchMode.ANYWHERE));
		if(search.getStart() != null && search.getEnd() != null) {
			criteria.add(Restrictions.or(Restrictions
					.between("start", search.getStart(), search.getEnd()),
					Restrictions.between("end", search.getStart(), search.getEnd())));
		} else if(search.getStart() != null) {
			criteria.add(Restrictions.ge("start", search.getStart()));
		} else if(search.getEnd() != null) {
			criteria.add(Restrictions.le("end", search.getEnd()));
		}

		return listBy(criteria);
	}

	@Override
	public boolean existDateInteval(Long id, Date start, Date end, String fieldStart, String fieldEnd) {

		try {
			Criteria criteria = createCriteria().add(Restrictions.or(
					Restrictions.between(fieldStart, start, end), 
					Restrictions.between(fieldEnd, start, end)));
			
			if(id != null) {
				criteria.add(Restrictions.ne("id", id));
			}
					
			criteria.setProjection(Projections.count("id"));

			return (Long)criteria.uniqueResult() > 0;
		} catch (Exception e) {
			throw new RepositoryException(e);
		}

	}

	@Override
	public boolean hasTeamSeller(Ata ata) {

		try {

			Criteria criteria = createCriteria()
					.createCriteria("teamSellers", "teamSeller", JoinType.INNER_JOIN);

			criteria.add(Restrictions.eq("ata", ata))
			.setProjection(Projections.count("id"));

			return (Long)criteria.uniqueResult() > 0;
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public Ata findByDate(Date date) {
		return findBy(Restrictions.and(Restrictions
				.ge("start", date), Restrictions.le("end", date)));
	}

	@Override
	public Ata findByRegistryDate(Date date) {

		String dateString = ConvertUtils.convert(String.class, date, ConvertUtils.DATE);
		
		StringBuilder sql = new StringBuilder()
			.append("SELECT {a.*} FROM TP_ATA a WHERE ")
			.append("to_date('" + dateString + "', 'dd/mm/yyyy')")
			.append(" BETWEEN a.INICIO_CADASTRO AND a.FIM_CADASTRO");
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addEntity("a", Ata.class);
		
		Ata ata = findBy(query);
		
		if(ata == null) {
			 sql = new StringBuilder()
				.append("SELECT {a.*} FROM TP_EXCECAO_ATA a WHERE ")
				.append("to_date('" + dateString + "', 'dd/mm/yyyy')")
				.append(" BETWEEN a.INICIO AND a.FIM");
			
			query = getSession().createSQLQuery(sql.toString());
			query.addEntity("a", AtaExcecao.class);
			
			AtaExcecao ataExcecao = (AtaExcecao) query.uniqueResult();
			if(ataExcecao != null) {
				ata = ataExcecao.getAta();
			}
		}
			
		return ata;

	}

	@Override
	public Ata findByMonthAndYear(Integer month, Integer year) {
		return findBy(Restrictions.and(
				Restrictions.eq("month", month), 
					Restrictions.eq("year", year)));
	}

	@Override
	public Ata findByRegistryDate(Date date, boolean start) {
		// TODO Auto-generated method stub
		return null;
	}


}
