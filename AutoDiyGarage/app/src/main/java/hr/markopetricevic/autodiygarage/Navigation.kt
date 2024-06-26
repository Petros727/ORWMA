
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hr.markopetricevic.autodiygarage.Routes
import hr.markopetricevic.autodiygarage.screens.HomeScreen.HomeScreen
import hr.markopetricevic.autodiygarage.models.RepairsInfo
import hr.markopetricevic.autodiygarage.screens.Audi.AudiScreen
import hr.markopetricevic.autodiygarage.screens.BMW.BMWViewModel
import hr.markopetricevic.autodiygarage.screens.Audi.AudiViewModel
import hr.markopetricevic.autodiygarage.screens.BMW.BMWScreen
import hr.markopetricevic.autodiygarage.screens.MainScreen.MainScreen
import hr.markopetricevic.autodiygarage.screens.addRepair.AddRepairScreen
import hr.markopetricevic.autodiygarage.screens.addRepair.AddRepairViewModel
import hr.markopetricevic.autodiygarage.screens.mercedes.MercedesScreen
import hr.markopetricevic.autodiygarage.screens.mercedes.MercedesViewModel
import hr.markopetricevic.autodiygarage.ui.theme.AutoDiyGarageTheme

@Composable
fun AutoDiyGarageApp() {
    val navController = rememberNavController()

    val BMWViewModel = hiltViewModel<BMWViewModel>()
    val AudiViewModel = hiltViewModel<AudiViewModel>()
    val addRepairViewModel = hiltViewModel<AddRepairViewModel>()
    val MercedesViewModel = hiltViewModel<MercedesViewModel>()

    AutoDiyGarageTheme {
        NavHost(navController = navController, startDestination = Routes.HOME.routePath) {
            composable(Routes.HOME.routePath) {
                HomeScreen(navController)
            }
            composable(Routes.MAIN.routePath) {
                MainScreen(navController)
            }
            composable(Routes.BMW.routePath) {
                BMWScreen(BMWViewModel.repairList, removeFromList = {
                    BMWViewModel.removeFromLrepairList(it)
                }, addRepairClicked = {
                    navController.navigate(
                        Routes.ADDREPAIR.routePath.replace(
                            "{RepairType}", "BMW"
                        )
                    )
                })
                BMWViewModel.getBMWList()
            }
            composable(Routes.Audi.routePath) {
                AudiViewModel.getAudiList()
                AudiScreen(AudiViewModel.AudiList, removeFromList = {
                    AudiViewModel.removeAudi(it)
                }, addRepairClicked = {
                    navController.navigate(
                        Routes.ADDREPAIR.routePath.replace(
                            "{RepairType}", "Audi"
                        )
                    )
                })
            }
            composable(Routes.Mercedes.routePath) {
                MercedesViewModel.getMercedesList()
                MercedesScreen(MercedesViewModel.mercedesList, removeFromList = {
                    MercedesViewModel.removeMercedes(it)
                }, addRepairClicked = {
                    navController.navigate(
                        Routes.ADDREPAIR.routePath.replace(
                            "{RepairType}", "Mercedes"
                        )
                    )
                })
            }
            composable(Routes.ADDREPAIR.routePath) { backStackEntry ->
                val RepairType = backStackEntry.arguments?.getString("RepairType")
                addRepairViewModel.addMoreData()
                AddRepairScreen(
                    addRepairViewModel.addRepairs,
                    addRepair = { title, description, imageUrl ->
                        val repair = RepairsInfo(
                            title = title,
                            description = description,
                            image = imageUrl
                        )
                        when (RepairType) {
                            "Audi" -> addRepairViewModel.addAudi(repair)
                            "BMW" -> addRepairViewModel.addBMW(repair)
                            "Mercedes" -> addRepairViewModel.addMercedes(repair)
                        }
                    },
                    navBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

