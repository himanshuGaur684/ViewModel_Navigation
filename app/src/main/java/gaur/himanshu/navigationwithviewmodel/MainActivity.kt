package gaur.himanshu.navigationwithviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import gaur.himanshu.navigationwithviewmodel.one.ScreenOne
import gaur.himanshu.navigationwithviewmodel.three.ScreenThree
import gaur.himanshu.navigationwithviewmodel.three.ScreenThreeViewModel
import gaur.himanshu.navigationwithviewmodel.two.ScreenTwo
import gaur.himanshu.navigationwithviewmodel.ui.theme.NavigationWithViewModelTheme
import javax.inject.Inject
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationWithViewModelTheme {

                val navHostController = rememberNavController()

                LaunchedEffect(Unit) {
                    navigator.navigationEvents
                        .collect { navigationEvent ->
                            when (navigationEvent) {
                                is NavigationEvent.Navigate -> {
                                    navHostController.navigate(navigationEvent.route)
                                }

                                NavigationEvent.PopBackStack -> {
                                    navHostController.popBackStack()
                                }
                            }
                        }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        navController = navHostController,
                        startDestination = Dest.ScreenOne
                    ) {

                        composable<Dest.ScreenOne> {
                            ScreenOne(viewModel = hiltViewModel())
                        }

                        composable<Dest.ScreenTwo> {
                            ScreenTwo(viewModel = hiltViewModel()) { name, age ->
                                navHostController.navigate(Dest.ScreenThree(Employee(name, age)))
                            }
                        }

                        composable<Dest.ScreenThree>(
                            typeMap = mapOf(
                                typeOf<Employee>() to
                                        CustomNavType(
                                            Employee::class,
                                            Employee.serializer()
                                        ),
                            )
                        ) {
                            ScreenThree(viewModel = hiltViewModel<ScreenThreeViewModel>())
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        navigator.clear()
        super.onDestroy()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationWithViewModelTheme {
        Greeting("Android")
    }
}