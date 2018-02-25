package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SalaCirurgia implements Serializable{

	private static final long serialVersionUID = 4117049334353094907L;
	private Integer codSala;
	private String desc;
	
	public SalaCirurgia() {
		super();
	}

	public SalaCirurgia(Integer codSala, String desc) {
		super();
		this.codSala = codSala;
		this.desc = desc;
	}

	public Integer getCodSala() {
		return codSala;
	}

	public void setCodSala(Integer codSala) {
		this.codSala = codSala;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}