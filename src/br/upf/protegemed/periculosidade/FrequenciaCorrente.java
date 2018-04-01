package br.upf.protegemed.periculosidade;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.utils.Calculos;
import br.upf.protegemed.utils.Utils;

public class FrequenciaCorrente {

	private float[] normal = new float[12];
	private float[] atencao = new float[12];
	private float[] perigo = new float[12];

	public FrequenciaCorrente() {
		/* Valores limite da percepção, dentro considerados normais */
		normal[0] = 0.060F; // 60Hz
		normal[1] = 0.061F; // 120Hz
		normal[2] = 0.064F; // 180Hz
		normal[3] = 0.068F; // 240Hz
		normal[4] = 0.073F; // 300Hz
		normal[5] = 0.078F; // 360Hz
		normal[6] = 0.083F; // 420Hz
		normal[7] = 0.088F; // 480Hz
		normal[8] = 0.093F; // 540Hz
		normal[9] = 0.099F; // 600Hz
		normal[10] = 0.103F; // 660Hz
		normal[11] = 0.107F; // 720Hz
		/*
		 * normal[12] = 0.0111; normal[13] = 0.0115; normal[14] = 0.0120; normal[15] =
		 * 0.0127;
		 */

		/*
		 * Valores limite para let-ago. Até esse valores normais, acima podem causar
		 * danos
		 */
		atencao[0] = 0.1000F; // 60Hz
		atencao[1] = 0.1028F; // 120Hz
		atencao[2] = 0.1057F; // 180Hz
		atencao[3] = 0.1104F; // 240Hz
		atencao[4] = 0.1158F; // 300Hz
		atencao[5] = 0.1248F; // 360Hz
		atencao[6] = 0.1260F; // 420Hz
		atencao[7] = 0.1311F; // 480Hz
		atencao[8] = 0.1359F; // 540Hz
		atencao[9] = 0.1400F; // 600Hz
		atencao[10] = 0.1446F; // 660Hz
		atencao[11] = 0.1486F; // 720Hz

		/*
		 * atencao[12] = 0.1525F; atencao[13] = 0.1564F; atencao[14] = 0.1630F;
		 * atencao[15] = 0.1637F;
		 */
		/* Valores que representam perigo */
		perigo[0] = 0.5000F; // 60Hz
		perigo[1] = 0.7919F; // 120Hz
		perigo[2] = 1.0781F; // 180Hz
		perigo[3] = 1.5691F; // 240Hz
		perigo[4] = 2.3368F; // 300Hz
		perigo[5] = 2.6370F; // 360Hz
		perigo[6] = 2.9746F; // 420Hz
		perigo[7] = 3.3344F; // 480Hz
		perigo[8] = 3.7110F; // 540Hz
		perigo[9] = 4.0876F; // 600Hz
		perigo[10] = 4.4893F; // 660Hz
		perigo[11] = 4.9049F; // 720Hz
		/*
		 * perigo[12] = 5.3347F; //780Hz perigo[13] = 5.7911F; //840Hz perigo[14] =
		 * 6.2682F //900Hz perigo[15] = 6.7558F; //960Hz
		 */
	}

	public float getNormal(int h) {
		return normal[h];
	}

	public float getAtencao(int h) {
		return atencao[h];
	}

	public float getPerigo(int h) {
		return perigo[h];
	}

	public static String getStatusFrequencia(CapturaAtual cap) {
		Double valor;
		FrequenciaCorrente obj = new FrequenciaCorrente();

		for (HarmAtual h : cap.getListHarmAtual()) {
			// Encontra o valor da barra (modulo)
			valor = Calculos.modulo(h.getSen(), h.getCos(), cap.getGain());
			// Corrige o valor de pico para RMS
			// valor = Uteis.Calculos.Pico2RMS(valor);
			/*
			 * // Corrige o valor com o ganho do amplificador operacional valor = valor *
			 * CorrecaoAmplificadorOperacional.getCorrecaoFrequencia(freq);
			 */
			// System.out.println("F: " + freq + "\tValor: " + valor);
			if (valor > obj.getPerigo(h.getCodHarmonica() - 1)) {
				// System.out.println(valor + " " + obj.getPerigo(h.getHarmonica()-1));
				return "Dangerous " + String.valueOf(h.getCodHarmonica() * Utils.FREQBASE) + "Hz";
			} else if (valor > obj.getAtencao(h.getCodHarmonica() - 1)) {
				return "Atention " + String.valueOf(h.getCodHarmonica() * Utils.FREQBASE) + "Hz";
			}
		}
		return "Normal";

	}

}
