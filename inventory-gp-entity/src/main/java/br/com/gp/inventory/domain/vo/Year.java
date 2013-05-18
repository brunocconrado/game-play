package br.com.gp.inventory.domain.vo;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.com.embracon.j4e.util.DateUtils;

public class Year implements Comparable<Year> {

	private static Map<Integer, Year> years;

	private Integer year;

	private String name;
	
	private static boolean started;

	public Year() {
		populateYears();
	}

	public Year(Integer year) {
		this();
		this.name = year.toString();
		this.year = year;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Year> getYears() {
		return new LinkedList<Year>(years.values());
	}
	
	private void populateYears() {

		if(!started && (years == null || years.isEmpty())) {

			started = Boolean.TRUE;
			
			years = new TreeMap<Integer, Year>();
			
			Integer currentYear = DateUtils.getCalendar().get(Calendar.YEAR);
			for(Integer year = currentYear; year < (currentYear + 2); year++) {
				years.put(year, new Year(year));
			}
		}
	}

	@Override
	public int compareTo(Year other) {
		return this.year.compareTo(other.year);
	}
}
