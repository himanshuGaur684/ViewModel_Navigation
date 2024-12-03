package gaur.himanshu.navigationwithviewmodel

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass

sealed class Dest {

    @Serializable
    data object FirstScreen : Dest()

    @Serializable
    data class SecondScreen(val name: String) : Dest()

    @Serializable
    data class ThirdScreen(val employee: Employee) : Dest()

}

@Serializable
@Parcelize
data class Employee(
    val name: String,
    val age: Int
) : Parcelable

class CustomNavType<T : Parcelable>(
    private val clazz: KClass<T>,
    private val serializer: KSerializer<T>,
) : NavType<T>(false) {
    override fun get(bundle: Bundle, key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, clazz.java)
        } else {
            bundle.getParcelable(key) as T?
        }
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(serializer, value)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: T): String {
        return Json.encodeToString(serializer, value)
    }
}
