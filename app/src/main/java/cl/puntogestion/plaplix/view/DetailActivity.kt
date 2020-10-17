package cl.puntogestion.plaplix.view

import android.content.Intent
import android.net.MailTo
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cl.puntogestion.plaplix.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_scrolling.*
import javax.security.auth.Subject

class DetailActivity : AppCompatActivity() {

    private val viewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            enviarEmail()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.selected.observe(this, Observer {
            textViewDetalle.text = it.name
        })
    }

    fun enviarEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:arebolledo@puntogestion.cl") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, "addresses")
            putExtra(Intent.EXTRA_SUBJECT, "subject")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}