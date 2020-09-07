package ar.com.avaco.educacion.ws.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.OrdenCatalogo;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;
import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.service.catalogo.CatalogoService;
import ar.com.avaco.educacion.ws.dto.CatalogoProfesorDTO;
import ar.com.avaco.educacion.ws.dto.ConsultaDTO;
import ar.com.avaco.educacion.ws.dto.HorarioDisponibleDTO;
import ar.com.avaco.educacion.ws.dto.PreguntaRespuestaDTO;
import ar.com.avaco.educacion.ws.service.CatalogoProfesorEPService;

@Service("catalogoProfesorEPService")
public class CatalogoProfesorEPServiceImpl implements CatalogoProfesorEPService {

	private CatalogoService catalogoService;

	@Autowired
	public CatalogoProfesorEPServiceImpl(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}

	@Override
	public List<CatalogoProfesorDTO> listCatalogoProfesor(OrdenCatalogo filtro, Long idMateria, Integer idNivel) {

		List<Profesor> entities = catalogoService.listCatalogoProfesor(filtro, idMateria, idNivel);
		
		List<CatalogoProfesorDTO> dtos = new ArrayList<CatalogoProfesorDTO>();
		for (Profesor entity : entities) {
			dtos.add(convertToDto(entity));
		}
		
		return dtos;

	}
	
	private CatalogoProfesorDTO convertToDto(Profesor entity) {
		CatalogoProfesorDTO dto = new CatalogoProfesorDTO();
		dto.setId(entity.getId());
		dto.setNombreApellido(entity.getNombreApellido());

		dto.setFoto(entity.getFoto());
		dto.setValorHora(entity.getValorHora());

		dto.setMaterias(entity.getTitulo());
		dto.setDescripcion(entity.getDescripcion());
		dto.setCalificacion(entity.getCalificacion());
		
		dto.setValorHora(entity.getValorHora());
		
		return dto;
	}

	@Override
	public CatalogoProfesorDTO getCatalogoProfesor(Long id) {
		Profesor profesor = catalogoService.getCatalogoProfesor(id);
		CatalogoProfesorDTO catalogoProfesorDTO = convertToDto(profesor);
		return catalogoProfesorDTO;
	}
	
	@Override
	public double getCatalogoCalificacion(Long id) {
		return catalogoService.getCatalogoCalificacion(id);
	}
	
	@Override
	public List<HorarioDisponibleDTO> getCatalogoHorarios(LocalDate fecha, Long id) {
		
		List<HorarioDisponible> entities = catalogoService.getCatalogoHorarios(fecha, id);
		
		List<HorarioDisponibleDTO> dtos = new ArrayList<HorarioDisponibleDTO>();
		for (HorarioDisponible entity : entities) {
			dtos.add(new HorarioDisponibleDTO(entity));
		}
		
		return dtos;
		
	}
	
	@Override
	public List<PreguntaRespuestaDTO> getCatalogoConsulta(Long id) {
		List<PreguntaRespuesta> entities = catalogoService.getCatalogoConsulta(id);
		entities.sort((x,y) -> Long.compare(y.getFechaPregunta().getTime(), x.getFechaPregunta().getTime()));
		List<PreguntaRespuestaDTO> dtos = new ArrayList<PreguntaRespuestaDTO>();
		for (PreguntaRespuesta entity : entities) {
			dtos.add(new PreguntaRespuestaDTO(entity));
		}
		
		
		
		return dtos;
	
	}
	
	@Override
	public ConsultaDTO createCatalogoConsulta(ConsultaDTO consultaDto) throws BusinessException {
		Profesor profesor = new Profesor();
		PreguntaRespuesta consulta = new PreguntaRespuesta();
		
		profesor.setId(consultaDto.getIdProfesor());
		
		consulta.setPregunta(consultaDto.getConsulta());
		consulta.setProfesor(profesor);
		
		consulta = catalogoService.createCatalogoConsulta(consulta);
		return new ConsultaDTO(consulta);
		
	}

}
