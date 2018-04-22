import { IMunicipio } from '../municipios/interface'

export interface ISede { 
	id: number
	codigo: string
	nombre: string
	direccion: string
	municipio: IMunicipio
}