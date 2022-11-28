package com.example.iuplayer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.iuplayer.databinding.ActivityMainBinding
import com.example.iuplayer.databinding.UsertabButtonBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    //toggle
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding
    lateinit var playlistFrag: PlaylistFrag


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //toolbar instance actionbar
        setSupportActionBar(binding.toolbar)
        //ActionBarDrawerToggle -> button
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.drawer_open, R.string.drawer_close)
        //up button -> enable
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //toggle sync
        toggle.syncState()

        //create adapter
        playlistFrag = PlaylistFrag()
        val pageAdapter = PageAdapter(this)
        val titleList = mutableListOf<String>("home", "playlist", "youtube", "concert")

        pageAdapter.addFragment(HomeFrag(), titleList[0])
        pageAdapter.addFragment(playlistFrag, titleList[1])
        pageAdapter.addFragment(YoutubeFrag(), titleList[2])
        pageAdapter.addFragment(ConcertFrag(), titleList[3])

        binding.viewPager2.adapter = pageAdapter

        //interlock tablayout, viewpager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager2){ tab, position ->
//            tab.text = titleList[position]
            tab.setCustomView(tabCreateView(titleList[position]))
        }.attach()

//        binding.navigationView.menu.findItem(R.id.navi_homepage).getIcon().setColorFilter(Color.parseColor("#D18063"), PorterDuff.Mode.SRC_ATOP)

        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navi_homepage -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("http://www.edam-ent.com/html/")
                    ContextCompat.startActivity(binding.root.context, intent, null)
                }
                R.id.navi_instargram -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.instagram.com/dlwlrma/")
                    ContextCompat.startActivity(binding.root.context, intent, null)
                }
                R.id.navi_fancafe -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://m.cafe.daum.net/IU/_rec")
                    ContextCompat.startActivity(binding.root.context, intent, null)
                }
                R.id.navi_youtube -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("https://www.youtube.com/c/dlwlrma")
                    ContextCompat.startActivity(binding.root.context, intent, null)
                }
                R.id.navi_account -> {
                    val intent = Intent(this, AccountActivity::class.java)
                    intent.action = Intent.ACTION_VIEW
                    startActivity(intent)
                }
                /** failed version
                R.id.navi_name -> {
                    val signDialog = SignDialog(binding.root.context, binding)
                    val naviHeaderBinding = NaviHeaderBinding.inflate(layoutInflater)
                    signDialog.showDialog()
                    naviHeaderBinding.nhEdtName.text = intent.getStringExtra("nh_name")
                    naviHeaderBinding.nhEdtBirth.text = intent.getStringExtra("nh_birth")
                    naviHeaderBinding.nhEdtAge.text = intent.getStringExtra("nh_age")
                    naviHeaderBinding.nhEdtPhone.text = intent.getStringExtra("nh_phone")
//                    signDialog.setOnClickListener(object: SignDialog.OnDialogClickListener{
//                        override fun onClicked(dataV3: DataV3) {
//                            naviHeaderBinding.drawEdtName.text = dataV3.name
//                            naviHeaderBinding.drawEdtBirth.text = dataV3.birth
//                            naviHeaderBinding.drawEdtAge.text = dataV3.age
//                            naviHeaderBinding.drawEdtPhone.text = dataV3.phone
//                        }
//                    })
                }
                // **/
            }
            true
        }

        /** failed Version
//        val naviHeaderBinding = NaviHeaderBinding.inflate(layoutInflater)
//
//        naviHeaderBinding.drawIvPerson.setOnClickListener {
//            val signDialog = SignDialog(applicationContext, binding)
//            signDialog.showDialog()
//        }
        // **/
    }

    private fun tabCreateView(title: String): View {
        val usertabButtonBinding = UsertabButtonBinding.inflate(layoutInflater)
        usertabButtonBinding.ivTabName.text = title
        when(title){
            "home" -> usertabButtonBinding.ivTabIcon.setImageResource(R.drawable.ic_home_24)
            "playlist" -> usertabButtonBinding.ivTabIcon.setImageResource(R.drawable.ic_music_note_24)
            "youtube" -> usertabButtonBinding.ivTabIcon.setImageResource(R.drawable.ic_youtube_24)
            "concert" -> usertabButtonBinding.ivTabIcon.setImageResource(R.drawable.ic_castle_24)
        }
        return usertabButtonBinding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //toggle -> event: true
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}