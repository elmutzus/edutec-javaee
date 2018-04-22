import { IEntity } from '../../../shared/interface/entity';
import { ISede } from '../sedes/interface'

export interface ISalon extends IEntity { 
	id: number
	codigo: string
	sede: ISede
}