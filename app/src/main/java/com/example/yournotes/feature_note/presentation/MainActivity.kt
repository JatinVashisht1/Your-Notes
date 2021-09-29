package com.example.yournotes.feature_note.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yournotes.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.example.yournotes.feature_note.presentation.notes.NotesScreen
import com.example.yournotes.feature_note.presentation.util.Screen
import com.example.yournotes.ui.theme.YourNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
//@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("HomeScreen", "Entered OnCreate")
        super.onCreate(savedInstanceState)
        setContent {
            YourNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Log.d("HomeScreen", "Entered MainActivity")
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.NoteScreen.route ){

                            composable(route = Screen.NoteScreen.route) {
                                NotesScreen(navController = navController)
                            }

                            composable(route = Screen.AddEditNoteScreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}",
                                arguments = listOf(
                                    navArgument(name = "noteId") {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    },

                                    navArgument(name = "noteColor") {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    }
                                )
                            ) {
                                val color = it.arguments?.getInt("noteColor") ?: -1
                                AddEditNoteScreen(navController = navController, noteColor = color)
                            }
                        }
                    }
                }
            }
        }
    }


