package it.polito.tdp.corsi.model;

import java.util.HashMap;
import java.util.Map;

public class Stats {

	private Map<String, Integer> cdsMap;

	public Stats() {
		cdsMap = new HashMap<String, Integer>();
	}

	public Map<String, Integer> getCdsMap() {
		return cdsMap;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-16s %s\n", "Corso di studio", "Numero di studenti"));
		for (String s : cdsMap.keySet()) {
			sb.append(String.format("%-16s %s\n", s, cdsMap.get(s)));
		}
		return sb.toString();
	}

}
