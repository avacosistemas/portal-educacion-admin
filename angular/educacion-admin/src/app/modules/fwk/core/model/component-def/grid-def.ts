import { DisplayCondition } from './display-condition';
import { ActionDef } from './action-def';
import { DynamicFieldConditionIf } from '../dynamic-form/dynamic-field-condition-if';

export class GridDef{
    // Obligatorios
    columnsDef: any;
    displayedColumns: string[];
    displayedColumnsCondition?: DisplayCondition[];
    displayedActionsCondition?: DisplayCondition[];
    selectCondition?: DynamicFieldConditionIf;

    // Opcionales
    key?: string;
    columnName?: string;
    sortAllColumns?: boolean;
    deleteAction?: boolean;
    actions?: ActionDef [];
    fromArrayField?: string;
    // Deprecated use delleteAction
    deleteColumn?: any;    
    deleteTernaria?: boolean;   
    columnsTernaria?: string[];
    titleKey?: string;
    title?: string;
}
