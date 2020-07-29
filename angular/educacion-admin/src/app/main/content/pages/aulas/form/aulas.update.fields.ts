import { PREFIX_DOMAIN_API_EDUCACION } from "environments/environment";

export const AULAS_UPDATE_FORM_FIELDS_DEF = [
  {
    key: 'id',
    labelKey: 'AULAS_UPDATE_FORM_FIELDS_DEF_FIELD_id',
    label: 'ID',
    type: 'string',
    controlType: 'textbox'
  },
  {
    key: 'materia',
    labelKey: 'aulas_create_form_fields_def_field_nombremateria',
    controlType: 'autocomplete',
    label: 'Materia',
    required: true,
    options: {
      transferIdToField: 'idMateria',
      elementLabel: 'descripcionNivel',
      elementValue: 'id',
      useNativeFilter: false,
      selectElementOrCleanField: 'Debe seleccionar un elemento o limpiar el campo'
    },
    apiOptions: {
      queryString: {
        descripcion: 'materia'
      },
      url: PREFIX_DOMAIN_API_EDUCACION + '/materias/'
    }
  },
  { 
    key: 'idMateria',   
    controlType: 'hidden'
  },
  {
    key: 'idInstitucion',
    labelKey: 'aulas_create_form_fields_def_field_nombreinstitucion',
    required: false,
    controlType: 'select',
    options: {
        elementLabel: 'nombre',
        elementValue: 'id',
        fromWs: {
          key: 'alumno_create_form_fields_def_field_idinstitucion',
          url: PREFIX_DOMAIN_API_EDUCACION + '/instituciones'
        }
    }
  },
  {
    key: 'fecha',
    labelKey: 'aulas_create_form_fields_def_field_fecha',
    label: 'Fecha',
    type: 'date',
    controlType: 'datepicker',
    required: true
  },
  {
    key: 'hora',
    labelKey: 'aulas_create_form_fields_def_field_hora',
    label: 'Hora',
    type: 'string',
    controlType: 'number',
    required: true
  }
];