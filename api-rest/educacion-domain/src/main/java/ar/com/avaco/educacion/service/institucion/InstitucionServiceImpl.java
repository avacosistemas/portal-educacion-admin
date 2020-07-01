package ar.com.avaco.educacion.service.institucion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.educacion.domain.entities.Institucion;
import ar.com.avaco.educacion.repository.institucion.InstitucionRepository;


@Transactional
@Service("institucionService")
public class InstitucionServiceImpl extends NJBaseService<Long, Institucion, InstitucionRepository> implements InstitucionService {
	
	
	/**
	 * @see InstitucionService#listByAlumno(Long)
	 */
	@Override
	public List<Institucion> listByAlumno(Long idAlumno) {
		return getRepository().findAllByAlumnosId(idAlumno);
	}
	
	/**
	 * @see InstitucionService#createInstitucion(Institucion)
	 */
	@Override
	public Institucion createInstitucion(Institucion entity) throws BusinessException {
		
		validateInstitucionNoEmpty(entity);
		validateInstitucionOnSave(entity);

		entity = this.getRepository().save(entity);

		return entity;
	}
	
	/**
	 * @see InstitucionService#updateInstitucion(Institucion)
	 */
	@Override
	public Institucion updateInstitucion(Institucion entity) throws BusinessException {

		validateInstitucionNoEmpty(entity);
		validateInstitucionOnSave(entity);

		Institucion institucion = this.get(entity.getId());
		institucion.setId(entity.getId());
		institucion.setNombre(entity.getNombre());

		institucion = this.getRepository().save(institucion);

		return institucion;
	}
	
	/**
	 * Valida que la institucion no sea null
	 * 
	 * @param institucion
	 * @throws BusinessException error de negocio
	 */
	private void validateInstitucionNoEmpty(Institucion institucion) throws BusinessException {
		if (institucion == null) {
			throw new BusinessException("Institucion vac�a.");
		}
	}

	/**
	 * Valida que la institucion no se encuentre registrada.
	 * @param institucion institucion a validar.
	 * @throws ErrorValidationException error al validar
	 * @throws BusinessException error de negocio
	 */
	public void validateInstitucionOnSave(Institucion institucion) throws ErrorValidationException, BusinessException {

		Map<String, String> errores = new HashMap<String, String>();

		// Validacion nombre
		if (StringUtils.isBlank(institucion.getNombre())) {
			errores.put("nombre", "El campo nombre es requerido.");
		} else if (getRepository().findByNombreEqualsIgnoreCase(institucion.getNombre()) != null) {
			errores.put("nombre", "El nombre no esta disponible. Intente otro diferente.");
		}

		if (!errores.isEmpty()) {
			throw new ErrorValidationException("Se encontraron los siguientes errores", errores);
		}

	}
	
	@Resource(name = "institucionRepository")
	void setInstitucionRepository(InstitucionRepository institucionRepository) {
		this.repository = institucionRepository;
	}

}
