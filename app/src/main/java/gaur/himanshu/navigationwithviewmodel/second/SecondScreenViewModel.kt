package gaur.himanshu.navigationwithviewmodel.second

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import gaur.himanshu.navigationwithviewmodel.Dest
import gaur.himanshu.navigationwithviewmodel.Employee
import gaur.himanshu.navigationwithviewmodel.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SecondScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val name = MutableStateFlow("")

    init {

        this.name.value = savedStateHandle.toRoute<Dest.SecondScreen>().name

    }

    fun navigate(name: String, age: Int) {
        navigator.navigate(Dest.ThirdScreen(Employee(name, age)))
    }


}