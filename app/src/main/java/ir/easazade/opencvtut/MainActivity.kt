package ir.easazade.opencvtut

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import org.opencv.android.OpenCVLoader

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    if (OpenCVLoader.initDebug()) {
      Log.d("opencvLoader", "open cv is loaded successfully")
    }
  }
}
