package gaur.himanshu.navigationwithviewmodel.third

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import gaur.himanshu.navigationwithviewmodel.CustomNavType
import gaur.himanshu.navigationwithviewmodel.Dest
import gaur.himanshu.navigationwithviewmodel.Employee
import gaur.himanshu.navigationwithviewmodel.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.reflect.typeOf

@HiltViewModel
class ThirdScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val employee = MutableStateFlow(Employee(name = "", age = 0))

    init {
        employee.update {
            savedStateHandle.toRoute<Dest.ThirdScreen>(
                typeMap = mapOf(
                    typeOf<Employee>() to CustomNavType<Employee>(
                        Employee::class,
                        Employee.serializer()
                    )
                )
            ).employee
        }
    }

    fun navigate() {
        navigator.popBackStack()
    }

}