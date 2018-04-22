export interface IParametro { 
  id:number
  nombre:string
  valor:string
  editable:number,
  publico: number,
  dominio: string,
  grupo?: string
}