package ar.com.avaco.educacion.service.horas.disp;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.educacion.domain.entities.HorasAlumno;
import ar.com.avaco.educacion.repository.horas.disp.HorasAlumnoRepository;

@Transactional
@Service("horasAlumnoService")
public class HorasAlumnoServiceImpl extends NJBaseService<Long, HorasAlumno, HorasAlumnoRepository> implements HorasAlumnoService {

	@Override
	public HorasAlumno getByAlumnoYProfesorId(Long idAlumno, Long idProfesor) {
		return repository. getHorasAlumnoByProfesorAndAlumno(idProfesor, idAlumno);
	}

	
	@Resource(name = "horasAlumnoRepository")
	void setAulaRepository(HorasAlumnoRepository horasAlumnoRepository) {
		this.repository = horasAlumnoRepository;
	}
}
