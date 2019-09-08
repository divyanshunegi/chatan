**Chatan**
 
 A library module which can be integrated in any app and will help in following cases:
 
 1 - Search any video in youtube
 2 - Search any GIF on giphy
 3 - Search any Sticker via Giphy
 
 more features coming in future, and please feel free to contribute.
 
**How to use** 

```
class MainActivity : AppCompatActivity(), Chatex.ChatexListener {

    // var isChatexVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Chatex.initialize(view = chatexView, giphKey = "GIF_API_KEY",youtubeKey = "YOUTUBE_API_KEY")
        floatingActionButton.setOnClickListener {
            Chatex.toggleView(it.x, it.y)
        }
    }

    override fun onBackPressed() {
        if (Chatex.chatExBackPressHandler()) {
            super.onBackPressed()
        }
    }
}
```

Simple add a view where you want to show it in your layout as 

```
 <com.blindmatch.chatex.ChatExView
        android:id="@+id/chatexView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
```

Link this view to the **Chatex** module

```
 Chatex.initialize(view = chatexView, giphKey = "GIF_API_KEY",youtubeKey = "YOUTUBE_API_KEY")
 Chatex.addListener(this)
 // Adding GIPHY KEY and YOUTUBE KEY is optional, but it would not work without them
```

also let your activity implement the `ChatExListener` so you can get the event when user selects the media data

When you want to toggle the view on and off , just use this function

```
Chatex.toggleView(it.x, it.y)
// You can pass the X and Y cordinates of the button which is toggeling this view, so the ChatexView 
// open up with a nice circular reveal animation
```

If you want to make sure the backButton press of Android closes the ChatexView you need to add this handler in the 
onBackpress like this 

```
  override fun onBackPressed() {
        if (Chatex.chatExBackPressHandler()) {
            super.onBackPressed()
        }
    }
```
