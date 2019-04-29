package com.example.notes.modules.home.fragments.noteList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.core.base.BaseActivity
import com.example.notes.core.base.BaseFragment
import com.example.notes.core.dagger.qualifiers.ActivityLevelFactoryProvider
import com.example.notes.models.firebasedbmodel.FirebaseDBFolder
import com.example.notes.models.firebasedbmodel.FirebaseDBNote
import com.example.notes.models.uimodel.FolderModel
import com.example.notes.models.uimodel.NoteModel
import com.example.notes.modules.home.activity.dagger.HomeComponent
import com.example.notes.modules.home.activity.viewmodel.HomeViewModel
import com.example.notes.modules.home.fragments.addNote.AddNoteFragment
import com.example.notes.modules.home.fragments.addfolder.AddFolderFragment
import com.example.notes.modules.home.fragments.noteList.dagger.NoteListComponent
import com.example.notes.modules.home.fragments.noteList.dagger.NoteListModule
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.adapter_folders_item.view.*
import kotlinx.android.synthetic.main.fragment_folders.*
import javax.inject.Inject

class NoteListFragment: BaseFragment<HomeViewModel, NoteListComponent>() {
    @Inject
    @field:ActivityLevelFactoryProvider
    lateinit var factory: ViewModelProvider.Factory

    var folderId: String? = null
//    var notes: List<NoteModel>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv.layoutManager = LinearLayoutManager(activity)
//        rv.adapter = NoteAdapter(notes!!)
        bt.setOnClickListener {
            val addNoteFragment = AddNoteFragment()
            addNoteFragment.parentFirebaseId = folderId
            addNoteFragment.show(activity?.supportFragmentManager, "")
        }
    }

    override fun onStart() {
        super.onStart()

        val databaseReference = FirebaseDatabase.getInstance().getReference("123/folders/$folderId/notes/")
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = ArrayList<NoteModel>()
                for (childDataSnapshot in dataSnapshot.children) {
                    val firebaseNote = childDataSnapshot.getValue(FirebaseDBNote::class.java)
                    val uiNoteModel = NoteModel(firebaseNote!!.note)

                    list.add(uiNoteModel)
                }

                rv.adapter = NoteAdapter(list)
            }
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }


    override fun setUpDaggerComponent() {
        if (daggerComponent == null) {
            val component = BaseActivity.getInstance().daggerComponent
            if (component is HomeComponent) {
                daggerComponent = component.addSubComponent(NoteListModule(this))
                daggerComponent?.inject(this)
            }
        }
    }

    override fun setUpViewModel() {
        viewModel = ViewModelProviders.of(activity!!, factory).get(HomeViewModel::class.java)
    }

    inner class NoteAdapter(private val folders: List<NoteModel>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_folders_item, null)
            return NoteViewHolder(view)
        }

        override fun getItemCount(): Int {
            return folders.size
        }

        override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
            holder.bind(folders[position])
        }

        inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
            private val tv: TextView = view.tv
            fun bind(folder: NoteModel) {
                tv.text = folder.note
            }
        }
    }
}