package com.seniorpro.thailang

import android.media.AudioFormat
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import android.os.CountDownTimer
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_prac_group_word.*
import android.media.AudioRecord
import cafe.adriel.androidaudioconverter.AndroidAudioConverter;
import cafe.adriel.androidaudioconverter.callback.IConvertCallback;
import cafe.adriel.androidaudioconverter.callback.ILoadCallback

//import sun.font.LayoutPathImpl.getPath


//import cafe.adriel.androidaudioconverter.model.AudioFormat;


val SAMPLE_RATE = 44100



private var outputFile: String? = null

class prac_group_word : AppCompatActivity() {
    var master: MediaPlayer? = null
    var AudioSavePathInDevice: String? = null
    var AudioSavePathInDevice_mp3: String? = null
    var mediaRecorder: MediaRecorder? = null
    var mediaPlayer: MediaPlayer? = null
    var words: String = ""
    //var mediaRecorder: AudioRecord? = null
    //var mContext: Context = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prac_group_word)
        var word: String
        var thaiWord: String
        var idWord: String
        val extras = intent.extras
        word = extras.getString("word")
        thaiWord = extras.getString("thaiWord")
        idWord = extras.getString("idWord")

        AndroidAudioConverter.load(this, object : ILoadCallback {
            override fun onSuccess() {
                // Great!
                Log.d("onSuccess","SUCCESS: ")
            }

            override fun onFailure(error: Exception) {
                // FFmpeg is not supported by device
                Log.d("onFailure1111","ERROR: " + error.message)
                error.printStackTrace()
            }
        })

        words = idWord + "_male"
        thai.setText(thaiWord)
        eng.setText(word)

        Log.d("word",words)

        webView_1.settings.javaScriptEnabled = true
        webView_1.loadUrl("http://ec2-35-163-238-165.us-west-2.compute.amazonaws.com/simple1.php?w="+words)

        AudioSavePathInDevice = Environment.getExternalStorageDirectory().absolutePath + "/recording.wav"
        AudioSavePathInDevice_mp3 = Environment.getExternalStorageDirectory().absolutePath + "/recording.wav"
        Log.d("information", AudioSavePathInDevice.toString())

        //val res: Resources = mContext.resources
        val soundId = resources.getIdentifier(words, "raw", this.packageName)
        Log.d("soundId",soundId.toString())

        master =  MediaPlayer.create(this,soundId)

        playBtn.setOnClickListener {
            master!!.start()
        }

