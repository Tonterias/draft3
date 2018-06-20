import { BaseEntity } from './../../shared';

export class Urllink implements BaseEntity {
    constructor(
        public id?: number,
        public linkText?: string,
        public linkURL?: string,
        public usefulLinks1?: BaseEntity,
        public usefulLinks2?: BaseEntity,
        public usefulLinks3?: BaseEntity,
        public usefulLinks4?: BaseEntity,
        public usefulLinks5?: BaseEntity,
        public usefulLinks6?: BaseEntity,
        public recentVideos1?: BaseEntity,
        public recentVideos2?: BaseEntity,
        public recentVideos3?: BaseEntity,
        public recentVideos4?: BaseEntity,
        public recentVideos5?: BaseEntity,
        public recentVideos6?: BaseEntity,
    ) {
    }
}
