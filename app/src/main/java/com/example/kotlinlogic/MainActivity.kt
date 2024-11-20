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
                        animalTitleLength = 10,
                        generation = "teens",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


open class HumanInfomation(var name: String, var generation: String){
    open var lovesAnimalParam = 0
        set(value){
            if (value in 0..100){
                field = value
            }
        }
    constructor(name: String,animalTitleLength: Int, generation: String) : this(name,generation){
        when(animalTitleLength){
            in 1..10 -> lovesAnimalParam = 10
            else -> lovesAnimalParam = -10
        }
    }
    open fun pluslovesAnimalParam(){
        lovesAnimalParam ++
    }
}

class MyInfomation(var myName:String,var myAnimalTitleLength:Int,var myGeneration: String) :
    HumanInfomation(name = myName,animalTitleLength = myAnimalTitleLength, generation = myGeneration){
    override fun pluslovesAnimalParam() {
        super.pluslovesAnimalParam()
        super.lovesAnimalParam +=10
    }
}


@Composable
fun Greeting(name: String, animalTitleLength: Int,generation: String, modifier: Modifier = Modifier) {
    val myHumanInfomation = MyInfomation(name,animalTitleLength,generation);
    myHumanInfomation.pluslovesAnimalParam()
    Column(){
        Text(
            text = "Hello ${myHumanInfomation.name}!", //クラス内の値をgetで表示
            modifier = modifier
        )
        Text(
            text = "lovesAnimalParam ${myHumanInfomation.lovesAnimalParam}",
            modifier = modifier
        )
        Text(
            text = "Your ${myHumanInfomation.generation}",
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
