package ar.com.avaco.educacion.domain.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "INSTITUCION")
@SequenceGenerator(name = "INSTITUCION_SEQ", sequenceName = "INSTITUCION_SEQ", allocationSize = 1)
public class Institucion extends ar.com.avaco.arc.core.domain.Entity<Long> {
	
	/** serializacion */
	private static final long serialVersionUID = -7943819746474249393L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSTITUCION_SEQ")
	@Column(name = "ID_INSTITUCION")
    private Long id;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@ManyToMany(mappedBy = "instituciones")
	Set<Alumno> alumnos;
	
	public Institucion() {}

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

	//TODO Agregar hashCode, equals y toString cuando se completen todos los atributos
	
}
