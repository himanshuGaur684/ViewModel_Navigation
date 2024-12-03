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
import gaur.himanshu.navigationwithviewmodel.first.FirstScreen
import gaur.himanshu.navigationwithviewmodel.second.SecondScreen
import gaur.himanshu.navigationwithviewmodel.third.ThirdScreen
import gaur.himanshu.navigationwithviewmodel.ui.theme.NavigationWithViewModelTheme
import kotlinx.coroutines.flow.collectLatest
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
                    navigator.navigationEvents.collectLatest { navigationEvent ->

                        when (navigationEvent) {
                            is NavigationEvent.Navigate -> navHostController.navigate(
                                navigationEvent.route
                            )

                            NavigationEvent.PopBackStack -> navHostController.popBackStack()
                        }

                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        navController = navHostController,
                        startDestination = Dest.FirstScreen
                    ) {

                        composable<Dest.FirstScreen> {
                            FirstScreen(viewModel = hiltViewModel())
                        }

                        composable<Dest.SecondScreen> {
                            SecondScreen(viewModel = hiltViewModel())
                        }

                        composable<Dest.ThirdScreen>(
                            typeMap = mapOf(
                                typeOf<Employee>() to CustomNavType<Employee>(
                                    Employee::class,
                                    Employee.serializer()
                                )
                            )
                        ) {
                            ThirdScreen(viewModel = hiltViewModel())
                        }

                    }


                }
            }
        }
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