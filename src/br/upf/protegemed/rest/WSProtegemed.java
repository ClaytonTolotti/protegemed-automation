package br.upf.protegemed.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.ParamRequest;
import br.upf.protegemed.beans.SalaCirurgia;
import br.upf.protegemed.beans.Tomada;
import br.upf.protegemed.dao.EquipamentoDAO;
import br.upf.protegemed.dao.SalaCirurgiaDAO;
import br.upf.protegemed.enums.TypesRequests;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.utils.Utils;

@Path("/operations")
public class WSProtegemed {

	private static KieServices ks;
	private static KieContainer kContainer;
	private static KieSession kSession;
	private static Integer inicializaoDrools = 0;
	public static final Integer ativarLog = 1;
	
	public static Integer getInicializaoDrools() {
		return inicializaoDrools;
	}

	public static void setInicializaoDrools(Integer inicializaoDrools) {
		WSProtegemed.inicializaoDrools = inicializaoDrools;
	}

	public static Integer getAtivarlog() {
		return ativarLog;
	}

	public static KieServices getKs() {
		return ks;
	}

	public static void setKs(KieServices ks) {
		WSProtegemed.ks = ks;
	}

	public static KieContainer getkContainer() {
		return kContainer;
	}

	public static void setkContainer(KieContainer kContainer) {
		WSProtegemed.kContainer = kContainer;
	}

	public static KieSession getkSession() {
		return kSession;
	}

	public static void setkSession(KieSession kSession) {
		WSProtegemed.kSession = kSession;
	}

	public WSProtegemed() {
		super();
		if (getInicializaoDrools() == 0) {
			getSession();
			setInicializaoDrools(1);
		}
	}

	@GET
	@Path("get/status")
	public String getStatus() {
		return "ON";
	}

	@GET
	@Path("get/init-drools")
	public void getSession() {
		setKs(KieServices.Factory.get());
		setkContainer(ks.getKieClasspathContainer());
		setkSession(kContainer.newKieSession("protegemed"));
		Utils.logger("DROOLS INICIALIZADO");
	}

	@POST
	@Path("post/receive-event")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void postReceiveEvent(String c) throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException {
		
		try {
			// Separar os parÃ¢metros recebidos Ex: RFID=000&TYPE=00F
			String[] temp = c.split("&");
			List<HarmAtual> listHarmAtual = new ArrayList<>();
			CapturaAtual capturaAtual = new CapturaAtual();
			Equipamento equipamento = new Equipamento();
			Eventos eventos = new Eventos();
			Tomada tomada = new Tomada();
			SalaCirurgia salaCirurgia;
			ParamRequest paramRequest;
			
			String[] arrayCos;
			String[] arraySen;
			Integer inc = 1;
	
			paramRequest = splitRequest(temp);
			
			capturaAtual.setCodCaptura(2736);
			eventos.setCodEvento(Integer.parseInt(paramRequest.getTYPE()));
			tomada.setCodTomada(Integer.parseInt(paramRequest.getOUTLET()));
			salaCirurgia = new SalaCirurgiaDAO().querySalaCirurgia(tomada.getCodTomada());
			equipamento = new EquipamentoDAO().queryCodEquipament(paramRequest.getRFID());
			equipamento.setRfid(paramRequest.getRFID());
			capturaAtual.setOffset(Float.parseFloat(paramRequest.getOFFSET()));
			capturaAtual.setGain(Utils.convertHexToFloat(paramRequest.getGAIN()));
			capturaAtual.setEficaz(Utils.convertHexToFloat(paramRequest.getRMS()));
			capturaAtual.setMv(Utils.convertHexToFloat(paramRequest.getMV()));
			capturaAtual.setMv2(Utils.convertHexToFloat(paramRequest.getMV2()));
			capturaAtual.setUnder(Integer.parseInt(paramRequest.getUNDER()));
			capturaAtual.setOver(Integer.parseInt(paramRequest.getOVER()));
			capturaAtual.setDuracao(Integer.parseInt(paramRequest.getDURATION()));
			
			capturaAtual.setEventos(eventos);
			capturaAtual.setEquipamento(equipamento);
			capturaAtual.setTomada(tomada);
			capturaAtual.setSalaCirurgia(salaCirurgia);
			
			arraySen = paramRequest.getSIN().split("%");
			arrayCos = paramRequest.getCOS().split("%");
			
			for (int i = 0; i < arrayCos.length; i++) {
				HarmAtual harmAtual = new HarmAtual();
				harmAtual.setCodHarmonica(inc);
				harmAtual.setSen(Utils.convertHexToFloat(arraySen[i]));
				harmAtual.setCos(Utils.convertHexToFloat(arrayCos[i]));
				listHarmAtual.add(harmAtual);
			}
	
			capturaAtual.setListHarmAtual(listHarmAtual);
			capturaAtual.setData(Calendar.getInstance());
			
			getkSession().insert(capturaAtual);
			getkSession().fireAllRules();
		} catch(SQLException pr) {
			throw new ProtegeDAOException(pr.getMessage());
		}
	}
	
	@GET
	@Path("get/list-all-events")
	public void listAllEvents() {
		Collection<FactHandle> collect = kSession.getFactHandles();
		
		if(!collect.isEmpty()) {
			Utils.logger("total events in drools " + collect.size());
		} else {
			Utils.logger("nothing events in drools");
			return;
		}
		Utils.logger("list events");
		for (FactHandle factHandle : collect) {
			Utils.logger("fact " + factHandle.toString());
		}
	}
	
	public ParamRequest splitRequest(String[] param) {
		
		String[] objetoTemp = null;
		ParamRequest paramRequest = new ParamRequest();
		
		for (String result : param) {
			// Separar atributos e valores RFID=00000, guardando apenas o valor
			objetoTemp = result.split("=");

			if(objetoTemp[0].equals(TypesRequests.TYPE.getUrl())) {
				paramRequest.setTYPE(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.OUTLET.getUrl())) {
				paramRequest.setOUTLET(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.RFID.getUrl())) {
				paramRequest.setRFID(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.OFFSET.getUrl())) {
				paramRequest.setOFFSET(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.GAIN.getUrl())) {
				paramRequest.setGAIN(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.RMS.getUrl())) {
				paramRequest.setRMS(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.MV.getUrl())) {
				paramRequest.setMV(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.MV2.getUrl())) {
				paramRequest.setMV2(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.UNDER.getUrl())) {
				paramRequest.setUNDER(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.OVER.getUrl())) {
				paramRequest.setOVER(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.DURATION.getUrl())) {
				paramRequest.setDURATION(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.SIN.getUrl())) {
				paramRequest.setSIN(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.COS.getUrl())) {
				paramRequest.setCOS(objetoTemp[1]);
			}
		}
		return paramRequest;
	}
}
