import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pertemuan11.navigation.DestinasiDosen
import com.example.pertemuan11.navigation.DestinasiHalamanUtama
import com.example.pertemuan11.navigation.DestinasiMataKuliah
import com.example.pertemuan11.navigation.DestinasiMataKuliahDetail
import com.example.pertemuan11.navigation.DestinasiMataKuliahUpdate
import com.example.pertemuan11.ui.view.dosen.DestinasiDosenInsert
import com.example.pertemuan11.ui.view.dosen.HomeDosenView
import com.example.pertemuan11.ui.view.dosen.InsertDosenView
import com.example.pertemuan11.ui.view.halamanutama.HalamanUtamaView
import com.example.pertemuan11.ui.view.matakuliah.DestinasiMataKuliahInsert
import com.example.pertemuan11.ui.view.matakuliah.DetailMataKuliahView
import com.example.pertemuan11.ui.view.matakuliah.HomeMataKuliahView
import com.example.pertemuan11.ui.view.matakuliah.InsertMataKuliahView
import com.example.pertemuan11.ui.view.matakuliah.UpdateMataKuliahView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHalamanUtama.route // Starting screen is HomeView
    ) {
        composable(route = DestinasiHalamanUtama.route) {
            HalamanUtamaView(
                onDosenClick = {
                    navController.navigate(DestinasiDosen.route)
                },
                onMataKuliahClick = {
                    navController.navigate(DestinasiMataKuliah.route)
                },
                modifier = modifier
            )
        }

        // Dosen List Screen
        composable(route = DestinasiDosen.route) {
            HomeDosenView(
                onBack = { navController.popBackStack() },
                onAddDosen = {
                    navController.navigate(DestinasiDosenInsert.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiDosenInsert.route
        ) {
            InsertDosenView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiMataKuliah.route
        ) {
            HomeMataKuliahView(
                onDetailClick = { kode ->
                    navController.navigate("${DestinasiMataKuliahDetail.route}/$kode")
                    println("PengelolaHalaman = $kode")
                },
                onBack = { navController.popBackStack() },
                onAddMatakuliah = {
                    navController.navigate(DestinasiMataKuliahInsert.route)
                },
                modifier = modifier
            )
        }

        composable(route = DestinasiMataKuliahInsert.route) {
            InsertMataKuliahView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier
            )
        }

        // Detail Matakuliah Screen
        composable(
            DestinasiMataKuliahDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiMataKuliahDetail.KODE) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val kode = backStackEntry.arguments?.getString(DestinasiMataKuliahDetail.KODE)
            kode?.let { kode ->
                DetailMataKuliahView(
                    onBack = { navController.popBackStack() },
                    onEditClick = {
                        navController.navigate("${DestinasiMataKuliahUpdate.route}/$it")
                    },
                    onDeleteClick = { navController.popBackStack() },
                    modifier = modifier
                )
            }
        }

        // Update Matakuliah Screen
        composable(
            DestinasiMataKuliahUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiMataKuliahUpdate.KODE) { type = NavType.StringType }
            )
        ) {
            UpdateMataKuliahView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier
            )
        }
    }
}

