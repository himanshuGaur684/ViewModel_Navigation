package gaur.himanshu.navigationwithviewmodel.one

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gaur.himanshu.navigationwithviewmodel.Dest
import gaur.himanshu.navigationwithviewmodel.Navigator
import javax.inject.Inject

@HiltViewModel
class OneViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {

    fun navigate() {
        navigator.navigate(Dest.ScreenTwo(name = "Himanshu"))
    }

}