package br.com.embracon.teamposition.domain.vo;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Month implements Comparable<Month> {

	private static Map<Integer, Month> months;

	private Integer month;

	private String name;
	
	private static boolean started;

	public Month() {
		populateMonths();
	}

	public Month(Integer month, String name) {
		this.name = name;
		this.month = month +1;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Month> getMonths() {
		return new LinkedList<Month>(months.values());
	}
	
	private void populateMonths() {

		if(!started && (months == null || months.isEmpty())) {

			started = Boolean.TRUE;
			
			months = new TreeMap<Integer, Month>();
			months.put(Calendar.JANUARY + 1, new Month(Calendar.JANUARY, "Janeiro"));
			months.put(Calendar.FEBRUARY + 1, new Month(Calendar.FEBRUARY, "Fevereiro"));
			months.put(Calendar.MARCH + 1, new Month(Calendar.MARCH, "Março"));
			months.put(Calendar.APRIL + 1, new Month(Calendar.APRIL, "Abril"));
			months.put(Calendar.MAY + 1, new Month(Calendar.MAY, "Maio"));
			months.put(Calendar.JUNE + 1, new Month(Calendar.JUNE, "Junho"));
			months.put(Calendar.JULY + 1, new Month(Calendar.JULY, "Julho"));
			months.put(Calendar.AUGUST + 1, new Month(Calendar.AUGUST, "Agosto"));
			months.put(Calendar.SEPTEMBER + 1, new Month(Calendar.SEPTEMBER, "Setembro"));
			months.put(Calendar.OCTOBER + 1, new Month(Calendar.OCTOBER, "Outubro"));
			months.put(Calendar.NOVEMBER + 1, new Month(Calendar.NOVEMBER, "Novembro"));
			months.put(Calendar.DECEMBER + 1, new Month(Calendar.DECEMBER, "Dezembro"));
		}
	}

	@Override
	public int compareTo(Month other) {
		return this.month.compareTo(other.month);
	}
}
