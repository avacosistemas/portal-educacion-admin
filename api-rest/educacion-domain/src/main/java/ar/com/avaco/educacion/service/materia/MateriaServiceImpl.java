package ar.com.avaco.educacion.service.materia;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.repository.materia.MateriaRepository;
import ar.com.avaco.educacion.repository.nivel.NivelRepository;

@Transactional
@Service("materiaService")
public class MateriaServiceImpl extends NJBaseService<Long, Materia, MateriaRepository> implements MateriaService {

	@Override
	public Materia createMateria(Materia entity) throws BusinessException {

		validateMateriaNoEmpty(entity);
		validateMateriaOnSave(entity);

		Materia materia = new Materia();
		materia.setDescripcion(entity.getDescripcion());

		materia = this.getRepository().save(materia);

		return materia;
	}
	
	@Override
	public Materia updateMateria(Materia entity) throws BusinessException {

		validateMateriaNoEmpty(entity);
		validateMateriaOnSave(entity);

		Materia materia = this.get(entity.getId());
		materia.setId(entity.getId());
		materia.setDescripcion(entity.getDescripcion());

		materia = this.getRepository().save(materia);

		return materia;
	}
	
	/**
	 * Valida que la materia no sea null
	 * 
	 * @param materia
	 * @throws BusinessException
	 */
	private void validateMateriaNoEmpty(Materia materia) throws BusinessException {
		if (materia == null) {
			throw new BusinessException("Materia vac�a.");
		}
	}

	/**
	 * Valida que la materia no se encuentre registrada.
	 * 
	 * @param materia a validar.
	 */
	public void validateMateriaOnSave(Materia materia) throws ErrorValidationException, BusinessException {

		Map<String, String> errores = new HashMap<String, String>();

		// Validacion descripcion
		if (StringUtils.isBlank(materia.getDescripcion())) {
			errores.put("materia", "El campo materia es requerido.");
		} else if (getRepository().findByDescripcionEqualsIgnoreCase(materia.getDescripcion()) != null) {
			errores.put("materia", "El materia no esta disponible. Intente otro diferente.");
		}

		if (!errores.isEmpty()) {
			throw new ErrorValidationException("Se encontraron los siguientes errores", errores);
		}

	}
	
	@Resource(name = "materiaRepository")
	void setMateriaRepository(MateriaRepository materiaRepository) {
		this.repository = materiaRepository;
	}
	
	
}
