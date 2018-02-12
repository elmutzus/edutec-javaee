import { IRol } from "../roles/interface";
import { IEntity } from "../../../shared/interface/entity";

export interface IUsuario extends IEntity { 
	nombre: string
	email: string
	telefono: string
	activo: number
	clave?: string
	rol: IRol
}