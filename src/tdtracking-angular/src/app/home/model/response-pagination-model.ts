import { FleetListResponse } from "./fleet-list.model";

export interface ResponsePagination {
    total: Number;
    page: number;
    returnedRecords: Number;
    result: FleetListResponse[];
}
