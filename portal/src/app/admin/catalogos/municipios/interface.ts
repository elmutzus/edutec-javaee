import { IEntity } from '../../../shared/interface/entity';

export interface IMunicipio extends IEntity { 
	codigo:string
	nombre:string
	idDepartamento?: number
}