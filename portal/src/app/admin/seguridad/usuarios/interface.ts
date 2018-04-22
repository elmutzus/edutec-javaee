import { IRol } from "../roles/interface";

export interface IUsuario { 
	id: number
	nombre: string
	email: string
	telefono: string
	rol: IRol
}