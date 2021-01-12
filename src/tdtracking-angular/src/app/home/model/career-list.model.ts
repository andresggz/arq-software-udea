export interface CareerListResponse{
    id: Number,
    name: String,
    description: String,
    iconId?: String,
    active: Boolean,
    totalRoadmaps?: Number,
    totalCourses?: Number,
    createDate: String,
    updateDate: String
}