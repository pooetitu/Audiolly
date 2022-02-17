package com.audiolly.features.search

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.audiolly.AlbumArtistAdapter
import com.audiolly.R
import com.audiolly.api.TheAudioDBNetworkManager
import com.audiolly.models.Section
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, parent, false)
    }

    var asyncTask: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        search_field.onRightDrawableClicked { search_field.text.clear() }
        search_field.textInputAsFlow()
            .debounce(500)
            .onEach {
                asyncTask = GlobalScope.launch(Dispatchers.Default) {
                    val albums =
                        TheAudioDBNetworkManager.searchAlbumByArtistNameAsync(it.toString()).albumsRanking ?: mutableListOf()
                    val artists =
                        TheAudioDBNetworkManager.searchArtistByNameAsync(it.toString()).artists ?: mutableListOf()
                    val objectsList = mutableListOf<Any>()
                    if (artists.size > 0) {
                        objectsList.add(Section(getString(R.string.artists)))
                        objectsList.addAll(artists)
                    }
                    if (albums.size > 0) {
                        objectsList.add(Section(getString(R.string.albums)))
                        objectsList.addAll(albums)
                    }
                    withContext(Dispatchers.Main) {
                        search_list.run {
                            layoutManager = LinearLayoutManager(this@SearchFragment.context)
                            adapter = AlbumArtistAdapter(objectsList, R.id.action_tab_search_to_artistFragment, R.id.action_tab_search_to_albumFragment)
                        }
                    }
                }
            }
            .launchIn(lifecycleScope)

    }

    override fun onDestroy() {
        super.onDestroy()
        asyncTask?.cancel()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun EditText.textInputAsFlow() = callbackFlow {
    val watcher: TextWatcher = doOnTextChanged { textInput: CharSequence?, _, _, _ ->
        offer(textInput)
    }
    awaitClose { this@textInputAsFlow.removeTextChangedListener(watcher) }
}

fun EditText.onRightDrawableClicked(onClicked: (view: EditText) -> Unit) {
    this.setOnTouchListener { v, event ->
        var hasConsumed = false
        if (v is EditText) {
            if (event.x >= v.width - v.totalPaddingRight) {
                if (event.action == MotionEvent.ACTION_UP) {
                    onClicked(this)
                }
                hasConsumed = true
            }
        }
        hasConsumed
    }
}