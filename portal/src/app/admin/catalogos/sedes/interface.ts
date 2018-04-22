import { IEntity } from '../../../shared/interface/entity';
import { IMunicipio } from '../municipios/interface'

export interface ISede extends IEntity { 
	id: number
	codigo: string
	nombre: string
	direccion: string
	municipio: IMunicipio
}