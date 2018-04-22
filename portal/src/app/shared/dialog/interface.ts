import { IEntity } from '../interface/entity'

export interface IModalDialogOptions {
    title: string
    componentClassName?: string
    message?: string
    size?: string
    bodyExtraClass?: string
    visibleTitle?: boolean
    readOnly?: boolean
    data?: any,
    yesCallback?:() => void
    noCallback?:() => void
    cancelCallback?:() => void
    previousComponent?: string
}

export interface ISimpleDialog {
    show(options: IModalDialogOptions);
}
