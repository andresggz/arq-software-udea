export interface FleetSaveResponse {
    id: Number,
    name: String,
    description: String,
    icon?: String,
    totalVehicles?: Number,
    totalTrips?: Number,
    createDate: String,
    updateDate: String,
    active: Boolean,
    vehicles: string[]
}
