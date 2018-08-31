package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.beans.TipoOnda;
import br.upf.protegemed.beans.Tomada;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class CapturaAtualDAO {

	public List<CapturaAtual> queryCaptureCurrent() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		CapturaAtual capturaAtual;
		Tomada tomada;
		TipoOnda tipoOnda;
		Equipamento equipamento;
		Eventos eventos;
		
		List<CapturaAtual> listCapturaAtual = new ArrayList<>();
		
		PreparedStatement stmt;
		ResultSet resultSet;
		
		try {
			stmt = ConnectionFactory.conexao.prepareStatement(Utils.QuerySelect.QUERY_CAPTURA_ATUAL);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				
				capturaAtual = new CapturaAtual();
				tomada = new Tomada();
				tipoOnda = new TipoOnda();
				equipamento = new Equipamento();
				eventos = new Eventos();
				
				capturaAtual.setCodCaptura(resultSet.getInt(1));
				tomada.setCodTomada(resultSet.getInt(2));
				tipoOnda.setCodTipoOnda(resultSet.getInt(3));
				equipamento.setCodEquip(resultSet.getInt(4));
				eventos.setCodEvento(resultSet.getInt(5));
				
				capturaAtual.setTomada(tomada);
				capturaAtual.setTipoOnda(tipoOnda);
				capturaAtual.setEquipamento(equipamento);
				capturaAtual.setEventos(eventos);
				capturaAtual.setMv(resultSet.getFloat(6));
				capturaAtual.setOffset(resultSet.getFloat(7));
				capturaAtual.setGain(resultSet.getFloat(8));
				capturaAtual.setEficaz(resultSet.getFloat(9));
				capturaAtual.setMv2(resultSet.getFloat(11));
				capturaAtual.setUnder(resultSet.getInt(12));
				capturaAtual.setOver(resultSet.getInt(13));
				capturaAtual.setDuracao(resultSet.getInt(14));
				listCapturaAtual.add(capturaAtual);
			}
			stmt.close();
			return listCapturaAtual;
			
		} catch (SQLException pr) {
			throw new ProtegeDAOException(pr.getMessage());
		}
	}
	
	public Integer getNextval() throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		
		PreparedStatement stmt;
		ResultSet resultSet;
		Integer sequence = 0;
		
		try {
			stmt = ConnectionFactory.conexao.prepareStatement(Utils.QuerySelect.QUERY_SEQ_CAPTURA_ATUAL);
			
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				sequence = resultSet.getInt(1);
			}
			
			return sequence;
			
		} catch (SQLException pr) {
			System.out.println(pr.getMessage());
			throw new ProtegeDAOException(pr.getMessage());
		}
	}
	
	public void insertCapturaAtual(CapturaAtual capturaAtual) throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		
		PreparedStatement stmt;
		
		try {
			stmt = ConnectionFactory.conexao.prepareStatement(Utils.QueryInsert.INSERT_CAPTURA_ATUAL);
			stmt.setInt(1, capturaAtual.getCodCaptura());
			stmt.setInt(2, capturaAtual.getTomada().getCodTomada());
			stmt.setInt(3, 1);
			stmt.setInt(4, 1);
			stmt.setInt(5, capturaAtual.getEventos().getCodEvento());
			stmt.setFloat(6, capturaAtual.getMv());
			stmt.setFloat(7, capturaAtual.getOffset());
			stmt.setFloat(8, capturaAtual.getGain());
			stmt.setFloat(9, capturaAtual.getEficaz());
			stmt.setString(10,new SimpleDateFormat(Utils.MASK_DATA).format(capturaAtual.getData().getTime()));
			stmt.setFloat(11, capturaAtual.getMv2());
			stmt.setInt(12, capturaAtual.getUnder());
			stmt.setInt(13, capturaAtual.getOver());
			stmt.setLong(14, capturaAtual.getDuracao());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException pr) {
			System.out.println(pr.getMessage());
			throw new ProtegeDAOException(pr.getMessage());
		}
		
	}
	
	public void updateCapturaAtual() {
		
	}
}
