package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

public class TestModel {

	public static void main(String[] args) {
		Model m = new Model();

		System.out.println("Corsi del secondo semestre");
		List<Corso> corsi = m.listaCorsiSemestre(2);
		for (Corso c : corsi)
			System.out.println(c);
		System.out.println(" ");

		System.out.println("Nome e cognome dello studente con matricola 148072");
		System.out.println(m.getNomeCognomeFromMatricola(148072));
		System.out.println(" ");

		System.out.println("Studenti iscritti al corso 02AQJPG");
		List<Studente> studenti = m.getStudentiFromCodins("02AQJPG");
		for (Studente s : studenti)
			System.out.println(s);
		System.out.println(" ");

		System.out.println("Statistiche dei corsi:");
		Map<Corso, Stats> statistiche = m.getStats();
		for (Corso c : statistiche.keySet()) {
			System.out.println(c);
			System.out.println(statistiche.get(c));
			System.out.println("\n");
		}
		System.out.println(" ");
	}

}
