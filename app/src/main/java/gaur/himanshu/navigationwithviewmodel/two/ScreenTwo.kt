package gaur.himanshu.navigationwithviewmodel.two

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenTwo(
    modifier: Modifier = Modifier,
    viewModel: SecondViewModel,
    onClick: (String, Int) -> Unit
) {

    val name by viewModel.name.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(name)
        Spacer(Modifier.height(12.dp))
        Button(onClick = {
            onClick.invoke(
                "himanshu",24
            )
//            viewModel.navigate(
//                name = "Himanshu",
//                age = 25
//            )
        }) {
            Text(text = "Go to third screen")
        }

    }

}