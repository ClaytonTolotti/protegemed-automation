package br.upf.protegemed.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.Modelo;
import br.upf.protegemed.beans.UsoSalaEquip;
import br.upf.protegemed.dao.SelectDAO;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.exceptions.ProtegemedParserException;

@Path("/query")
public class Query {

	@GET
	@Path("get/list-useroomequip")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsoSalaEquip> getUseRoomEquip() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		return new SelectDAO().queryUseRoomEquip();
	}
	
	@GET
	@Path("get/list-events")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Eventos> getEvents() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		return new SelectDAO().queryEvents();
	}
	
	@GET
	@Path("get/list-models")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Modelo> getModels() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		return new SelectDAO().queryModels();
	}
	
	@GET
	@Path("get/list-harmonic-current")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HarmAtual> getHarmCurrent() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		return new SelectDAO().queryHarmCurrent();
	}

	@GET
	@Path("get/list-capture-current")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CapturaAtual> getCaptureCurrent() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		return new SelectDAO().queryCaptureCurrent();
	}
	
	@GET
	@Path("get/list-equipaments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Equipamento> getEquipaments() throws ProtegeDAOException, ProtegemedParserException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		return new SelectDAO().queryEquipament();
	}
}
