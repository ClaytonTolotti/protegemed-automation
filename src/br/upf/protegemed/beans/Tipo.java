package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tipo implements Serializable{

	private static final long serialVersionUID = 5910612692251952687L;
	private Integer codTipo;
	private String desc;
	
	public Tipo() {
		super();
	}

	public Tipo(Integer codTipo, String desc) {
		super();
		this.codTipo = codTipo;
		this.desc = desc;
	}

	public Integer getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(Integer codTipo) {
		this.codTipo = codTipo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
