package br.upf.protegemed.funcoes;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.utils.Calculos;
import br.upf.protegemed.utils.Utils;

public class FrequenciaCorrente {

	private static float[] normal = new float[Utils.PONTOSONDA];
	private static float[] atencao = new float[Utils.PONTOSONDA];
	private static float[] perigo = new float[Utils.PONTOSONDA];

	private FrequenciaCorrente(){
		super();
	}

	public static float getNormal(int h) {
		return normal[h];
	}

	public static float getAtencao(int h) {
		return atencao[h];
	}

	public static float getPerigo(int h) {
		return perigo[h];
	}

	public static void setNormal(float[] normal) {
		FrequenciaCorrente.normal = normal;
	}

	public static void setAtencao(float[] atencao) {
		FrequenciaCorrente.atencao = atencao;
	}

	public static void setPerigo(float[] perigo) {
		FrequenciaCorrente.perigo = perigo;
	}

	public static Integer getStatusFrequencia(CapturaAtual cap) {
		Float valor;

		for (HarmAtual h : cap.getListHarmAtual()) {
			// Encontra o valor da barra (modulo)
			valor = Calculos.modulo(h.getSen(), h.getCos(), cap.getGain());
			Utils.logger("indice : " + (h.getCodHarmonica()));
			if (valor > FrequenciaCorrente.getPerigo(h.getCodHarmonica())) {
				return 3;
			} else if (valor > FrequenciaCorrente.getAtencao(h.getCodHarmonica())) {
				return 2;	
			}
		}
		return 1;
	}
}
