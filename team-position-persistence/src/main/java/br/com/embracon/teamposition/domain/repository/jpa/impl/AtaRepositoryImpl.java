package br.com.embracon.teamposition.domain.repository.jpa.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import br.com.embracon.j4e.domain.repository.RepositoryException;
import br.com.embracon.j4e.util.ConvertUtils;
import br.com.embracon.teamposition.domain.entity.Ata;
import br.com.embracon.teamposition.domain.repository.AtaRepository;
import br.com.embracon.teamposition.domain.repository.jpa.AbstractPersistenceRepository;
import br.com.embracon.teamposition.domain.search.AtaSearch;

@Stateless
@Local(AtaRepository.class)
public class AtaRepositoryImpl extends AbstractPersistenceRepository<Ata> implements AtaRepository {

	private static final long serialVersionUID = -5103254101279397947L;

	public AtaRepositoryImpl() {
		super(Ata.class);
	}

	@Override
	public Ata findByDate(Date date) {

		try {

			CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
			CriteriaQuery<Ata> criteriaQuery = criteriaBuilder.createQuery(Ata.class);
			Root<Ata> venda = criteriaQuery.from(Ata.class);

			ParameterExpression<Date> dataInicio = criteriaBuilder.parameter(Date.class, "start");
			ParameterExpression<Date> dataFim = criteriaBuilder.parameter(Date.class, "registryEnd");

			Expression<Date> data = venda.get("data");
			criteriaQuery.where(criteriaBuilder.between(data, dataInicio, dataFim));

			TypedQuery<Ata> typedQuery = entityManager.createQuery(criteriaQuery);
			typedQuery.setParameter("start", date);
			typedQuery.setParameter("registryEnd", date);

			return typedQuery.getSingleResult();

		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public List<Ata> searchBy(AtaSearch search) {
		return null;
	}

	@Override
	public boolean hasTeamSeller(Ata ata) {
		return false;
	}

	@Override
	public Ata findByRegistryDate(Date date) {

		try {

			String dateString = ConvertUtils.convert(String.class, date, ConvertUtils.DATE);

			StringBuilder sql = new StringBuilder()
				.append("SELECT a.* FROM TP_ATA a LEFT JOIN TP_EXCECAO_ATA e")
				.append(" ON (a.INICIO_CADASTRO >= ")
				.append("to_date('" + dateString + "', 'dd/mm/yyyy')")
				.append(" or a.FIM_CADASTRO <= ")
				.append("to_date('" + dateString + "', 'dd/mm/yyyy')")
				.append(") or ( e.INICIO >= ")
				.append("to_date('" + dateString + "', 'dd/mm/yyyy')")
				.append(" or e.FIM <= ")
				.append("to_date('" + dateString + "', 'dd/mm/yyyy')")
				.append(")");

			Query query = entityManager.createNativeQuery(sql.toString(), Ata.class);

			return (Ata) query.getSingleResult();
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public Ata findByRegistryDate(Date date, boolean start) {
		try {

			String dateString = ConvertUtils.convert(String.class, date, ConvertUtils.DATE);

			String ataDate = "INICIO_CADASTRO";
			String ataExcecao = "INICIO";
			if(!start) {
				ataDate = "FIM_CADASTRO";
				ataExcecao = "FIM";
			}

			StringBuilder sql = new StringBuilder()
				.append("SELECT * FROM TP_ATA a LEFT JOIN TP_EXCECAO_ATA e")
				.append(" ON a." + ataDate + " = ")
				.append("to_date('" + dateString + "', 'dd/mm/yyyy')")
				.append(" or ")
				.append(" e." + ataExcecao + " = ")
				.append("to_date('" + dateString + "', 'dd/mm/yyyy')");

			Query query = entityManager.createNativeQuery(sql.toString(), Ata.class);

			return (Ata) query.getSingleResult();
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public Ata findByMonthAndYear(Integer month, Integer year) {
		
		try {

			CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
			CriteriaQuery<Ata> criteriaQuery = criteriaBuilder.createQuery(Ata.class);
			Root<Ata> ata = criteriaQuery.from(Ata.class);

			ParameterExpression<String> monthParam = criteriaBuilder.parameter(String.class, "month");
			ParameterExpression<String> yearParam = criteriaBuilder.parameter(String.class, "year");

			criteriaQuery.where(criteriaBuilder.and(
					criteriaBuilder.equal(ata.get("month"), monthParam),
					criteriaBuilder.equal(ata.get("year"), yearParam)
			));

			TypedQuery<Ata> typedQuery = entityManager.createQuery(criteriaQuery);
			typedQuery.setParameter("month", month);
			typedQuery.setParameter("year", year);

			return typedQuery.getSingleResult();

		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public boolean existDateInteval(Long id, Date start, Date end, String fieldStart, String fieldEnd) {
		return false;
	}

}
