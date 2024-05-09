package com.mobilegame.spaceshooter.presentation.theme

import androidx.compose.ui.graphics.Color
import com.mobilegame.spaceshooter.utils.extensions.alpha

inline class MyColor(val color: Color){
    companion object {
        val white = Color(0xFFFFFFFF)
        val whiteDark1 = Color(0xFFEEEEEE)
        val whiteDark2 = Color(0xFFDDDDDD)
        val whiteDark3 = Color(0xFFCCCCCC)
        val whiteDark4 = Color(0xFFBBBBBB)
        val whiteDark5 = Color(0xFFAAAAAA)
        val whiteDark6 = Color(0xFF999999)
        val whiteDark6Plus = Color(0xFF959595)
        val whiteDark7 = Color(0xFF888888)
        val whiteDark8 = Color(0xFF777777)
        val whiteDark9 = Color(0xFF666666)

        val gray = Color(0xFF888888)
        val grayDark1_0 = Color(0xFF787878)
        val grayDark1_1 = Color(0xFF767676)
        val grayDark1_2 = Color(0xFF747474)
        val grayDark1_3 = Color(0xFF727272)
        val grayDark1_4 = Color(0xFF707070)
        val grayDark2 = Color(0xFF686868)
        val grayDark3 = Color(0xFF585858)
        val grayDark4 = Color(0xFF484848)
        val grayDark4Plus = Color(0xFF434343)
        val grayDark5 = Color(0xFF383838)
        val grayDark6 = Color(0xFF343434)
        val grayDark7 = Color(0xFF282828)
        val grayDark8 = Color(0xFF191919)
        val grayDark9 = Color(0xFF101010)

        val green = Color(0xFF00FF00)
        val greendark1 = Color(0xFF00DF00)
        val greendark2 = Color(0xFF00CF00)
        val greendark3 = Color(0xFF00BF00)
        val greendark4 = Color(0xFF00AF00)
        val greendark5 = Color(0xFF009F00)
        val greendark6 = Color(0xFF008F00)
        val greendark7 = Color(0xFF007F00)
        val greendark8 = Color(0xFF006F00)
        val greendark9 = Color(0xFF005F00)
        val greendark10 = Color(0xFF004F00)
        val greendark11 = Color(0xFF003F00)
        val greendark12 = Color(0xFF002F00)

        val blue = Color(0xFF0000FF)
        val blueDark1 = Color(0xFF0000EF)
        val blueDark2 = Color(0xFF0000DF)
        val blueDark3 = Color(0xFF0000CF)
        val blueDark4 = Color(0xFF0000BF)
        val blueDark5 = Color(0xFF0000AF)
        val blueDark6 = Color(0xFF00009F)
        val blueDark7 = Color(0xFF000080)
        val blueDark8 = Color(0xFF00007F)
        val blueDark9 = Color(0xFF00008F)
        val blueDark10 = Color(0xFF00007F)
        val blueDark11 = Color(0xFF00006F)
        val blueDark12 = Color(0xFF00005F)

        val yellow = Color(0xFFFFFF00)
        val yellowDark1 = Color(0xFFDFDF00)
        val yellowDark2 = Color(0xFFCFCF00)
        val yellowDark3 = Color(0xFFBFBF00)
        val yellowDark4 = Color(0xFFAFAF00)
        val yellowDark5 = Color(0xFF9F9F00)
        val yellowDark6 = Color(0xFF8F8F00)
        val yellowDark7 = Color(0xFF7F7F00)
        val yellowDark8 = Color(0xFF6F6F00)
        val yellowDark9 = Color(0xFF5F5F00)
        val yellowDark10 = Color(0xFF4F4F00)
        val yellowDark11 = Color(0xFF3F3F00)
        val yellowDark12 = Color(0xFF2F2F00)

        val red= Color(0xFFFF0000)
        val redDark0= Color(0xFFFF0000)
        val redDark1= Color(0xFFDF0000)
        val redDark2= Color(0xFFCF0000)
        val redDark3= Color(0xFFBF0000)
        val redDark4= Color(0xFFAF0000)
        val redDark5= Color(0xFF9F0000)
        val redDark6= Color(0xFF8F0000)
        val redDark7= Color(0xFF7F0000)
        val redDark8= Color(0xFF6F0000)
        val redDark9= Color(0xFF5F0000)
        val redDark10= Color(0xFF5F0000)
        val redDark11= Color(0xFF4F0000)
        val redDark12= Color(0xFF3F0000)

        val greenVariant = Color(0xFF03DAC6)
        val greenSecondVariant = Color (0XFF018786)
        val greenSecondVariant1 = Color (0XEE018786)
        val greenSecondVariant2 = Color (0XDD018786)
        val greenSecondVariant3 = Color (0XCC018786)
        val greenSecondVariant4 = Color (0XBC018786)
        val greenSecondVariant5 = Color (0XAC018786)
        val greenSecondVariant6 = Color (0X9C018786)
        val greenSecondVariant7 = Color (0X8C018786)
        val greenSecondVariant8 = Color (0X7C018786)

        val Platinium = Color(0XFFEAE1DF)
        val GreenXanadou = Color(0XFF667761)
        val Redwood = Color(0XFF8D5B4C)
        val BlastOffBronze = Color(0xFF9f6856)
        val Bole = Color(0xFF855747)
        val Liver = Color(0xFF6A4539)
        val GoldMetallic = Color(0XFFDDB967)
        val AeroBlue = Color(0XFFB2FAEB)
        val quarterdeck = Color(0XFF1171A3)
        val meatBrown = Color(0XFFE8BA3B)
        val superStar = Color (0XFFFFCA0E)
        val coralGold = Color(0XFFd37c57)
        val redDamask = Color(0XFFd06d45)

        val transparent = Color.Transparent
//        val applicationBackground = Redwood
        val applicationBackground = Color(0XFFd06d45)
        val applicationBackgroundLight = BlastOffBronze
        val applicationText = Platinium
        val applicationContrast = Platinium
        val applicationContrastTransparent = Platinium.alpha(0.75F)
        val applicationBackgroundBannerInitial = Platinium.alpha(0.02F)
        val applicationBackgroundBannerTarget = Platinium.alpha(0.13F)
        val defaultShip = Bole
        val squareShip = quarterdeck
//        val roundShip = meatBrown
        val roundShip = applicationBackground
    }
}
