import { IEntity } from '../../../shared/interface/entity';
import { IMunicipio } from '../municipios/interface';

export interface IDepartamento extends IEntity {
  	codigo:string, 
  	nombre:string,
  	municipios: IMunicipio[]
}