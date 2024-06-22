package org.d3if3076.mobpro.ui.screen

import android.content.res.Configuration
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.d3if3076.Assesment.R
import org.d3if3076.Assesment.ui.theme.MobproTheme

@Composable
fun LaundryDialog(
    bitmap: Bitmap?,
    onDismissRequest: () -> Unit,
    onConfirmation: (String, String, String) -> Unit
) {
    var nama by remember { mutableStateOf("") }
    var berat by remember { mutableStateOf("") }
    var jenis by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                }
                OutlinedTextField(
                    value = nama,
                    onValueChange = {
                        nama = it
                        showError = false
                    },
                    label = { Text(text = stringResource(id = R.string.name)) },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.padding(8.dp)
                )
                OutlinedTextField(
                    value = berat,
                    onValueChange = {
                        berat = it
                        showError = false
                    },
                    label = { Text(text = stringResource(id = R.string.weight)) },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.padding(8.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = stringResource(id = R.string.jenis))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = jenis == "Premium",
                            onClick = {
                                jenis = "Premium"
                                showError = false
                            }
                        )
                        Text(text = stringResource(id = R.string.premium))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = jenis == "Normal",
                            onClick = {
                                jenis = "Normal"
                                showError = false
                            }
                        )
                        Text(text = stringResource(id = R.string.normal))
                    }
                }
                if (showError) {
                    Text(
                        text = stringResource(id = R.string.errormsg),
                        color = androidx.compose.ui.graphics.Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = stringResource(id = R.string.cancel))
                    }
                    OutlinedButton(
                        onClick = {
                            if (nama.isEmpty() || berat.isEmpty() || jenis.isEmpty()) {
                                showError = true
                            } else {
                                onConfirmation(nama, berat, jenis)
                            }
                        },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = stringResource(id = R.string.save))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AddDialogPreview() {
    MobproTheme {
        LaundryDialog(
            bitmap = null,
            onDismissRequest = {  },
            onConfirmation = { _, _,_ -> }
        )
    }
}
