package ir.easazade.opencvtut

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.SurfaceView
import android.widget.Toast
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame
import org.opencv.android.JavaCamera2View
import org.opencv.android.OpenCVLoader
import org.opencv.core.CvType
import org.opencv.core.Mat

class MainActivity : AppCompatActivity(), CameraBridgeViewBase.CvCameraViewListener2 {

  private var mCameraBridgeViewBase: CameraBridgeViewBase? = null
  private var mat1: Mat? = null
  private var mat2: Mat? = null
  private var mat3: Mat? = null

  override fun onCameraViewStarted(width: Int, height: Int) {
    mat1 = Mat(width, height, CvType.CV_8UC4)
    mat2 = Mat(width, height, CvType.CV_8UC4)
    mat3 = Mat(width, height, CvType.CV_8UC4)
  }

  override fun onCameraViewStopped() {
    mat1!!.release()
    mat2!!.release()
    mat3!!.release()
  }

  override fun onCameraFrame(inputFrame: CvCameraViewFrame?): Mat? {
//    if (mat1 == null || mat2 == null || mat3 == null) return null
    mat1 = inputFrame!!.rgba()
    return mat1!!
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    mCameraBridgeViewBase = findViewById<JavaCamera2View>(R.id.cameraView)
    mCameraBridgeViewBase!!.enableView()
    mCameraBridgeViewBase!!.visibility = SurfaceView.VISIBLE
    mCameraBridgeViewBase!!.setCvCameraViewListener(this)
  }

  override fun onResume() {
    super.onResume()
    if (!OpenCVLoader.initDebug()) {
      Toast.makeText(this, "camera view is not initialized", Toast.LENGTH_SHORT).show()
    }
  }

  override fun onPause() {
    super.onPause()
    mCameraBridgeViewBase!!.disableView()
  }

  override fun onDestroy() {
    super.onDestroy()
    mCameraBridgeViewBase!!.disableView()
  }
}
