# Reto-Kotlin
Reto Kotlin - Construye tu primera App Android en Kotlin https://devexperto.com/training-gratuito/

## Vídeo 1 

### Contenido

- Convertir un clase Java a Kotin :

code / Convert Java file for Kotlin file

- Configurar proyecto para que trabaje con Kotlin :

Tools / Kotlin / Configure Kotlin in Project

- Kotlin compiler and runtime versión

Seleccionar Kotlin 1.1.1

Sincronizar proyecto

```
apply plugin: 'kotlin-android'

```

```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.android.support:appcompat-v7:25.1.0'
    testCompile 'junit:junit:4.12'
    compile "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
}
```

Agregar extensions 

```
apply plugin: 'kotlin-android-extensions'
```

```
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion 25
    buildToolsVersion "25.0.2"
    defaultConfig {
        applicationId "com.emedinaa.retokotlin"
        minSdkVersion 15
        targetSdkVersion 25
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.android.support:appcompat-v7:25.1.0'
    testCompile 'junit:junit:4.12'
    compile "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
}
repositories {
    mavenCentral()
}

```
### Ejemplo 

XML

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.emedinaa.retokotlin.MainActivity">

        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginLeft="@dimen/margin20"
            android:layout_marginRight="@dimen/margin20"
            android:layout_centerHorizontal="true"
            android:layout_marginTop="@dimen/margin20"
            android:hint="@string/hint_name"
            android:id="@+id/editTextName"/>

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_below="@+id/editTextName"
            android:layout_centerHorizontal="true"
            android:layout_marginTop="@dimen/margin10"
            android:text="@string/show"
            android:id="@+id/buttonShow"/>
</RelativeLayout>
```

```
package com.emedinaa.retokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ui()
    }

    fun ui(){
        buttonShow.setOnClickListener {
            val text= editTextName.text
            Toast.makeText(this,text,Toast.LENGTH_LONG).show()
        }
    }
}

```


### Referencias 


