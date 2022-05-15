package com.example.mad155final

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.ContextMenu
import android.view.Display
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.util.Arrays.asList


public var list = ArrayList<Model>()

class MainActivity : AppCompatActivity() {

    lateinit var fab1: FloatingActionButton
    lateinit var fab2: FloatingActionButton
    var adapter: ArrayAdapter<Model>? = null
    lateinit var listPlayers: ListView
    lateinit var undoOnClickListener: View.OnClickListener
    var clickedPlayerPosition: Int = 0
    var clickedPlayerMain: Int = 0

    var charactersNumbered: CharactersNumbered = CharactersNumbered(R.id.Bowser,R.id.CaptainFalcon,R.id.DonkeyKong,R.id.DrMario,R.id.Falco,
        R.id.Fox,R.id.Ganondorf,R.id.IceClimbers,R.id.Jigglypuff,R.id.Kirby,R.id.Link,R.id.Luigi,R.id.Mario,R.id.Marth,R.id.Mewtwo,R.id.MrGameAndWatch,
        R.id.Ness,R.id.Peach,R.id.Pichu,R.id.Pikachu,R.id.Roy,R.id.Samus,R.id.Sheik,R.id.Yoshi,R.id.YoungLink,R.id.Zelda)
    var bowserDrawables: BowserDrawables = BowserDrawables(R.drawable.bowser0, R.drawable.bowser1, R.drawable.bowser2, R.drawable.bowser3)
    var captainFalconDrawables: CaptainFalconDrawables = CaptainFalconDrawables(R.drawable.captain0, R.drawable.captain1,R.drawable.captain2,R.drawable.captain3,R.drawable.captain4,R.drawable.captain5)
    var donkeyKongDrawables: DonkeyKongDrawables = DonkeyKongDrawables(R.drawable.dk0,R.drawable.dk1,R.drawable.dk2,R.drawable.dk3)
    var drMarioDrawables: DrMarioDrawables = DrMarioDrawables(R.drawable.dr0,R.drawable.dr1,R.drawable.dr2,R.drawable.dr3,R.drawable.dr4)
    var falcoDrawables: FalcoDrawables = FalcoDrawables(R.drawable.falco0,R.drawable.falco1,R.drawable.falco2,R.drawable.falco3)
    var foxDrawables: FoxDrawables = FoxDrawables(R.drawable.fox0, R.drawable.fox1,R.drawable.fox2,R.drawable.fox3)
    var ganondorfDrawables: GanondorfDrawables = GanondorfDrawables(R.drawable.ganon0,R.drawable.ganon1,R.drawable.ganon2,R.drawable.ganon3,R.drawable.ganon4)
    var iceClimbersDrawables: IceClimbersDrawables = IceClimbersDrawables(R.drawable.ice0,R.drawable.ice1,R.drawable.ice2,R.drawable.ice3)
    var jigglypuffDrawables: JigglypuffDrawables = JigglypuffDrawables(R.drawable.jiggly0,R.drawable.jiggly1,R.drawable.jiggly2,R.drawable.jiggly3,R.drawable.jiggly4)
    var kirbyDrawables: KirbyDrawables = KirbyDrawables(R.drawable.kirby0,R.drawable.kirby1,R.drawable.kirby2,R.drawable.kirby3,R.drawable.kirby4,R.drawable.kirby5)
    var linkDrawables: LinkDrawables = LinkDrawables(R.drawable.link0,R.drawable.link1,R.drawable.link2,R.drawable.link3,R.drawable.link4)
    var luigiDrawables: LuigiDrawables = LuigiDrawables(R.drawable.luigi0,R.drawable.luigi1,R.drawable.luigi2,R.drawable.luigi3)
    var marioDrawables: MarioDrawables = MarioDrawables(R.drawable.mario0,R.drawable.mario1,R.drawable.mario2,R.drawable.mario3,R.drawable.mario4)
    var marthDrawables: MarthDrawables = MarthDrawables(R.drawable.marth0,R.drawable.marth1,R.drawable.marth2,R.drawable.marth3)
    var mewtwoDrawables: MewtwoDrawables = MewtwoDrawables(R.drawable.mewtwo0,R.drawable.mewtwo1,R.drawable.mewtwo2,R.drawable.mewtwo3)
    var mrGameAndWatchDrawables: MrGameAndWatchDrawables = MrGameAndWatchDrawables(R.drawable.gnw0,R.drawable.gnw1,R.drawable.gnw2,R.drawable.gnw3)
    var nessDrawables: NessDrawables = NessDrawables(R.drawable.ness0,R.drawable.ness1,R.drawable.ness2,R.drawable.ness3)
    var peachDrawables: PeachDrawables = PeachDrawables(R.drawable.peach0,R.drawable.peach1,R.drawable.peach2,R.drawable.peach3,R.drawable.peach4)
    var pichuDrawables: PichuDrawables = PichuDrawables(R.drawable.pichu0,R.drawable.pichu1,R.drawable.pichu2,R.drawable.pichu3)
    var pikachuDrawables: PikachuDrawables = PikachuDrawables(R.drawable.pika0,R.drawable.pika1,R.drawable.pika2,R.drawable.pika3)
    var royDrawables: RoyDrawables = RoyDrawables(R.drawable.roy0,R.drawable.roy1,R.drawable.roy2,R.drawable.roy3,R.drawable.roy4)
    var samusDrawables: SamusDrawables = SamusDrawables(R.drawable.samus0,R.drawable.samus1,R.drawable.samus2,R.drawable.samus3,R.drawable.samus4)
    var sheikDrawables: SheikDrawables = SheikDrawables(R.drawable.sheik0,R.drawable.sheik1,R.drawable.sheik2,R.drawable.sheik3,R.drawable.sheik4)
    var yoshiDrawables: YoshiDrawables = YoshiDrawables(R.drawable.yoshi0,R.drawable.yoshi1,R.drawable.yoshi2,R.drawable.yoshi3,R.drawable.yoshi4,R.drawable.yoshi5)
    var youngLinkDrawables: YoungLinkDrawables = YoungLinkDrawables(R.drawable.young0,R.drawable.young1,R.drawable.young2,R.drawable.young3,R.drawable.young4)
    var zeldaDrawables: ZeldaDrawables = ZeldaDrawables(R.drawable.zelda0,R.drawable.zelda1,R.drawable.zelda2,R.drawable.zelda3,R.drawable.zelda4)

