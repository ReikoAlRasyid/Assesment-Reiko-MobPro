package org.d3if3076.Assesment.ui.screen

import org.d3if3076.Assesment.R
import org.d3if3076.Assesment.ui.theme.MobproTheme
import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DeleteDialog(
    openDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    if (openDialog){
        AlertDialog(
            text = { Text(text = stringResource(R.string.delete))},
            confirmButton = {
                TextButton(onClick = { onConfirmation() }) {
                    Text(text = stringResource(R.string.delete))
                }
            },
            dismissButton = {
                TextButton(onClick = {onDismissRequest()}) {
                    Text(text = stringResource(R.string.cancel))
                }
            },
            onDismissRequest = {onDismissRequest()}
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DisplayAlertDialogPreview() {
    MobproTheme {
        DeleteDialog(
            openDialog = true,
            onDismissRequest = {},
            onConfirmation = {}
        )
    }
}