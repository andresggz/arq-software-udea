export interface VehicleSaveResponse {
    id: Number,
    name: String,
    description: String,
    licensePlate: String,
    model: String,
    brand: String,
    active: Number,
    totalTrips: Number,
    createDate: String,
    updateDate?: String,
    trips: string[]
}
