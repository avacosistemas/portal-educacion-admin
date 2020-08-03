package ar.com.avaco.educacion.service.pregresp;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;

public interface PreguntaRespuestaService extends NJService<Long, PreguntaRespuesta> {

	List<PreguntaRespuesta> listByProfesor(Long idProfesor);
	
}