    //creating variables that handle animations loading
    // and initializing them with animation files
    private val rotateOpen : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim)}
    private val rotateClose : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim)}
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim)}
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim)}
    //used to check if FAB menu is opened or closed
    private var closed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listPlayers = findViewById<ListView>(R.id.listViewPlayers)
        adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            list
        )
        listPlayers.adapter = MyCustomAdapter(this,R.layout.player_layout, list)
        list.add(Model("energy", "Matt Doren", R.drawable.falco1))
        registerForContextMenu(listPlayers)

//        listPlayers.adapter = MyCustomAdapter(this,R.layout.player_layout, list)
//        listPlayers.adapter = adapter
        fab1 = findViewById(R.id.FAB1)
        fab2 = findViewById(R.id.FABAdd)

        fab1.setOnClickListener{
            setAnimation(closed)
            setVisibility(closed)
            closed = !closed
        }
        fab2.setOnClickListener{
            addListPlayer()
//            listPlayers.adapter = MyCustomAdapter(this,R.layout.player_layout, list)
//            adapter?.notifyDataSetChanged()
            (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
            Snackbar.make(it, "Added a player", Snackbar.LENGTH_LONG)
                .setAction("UNDO", undoOnClickListener).show()
        }
        undoOnClickListener = View.OnClickListener{
            list.removeAt(list.size - 1)
            (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
            Snackbar.make(it, "Removed a player", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        listPlayers.setOnItemClickListener{parent: AdapterView<*>, view:View, position:Int, id:Long ->
            // first instance of clickedPlayerPosition (list[Int])
            clickedPlayerPosition = position
            openContextMenu(view)
//                Toast.makeText(this, "list[position]", Toast.LENGTH_SHORT).show()
            }

//        listPlayers.onItemLongClickListener =
//            OnItemLongClickListener { arg0, arg1, pos, id ->
//                Toast.makeText(applicationContext, "long clicked", Toast.LENGTH_SHORT).show()
//                openContextMenu(arg1)
//                true
//            }

        //end of onCreate
    }

    // function to set animation effect for FABs
    private fun setAnimation(closed:Boolean){
        if (!closed) {
            fab2.startAnimation(fromBottom)
            fab1.startAnimation(rotateOpen)
        } else {
            fab2.startAnimation(toBottom)
            fab1.startAnimation(rotateClose)
        }
    }
    // function to set visibility for FABs
    private fun setVisibility(closed:Boolean){
        if (!closed){
            fab2.visibility = View.VISIBLE
        }else{
            fab2.visibility = View.INVISIBLE
        }
    }
    // add player to listView
    private fun addListPlayer() {
        list.add(Model("Tag", "Name", R.drawable.bowser0))
//        adapter?.notifyDataSetChanged()
    }

    //mainString = convertIntToMain
    //playerInList = clickedPlayerPosition
//    fun changeMain(charsNumbered: CharactersNumbered): Int {
//    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.player_menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        info.position = clickedPlayerPosition
//        val mainOfSelected: Int = changeMain(charactersNumbered, bowserDrawables)
//        Toast.makeText(this, "changeMain gave us $mainOfSelected", Toast.LENGTH_LONG).show()
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    fun changeMain(): Boolean{
//        var thePlayer: Int = list[clickedPlayerPosition].toString().toInt()
        when (clickedPlayerMain){
            0 -> {
                val bowserPic = bowserDrawables.bowser0
                list.set(clickedPlayerPosition,Model("$this","$this",bowserPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            1 -> {
                val falconPic = captainFalconDrawables.captain0
                list.set(clickedPlayerPosition,Model("${this}","${this}",falconPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            2 -> {
                val donkeyKongPic = donkeyKongDrawables.dk0
                list.set(clickedPlayerPosition,Model("${this}","${this}", donkeyKongPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            3 -> {
                val drMarioPic = drMarioDrawables.dr0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",drMarioPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            4 -> {
                val falcoPic = falcoDrawables.falco0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",falcoPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            5 -> {
                val foxPic = foxDrawables.fox0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",foxPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            6 -> {
                val GanondorfPic = ganondorfDrawables.ganon0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",GanondorfPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            7 -> {
                val iceClimbersPic = iceClimbersDrawables.ice0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",iceClimbersPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            8 -> {
                val jigglypuffPic = jigglypuffDrawables.jiggly0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",jigglypuffPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            9 -> {
                val kirbyPic = kirbyDrawables.kirby0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",kirbyPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            10 -> {
                val linkPic = linkDrawables.link0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",linkPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            11 -> {
                val luigiPic = luigiDrawables.luigi0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",luigiPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            12 -> {
                val marioPic = marioDrawables.mario0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",marioPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            13 -> {
                val marthPic = marthDrawables.marth0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",marthPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            14 -> {
                val mewtwoPic = mewtwoDrawables.mewtwo0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",mewtwoPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            15 -> {
                val mrGameAndWatchPic = mrGameAndWatchDrawables.gnw0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",mrGameAndWatchPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            16 -> {
                val nessPic = nessDrawables.ness0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",nessPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            17 -> {
                val peachPic = peachDrawables.peach0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",peachPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            18 -> {
                val pichuPic = pichuDrawables.pichu0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",pichuPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            19 -> {
                val pikachuPic = pikachuDrawables.pika0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",pikachuPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            20 -> {
                val royPic = royDrawables.roy0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",royPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            21 -> {
                val samusPic = samusDrawables.samus0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",samusPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            22 -> {
                val sheikPic = sheikDrawables.sheik0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",sheikPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            23 -> {
                val yoshiPic = yoshiDrawables.yoshi0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",yoshiPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            24 -> {
                val youngLinkPic = youngLinkDrawables.young0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",youngLinkPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            25 -> {
                val zeldaPic = zeldaDrawables.zelda0
                list.set(clickedPlayerPosition,Model("${this}", "${this}",zeldaPic))
                (listPlayers.adapter as MyCustomAdapter).notifyDataSetChanged()
                return true
            }
            return true ->
                return true
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_main -> {
                return true
            }
            R.id.change_tag -> {
                Toast.makeText(this, "Change your lame tag", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.change_name -> {
                Toast.makeText(this, "Change your actual fucking name", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Bowser -> {
                clickedPlayerMain = 0
                changeMain()
                Toast.makeText(this,"You play the worst character", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.CaptainFalcon -> {
                clickedPlayerMain = 1
                changeMain()
                Toast.makeText(this, "Captain Falcon is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.DonkeyKong -> {
                clickedPlayerMain = 2
                changeMain()
                Toast.makeText(this,"Donkey Kong is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.DrMario -> {
                clickedPlayerMain = 3
                changeMain()
                Toast.makeText(this,"Dr. Mario is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Falco -> {
                clickedPlayerMain = 4
                changeMain()
                Toast.makeText(this,"Falco is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Fox -> {
                clickedPlayerMain = 5
                changeMain()
                Toast.makeText(this, "Fox is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Ganondorf -> {
                clickedPlayerMain = 6
                changeMain()
                Toast.makeText(this, "Ganondorf is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.IceClimbers ->{
                clickedPlayerMain = 7
                changeMain()
                Toast.makeText(this, "Ice Climbers is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Jigglypuff ->{
                clickedPlayerMain = 8
                changeMain()
                Toast.makeText(this, "Jigglypuff is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Kirby ->{
                clickedPlayerMain = 9
                changeMain()
                Toast.makeText(this, "Kirby is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Link ->{
                clickedPlayerMain = 10
                changeMain()
                Toast.makeText(this, "Link is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Luigi ->{
                clickedPlayerMain = 11
                changeMain()
                Toast.makeText(this, "Luigi is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Mario ->{
                clickedPlayerMain = 12
                changeMain()
                Toast.makeText(this, "Mario is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Marth ->{
                clickedPlayerMain = 13
                changeMain()
                Toast.makeText(this, "Marth is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Mewtwo ->{
                clickedPlayerMain = 14
                changeMain()
                Toast.makeText(this, "Mewtwo is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.MrGameAndWatch ->{
                clickedPlayerMain = 15
                changeMain()
                Toast.makeText(this, "Mr. Game & Watch is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Ness ->{
                clickedPlayerMain = 16
                changeMain()
                Toast.makeText(this, "Ness is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Peach ->{
                clickedPlayerMain = 17
                changeMain()
                Toast.makeText(this, "Peach is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Pichu ->{
                clickedPlayerMain = 18
                changeMain()
                Toast.makeText(this, "Pichu is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Pikachu ->{
                clickedPlayerMain = 19
                changeMain()
                Toast.makeText(this, "Pikachu is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Roy ->{
                clickedPlayerMain = 20
                changeMain()
                Toast.makeText(this, "Roy is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Samus ->{
                clickedPlayerMain = 21
                changeMain()
                Toast.makeText(this, "Samus is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Sheik ->{
                clickedPlayerMain = 22
                changeMain()
                Toast.makeText(this, "Sheik is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Yoshi ->{
                clickedPlayerMain = 23
                changeMain()
                Toast.makeText(this, "Yoshi is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.YoungLink ->{
                clickedPlayerMain = 24
                changeMain()
                Toast.makeText(this, "Young Link is your main", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.Zelda ->{
                clickedPlayerMain = 25
                changeMain()
                Toast.makeText(this, "Zelda is your main", Toast.LENGTH_LONG).show()
                return true
            }
            else -> return super.onContextItemSelected(item)
        }

    }

}


