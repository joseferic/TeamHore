package com.bangkitsubmission.pastiin_ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bangkitsubmission.pastiin_ui.databinding.ActivitySetImageBinding
import com.bangkitsubmission.pastiin_ui.ml.ModelFruit11
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class SetImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetImageBinding

    lateinit var bitmap: Bitmap

    companion object {
        private const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA = 2
        private const val CODE_CAMERA = 42
        private const val GALERY = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_image)

        binding = ActivitySetImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.setClipToOutline(true)
        binding.after.visibility = View.INVISIBLE
        binding.before.visibility = View.VISIBLE

        val fileName = "label_per_id_fruit.txt"
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

        binding.btnGallery.setOnClickListener {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"

            startActivityForResult(intent,100)
        }
        binding.btnback.setOnClickListener {
            binding.after.visibility = View.INVISIBLE
            binding.before.visibility = View.VISIBLE
        }
        binding.btnpredict.setOnClickListener {
            var resized: Bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true)
            //val model = MobilenetV110224Quant.newInstance(this)
            var model =  ModelFruit11.newInstance(this)

            // Creates inputs for reference.
            var inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 100, 100, 3), DataType.FLOAT32)

            var tensorImage = TensorImage(DataType.FLOAT32)
            tensorImage.load(resized)

            var byteBuffer = tensorImage.buffer

            inputFeature0.loadBuffer(byteBuffer)

            // Runs model inference and gets result.
            var outputs = model.process(inputFeature0)
            var outputFeature0 = outputs.outputFeature0AsTensorBuffer

            var max = getMaxIndex(outputFeature0.floatArray)

            binding.textView.setText(listFromLabel[max])// ini jadiin kirim ke result sebelah

            var intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("id",listFromLabel[max])
            // Releases model resources if no longer used.
            model.close()
            startActivity(intent)
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
                binding.imageView.setImageBitmap(image)

            }
        }

//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
//            val result: CropImage.ActivityResult? = CropImage.getActivityResult(data)
//            if (resultCode == RESULT_OK) {
//                val cropUri = result?.uriContent
//                bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, cropUri)
//                binding.imageView.setImageURI(cropUri)
//            } else {
//                Log.d("image", "error")
//            }
//        }

        if (requestCode == GALERY){
            binding.imageView.setImageURI(data?.data)
            var uri = data?.data
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,uri)
        }
        binding.imageView.setClipToOutline(true)
        binding.before.visibility = View.INVISIBLE
        binding.after.visibility = View.VISIBLE
    }

    fun getMaxIndex(arr: FloatArray): Int {
        var ind = 0
        var min = 0.0f

        for (i in 0 until arr.count() - 1 ) {
            if (arr[i] > min) {
                ind = i
                min = arr[i]
            }
        }
        return ind
    }

//    private fun startCropActivity() {
//        CropImage.activity().setAspectRatio(8, 8).setGuidelines(CropImageView.Guidelines.ON)
//            .start(this)
//    }
}