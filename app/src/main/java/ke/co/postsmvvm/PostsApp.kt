package ke.co.postsmvvm

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho

class PostsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        PostsApp.appContext = applicationContext
        Stetho.initializeWithDefaults(baseContext)
    }

    companion object{
        lateinit var appContext: Context
    }
}