package com.example.composeprofile.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.composeprofile.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    var email by remember { mutableStateOf("sdxky0@gmail.com") }
    var phone by remember { mutableStateOf("996704040121") }
    var isDialogVisible by remember { mutableStateOf(false) }
    var dialogType by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Gray,
            ),
            title = {
                Text(
                    text = "Profile",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }
        )

        Spacer(
            modifier = Modifier
                .height(40.dp)
        )

        ProfileImg()

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Text(
            text = "user",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        Spacer(
            modifier = Modifier
                .height(40.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            horizontalArrangement = Arrangement.Start
        ) {

            Text(
                text = "\t•\t\uD83D\uDCE7 Email:",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        dialogType = "email"
                        isDialogVisible = true
                    },
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        dialogType = "email"
                        isDialogVisible = true
                    },
                text = email,
                fontSize = 20.sp
            )


        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 20.dp),
            horizontalArrangement = Arrangement.Start
        ) {

            Text(
                text = "\t•\t\uD83D\uDCDE Телефон:",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        dialogType = "phone"
                        isDialogVisible = true
                    },
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        dialogType = "phone"
                        isDialogVisible = true
                    },
                text = phone,
                fontSize = 20.sp
            )


            if (isDialogVisible) {
                when (dialogType) {
                    "email" -> EditTextDialog(
                        initialText = email,
                        onDismiss = { isDialogVisible = false },
                        onSave = { newText ->
                            email = newText
                            isDialogVisible = false

                        }
                    )

                    "phone" -> EditTextDialog(
                        initialText = phone,
                        onDismiss = { isDialogVisible = false },
                        onSave = { newText ->
                            phone = newText
                            isDialogVisible = false
                        }
                    )
                }
            }
        }
    }

}


@Composable
fun EditTextDialog(
    initialText: String,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit,
) {
    var inputText by remember { mutableStateOf(initialText) }

    Dialog(onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "Edit",
                    fontSize = 20.sp
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = inputText,
                    onValueChange = { inputText = it },
                    singleLine = false,
                    placeholder = { Text(text = "Enter text here...") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = { onSave(inputText) }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

@Composable
fun CustomText(text: String, onClick: () -> Unit) {
    Text(
        modifier = Modifier
            .padding(end = 8.dp)
            .clickable { onClick },
        text = text,
        fontSize = 20.sp
    )
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileImg() {
    GlideImage(
        modifier = Modifier
            .border(1.dp, Color.Black, CircleShape)
            .clip(CircleShape)
            .size(200.dp),
        contentDescription = "",
        model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRk-EGYxve_nxW1Xa-0HbjlPngWNuNZluR4g&s"
    )
}


@Preview(showBackground = true)
@Composable
fun ProfileScreen_preview() {
    ProfileScreen()
}
