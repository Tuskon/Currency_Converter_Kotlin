package com.example.currencyconverter.ui.components.modalFailRequest

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.RotateLeft
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ModalFailRequestCurrency(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {

    Dialog (
        onDismissRequest = {},
    ) {

        Column(
            Modifier
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(height = 240.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
                ){
                Icon(
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(22.dp)
                        .clickable(enabled = true, onClick = {onDismiss()}),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.Black
                )
            }
            Icon(
                modifier = Modifier.size(62.dp),
                imageVector = Icons.Default.ErrorOutline,
                contentDescription = "Error Outline",
                tint = Color.Red
            )
            Text(
                modifier = Modifier
                    .padding(top = 25.dp),
                text = "Failed to retrieve conversion value",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )

            Text(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = "Try again ?",
                fontSize = 16.sp,
                fontWeight = FontWeight.W300,
                textAlign = TextAlign.Center,
                color = Color.Black
            )

            Box(
                modifier = Modifier
                    .padding(top = 25.dp)
                    .width(200.dp)
                    .height(40.dp)
                    .background(color = Color.Red, shape = RoundedCornerShape(5.dp))
                    .clickable(enabled = true, onClick = { onConfirm() })
                    .border(
                        width = 4.dp,
                        color = Color.Red,
                        shape = RoundedCornerShape(5.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(26.dp),
                    imageVector = Icons.Default.RotateLeft,
                    contentDescription = "Rotate Left",
                    tint = Color.White
                )
            }
        }

    }



}
