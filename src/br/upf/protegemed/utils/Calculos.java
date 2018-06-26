package br.upf.protegemed.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.OndaPadrao;
import br.upf.protegemed.dao.FrequenciasDAO;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.funcoes.ProtegeDataset;
import br.upf.protegemed.funcoes.Similaridade;

/**
 *
 * @author rebonatto
 */
public class Calculos {

	private Calculos() {
		super();
	}
	
	public static float ajustaRMSValorMedio(CapturaAtual c) {
		
		float res;
		float total;
		float mod;
		float vm;
		float totalharm = 0.0F;

		if (c.getMv() == 0)
			return c.getEficaz();
		// coloca o valor do VM
		vm = Math.abs((c.getMv() / 256 - c.getOffset()) / c.getGain());
		total = vm;

		// calcula e adiciona cada uma das barras
		for (HarmAtual h : c.getListHarmAtual()) {
			mod = modulo(h.getSen(), h.getCos(), c.getGain());
			totalharm += mod;
		}
		total += totalharm;
		res = (totalharm * c.getEficaz()) / total;
		return res;
	}

	public static float modulo(float sen, float cos, float gain) {
		/* calcula o modulo, igual ao grafico do programa */
		float f = (float) (Math.sqrt(sen * sen + cos * cos) / 128);
		f = f / gain;

		return f;
	}

	public static float picoDoisRMS(float f) {
		/* ajusta o valor de pico para RMS */
		f = f * f;
		f = f / 2;
		f = (float) Math.sqrt(f);
		return f;
	}
	
	public static OndaPadrao minDifValorMedio(CapturaAtual cap) {
		OndaPadrao ondaPadrao = null;

		float dif;
		float aux;

		Collection<OndaPadrao> lista = new ArrayList<>();
		dif = Float.MAX_VALUE;

		for (OndaPadrao onda : lista) {
			aux = Math.abs(cap.getMv() - onda.getValorMedio());

			if (aux < dif) {
				ondaPadrao = onda;
				dif = aux;
			}
		}
		return ondaPadrao;
	}
	
	public static boolean calcularSpearman(CapturaAtual capturaAtualOne,
			CapturaAtual capturaAtualTwo) throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		
		List<Double> listaUm = ProtegeDataset.newDatasetOnda(capturaAtualOne, Boolean.TRUE);
		List<Double> listaDois = ProtegeDataset.newDatasetOnda(capturaAtualTwo, Boolean.TRUE);

		double[] d = Similaridade.spearman(listaUm, listaDois);
		
		return new FrequenciasDAO().salvarFrequencia(capturaAtualOne, capturaAtualTwo, d);
	}
}