package ar.com.avaco.educacion.domain.entities;

import java.time.DayOfWeek;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "HORARIO_DISP")
@SequenceGenerator(name = "HORARIO_DISP_SEQ", sequenceName = "HORARIO_DISP_SEQ", allocationSize = 1)
public class HorarioDisponible extends ar.com.avaco.arc.core.domain.Entity<Long> {
	
	/** serializacion */
	private static final long serialVersionUID = -8013219905860523771L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HORARIO_DISP_SEQ")
	@Column(name = "ID_HORARIO_DISP")
    private Long id;

	//@Enumerated
	@Column(name = "DIA_DISPONIBLE", nullable = false)
	//@Convert(converter = DayOfWeekIntegerConverter.class)
	private DayOfWeek dia;
	
	@Column(name = "HORA_DISPONIBLE", nullable = false)
	private Integer hora;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.MERGE)
    @JoinColumn(name = "ID_PROFESOR")
	private Profesor profesor;

	public HorarioDisponible() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public DayOfWeek getDia() {
		return dia;
	}

	public void setDia(DayOfWeek dia) {
		this.dia = dia;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	//TODO Agregar hashCode, equals y toString cuando se completen todos los atributos

}
