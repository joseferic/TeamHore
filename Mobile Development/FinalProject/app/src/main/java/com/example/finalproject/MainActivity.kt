package com.example.finalproject


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageView
import com.example.finalproject.databinding.ActivityMainBinding
import com.example.finalproject.ml.*
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var bitmap: Bitmap

    companion object {
        private const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA = 2
        private const val CODE_CAMERA = 42
        private const val GALERY = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fileName = "labelmap.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use { it.readText() }
        var listFromLabel = inputString.split("\n")

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CODE_CAMERA
            )
        }

        binding.btnCamera.setOnClickListener {
            //startCropActivity()
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA)
                Log.d("Main Activity", "Success to open camera")
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE
                )
            }
        }

        binding.btnPredict.setOnClickListener {
            var resized: Bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true)
            //val model = MobilenetV110224Quant.newInstance(this)
            var model =  ModelFruit9.newInstance(this)

            // Creates inputs for reference.
            var inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 100, 100, 3), DataType.FLOAT32)

//            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)

            //var inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
//            var tbuffer = TensorImage.fromBitmap(resized)
            var tensorImage = TensorImage(DataType.FLOAT32)
            tensorImage.load(resized)

            var byteBuffer = tensorImage.buffer

            inputFeature0.loadBuffer(byteBuffer)

            // Runs model inference and gets result.
            var outputs = model.process(inputFeature0)
            var outputFeature0 = outputs.outputFeature0AsTensorBuffer

            var max = getMaxIndex(outputFeature0.floatArray)

            binding.resultText.setText(listFromLabel[max])

            // Releases model resources if no longer used.
            model.close()
        }

        binding.btnGallery.setOnClickListener {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"

            startActivityForResult(intent,100)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA)
            } else {
                Toast.makeText(this, "You just denied permission for camera", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA) {
                val image: Bitmap = data!!.extras!!.get("data") as Bitmap
                bitmap = image.copy(Bitmap.Config.ARGB_8888, true)
                //var uri:Uri? = data?.data
                //bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,uri)
                binding.showImage.setImageBitmap(image)
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            val result: CropImage.ActivityResult? = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                val cropUri = result?.uriContent
                bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, cropUri)
                binding.showImage.setImageURI(cropUri)
            } else {
                Log.d("image", "error")
            }
        }
        if (requestCode == GALERY){
            binding.showImage.setImageURI(data?.data)
            var uri = data?.data
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,uri)
        }
    }

    fun getMaxIndex(arr: FloatArray): Int {
        var ind = 0
        var min = 0.0f

        for (i in 0 until arr.count() - 1) {
            if (arr[i] > min) {
                ind = i
                min = arr[i]
            }
        }
        return ind
    }

    private fun startCropActivity() {
        CropImage.activity().setAspectRatio(8, 8).setGuidelines(CropImageView.Guidelines.ON)
            .start(this)
    }

}