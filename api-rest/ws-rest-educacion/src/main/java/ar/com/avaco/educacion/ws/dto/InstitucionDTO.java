package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class InstitucionDTO extends DTOEntity<Long> {

	private Long id;
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
