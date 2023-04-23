package com.example.workingwithlazycolumn

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.workingwithlazycolumn.ui.theme.WorkingWithLazyColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkingWithLazyColumnTheme {
                var name by remember {
                    mutableStateOf("")
                }
                var namesList by remember {
                    mutableStateOf(listOf<String>())
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { text ->
                                name = text
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Gray,
                                unfocusedBorderColor = Color.Black,
                                cursorColor = Color.Black,
                            ),
                            placeholder = {
                                Text(
                                    text = "Enter Name",
                                    style = TextStyle(
                                        color = Color.Gray,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            },
                            textStyle = TextStyle(
                                color = Color.Black,
//                                fontFamily = FontFamily.Cursive,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold, // set the font weight to bold
                            ),
                            modifier = Modifier.weight(1f) // it means outlinedTF will fill all the space it can get
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        Button(onClick = {
                            if (name.isNotBlank()){
                                namesList = namesList + name
                                name = ""
                            }
                            else Toast.makeText(applicationContext, "Cannot Add Empty Name!", Toast.LENGTH_SHORT).show()
                        },
                            colors = ButtonDefaults.buttonColors(Color.Blue)
                            ) {
                            Text(text = "Add Name", color = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    LazyColumn(){
                        items(namesList){ currentName ->
                            Text(
                                text = currentName,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
//                                        Add onClickListener like this -
                                    .clickable {
                                        Toast.makeText(applicationContext, "Item Clicked -> $currentName", Toast.LENGTH_SHORT).show()
                                    }
                            )
                            Divider()
                        }
                    }
                }
            }
        }
    }
}

