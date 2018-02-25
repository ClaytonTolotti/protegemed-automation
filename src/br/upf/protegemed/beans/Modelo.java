package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Modelo implements Serializable{

	private static final long serialVersionUID = -675120677551958723L;
	private Integer codModelo;
	private String desc;
	
	public Modelo() {
		super();
	}

	public Modelo(Integer codModelo, String desc) {
		super();
		this.codModelo = codModelo;
		this.desc = desc;
	}

	public Integer getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(Integer codModelo) {
		this.codModelo = codModelo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
