/*
TODO: Moverlos al lugar correcto*/

export interface ICatalog {
	initEntity(): void;
	resolveRedirect(wasCanceled:boolean): void;
	save(): void;
	//initOnDialog(): void;
}
