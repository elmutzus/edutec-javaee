export interface dataColumnDefinition {
	name: string
	caption: string
	sortable?: boolean
	sortDirection: number
	width: string
}

export interface IDatagridOptions {
	title: string
	pageSize?: number
	baseUrl: string
	editComponentName?: string
	showHeader: boolean
	showTitle?: boolean
	columns: dataColumnDefinition[]
	insideDialog?: boolean
}