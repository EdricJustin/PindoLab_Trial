package com.pmberjaya.tvadsmanager.util

import android.app.Activity
import android.app.ActivityManager
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Environment
import android.text.format.DateUtils
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.pmberjaya.tvadsmanager.R
import com.pmberjaya.tvadsmanager.cache.sharedpref.PreferencesHelper
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*


object Utility {
    fun convertDPtoPX(dip: Float, context: Context): Float {
        val r: Resources = context!!.resources
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dip,
            r.getDisplayMetrics()
        )
        return px
    }

    fun checkIfStringIsNotNullOrEmpty(text: String?): Boolean {
        return text != null && text.trim { it <= ' ' }.isNotEmpty()
    }

    fun initToolbar(
        activity: AppCompatActivity,
        toolbar: View?,
        title: String?,
        onBackPressedListener: View.OnClickListener?
    ) {
        if (toolbar != null) {
            activity.setSupportActionBar(toolbar as Toolbar)
            if (onBackPressedListener == null) {
                toolbar.setNavigationOnClickListener { activity.onBackPressed() }
            } else {
                toolbar.setNavigationOnClickListener(onBackPressedListener)
            }
        }
        val actionBar = activity.supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = title
        }
    }

    fun showSimpleAlertDialog(
        context: Context,
        title: String?,
        msg: String?,
        positiveMessage: String?,
        positiveListener: DialogInterface.OnClickListener?,
        negativeMessage: String?,
        negativeListener: DialogInterface.OnClickListener?,
        isCancelable: Boolean
    ) {
        val dialog: android.app.AlertDialog
        val builder = android.app.AlertDialog.Builder(context)
        if (title != null) {
            builder.setTitle(title)
        }
        builder.setMessage(msg)
            .setCancelable(false)
            .setIcon(R.mipmap.ic_launcher)
        if (positiveMessage != null && positiveListener != null) {
            builder.setPositiveButton(positiveMessage, positiveListener)
        } else if (positiveMessage != null) {
            builder.setPositiveButton(positiveMessage) { dialog, _ -> dialog.dismiss() }
        }
        if (negativeMessage != null && negativeListener != null) {
            builder.setNegativeButton(negativeMessage, negativeListener)
        } else if (negativeMessage != null) {
            builder.setNegativeButton(negativeMessage) { dialog, _ -> dialog.dismiss() }
        }
        dialog = builder.create()
        dialog.setCancelable(isCancelable)
        dialog.setCanceledOnTouchOutside(isCancelable)
        if (!(context as Activity).isFinishing) {
            dialog.show()
        }

    }

    fun showSingleChoiceAlertDialog(
        mContext: Context?,
        title: String?,
        choiceList: Array<String?>,
        choiceListener: DialogInterface.OnClickListener,
        isCancelable: Boolean,
        checkedItem: Int
    ) {
        val dialog: AlertDialog
        val builder = AlertDialog.Builder(mContext!!)
        if (title != null) {
            builder.setTitle(title)
        }
        builder.setSingleChoiceItems(choiceList, checkedItem, choiceListener)
        dialog = builder.create()
        dialog.setCancelable(isCancelable)
        dialog.setCanceledOnTouchOutside(isCancelable)
        if (!(mContext as Activity).isFinishing) {
            dialog.show()
        }

    }

    fun showSingleChoiceAlertDialog(
        mContext: Context?,
        title: String?,
        choiceList: ArrayList<String>,
        choiceListener: DialogInterface.OnClickListener,
        isCancelable: Boolean,
        checkedItem: Int
    ) {
        val dialog: AlertDialog
        val builder = AlertDialog.Builder(mContext!!)
        if (title != null) {
            builder.setTitle(title)
        }
        builder.setSingleChoiceItems(choiceList.toTypedArray(), checkedItem, choiceListener)
        dialog = builder.create()
        dialog.setCancelable(isCancelable)
        dialog.setCanceledOnTouchOutside(isCancelable)
        if (!(mContext as Activity).isFinishing) {
            dialog.show()
        }

    }

    @Throws(Resources.NotFoundException::class)
    fun getColor(paramResources: Resources, paramInt: Int, paramTheme: Resources.Theme?): Int {
        return if (Build.VERSION.SDK_INT >= 23) {
            paramResources.getColor(paramInt, paramTheme)
        } else paramResources.getColor(paramInt)
    }

    private var invalidApiKeyDialogCount = 0
    fun showInvalidApiKeyAlert(activity: Activity) {
        if (invalidApiKeyDialogCount == 0) {
            invalidApiKeyDialogCount = 1
            val pref = PreferencesHelper(activity)
            pref.signOut()
            val dialog: android.app.AlertDialog
            val builder = android.app.AlertDialog.Builder(activity)
            builder.setTitle(activity.resources.getString(R.string.alert))
                .setMessage(activity.resources.getString(R.string.unauthorized))
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("OK") { dialogInterface, _ ->
//                    val intent = Intent(activity, SplashScreen::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    activity.startActivity(intent)
//                    activity.finish()
//                    dialogInterface.dismiss()
//                    invalidApiKeyDialogCount = 0
                }
            dialog = builder.create()
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }
    }

    fun convertPrice(paramString: String?): String {
        try {
            val localBigDecimal = BigDecimal(paramString)
            val localDecimalFormatSymbols = DecimalFormatSymbols.getInstance()
            localDecimalFormatSymbols.groupingSeparator = '.'
            val dfnd = DecimalFormat("###,###.##", localDecimalFormatSymbols).format(
                localBigDecimal.toLong()
            )
            val currency = "Rp. "
            return currency + dfnd
        } catch (localException: Exception) {
            localException.printStackTrace()
        }
        return "Rp. 0"
    }

    fun convertPriceFromDouble(paramString: Double): String {
        try {
            val localBigDecimal = BigDecimal(paramString)
            val otherSymbols = DecimalFormatSymbols(Locale.GERMAN)
            otherSymbols.decimalSeparator = ','
            otherSymbols.groupingSeparator = '.'
            val dfnd = DecimalFormat("###,###.##", otherSymbols).format(localBigDecimal.toLong())
            val currency = "Rp. "
            return currency + dfnd
        } catch (localException: Exception) {
            localException.printStackTrace()
        }

        return "Rp. 0"
    }

    fun loadImageUrl(url: String?, iv: ImageView) {
        if (url != null && url != "") {
            Picasso.get().load(url).error(R.drawable.ic_no_images).into(iv)
        } else {
            iv.setImageResource(R.drawable.ic_no_images)
        }
    }

    fun loadProfilePhotoUrl(url: String?, iv: ImageView) {
        if (url != null && url != "") {
            Picasso.get().load(url).error(R.drawable.ic_profile_placeholder)
                .transform(CircleTransform()).into(
                    iv
                )
        } else {
            iv.setImageResource(R.drawable.ic_profile_placeholder)
        }
    }

    fun convertPriceFromInt(paramString: Int): String {
        try {
            val localBigDecimal = BigDecimal(paramString)
            val otherSymbols = DecimalFormatSymbols(Locale.US)
            otherSymbols.decimalSeparator = ','
            otherSymbols.groupingSeparator = '.'
            val dfnd = DecimalFormat("###,###.##", otherSymbols).format(localBigDecimal.toLong())
            val currency = "Rp. "
            return currency + dfnd
        } catch (localException: Exception) {
            localException.printStackTrace()
        }

        return "Rp. 0"
    }

    fun convertPriceFromIntforPrinter(paramString: Int): String {
        try {
            val localBigDecimal = BigDecimal(paramString)
            val otherSymbols = DecimalFormatSymbols(Locale.US)
            otherSymbols.decimalSeparator = ','
            otherSymbols.groupingSeparator = '.'
            val dfnd = DecimalFormat("###,###.##", otherSymbols).format(localBigDecimal.toLong())
            val currency = "Rp. "
            return dfnd
        } catch (localException: Exception) {
            localException.printStackTrace()
        }

        return "Rp. 0"
    }

    fun setImageRounded(url: String, imageView: ImageView, context: Context) {
        Picasso.get().load(url)
            .fit()
            .centerCrop()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    val imageBitmap =
                        (imageView.getDrawable() as BitmapDrawable).bitmap
                    val imageDrawable =
                        RoundedBitmapDrawableFactory.create(
                            context.resources,
                            imageBitmap
                        )
                    imageDrawable.isCircular = true
                    imageView.setImageDrawable(imageDrawable)
                }

                override fun onError(e: Exception?) {
                    imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_profile_placeholder
                        )
                    )
                }

            })
    }

    fun createLoadingDialog(activity: Activity): AlertDialog {
        val dialog = AlertDialog.Builder(activity)
        dialog.setView(activity.layoutInflater.inflate(R.layout.loading_dialog, null))
        dialog.setCancelable(false)
        return dialog.create()
    }

    //    fun convertToLocalTime(dateSrc : String): String{
