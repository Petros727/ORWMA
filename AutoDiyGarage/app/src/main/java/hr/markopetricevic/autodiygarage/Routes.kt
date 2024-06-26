package hr.markopetricevic.autodiygarage

sealed class Routes(val routePath: String) {
    object HOME : Routes("home")
    object MAIN : Routes("izbornik")
    object Audi : Routes("Audi")
    object BMW : Routes("BMW")
    object Mercedes : Routes("Mercedes")
    object ADDREPAIR : Routes("ADD_REPAIR" + "/{RepairType}")
}