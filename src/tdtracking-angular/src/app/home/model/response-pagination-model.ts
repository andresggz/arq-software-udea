import { CareerListResponse } from "./career-list.model";

export interface ResponsePagination{
    total: Number;
    page: number;
    returnedRecords: Number;
    result: CareerListResponse[];
}