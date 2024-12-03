package gaur.himanshu.navigationwithviewmodel

import androidx.navigation.NavHostController
import javax.inject.Inject

interface Navigator {

    fun setController(navHostController: NavHostController)

    fun navigate(route: Any)

    fun popBackStack()

    fun clear()
}

class NavigatorImpl @Inject constructor() : Navigator {

    private var navHostController: NavHostController? = null

    override fun setController(navHostController: NavHostController) {
        this.navHostController = navHostController
    }

    override fun navigate(route: Any) {
        navHostController?.navigate(route)
    }

    override fun popBackStack() {
        navHostController?.popBackStack()
    }

    override fun clear() {
        this.navHostController = null
    }
}
