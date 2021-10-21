package se.warting.examplematerialyou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.samples.ButtonSample
import androidx.compose.material3.samples.ButtonWithIconSample
import androidx.compose.material3.samples.ElevatedButtonSample
import androidx.compose.material3.samples.ExtendedFloatingActionButtonSample
import androidx.compose.material3.samples.FilledTonalButtonSample
import androidx.compose.material3.samples.FloatingActionButtonSample
import androidx.compose.material3.samples.IconButtonSample
import androidx.compose.material3.samples.IconToggleButtonSample
import androidx.compose.material3.samples.LargeFloatingActionButtonSample
import androidx.compose.material3.samples.OutlinedButtonSample
import androidx.compose.material3.samples.SmallFloatingActionButtonSample
import androidx.compose.material3.samples.TextButtonSample
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import se.warting.examplematerialyou.ui.theme.ExampleMaterialYouTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = dynamicLightColorScheme(this)
            ) {
                Column {
                    MyNavigationDrawerSample()
                    //ColorSchemeSample()
                    //SimpleSmallTopAppBar()
                    //SimpleCenterAlignedTopAppBar()
                    //EnterAlwaysSmallTopAppBar()
                    //ExitUntilCollapsedMediumTopAppBar()
                    //ExitUntilCollapsedLargeTopAppBar()
                    //AlertDialogSample()
                    //AlertDialogWithIconSample()
                    //NavigationBarItemWithBadge()
                    //NavigationDrawerSample()
                    //ExtendedFloatingActionButtonSample()
                    //FloatingActionButtonSample()
                    //LargeFloatingActionButtonSample()
                    //SmallFloatingActionButtonSample()
                    //NavigationBarSample()
                    //NavigationBarWithOnlySelectedLabelsSample()
                    //NavigationRailBottomAlignSample()
                    //NavigationRailSample()
                    //NavigationRailWithOnlySelectedLabelsSample()
                    //AppIcons()
                    //DrawIcon()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNavigationDrawerSample() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    NavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                onClick = { scope.launch { drawerState.close() } },
                content = { Text("Close Drawer") }
            )
        },
        content = {

            MySmallTopAppBar(drawerState) {
                scope.launch { drawerState.open() }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySmallTopAppBar(drawerState: DrawerState, openDrawer: () -> Unit) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            MyNavigationBarItemWithBadge()
        },
        topBar = {
            SmallTopAppBar(
                title = { Text("Small TopAppBar") },
                navigationIcon = {
                    IconButton(onClick = { openDrawer() }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    // RowScope here, so these icons will be placed horizontally
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {


                item {

                    Text(text = if (drawerState.isClosed) ">>> Swipe to open or close drawer >>>" else "<<< Swipe <<<")
                    Spacer(Modifier.height(20.dp))
                    Button(onClick = { openDrawer() }) {
                        Text("Click to open drawer")
                    }
                }
                item {
                    ButtonSample()
                    ButtonWithIconSample()
                    ElevatedButtonSample()
                    FilledTonalButtonSample()
                    OutlinedButtonSample()
                    TextButtonSample()
                }
                item {
                    ExtendedFloatingActionButtonSample()
                    FloatingActionButtonSample()
                    LargeFloatingActionButtonSample()
                    SmallFloatingActionButtonSample()
                }

                item {
                    IconButtonSample()
                    IconToggleButtonSample()
                }
                val list = (0..15).map { it.toString() }
                items(count = list.size) {
                    Text(
                        text = list[it],
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    )
}

@Composable
fun MyNavigationBarItemWithBadge() {
    NavigationBar {
        NavigationBarItem(
            icon = {
                BadgedBox(badge = { Badge { Text("8") } }) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Favorite"
                    )
                }
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                BadgedBox(badge = { Badge { Text("8") } }) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Favorite"
                    )
                }
            },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                BadgedBox(badge = { }) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Favorite"
                    )
                }
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun Greeting(name: String) {
    Column() {


        Button(onClick = { /*TODO*/ }) {
            Text(text = "Knappi!")
        }
        Text(text = "Hello $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExampleMaterialYouTheme {
        Greeting("Android")
    }
}