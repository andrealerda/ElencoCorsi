package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Stats;

public class CorsoDAO {

	// Ritorna tutti gli elementi della tabella CORSO
	public List<Corso> listAll() {

		String sql = "SELECT codins, crediti, nome, pd " + "FROM corso";
		List<Corso> result = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Corso c = new Corso(res.getString("codins"), res.getInt("crediti"), res.getString("nome"), res.getInt("pd"));
				result.add(c);
			}

			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	// Ritorna i corsi che hanno periodo didattico uguale a pd
	public List<Corso> listByPD(int pd) {

		String sql = "SELECT codins, crediti, nome, pd  FROM corso WHERE pd = ?";
		List<Corso> result = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Corso c = new Corso(res.getString("codins"), res.getInt("crediti"), res.getString("nome"), res.getInt("pd"));
				result.add(c);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	// Calcola alcune statistiche sugli iscritti per il corso codins
	public Stats getStatsByCodins(String codins) {

		String sql = "SELECT s.cds, count(distinct(s.matricola)) as tot FROM studente as s, iscrizione as i "
				+ "WHERE s.matricola = i.matricola and i.codins = ? and s.cds <> \"\" GROUP BY s.cds";
		Stats stats = new Stats();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				stats.getCdsMap().put(res.getString("cds"), res.getInt("tot"));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return stats;
	}

}
