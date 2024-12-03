package gaur.himanshu.navigationwithviewmodel

import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

interface Navigator {


    val navigationEvents: MutableSharedFlow<NavigationEvent>

    fun navigate(route: Any)

    fun popBackStack()


}

sealed class NavigationEvent() {

    data object PopBackStack : NavigationEvent()

    data class Navigate(val route: Any) : NavigationEvent()

}


class NavigatorImpl : Navigator {
    override val navigationEvents: MutableSharedFlow<NavigationEvent> = MutableSharedFlow()

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun navigate(route: Any) {
        scope.launch {
            navigationEvents.emit(NavigationEvent.Navigate(route))
        }
    }

    override fun popBackStack() {
        scope.launch {
            navigationEvents.emit(NavigationEvent.PopBackStack)
        }
    }
}