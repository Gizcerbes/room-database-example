package com.example.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.di.ModuleViewModel

object Screen {

    @Composable
    operator fun invoke(
        vm: ListViewModel = viewModel { ModuleViewModel.ListViewModel }
    ) {
        val ilist by vm.list.collectAsState()

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier.fillMaxSize()
        ) {

            item {

                var key by remember { mutableStateOf("") }
                var value by remember { mutableStateOf("") }

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.padding(13.dp)
                ) {
                    TextField(
                        value = key,
                        onValueChange = { key = it },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.size(3.dp))
                    TextField(
                        value = value,
                        onValueChange = { value = it },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.size(3.dp))
                    Button(
                        onClick = { vm.save(key, value) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Save")
                    }
                }

            }

            items(ilist.size) {
                val item = ilist[it]
                Card {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(13.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("POSITION: $it")
                            Text("KEY: ${item.key}")
                            Text("VALUE: ${item.value}")
                        }
                        Spacer(modifier = Modifier.size(3.dp))
                        Button(onClick = { vm.delete(item.key) }) {
                            Text("DELETE")
                        }
                    }
                }


            }

        }
    }
}