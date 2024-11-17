package com.example.kotlinlogic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlinlogic.ui.theme.KotlinLogicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinLogicTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        animalLength = 10,
                        generation = "teens",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, animalLength: Int,generation: String, modifier: Modifier = Modifier) {
    Column(){
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Text(
            text = "Favorite AniName Length $animalLength",
            modifier = modifier
        )
        Text(
            text = "Your $generation",
            modifier = modifier
        )

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val userName : String = judgeType("man")
    val userGeneration = judgeGeneration(25)
    val favoriteAnimaltext : String? = "aa"
    /*
    * nullを明示的に否定する
    * nullの場合でも lengthは実行されるため nullpointer エラーになる
    * nullが確実に入る場合のみで動かす必要がある
    * */

    // val favoriteAnimaltextLength : Int = favoriteAnimaltext!!.length

//    val favoriteAnimaltextLength =
//        if(favoriteAnimaltext !=null )
//        {
//            favoriteAnimaltext.length
//        }else{
//            0
//        }

    val favoriteAnimaltextLength = favoriteAnimaltext?.length ?: 0


    KotlinLogicTheme {
        Greeting(userName,favoriteAnimaltextLength,userGeneration)
    }
}

fun judgeType(gender: String): String{
    var userName = ""
    when(gender){
        "woman" -> userName = "Annna"
        "man" -> userName = "Mike"
        "not" -> userName = "Pico"
        else -> userName = "notName"
    }
    return userName
}

fun judgeGeneration(old: Int): String{

    var userGeneration = when(old){
        10,11,12,13,14,15,16,17,18,19 -> "Teens"
        in 20..29 -> "twenties"
        //is Int -> userGeneration = "no teen & twebties"
        else -> "no generation"
    }

    return userGeneration;
}