package ar.com.avaco.educacion.service.aula;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.AulaAlumno;
import ar.com.avaco.educacion.repository.aula.AulaAlumnoRepository;
import ar.com.avaco.educacion.service.SolapaUtils;
import ar.com.avaco.educacion.service.alumno.AlumnoService;

@Transactional
@Service("aulaAlumnoService")
public class AulaAlumnoServiceImpl extends NJBaseService<Long, AulaAlumno, AulaAlumnoRepository>
		implements AulaAlumnoService {

	@Autowired
	private AlumnoService alumnoService;

	@Autowired
	private AulaService aulaService;

	@Override
	public List<AulaAlumno> listByAula(Long idAula) {
		return this.getRepository().findAllByAulaId(idAula);
	}

	@Override
	public AulaAlumno getByIdAulaIdAlumno(Long idAula, Long id) {
		return this.repository.findOneByAulaIdAndAlumnoId(idAula, id);
	}
	
	@Override
	public AulaAlumno saveAlumno(AulaAlumno aulaAlumno) throws BusinessException {
		validarDisponibilidadAula(aulaAlumno.getAula().getId(), aulaAlumno.getAlumno().getId());
		return super.save(aulaAlumno);
	}

	@Override
	public void validarDisponibilidadAula(Long idAula, Long idAlumno) throws BusinessException {
		Aula aula = this.aulaService.get(idAula);
		Alumno alumno = this.alumnoService.get(idAlumno);

		if (alumno == null)
			throw new BusinessException("El alumno id " + idAlumno + " no existe");

		if (aula.getInstitucion() != null && !alumno.getInstitucion().getId().equals(aula.getInstitucion().getId()))
			throw new BusinessException("El alumno no pertenece a la Institucion del aula");

		
		Iterator<AulaAlumno> iter = aula.getAlumnos().iterator();
		boolean found = false;
		while (iter.hasNext() && !found) {
			AulaAlumno aa = iter.next();
			if (aa.getAlumno().getId().equals(alumno.getId())) {
				throw new BusinessException("El aula ya tiene asociado al alumno");
			}
		}
		
		List<AulaAlumno> aulasAlumno = this.getRepository().findAllByAlumnoId(alumno.getId());
		
		if (aulasAlumno != null && !alumno.getAulas().isEmpty()) {
			for (AulaAlumno aa : aulasAlumno) {
				if (SolapaUtils.seSolapanAulas(aa.getAula(), aula, 60)) {
					throw new BusinessException(
							"El alumno ya tiene una clase en ese dia y horario. Ver Aula para Materia: "
									+ aa.getAula().getMateria().getDescripcion());
				}
			}
		}
	}

	@Override
	public List<AulaAlumno> listByProfesorId(Long id) {
		return this.repository.findAllByAulaProfesorId(id);
	}
	
	@Override
	public List<AulaAlumno> listByAlumnoId(Long id) {
		return this.repository.findAllByAlumnoId(id);
	}

	
	@Resource(name = "aulaAlumnoRepository")
	public void setRepository(AulaAlumnoRepository aulaAlumnoRepository) {
		this.repository = aulaAlumnoRepository;
	}

	@Resource(name = "aulaService")
	public void setAulaService(AulaService aulaService) {
		this.aulaService = aulaService;
	}

	@Resource(name = "alumnoService")
	public void setAlumnoService(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}

}
