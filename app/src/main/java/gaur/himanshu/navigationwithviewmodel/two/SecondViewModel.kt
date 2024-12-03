package gaur.himanshu.navigationwithviewmodel.two

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import gaur.himanshu.navigationwithviewmodel.Dest
import gaur.himanshu.navigationwithviewmodel.Employee
import gaur.himanshu.navigationwithviewmodel.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    private val navigator: Navigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val name = MutableStateFlow("")

    init {
        name.update { savedStateHandle.toRoute<Dest.ScreenTwo>().name }
    }

    fun navigate(
        name: String,
        age: Int
    ) {
        navigator.navigate(
            Dest.ScreenThree(
                Employee(name = name, age = age)
            )
        )
    }

}