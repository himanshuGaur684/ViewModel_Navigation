package gaur.himanshu.navigationwithviewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class NavigationEvent {
    data class Navigate(val route: Any) : NavigationEvent()
    data object PopBackStack : NavigationEvent()
}

interface Navigator {

    val navigationEvents: MutableSharedFlow<NavigationEvent>

    fun navigate(route: Any)

    fun popBackStack()

    fun clear()

}

class NavigatorImpl @Inject constructor() : Navigator {

    val scope = CoroutineScope(Dispatchers.IO)

    override val navigationEvents: MutableSharedFlow<NavigationEvent> = MutableSharedFlow<NavigationEvent>()


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

    override fun clear() {
        scope.cancel()
    }

}
