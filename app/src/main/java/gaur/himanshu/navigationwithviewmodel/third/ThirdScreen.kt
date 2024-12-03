package gaur.himanshu.navigationwithviewmodel.third

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
fun ThirdScreen(modifier: Modifier = Modifier, viewModel: ThirdScreenViewModel) {

    val employee by viewModel.employee.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(employee.name)
        Spacer(Modifier.height(12.dp))
        Text(employee.age.toString())
        Spacer(Modifier.height(12.dp))

        Button(onClick = {
            viewModel.navigate()
        }) { Text("go to second screen") }
    }

}