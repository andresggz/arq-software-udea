export interface FleetListResponse{
    id: Number,
    name: String,
    description: String,
    iconId?: String,
    active: Boolean,
    totalVehicles?: Number,
    totalTrips?: Number,
    createDate: String,
    updateDate: String
}
