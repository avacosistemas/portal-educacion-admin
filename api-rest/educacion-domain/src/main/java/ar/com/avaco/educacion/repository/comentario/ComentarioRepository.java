package ar.com.avaco.educacion.repository.comentario;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Comentario;

public interface ComentarioRepository extends NJRepository<Long, Comentario>, ComentarioRepositoryCustom {

	List<Comentario> findAllByAulaId(Long idClase);
	
}
