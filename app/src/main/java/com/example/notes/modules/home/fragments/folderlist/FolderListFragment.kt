package com.example.notes.modules.home.fragments.folderlist

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
import com.example.notes.models.uimodel.FolderModel
import com.example.notes.models.uimodel.NoteModel
import com.example.notes.modules.home.activity.dagger.HomeComponent
import com.example.notes.modules.home.activity.viewmodel.HomeViewModel
import com.example.notes.modules.home.fragments.addfolder.AddFolderFragment
import com.example.notes.modules.home.fragments.folderlist.dagger.FolderListComponent
import com.example.notes.modules.home.fragments.folderlist.dagger.FolderListModule
import com.example.notes.modules.home.fragments.notelist.NoteListFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.adapter_folders_item.view.*
import kotlinx.android.synthetic.main.fragment_folders.*
import javax.inject.Inject

class FolderListFragment: BaseFragment<HomeViewModel, FolderListComponent>() {
    @Inject
    @field:ActivityLevelFactoryProvider
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_folders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv.layoutManager = LinearLayoutManager(activity)

        bt.setOnClickListener {
            AddFolderFragment()
                .show(activity?.supportFragmentManager, "")
        }
    }

    override fun onStart() {
        super.onStart()

        val databaseReference = FirebaseDatabase.getInstance().getReference("123/folders/")
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = ArrayList<FolderModel>()
                for (childDataSnapshot in dataSnapshot.children) {
                    val firebaseFolder = childDataSnapshot.getValue(FirebaseDBFolder::class.java)
                    val uiFolderModel = FolderModel(firebaseFolder!!.name)
                    uiFolderModel.firebaseId = childDataSnapshot.key!!

                    val notes = ArrayList<NoteModel>()
                    for (entry in firebaseFolder.notes) {
                        val noteModel = NoteModel(entry.value.note)
                        noteModel.firebaseId = entry.key
                        notes.add(noteModel)
                    }
                    uiFolderModel.notes = notes

                    list.add(uiFolderModel)
                }

                if (rv != null) rv.adapter = FoldersAdapter(list)
                println("hello")
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
                daggerComponent = component.addSubComponent(FolderListModule(this))
                daggerComponent?.inject(this)
            }
        }
    }

    override fun setUpViewModel() {
        viewModel = ViewModelProviders.of(activity!!, factory).get(HomeViewModel::class.java)
    }

    inner class FoldersAdapter(private val folders: List<FolderModel>): RecyclerView.Adapter<FoldersAdapter.FolderViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_folders_item, null)
            return FolderViewHolder(view)
        }

        override fun getItemCount(): Int {
            return folders.size
        }

        override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
            holder.bind(folders[position])
        }

        inner class FolderViewHolder(view: View): RecyclerView.ViewHolder(view) {
            private val tv: TextView = view.tv
            fun bind(folder: FolderModel) {
                tv.text = folder.name

                itemView.setOnClickListener {
                    val noteListFragment = NoteListFragment()
                    noteListFragment.folderId = folder.firebaseId
//                    noteListFragment.notes = folder.notes
                    activity?.supportFragmentManager?.beginTransaction()!!
                        .replace(R.id.main_container, noteListFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }
}