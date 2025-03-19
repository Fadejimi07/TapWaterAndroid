package com.example.trappingrainwater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trappingrainwater.logic.RainWaterLogic.initTrap
import com.example.trappingrainwater.logic.RainWaterLogic.trap
import com.example.trappingrainwater.ui.theme.TrappingRainWaterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrappingRainWaterTheme {
                var tapHeightsList = remember {
                    initTrap(10).toList().toMutableStateList()
                }

                Tap(tapHeightsList) {
                    val newList = trap(tapHeightsList.toTypedArray())
                    tapHeightsList.apply {
                        tapHeightsList.clear()
                        addAll(newList)
                    }
                }
            }
        }
    }

    @Composable
    fun WaterHeight(heightArr: IntArray, onClick: () -> Unit) {
        val blackBoxHeight = heightArr[0]
        val blueBoxHeight = heightArr[1]

        Column {
            for (i in 1..blueBoxHeight) {
                CreateBox(Color.Blue, onClick = onClick)
            }

            for (i in 1..blackBoxHeight) {
                CreateBox(Color.Black, onClick = onClick)
            }
        }
    }

    @Composable
    fun CreateBox(color: Color, onClick: () -> Unit) {
        Box(
            modifier = Modifier.height(20.dp)
                .width(20.dp)
                .background(color = color)
                .clickable { onClick() },
        )
    }

    @Composable
    fun Tap(tapHeights: List<IntArray>, onClick: () -> Unit) {
        LazyRow {
            items(
                tapHeights
            ) { heights ->
                WaterHeight(heights, onClick = onClick)
            }
        }
    }
}
