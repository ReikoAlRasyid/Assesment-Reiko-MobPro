package org.d3if3076.Assesment.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import org.d3if3076.Assesment.R
import org.d3if3076.Assesment.navigation.Screen
import org.d3if3076.Assesment.ui.theme.MobproTheme

        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun MainScreen(navController: NavHostController) {
            Scaffold (
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = stringResource(id = R.string.app_name))
                        },
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        actions = {
                            IconButton(onClick = {navController.navigate(Screen.About.route)}) {
                                Icon(
                                    imageVector = Icons.Outlined.Info,
                                    contentDescription = stringResource(R.string.about),
                                    tint = MaterialTheme.colorScheme.primary
                                )

                            }
                        }
                    )
                }
            ) { padding ->
                ScreenContent(Modifier.padding(padding))
            }
        }
        @Composable
        fun ScreenContent(modifier: Modifier) {
            val selectedType = rememberSaveable { mutableStateOf("") }
            val berat = rememberSaveable { mutableStateOf("") }

            var totalHarga by remember { mutableStateOf<Float?>(null) }
            var valid by remember { mutableStateOf(true) }
            val context = LocalContext.current


            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = R.drawable.laundry,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = "Laundry Img",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Text(
                    text = stringResource(id = R.string.intro),
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically //
                ) {
                    RadioButton(
                        selected = selectedType.value == "Normal",
                        onClick = { selectedType.value = "Normal" }
                    )
                    Text(text = stringResource(id = R.string.normal))
                    RadioButton(
                        selected = selectedType.value == "Premium",
                        onClick = { selectedType.value = "Premium" }
                    )
                    Text(text = stringResource(id = R.string.premium))
                }
                OutlinedTextField(
                    value = berat.value,
                    onValueChange = { newValue ->
                        berat.value = newValue
                    },
                    label = { Text(text = stringResource(id = R.string.weight)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )

                Button(
                    onClick = {
                        valid = true
                        if (selectedType.value.isEmpty() || berat.value.isEmpty()) {
                            valid = false
                        } else {
                            val berat = berat.value.toFloatOrNull()
                            if (berat != null) {
                                val total: Float
                                if (selectedType.value == "Premium") {
                                    total = berat * 8000
                                    totalHarga = total
                                } else if (selectedType.value == "Normal") {
                                    total = berat * 6000
                                    totalHarga = total
                                }
                            }

                        }
                    },
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text(text = "Submit")
                }
                if (valid && totalHarga != null) {
                    Text(
                        text = "Laundry: ${selectedType.value}\nTotal: Rp.${totalHarga!!}",
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Button(
                        onClick = {
                            shareData(context, "Laundry: ${selectedType.value}\nTotal: Rp.${totalHarga!!}")
                        },
                        modifier = Modifier.padding(top = 8.dp),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                    ) {
                        Text(text = stringResource(R.string.share))
                    }
                }
                if (!valid) {
                    Text(text = stringResource(R.string.invalid_input))
                }
            }
        }
        private fun shareData(context: Context, message: String) {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, message)
            }
            if (shareIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(shareIntent)
            }
        }

        @Preview(showBackground = true)
        @Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
        @Composable
        fun ScreenPreview() {
            val navController = rememberNavController()
            MobproTheme {
                MainScreen(navController)
            }
        }