//        try{
//            val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//            val utcZone = TimeZone.getTimeZone("UTC")
//            df.timeZone = utcZone
//            val myDate = df.parse(dateSrc)
//            //to display
//            df.timeZone = TimeZone.getDefault()
//            val formattedDate = df.format(myDate)
//            return formattedDate
//        }catch (e : java.lang.Exception){
//            return ""
//        }
//    }
    fun formatDate(dateSrc: String): String {
        try {
            val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
            val utcZone = TimeZone.getTimeZone("Asia/Jakarta")
            df.timeZone = utcZone
            val myDate = df.parse(dateSrc)
            val fmtOut = SimpleDateFormat("EEEE, dd MMMM yyyy - H:mm")
            return fmtOut.format(myDate)
        } catch (e: java.lang.Exception) {
            return ""
        }
    }

    fun checkChatRoomTime(dateSrc: String): String {
        try {
            val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val utcZone = TimeZone.getTimeZone("UTC")
            df.timeZone = utcZone
            val myDate = df.parse(dateSrc)
            //to display
            df.timeZone = TimeZone.getDefault()
            val formattedDate = df.format(myDate)
            val localDate = df.parse(formattedDate)
            if (DateUtils.isToday(localDate.time)) {
                return formattedDate.substring(11, 16)
            } else if (DateUtils.isToday(localDate.time + DateUtils.DAY_IN_MILLIS)) {
                return "yesterday"
            } else {
                return formattedDate.substring(0, 11)
            }
        } catch (e: java.lang.Exception) {
            return ""
        }
    }

    fun getDisplayHeight(context: Context): Int {
        val displaymetrics = DisplayMetrics()
        val activity = context as Activity
        activity.windowManager.defaultDisplay.getMetrics(displaymetrics)
        return displaymetrics.heightPixels
    }

    fun getDisplayWidth(context: Context): Int {
        val displaymetrics = DisplayMetrics()
        val activity = context as Activity
        activity.windowManager.defaultDisplay.getMetrics(displaymetrics)
        return displaymetrics.widthPixels
    }

    fun requestEnableBluetooth(activity: Activity, requestcode: Int) {
        val enableIntent = Intent(
            BluetoothAdapter.ACTION_REQUEST_ENABLE
        )
        activity.startActivityForResult(enableIntent, requestcode)
    }

    @Suppress("DEPRECATION")
    fun isServiceRunning(serviceName: String, context: Context): Boolean {
        val manager = context?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return manager.getRunningServices(Integer.MAX_VALUE)
            .any { serviceName == it.service.className }
    }

    fun dateFormatter(date: Date?, type: Int, dateString: String?): String {
//        type 1 = api
//        type 2 = view
//        type 3 = date hour
        //passing salah satu saja : date atau datestring
        try {
            val date1: Date
            if (dateString != null) {
                val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                date1 = df.parse(dateString)
            } else {
                date1 = date!!
            }
            when (type) {
                1 -> {
                    var dateFormat = SimpleDateFormat("yyyy-MM-dd")
                    return dateFormat.format(date1)
                }
                2 -> {
                    var dateFormat = SimpleDateFormat("dd-MM-yyyy")
                    return dateFormat.format(date1)
                }
                3 -> {
                    var dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
                    return dateFormat.format(date1)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    fun getAbsoluteFile(relativePath: String, context: Context): File {
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            File(context.getExternalFilesDir(null), relativePath)
        } else {
            File(context.filesDir, relativePath)
        }
    }

    fun convertPriceToData(value: String): String {
        return value.replace(".", "")
    }

    fun saveOutletImage(url: String, context: Context) {

        var loadtarget: Target? = null
        if (loadtarget == null) {
            loadtarget = object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    if (bitmap != null) {
                        try {
                            val fileLocation = getAbsoluteFile("outletImg.png", context)
                            FileOutputStream(fileLocation).use { out ->
                                bitmap.compress(
                                    Bitmap.CompressFormat.PNG,
                                    100,
                                    out
                                ) // bmp is your Bitmap instance
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onBitmapFailed(
                    e: java.lang.Exception?,
                    errorDrawable: Drawable?
                ) {
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                }

            }

        }
        Picasso.get().load(url).into(loadtarget)
    }

}