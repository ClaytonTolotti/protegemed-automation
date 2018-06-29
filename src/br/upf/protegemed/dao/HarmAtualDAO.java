package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class HarmAtualDAO {

	public List<HarmAtual> queryHarmCurrent() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{

		PreparedStatement stmt;
		HarmAtual harmAtual;
		CapturaAtual capturaAtual;
		List<HarmAtual> listHarmAtual = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_HARMONICA_ATUAL);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				harmAtual = new HarmAtual();
				capturaAtual = new CapturaAtual();
				capturaAtual.setCodCaptura(resultSet.getInt(1));
				harmAtual.setCapturaAtual(capturaAtual);
				harmAtual.setCodHarmonica(resultSet.getInt(2));
				harmAtual.setSen(resultSet.getFloat(3));
				harmAtual.setCos(resultSet.getFloat(4));
				listHarmAtual.add(harmAtual);
			}
			stmt.close();
			return listHarmAtual;
			
		} catch(SQLException pr) {
			throw new ProtegeDAOException(pr.getMessage());
		}
	}
}