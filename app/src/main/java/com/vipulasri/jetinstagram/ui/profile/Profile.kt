package com.vipulasri.jetinstagram.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun profileBody() {

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        // add here each component of body
        Text(text = "this the full body of profile")

    }
}
//create different composable function here such as heater and the other options