        recordBtn.setOnClickListener{
            RecordMethod()
        }
/*        buttonPlayLastRecordAudio_1.setOnClickListener {

            mediaPlayer = MediaPlayer()
            try {
                mediaPlayer?.setDataSource(AudioSavePathInDevice)
                mediaPlayer?.prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }


            mediaPlayer?.start()
            Toast.makeText(this, "Recording Playing",
                    Toast.LENGTH_LONG).show()

        }*/
    }

    fun convertAudio() {
        /**
         * Update with a valid audio file!
         * Supported formats: [AndroidAudioConverter.AudioFormat]
         *
         */


        val wavFile = File(AudioSavePathInDevice)
        val callback = object : IConvertCallback {
            override fun onSuccess(convertedFile: File) {
                //Toast.makeText(this@prac_group_word, "SUCCESS: " + convertedFile.path, Toast.LENGTH_LONG).show()
                Log.d("onSuccess","SUCCESS: " + convertedFile.path)
            }

            override fun onFailure(error: Exception) {
                //Toast.makeText(this@prac_group_word, "ERROR: " + error.message, Toast.LENGTH_LONG).show()
                Log.d("onFailure","ERROR: " + error.message)
            }
        }
        //Toast.makeText(this, "Converting audio file...", Toast.LENGTH_SHORT).show()
        AndroidAudioConverter.with(this)
                .setFile(wavFile)
                .setFormat(cafe.adriel.androidaudioconverter.model.AudioFormat.WAV)
                .setCallback(callback)
                .convert()

    }

    fun RecordMethod(){
        MediaRecorderReady()

        try {
            //mediaRecorder?.startRecording();
            mediaRecorder?.prepare()
            mediaRecorder?.start()
        } catch (e: IllegalStateException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        recordBtn.setEnabled(false)

        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                percen.setText("seconds remaining: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                percen.setText("done!")
                mediaRecorder?.stop()
                //mediaRecorder?.release()
                Log.d("3min","Stop Recording")
                recordBtn.setEnabled(true)

                AsyncTaskExample().execute()
            }
        }.start()
    }


    fun MediaRecorderReady() {
        mediaRecorder = MediaRecorder()
        val bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE.toInt(),
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT)

       /* mediaRecorder = AudioRecord(MediaRecorder.AudioSource.DEFAULT,
                44100,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufferSize)*/

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder?.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB )
//        mediaRecorder?.setAudioEncodingBitRate(16)
//        mediaRecorder?.setAudioSamplingRate(44100)
//        mediaRecorder?.setAudioChannels(2)
        mediaRecorder?.setOutputFile(AudioSavePathInDevice)
    }
    inner class AsyncTaskExample: AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            convertAudio()
            Log.d("onPreExecute","Waiting...")
        }

        override fun doInBackground(vararg p0: String?): String {

            var Result: String = "";
            var Result2 = ""
            var re = ""
            Result = doFileUpload()
            Result2 = praat()


            return Result2

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.d("onPostExecute","Send data to server...")

            if (result == "") {
                resultText.text = "Network Error";
            }
            else if(result == "-1"){
                percen.text = "Too short!"
                Toast.makeText(this@prac_group_word,"Please make long voice.",Toast.LENGTH_LONG).show()
            }
            else {
                percen.text = result
                webView_1.loadUrl("http://ec2-35-163-238-165.us-west-2.compute.amazonaws.com/simple_praat.php?w="+words)
            }
        }
    }

    fun ConvertToString(inStream: InputStream): String {

        var Result: String = ""
        val isReader = InputStreamReader(inStream)
        var bReader = BufferedReader(isReader)
        var temp_str: String?

        try {

            while (true) {
                temp_str = bReader.readLine()
                if (temp_str == null) { break }
                Result += temp_str;
            }
        } catch(Ex: Exception) {
            Log.e("main2", "Error in ConvertToString " + Ex.printStackTrace())
        }
        return Result
    }


    //Upload File to server

    private fun doFileUpload() : String {

        var conn: HttpURLConnection? = null
        var dos: DataOutputStream? = null
        var inStream: DataInputStream? = null
        val existingFileName = AudioSavePathInDevice_mp3
        val lineEnd = "\r\n"
        val twoHyphens = "--"
        val boundary = "*****"
        var bytesRead: Int
        var bytesAvailable: Int
        var bufferSize: Int
        val buffer: ByteArray
        val maxBufferSize = 1 * 1024 * 1024
        val responseFromServer = ""
        val urlString = "http://ec2-35-163-238-165.us-west-2.compute.amazonaws.com/uploads.php"

        try {

            //------------------ CLIENT REQUEST
            val fileInputStream = FileInputStream(File(existingFileName))
            // open a URL connection to the Servlet
            val url = URL(urlString)
            // Open a HTTP connection to the URL
            conn = url.openConnection() as HttpURLConnection
            // Allow Inputs
            conn.doInput = true
            // Allow Outputs
            conn.doOutput = true
            // Don't use a cached copy.
            conn.useCaches = false
            // Use a post method.
            conn.requestMethod = "POST"
            conn.setRequestProperty("Connection", "Keep-Alive")
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary)
            conn.setRequestProperty("uploaded_file", "recording.wav");

            dos = DataOutputStream(conn.outputStream)
            dos.writeBytes(twoHyphens + boundary + lineEnd)
            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"$existingFileName\"$lineEnd")
            dos.writeBytes(lineEnd)
            // create a buffer of maximum size
            bytesAvailable = fileInputStream.available()
            bufferSize = Math.min(bytesAvailable, maxBufferSize)
            buffer = ByteArray(bufferSize)
            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize)

            while (bytesRead > 0) {

                dos.write(buffer, 0, bufferSize)
                bytesAvailable = fileInputStream.available()
                bufferSize = Math.min(bytesAvailable, maxBufferSize)
                bytesRead = fileInputStream.read(buffer, 0, bufferSize)

            }

            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd)
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd)
            // close streams
            Log.e("Debug", "File is written")
            fileInputStream.close()
            dos.flush()
            dos.close()

        } catch (ex: MalformedURLException) {
            Log.e("Debug", "error: " + ex.message, ex)
        } catch (ioe: IOException) {
            Log.e("Debug", "error: " + ioe.message, ioe)
        }
        var str: String = ""
        //------------------ read the SERVER RESPONSE
        try {

            inStream = DataInputStream(conn!!.inputStream)
            str = inStream.readLine()




            inStream.close()

        } catch (ioex: IOException) {
            Log.e("Debug", "error: " + ioex.message, ioex)
        }
        return str
    }

    private fun praat() : String{

        val API_URL = "http://ec2-35-163-238-165.us-west-2.compute.amazonaws.com/normalize_praat.php?w="+words
        var Result = ""
        try {

            val URL = URL(API_URL)
            val connect = URL.openConnection() as HttpURLConnection

            connect.readTimeout = 8000
            connect.connectTimeout = 8000
            connect.requestMethod = "GET"
            connect.doOutput = true;
            connect.doInput = true
            connect.connect();

            val ResponseCode: Int = connect.responseCode;
            Log.d("main2", "ResponseCode" + ResponseCode)

            if (ResponseCode == 200) {
                val tempStream: InputStream = connect.inputStream;
                if (tempStream != null) {
                    Result = ConvertToString(tempStream);
                }
            }
        } catch(Ex: Exception) {
            Log.d("", "Error in doInBackground " + Ex.message);
        }
        return Result
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return true
    }

}

